public class BookBST {
    private Book root;

    public BookBST(){
        root = null;
    }

    /**
     * Public interface to insert a new book into the tree
     */
    public void insert(int isbn, String title, String author) {
        root = ins(root, isbn, title, author);
    }

    /**
     * Recursive helper method to place the book in the correct logarithmic position
     */
    private Book ins(Book root, int isbn, String title, String author) {
        if (root == null){
            root = new Book(isbn, title, author);
            return root;
        }

        if (isbn < root.isbn){
            root.left = ins(root.left, isbn, title, author);
        
        }else if (isbn > root.isbn){
            root.right = ins(root.right, isbn, title, author);
        }
        
        return root;
    }

    /**
     * Public interface to search for a book by its ISBN
     */
    public Book search(int isbn) {
        return searching(root, isbn);
    }

    /**
     * Recursive helper method that searches the tree with O(log n) efficiency
     */
    private Book searching(Book root, int isbn) {
        if (root == null){
            return false;
        }
        if (root.isbn == isbn){
            return true;
        }
        if (root.isbn < isbn){
            return searching(root.right, isbn);
        }return searching(root.left, isbn);
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
}
