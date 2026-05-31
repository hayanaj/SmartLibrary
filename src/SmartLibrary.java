public class SmartLibrary implements LibraryADT {
    // Private core storage references to preserve internal structures
    private BookBST catalogue;
    private BorrowStack history;

    /**
     * Admin-only access logic to insert a book into the catalog tree
     */
    @Override
    public void addBook(int isbn, String title, String author) {
        // TODO: Call catalogue.insert() to add the book into the BST
    }

    /**
     * Searches the catalog tree and outputs the results or a "Not Found" error message
     */
    @Override
    public void searchBook(int isbn) {
        // TODO: Call catalogue.search(), print details if found, otherwise show an empty/not found message
    }

    /**
     * Checks borrowing rules and moves a record from the catalog to the history stack
     */
    @Override
    public void borrowBook(int isbn) {
        // TODO: Check borrow limit, find book in BST, push to stack if valid, handle catalog status
    }

    /**
     * Invokes the history stack rendering method
     */
    @Override
    public void viewLatestHistory() {
        // TODO: Delegate task directly to history.show()
    }

    /**
     * Invokes the BST's In-Order traversal method to show available books to students.
     */
    @Override
    public void displayCatalog() {
        // TODO: Delegate task directly to catalogue.displayInOrder()
    }

    /**
     * Evaluates if the student has reached their maximum allowed borrowed books before approving a new checkout.
     */
    private boolean checkBorrowLimit() {
        // TODO: Evaluate current stack size against safety parameters (e.g., max 3 books)
        return false;
    }
}