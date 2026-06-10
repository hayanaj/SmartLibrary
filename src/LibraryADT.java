import java.util.*;

interface LibraryADT {
    void addBook(int isbn, String title, String author);
    void searchBook(int isbn);
    void borrowBook(int isbn);
    void viewLatestHistory();
}

class Book {
    int isbn;
    String title, author;
    Book left, right;

    public Book(int isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.left = null;
        this.right = null;
    }
}

class BookBST {
    private Book root;

    public void insert(int isbn, String t, String a) {
        root = ins(root, isbn, t, a);
    }

    private Book ins(Book r, int i, String t, String a) {
        if (r == null) return new Book(i, t, a);
        if (i < r.isbn) {
            r.left = ins(r.left, i, t, a);
        } else if (i > r.isbn) {
            r.right = ins(r.right, i, t, a);
        }
        return r;
    }

    public Book search(int i) {
        return sea(root, i);
    }

    private Book sea(Book r, int i) {
        if (r == null || r.isbn == i) return r;
        return (i < r.isbn) ? sea(r.left, i) : sea(r.right, i);
    }

    public void delete(int isbn) {
        root = deleteRec(root, isbn);
    }

    private Book deleteRec(Book r, int isbn) {
        if (r == null) return null;

        if (isbn < r.isbn) {
            r.left = deleteRec(r.left, isbn);
        } else if (isbn > r.isbn) {
            r.right = deleteRec(r.right, isbn);
        } else {
            if (r.left == null) return r.right;
            if (r.right == null) return r.left;

            Book successor = minValue(r.right);
            r.isbn = successor.isbn;
            r.title = successor.title;
            r.author = successor.author;

            r.right = deleteRec(r.right, successor.isbn);
        }
        return r;
    }

    private Book minValue(Book r) {
        Book current = r;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }
}

class BorrowStack {
    private Stack<Book> stack = new Stack<>();

    public void push(Book b) {
        stack.push(b);
    }

    public void show() {
        if (stack.isEmpty()) {
            System.out.println("History is empty.");
            return;
        }
        for (int i = stack.size() - 1; i >= 0; i--) {
            Book b = stack.get(i);
            System.out.println("[ISBN: " + b.isbn + "] " + b.title + " by " + b.author);
        }
    }
}

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
