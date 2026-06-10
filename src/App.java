import java.util.List;

public class App {
    public static void main(String[] args) {
        SmartLibrary library = new SmartLibrary();

        loadInitialBooks(library, "books.txt");

        MenuSystem menuSystem = new MenuSystem(library, new DatabaseManager());
        menuSystem.runMainMenu();
    }

    private static void loadInitialBooks(SmartLibrary library, String filePath) {
        try {
            List<Book> books = new FileHandler().loadBooksFromTxt(filePath);
            
            for (Book book : books) {
                library.addBook(book.getIsbn(), book.getTitle(), book.getAuthor());
            }
            System.out.println("Loaded " + books.size() + " book(s) from " + filePath + ".");
        } catch (RuntimeException exception) {
            System.err.println("Could not load initial books: " + exception.getMessage());
        }
    }
}