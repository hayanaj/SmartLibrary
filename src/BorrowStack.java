import java.util.Stack;

public class BorrowStack {
    private Stack<Book> stack;

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
    
    /**
     * Looks at the book at the top of the stack without removing it.
     */
    public Book peek() {
        if (stack.isEmpty()) { 
            return null;
        }
        
        int topIndex = stack.size() - 1;
        return stack.get(topIndex);
    }

    public boolean isEmpty() {
        return stack.size() == 0;
    }
}
