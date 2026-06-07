Please do not delete any of the automatically generated folders .vscode, bin, lib, src, and the README.md
---

## 📋 Smart Library System:

1. Catalogue Architect: Build a BST to store book titles and authors indexed by ISBN.
2.  Borrowing History: Implement a Stack to keep track of books checked out (Most recent on top).
3. Record Finder: Implement a recursive Search function within the BST to find books by ISBN.
4. ADT Designer: Create the Interface for the Library System to ensure "Information Hiding."
5. Admin Logic: Handle the borrowing/returning process (removing from the catalogue 
and pushing to the stack). 

Requirement: The Console Interface 
The interface must provide a menu-driven experience including: 
1. Add Book: Input ISBN, Title, and Author. 
2. Search Book: Find details by ISBN ($O(\log n)$ efficiency). 
3. Borrow Book: Move record to history stack. 
4. View History: Display all borrowed books (LIFO order). 
5. Exit: Terminate the program. 
