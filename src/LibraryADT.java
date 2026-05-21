interface LibraryADT {
    /**
     * Public blueprint to add a book to the catalog
     */
    void addBook(int isbn, String title, String author);

    /**
     * Public blueprint to process a book borrow request
     */
    void borrowBook(int isbn);

    /**
     * Public blueprint to render the student's checkout history
     */
    void viewLatestHistory();

    /**
     * Public blueprint to search for a book by its ISBN
     */
    void searchBook(int isbn);

    /**
     * Public blueprint to display all books currently available in the catalog.
     */
    void displayCatalog();
}