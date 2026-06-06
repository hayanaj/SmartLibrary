import java.util.*;

class SmartLibrary implements LibraryADT {
    private BookBST catalogue = new BookBST();
    private BorrowStack history = new BorrowStack();

    @Override
    public void addBook(int isbn, String title, String author) {
        catalogue.insert(isbn, title, author);
        System.out.println("=> Success: \"" + title + "\" has been successfully added to the catalogue.");
    }

    @Override
    public void searchBook(int isbn) {
        Book b = catalogue.search(isbn);
        if (b != null) {
            System.out.println("=> Found: [ISBN: " + b.isbn + "] " + b.title + " - " + b.author);
        } else {
            System.out.println("=> Book not found in the catalogue.");
        }
    }

    @Override
    public void borrowBook(int isbn) {
        Book b = catalogue.search(isbn);
        if (b != null) {
            history.push(b);
            catalogue.delete(isbn);
            System.out.println("=> Checked Out: You successfully borrowed \"" + b.title + "\".");
        } else {
            System.out.println("=> Error: Book is unavailable or already checked out.");
        }
    }

    @Override
    public void viewLatestHistory() {
        history.show();
    }

    public void runMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n--- SmartLibrary Menu ---");
            System.out.println("1. Add Book");
            System.out.println("2. Search Book (BST)");
            System.out.println("3. Borrow Book (Stack)");
            System.out.println("4. History");
            System.out.println("5. Exit");
            
            System.out.print("Choice: ");
            String choiceInput = sc.nextLine().trim();
            
            if (choiceInput.equals("5")) {
                System.out.println("Thank you for using the SmartLibrary system!");
                break;
            }

            switch (choiceInput) {
                case "1":
                    int isbnInput = readSafeInt(sc, "Enter ISBN: ");
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine().trim();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine().trim();
                    addBook(isbnInput, title, author);
                    break;

                case "2":
                    int searchIsbn = readSafeInt(sc, "Enter ISBN to search: ");
                    searchBook(searchIsbn);
                    break;

                case "3":
                    int borrowIsbn = readSafeInt(sc, "Enter ISBN to borrow: ");
                    borrowBook(borrowIsbn);
                    break;

                case "4":
                    System.out.println("\n--- Borrowing History (Most Recent First) ---");
                    viewLatestHistory();
                    break;

                default:
                    System.out.println("❌ Invalid choice! Please select an option between 1 and 5.");
            }
        }
        sc.close();
    }

    private int readSafeInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Error: Invalid input! ISBN and choices must be integer numbers.");
            }
        }
    }
}