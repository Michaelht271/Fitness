package view;

import java.util.Scanner;
import controller.CoachController;
import controller.CourseController;

public class MainInterface extends Menu<String> {
    private LoginView loginView;
    private SignupView signupView;

    private final static String MainTitle = "\"=====================================\"\n"
                                            + "\"      FITNESS COURSE MANAGEMENT      \" \n"
                                            + "\"=====================================\"";
    private final static String[] MainMenuChoices = { 
        "Login",
        "Sign Up",
        "View Coach List",
        "View Course List",
       
    };

    public MainInterface() {
        super(MainTitle, MainMenuChoices);
        this.loginView = new LoginView();
        this.signupView = new SignupView();
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1:
                loginView.run();
                break;
            case 2:
                signupView.run();
                break;
            case 3:
                CoachController.viewCoaches();
                break;
            case 4:
                CourseController.viewCourse();
            
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }

    public void showMainMenu() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            display();
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());
            execute(choice);
        }
    }
}
