import java.util.List;
import java.util.Scanner;

public class LoginPage {
    private StudentHelpingPortal portal;
    private User loggedInUser;

    public LoginPage(StudentHelpingPortal portal) {
        this.portal = portal;
        this.loggedInUser = null;
    }

    public void login() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Login");
        System.out.print("Enter your RegID: ");
        String regID = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        for (User user : portal.getUsers()) {
            if (user.getRegId().equals(regID) && user.getPassword().equals(password)) {
                System.out.println("Login successful!");
                loggedInUser = user;
                return;
            }
        }


        System.out.println("Invalid RegID or password. Please try again.");
    }

    public boolean isLoggedIn() {
        return loggedInUser != null;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}