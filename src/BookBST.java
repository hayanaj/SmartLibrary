public class BookBST {
    // The root is private to ensure Information Hiding
    private Book root;

    public BookBST() {
        this.root = null;
    }

    // Public wrapper method - this is what the Admin Menu (Task 5) will call
    public void insert(int isbn, String title, String author) {
        root = insertRecursive(root, isbn, title, author);
    }

    // Private recursive method 
    private Book insertRecursive(Book current, int isbn, String title, String author) {
        // Base case: If we find an empty spot, put the new book here
        if (current == null) {
            return new Book(isbn, title, author);
        }

        // Recursive traversal: Decide whether to go left or right
        if (isbn < current.isbn) {
            // Smaller ISBNs go down the left branch
            current.left = insertRecursive(current.left, isbn, title, author);
            
        } else if (isbn > current.isbn) {
            // Larger ISBNs go down the right branch
            current.right = insertRecursive(current.right, isbn, title, author);
            
        } else {
            // Handling duplicate ISBNs instead of failing silently
            System.out.println("Warning: A book with ISBN " + isbn + " already exists in the catalogue. Insertion skipped.");
        }
        return current;
    }
    
    // The "Record Finder"  (Task 3) will add the 
    // public search() method into this class here
}
