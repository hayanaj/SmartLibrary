public class Book {
    int isbn;
    String title;
    String author;
    
    // Pointers to the left and right children in the tree
    Book left;
    Book right;

    // Constructor to initialize a new book
    public Book(int isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.left = null;
        this.right = null;
    }
}
