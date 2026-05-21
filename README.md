PLease do not delete any of the automatically generated folders .vscode, bin, lib, src, and the README.md
---

## 📋 Smart Library System:

Catalogue Architect: Build a BST to store book titles and authors indexed by ISBN. 
2. Borrowing History: Implement a Stack to keep track of books checked out (Most 
recent on top). 
3. Record Finder: Implement a recursive Search function within the BST to find books 
by ISBN. 
4. ADT Designer: Create the Interface for the Library System to ensure "Information 
Hiding." 
5. Admin Logic: Handle the borrowing/returning process (removing from the catalogue 
and pushing to the stack). 

Requirement: The Console Interface 
The interface must provide a menu-driven experience including: 
1. Add Book: Input ISBN, Title, and Author. 
2. Search Book: Find details by ISBN ($O(\log n)$ efficiency). 
3. Borrow Book: Move record to history stack. 
4. View History: Display all borrowed books (LIFO order). 
5. Exit: Terminate the program. 


### 1. Database & Authentication Layer

#### 📑 `DatabaseManager` Class

*Task: Manages the MySQL database connection and handles student registration and authentication.*

* [ ] **`public Connection getConnection()`**
* Establishes and returns a connection to your MySQL database.


* [ ] **`public boolean registerStudent(String email, String password)`**
* Validates if the email is a valid `siswamail` and inserts the new student credentials into the database.


* [ ] **`public boolean authenticateStudent(String email, String password)`**
* Verifies if the provided credentials exist and match records in the database.

---

### 2. Core Data Structures & Entity Layer

#### 📑 `Book` Class

*Task: Represents a single book entity inside the Binary Search Tree (BST).*

* [ ] **`public Book(int isbn, String title, String author)`**
* Constructor to initialize the book with an ISBN, title, and author.

* [ ] **Fields to include:** `isbn`, `title`, `author`, `left` (pointer), and `right` (pointer).


#### 📑 `BookBST` Class

Task: Implements a Binary Search Tree to store and search the library catalog efficiently.

* [ ] **`public void insert(int isbn, String title, String author)`**
* Public interface to insert a new book into the tree.

* [ ] **`private Book ins(Book root, int isbn, String title, String author)`**
* Recursive helper method to place the book in the correct logarithmic position.

* [ ] **`public Book search(int isbn)`**
* Public interface to search for a book by its ISBN.

* [ ] **`private Book sea(Book root, int isbn)`**
* Recursive helper method that searches the tree with $O(\log n)$ efficiency.

* [ ] **`public void displayInOrder()`**
* Performs an In-Order traversal to display the entire book catalog alphabetically or numerically by ISBN.

#### 📑 `BorrowStack` Class

Task: Implements a Stack tracking a student’s borrowed history using Last-In, First-Out (LIFO) order.

* [ ] **`public void push(Book book)`**
* Pushes a newly borrowed book onto the top of the stack.

* [ ] **`public void show()`**
* Iterates backwards through the stack to display books from most recently borrowed to oldest.

---

### 3. File Processing Layer

#### 📑 `FileHandler` Class

*Task: Reads raw book data from an external text file to populate the system.*

* [ ] **`public List<Book> loadBooksFromTxt(String filePath)`**
* Parses a `.txt` file containing book titles, authors, and ISBNs, returning them as a collectable list.
---

### 4. Application Logic Layer

#### 📑 `LibraryADT` Interface

Task: Dictates system capabilities while enforcing information hiding.

* [ ] **`void addBook(int isbn, String title, String author);`** 

* [ ] **`void borrowBook(int isbn);`** 

* [ ] **`void viewLatestHistory();`** 

* [ ] **`void searchBook(int isbn);`** 

* [ ] **`void displayCatalog();`**

#### 📑 `SmartLibrary` Class

Task: Implements `LibraryADT` to govern core application rules, tracking the catalog and borrow limits.

* [ ] **`public void addBook(int isbn, String title, String author)`**
* Admin-only access logic to insert a book into the catalog tree.

* [ ] **`public void searchBook(int isbn)`**
* Searches the catalog tree and outputs the results or an error.

* [ ] **`public void borrowBook(int isbn)`**
* Checks if the book exists, removes it from the catalog tree, and places it into the history stack.

* [ ] **`public void viewLatestHistory()`**
* Invokes the history stack rendering method.

* [ ] **`public void displayCatalog()`**
* Invokes the BST's In-Order traversal method to show available books to students.

* [ ] **`private boolean checkBorrowLimit()`**
* Evaluates if the student has reached their maximum allowed borrowed books before approving a new checkout.

---

### 5. Presentation Layer (UI)

#### 📑 `MenuSystem` Class

Task: Drives the user's console interaction using an interactive terminal layout.

* [ ] **`public void runMainMenu()`**
* Provides the initial console application entry point (Login, Sign-Up, Exit).

* [ ] **`public void runStudentMenu()`**
* Displays student-specific options (View Catalog, Search Book, Borrow Book, View History, Logout).

* [ ] **`public void runAdminMenu()`**
* Displays admin-specific options (Add Book to Catalog, Search Book, View Catalog, Logout).


* [ ] **`private int getOption(Scanner sc)`**
* A shared method to securely capture, clean, and validate user input while handling non-integer input exceptions gracefully.


#### 📑 `Main` Class

* [ ] **`public static void main(String[] args)`**
* Initializes the application by calling the system initialization logic and executing `MenuSystem`.
