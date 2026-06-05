import java.util.Stack;

public class BorrowStack {
    private Stack<Book> stack;

    // Initialize object
    public BorrowStack(){
        stack = new Stack<>(); 
    }
    
    /**
     * Pushes a newly borrowed book onto the top of the stack
     */
    public void push(Book book) {
        
        // Null check & push book
        if(book != null){
            stack.push(book);
            }
        }

    /**
     * Iterates backwards through the stack to display books from most recently borrowed to oldest (LIFO order)
     */
    public void show() {
        
        // Empty check
        if(stack.isEmpty()){
            System.out.println("No borrowing history available. ");
            return;
            }
        System.out.println("===== Borrow History (Newest → Oldest) =====");
        
        // Traverse
        for(int i = stack.size()-1; i >= 0; i--){
            Book b = stack.get(i);
            System.out.printf("ISBN: %d | Title: %s | Author: %s%n", b.isbn, b.title, b.author);
            }
        }
}
