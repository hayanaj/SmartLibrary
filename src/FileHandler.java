import java.io.BufferedReader;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    public List<Book> loadBooksFromTxt(String filePath) {
        if (filePath == null || filePath.trim().isEmpty()) {
            throw new IllegalArgumentException("File path cannot be empty.");
        }

        List<Book> books = new ArrayList<>();
        Path path = Path.of(filePath);

        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                String trimmed = lineNumber == 1 ? removeBom(line).trim() : line.trim();

                if (trimmed.isEmpty() || trimmed.startsWith("#")) {
                    continue;
                }

                char delimiter = findDelimiter(trimmed, lineNumber);
                List<String> fields = splitLine(trimmed, delimiter);
                if (fields.size() != 3) {
                    throw invalidLine(lineNumber,
                            "expected exactly 3 fields separated by '" + delimiter + "'");
                }

                if (looksLikeHeader(fields)) {
                    continue;
                }

                books.add(toBook(fields, lineNumber));
            }
        } catch (IOException exception) {
            throw new UncheckedIOException("Could not read book file: " + path, exception);
        }

        return books;
    }

    private Book toBook(List<String> fields, int lineNumber) {
        String first = fields.get(0).trim();
        String second = fields.get(1).trim();
        String third = fields.get(2).trim();

        int isbn;
        String title;
        String author;

        if (isInteger(first)) {
            isbn = parseIsbn(first, lineNumber);
            title = second;
            author = third;
        } else if (isInteger(third)) {
            title = first;
            author = second;
            isbn = parseIsbn(third, lineNumber);
        } else {
            throw invalidLine(lineNumber, "ISBN must be the first or last field");
        }

        if (title.isEmpty() || author.isEmpty()) {
            throw invalidLine(lineNumber, "title and author cannot be empty");
        }
        return new Book(isbn, title, author);
    }

    private int parseIsbn(String value, int lineNumber) {
        try {
            int isbn = Integer.parseInt(value);
            if (isbn <= 0) {
                throw invalidLine(lineNumber, "ISBN must be a positive integer");
            }
            return isbn;
        } catch (NumberFormatException exception) {
            throw invalidLine(lineNumber, "ISBN is outside the supported integer range");
        }
    }

    private char findDelimiter(String line, int lineNumber) {
        char[] delimiters = { '|', ',', ';', '\t' };
        for (char delimiter : delimiters) {
            if (line.indexOf(delimiter) >= 0) {
                return delimiter;
            }
        }
        throw invalidLine(lineNumber, "no supported delimiter found");
    }

    private List<String> splitLine(String line, char delimiter) {
        List<String> fields = new ArrayList<>();
        StringBuilder field = new StringBuilder();
        boolean quoted = false;

        for (int i = 0; i < line.length(); i++) {
            char current = line.charAt(i);
            if (current == '"') {
                if (quoted && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    field.append('"');
                    i++;
                } else {
                    quoted = !quoted;
                }
            } else if (current == delimiter && !quoted) {
                fields.add(field.toString().trim());
                field.setLength(0);
            } else {
                field.append(current);
            }
        }
        fields.add(field.toString().trim());
        return fields;
    }

    private boolean looksLikeHeader(List<String> fields) {
        for (String field : fields) {
            if (field.trim().equalsIgnoreCase("isbn")) {
                return true;
            }
        }
        return false;
    }

    private boolean isInteger(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException exception) {
            return false;
        }
    }

    private String removeBom(String line) {
        return line.startsWith("\uFEFF") ? line.substring(1) : line;
    }

    private IllegalArgumentException invalidLine(int lineNumber, String reason) {
        return new IllegalArgumentException("Invalid book data on line " + lineNumber + ": " + reason + ".");
    }
}
