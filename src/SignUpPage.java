import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SignUpPage {
    private StudentHelpingPortal portal;

    public SignUpPage(StudentHelpingPortal portal) {
        this.portal = portal;
    }

    public void signUp() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Sign Up");
        System.out.print("Enter RegId: ");
        String regID = scanner.nextLine();

        System.out.print("Enter  password: ");
        String password = scanner.nextLine();

        User user = new User(regID, password);
        portal.addUser(user);

        System.out.println("Sign up successful!");
    }
}