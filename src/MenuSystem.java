import java.util.Scanner;

public class MenuSystem {
    private SmartLibrary library;

    /**
     * Provides the initial console application entry point (Login, Sign-Up, Exit).
     */
    public void runMainMenu() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Welcome to Universiti Malaya Library");
        boolean running = true;
        while (running){
            System.out.print("\nAre you a student? (Y/N) or type 'exit' to quit: " );
            String choice = sc.nextLine().toUpperCase();
            switch (choice) {
                case ("Y"):
                    runStudentAuth(sc);
                    break;
                case ("N"):
                    runAdminMenu(sc);
                    break;
                case("EXIT"):
                    running = false;
                    break;
                default:
                    System.out.println("INVALID INPUT");;
            }
        }
    }

    private void runStudentAuth(Scanner sc) { //similar workflow as main menu, only for student authentication
        boolean backToMain = false;
        while (backToMain == false) {
            System.out.println("Options:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            String choice = sc.nextLine().trim().toUpperCase();
            switch (choice) {
                case ("1"):
                    studentLogin(sc);
                    break;
                case ("2"):
                    studentReg(sc);
                    break;
                case("0"):
                    backToMain = true;
                    break;
                default:
                    System.out.println("INVALID INPUT.");;
            }
        }
    }
        
    /**
     * Student login logic - link to the database manager
     */
    private void studentLogin(Scanner sc){
        System.out.print("Enter your siswamail: ");
        String email = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();
        
        DatabaseManager user = new DatabaseManager();
        if (user.authenticateStudent(email, password) == true){
            System.out.println("Login success!\n");
            library.runMenu();
        }else{
            System.out.println("Access denied.\n");
        }
    }
    
    /**
     * Student new user registration logic - link to the database manager
     */
    private void studentReg(Scanner sc){
        System.out.print("Enter your siswamail: ");
        String email = sc.nextLine();
        System.out.print("Enter your password: ");
        String password = sc.nextLine();
        
        DatabaseManager user = new DatabaseManager();
        if (user.registerStudent(email, password)){
            System.out.println("Account created successfully!\n");
            library.runMenu();
        }else{
            System.out.println("Account creation failed.\n");
        }
    }

    /**
     * Displays admin-specific options (Add Book to Catalog, Search Book, View Catalog, Logout).
     */
    public void runAdminMenu(Scanner sc) {
        System.out.println("Options:");
        System.out.println("1. View Catalog");
        System.out.println("2. Search Book");
        System.out.println("3. Add Book to Catalog");
        System.out.println("0. Logout");
        System.out.print("Enter choice: ");
        int choice = getOption(sc);
        
        switch (choice) {
            case 1:
                
                break;
            case 2:
                
                break;
            case 3:
                
                break;
            case 0:
                
                break;
            default:
                break;
        }
    }

    private int getOption(Scanner sc) {
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}
