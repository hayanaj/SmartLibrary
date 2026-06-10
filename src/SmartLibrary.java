public class SmartLibrary implements LibraryADT {
    /////////NO BORROW NUMBER LIMIT/////////
    private final BookBST catalogue;
    private final BorrowStack history;
    public SmartLibrary() {
        catalogue = new BookBST();
        history = new BorrowStack();
    }
    /**
     * Admin-only access logic to insert a book into the catalog tree[cite: 15, 77].
     */
    @Override
    public void addBook(int isbn, String title, String author) {
        if (isbn <= 0 || title == null || title.trim().isEmpty()
                || author == null || author.trim().isEmpty()) {
            System.out.println("ISBN must be positive, and title and author are required.");
            return;
        }
        if (catalogue.search(isbn) != null) {
            System.out.println("A book with ISBN " + isbn + " already exists.");
            return;
        }
        catalogue.insert(isbn, title.trim(), author.trim());
        System.out.println("Book added: " + title.trim());
    }
    /**
     * Searches the catalog tree and outputs the results or a "Not Found" error message[cite: 78, 80, 82].
     */
    @Override
    public void searchBook(int isbn) {
        Book book = catalogue.search(isbn);
        if (book == null) {
            System.out.println("Book not found.");
        } else {
            System.out.println(book);
        }
    }
    /**
     * Checks borrowing rules and moves a record from the catalog to the history stack[cite: 11, 17, 83].
     */
    @Override
    public void borrowBook(int isbn) {
        
    //////OPTIMIZED & DELETE//////
        // no checkBorrowLimit
        // remove → delete: unify method names
        Book book = catalogue.delete(isbn);
        if (book == null) {
            System.out.println("Book not found or already borrowed.");
            return;
        }
        history.push(book);
        System.out.println("Borrowed: " + book.getTitle());
    }
    /**
     * Invokes the history stack rendering method[cite: 91, 92].
     */
    @Override
    public void viewLatestHistory() {
        history.show();
    }
    /**
     * Invokes the BST's In-Order traversal method to show available books to students.
     */
    @Override
    public void displayCatalog() {
        catalogue.displayInOrder();
    }
    
////////DELETE////////
    //no checkBorrowLimit boolean method
}
