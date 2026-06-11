import java.util.Scanner;

public class MenuSystem {
    private final SmartLibrary library;
    private final DatabaseManager databaseManager;
    private final Scanner scanner;

    public MenuSystem() {
        this(new SmartLibrary(), new DatabaseManager(), new Scanner(System.in));
    }

    public MenuSystem(SmartLibrary library, DatabaseManager databaseManager) {
        this(library, databaseManager, new Scanner(System.in));
    }

    MenuSystem(SmartLibrary library, DatabaseManager databaseManager, Scanner scanner) {
        this.library = library;
        this.databaseManager = databaseManager;
        this.scanner = scanner;
    }

    /**
     * Provides the initial console application entry point (Login, Sign-Up, Exit).
     */
    public void runMainMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Smart Library ===");
            System.out.println("1. Student Login");
            System.out.println("2. Student Sign-Up");
            System.out.println("3. Admin Login");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            switch (getOption(scanner)) {
                case 1:
                    loginStudent();
                    break;
                case 2:
                    registerStudent();
                    break;
                case 3:
                    loginAdmin();
                    break;
                case 4:
                    running = false;
                    System.out.println("Goodbye.");
                    break;
                default:
                    System.out.println("Please choose an option from 1 to 4.");
            }
        }
    }

    /**
     * Displays student-specific options (View Catalog, Search Book, Borrow Book, View History, Logout).
     */
    public void runStudentMenu() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n=== Student Menu ===");
            System.out.println("1. View Catalogue");
            System.out.println("2. Search Book");
            System.out.println("3. Borrow Book");
            System.out.println("4. View Borrowing History");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");

            switch (getOption(scanner)) {
                case 1:
                    library.displayCatalog();
                    break;
                case 2:
                    library.searchBook(readIsbn());
                    break;
                case 3:
                    library.borrowBook(readIsbn());
                    break;
                case 4:
                    library.viewLatestHistory();
                    break;
                case 5:
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Please choose an option from 1 to 5.");
            }
        }
    }

    /**
     * Displays admin-specific options (Add Book to Catalog, Search Book, View Catalog, Logout).
     */
    public void runAdminMenu() {
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Add Book to Catalogue");
            System.out.println("2. Search Book");
            System.out.println("3. View Catalogue");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");

            switch (getOption(scanner)) {
                case 1:
                    int isbn = readIsbn();
                    System.out.print("Title: ");
                    String title = scanner.nextLine().trim();
                    System.out.print("Author: ");
                    String author = scanner.nextLine().trim();
                    library.addBook(isbn, title, author);
                    break;
                case 2:
                    library.searchBook(readIsbn());
                    break;
                case 3:
                    library.displayCatalog();
                    break;
                case 4:
                    loggedIn = false;
                    break;
                default:
                    System.out.println("Please choose an option from 1 to 4.");
            }
        }
    }

    private int getOption(Scanner sc) {
        while (true) {
            String input = sc.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                System.out.print("Please enter a whole number: ");
            }
        }
    }

    private int readIsbn() {
        while (true) {
            System.out.print("ISBN: ");
            int isbn = getOption(scanner);
            if (isbn > 0) {
                return isbn;
            }
            System.out.println("ISBN must be a positive integer.");
        }
    }

    private void loginStudent() {
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (databaseManager.authenticateStudent(email, password)) {
            System.out.println("Login successful.");
            runStudentMenu();
        } else {
            System.out.println("Login failed. Check your credentials.");
        }
    }

    private void registerStudent() {
        System.out.println("\n=== Student Registration Menu ===");
        System.out.print("Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Password (at least 6 characters): ");
        String password = scanner.nextLine();

        if (databaseManager.registerStudent(email, password)) {
            System.out.println("Registration successful.");
        } else {
            System.out.println("Registration failed.");
        }
    }

    private void loginAdmin() {
        String expectedEmail = System.getenv().getOrDefault(
                "SMART_LIBRARY_ADMIN_EMAIL", "admin@library.local");
        String expectedPassword = System.getenv().getOrDefault(
                "SMART_LIBRARY_ADMIN_PASSWORD", "admin123");

        System.out.print("Admin email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Admin password: ");
        String password = scanner.nextLine();

        if (expectedEmail.equalsIgnoreCase(email) && expectedPassword.equals(password)) {
            System.out.println("Admin login successful.");
            runAdminMenu();
        } else {
            System.out.println("Invalid admin credentials.");
        }
    }
}
