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
    
    /**
     * Public search method to search for a book by its ISBN
     */
    public Book search(int isbn) {
        return searching(root, isbn);
    }
    /**
     * Recursive helper method that searches the tree with O(log n) efficiency
     */
    private Book searching(Book root, int isbn) {
        if (root == null){
            return null;
        }
        if (root.isbn == isbn){
            return root;
        }
        if (root.isbn < isbn){
            return searching(root.right, isbn);
        }
        return searching(root.left, isbn);
    }
    
    /**
     * Performs an In-Order traversal to display the entire book catalog
     * alphabetically or numerically by ISBN.
     */
    public void displayInOrder() {
        inOrderRecursive(root);
        System.out.println();
    }
    private void inOrderRecursive(Book node){
        if(node == null){
            return;
        }
        inOrderRecursive(node.left);
        System.out.print(node.toString());
        inOrderRecursive(node.right);
    }
    
//////////OPTIMIZED//////////
    // return deleted book, adapt borrow logic, store in history stack
    public Book delete(int isbn) {
        Book target = search(isbn);
        if (target == null) {
            return null;
        }
        root = deleteRec(root, isbn);
        return target;
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
