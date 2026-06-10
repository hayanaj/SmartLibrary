public interface LibraryADT {
    void addBook(int isbn, String title, String author);
    void searchBook(int isbn);
    void borrowBook(int isbn);
    void viewLatestHistory();
////////NEW////////
    // add new interface method
    void displayCatalog();
}
////////ONLY RETAIN ADT METHOD////////
