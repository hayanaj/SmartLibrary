## Smart Library System

1. **Catalogue Architect:** Build a BST to store book titles and authors indexed by ISBN.
2. **Borrowing History:** Implement a Stack to keep track of books checked out (Most recent on top).
3. **Record Finder:** Implement a recursive Search function within the BST to find books by ISBN.
4. **ADT Designer:** Create the Interface for the Library System to ensure "Information Hiding."
5. **Admin Logic:** Handle the borrowing/returning process (removing from the catalogue and pushing to the stack). 

### Requirement: The Console Interface 
The interface must provide a menu-driven experience including: 
1. **Add Book:** Input ISBN, Title, and Author. 
2. **Search Book:** Find details by ISBN ($O(\log n)$ efficiency). 
3. **Borrow Book:** Move record to history stack. 
4. **Return Book:** Verify and re-index a borrowed book back into the catalog.
5. **View History:** Display all borrowed books (LIFO order). 
6. **Exit:** Terminate the program.

---

### Execution & Deployment Guide

This system compiles into a platform-independent executable Java Archive (JAR) file. Users do not need an IDE (like VS Code, Eclipse, or IntelliJ) to run the system; it runs directly from any native terminal environment as long as the **Java Development Kit (JDK) or Java Runtime Environment (JRE)** is installed.

### Repository & File Prerequisites
For the executable application to load properly, the data storage stream files must sit in the same working directory as the executable engine. Ensure your folder contains:
* `SmartLibrary.jar` (The compiled engine)
* `books.txt` (The predefined inventory catalog containing the pipe-separated classic records)

### 💻 How to Run the JAR File via Terminal

1. **Download and Extract:** Download this repository as a ZIP archive and extract it anywhere on your local computer.
2. **Open your Terminal:** open your system's native command line prompt (Command Prompt or PowerShell on Windows; Terminal on macOS/Linux).
3. **Navigate to the Directory:** Use the `cd` command to enter the specific directory containing your compiled executable JAR file and text resources:
   ```bash
   cd path/to/Your-Repository-Folder/executable