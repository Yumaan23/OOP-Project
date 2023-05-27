import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentHelpingPortal portal = new StudentHelpingPortal();
        Scanner scanner = new Scanner(System.in);
        boolean isLoggedIn = false;

        while (true) {
            if (!isLoggedIn) {
                System.out.println("Student Helping Portal");
                System.out.println("1. Login");
                System.out.println("2. Sign Up");
                System.out.println("3. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter your regId: ");
                        String regId = scanner.nextLine();
                        System.out.print("Enter your password: ");
                        String password = scanner.nextLine();
                        User user = portal.login(regId, password);
                        if (user != null) {
                            System.out.println("Login successful!");
                            isLoggedIn = true;
                        } else {
                            System.out.println("Invalid credentials. Please try again.");
                        }
                        break;
                    case 2:
                        System.out.print("Enter RegId: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String signupPassword = scanner.nextLine();
                        User newUser = new User(email, signupPassword);
                        portal.addUser(newUser);
                        System.out.println("User created successfully!");
                        break;
                    case 3:
                        System.out.println("Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            } else {
                System.out.println("Student Helping Portal");
                System.out.println("1. Add File");
                System.out.println("2. Search Files");
                System.out.println("3. Display All Files");
                System.out.println("4. Delete File");
                System.out.println("5.Logout");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter the semester: ");
                        String semester = scanner.nextLine();
                        System.out.print("Enter the file type (assignment or quiz): ");
                        String fileType = scanner.nextLine();
                        System.out.print("Enter the file name: ");
                        String fileName = scanner.nextLine();
                        System.out.print("Enter your user ID: ");
                        String userId = scanner.nextLine();
                        System.out.print("Enter your name: ");
                        String uploaderName = scanner.nextLine();
                        System.out.print("Enter the text content: ");
                        String text = scanner.nextLine();
                        portal.addFile(semester, fileName, userId, uploaderName, FileType.valueOf(fileType.toUpperCase()), text);
                        break;
                    case 2:
                        System.out.print("Enter the semester: ");
                        String searchSemester = scanner.nextLine();
                        System.out.print("Enter the file type (assignment or quiz): ");
                        String searchFileTypeInput = scanner.nextLine();
                        FileType searchFileType;
                        if (searchFileTypeInput.equalsIgnoreCase("assignment")) {
                            searchFileType = FileType.ASSIGNMENT;
                        } else if (searchFileTypeInput.equalsIgnoreCase("quiz")) {
                            searchFileType = FileType.QUIZ;
                        } else {
                            System.out.println("Invalid file type. Search canceled.");
                            break;
                        }
                        portal.searchFiles(searchSemester, searchFileType);
                        break;
                    case 3:
                        portal.displayAllFiles();
                        break;
                    case 4:
                        System.out.print("Enter the file name: ");
                        String deleteFileName = scanner.nextLine();
                        portal.deleteFile(deleteFileName);
                        break;

                    case 5:
                        System.out.println("Goodbye!");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        }
    }
}