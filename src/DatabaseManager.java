import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class DatabaseManager {
    private final String FILE_PATH = "studentData.txt";

    /**
     * Validates if the email is valid and inserts the new student credentials into the file.
     */
    public boolean registerStudent(String email, String password) {
        
        if (findUser(email, password)) {
            System.out.println("This user is already registered.");
            return false;
        }

        if (!email.toLowerCase().endsWith("@siswa.um.edu.my")) {
                    System.out.println("Invalid siswamail address.");
                    return false;
                }

        String matric = email.substring(0,email.lastIndexOf("@siswa.um.edu.my"));
        
        if(matric == null || matric.isEmpty()) return false;
        else{
            for (int i = 0; i < matric.length(); i++) {
                if (!Character.isDigit(matric.charAt(i))) {
                    System.out.println("Invalid student matric.");
                    return false;
                }
            }
        }

        if (password == null || password.length() < 6) {
            System.out.println("Password must be at least 6 characters long.");
            return false;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(email + "," + password);
            writer.newLine();
            return true;
        } catch (IOException e) {
            System.out.println("Error writing data: " + e.getMessage());
            return false;
        }
    }

    /**
     * Verifies if the provided credentials exist and match records in the file.
     */
    public boolean authenticateStudent(String email, String password) {
        return findUser(email, password);
    }

    private boolean findUser(String email, String password) {
        boolean userFound = false;
        Scanner reader = null;

        try {
            reader = new Scanner(new FileInputStream(FILE_PATH));

            while (reader.hasNextLine() && !userFound) {
                String line = reader.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] credentials = line.split(",");
                if (credentials.length == 2) {
                    String storedEmail = credentials[0].trim();
                    String storedPassword = credentials[1].trim();

                    if (storedEmail.equalsIgnoreCase(email) && storedPassword.equals(password)) {
                        userFound = true;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return userFound;
    }
}