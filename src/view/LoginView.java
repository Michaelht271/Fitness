package view;

import java.util.Scanner;

import controller.AuthController;
import model.Coach;
import model.Trainee;
import utils.Utils;

public class LoginView extends Menu<String> {
    
    private final static String LoginTitle = "\"=====================================\"\n"
                                              + "\"              LOGIN MENU             \" \n"
                                              + "\"=====================================\"";
    
    private final static String[] LoginMenuChoices = { 
        "Login as Trainee",
        "Login as Coach",
        "Login as Boss",
        "Exit"
    };

    private Scanner scanner;

    public LoginView() {
        super(LoginTitle, LoginMenuChoices);
        this.scanner = new Scanner(System.in);
    }

    private void loginTrainee() {
        System.out.println("===== Login Trainee =====");
        String traineeID = Utils.readTraineeId("Enter Trainee ID: ");
        String traineePassword = Utils.readPassword("Enter Trainee Password: ");
        Trainee trainee = AuthController.checkPassWordTrainee(traineeID, traineePassword);
        if (trainee != null) {
            TraineeView traineeView = new TraineeView(trainee);
            traineeView.run();
        } else {
            System.out.println("Invalid Trainee ID or password.");
        }
    }

    private void loginCoach() {
        System.out.println("===== Login Coach =====");
        String coachID = Utils.readCoachId("Enter Coach ID: ");
        String coachPassword = Utils.readPassword("Enter Coach Password: ");
        Coach coach = AuthController.checkPassWordCoach(coachID, coachPassword);
        if (coach != null) {
            CoachView coachView = new CoachView(coach);
            coachView.run();
        } else {
            System.out.println("Invalid Coach ID or password.");
        }
    }

    private void loginBoss() {
        System.out.println("===== Login Boss =====");
        String bossID = Utils.readString("Enter Boss ID: ");
        String bossPassword = Utils.readString("Enter Boss Password: ");
        boolean status = AuthController.checkBossCredentials(bossID, bossPassword);
        if (status) {
            BossView bossView = new BossView();
            bossView.run();
        } else {
            System.out.println("Invalid Boss ID or password.");
        }
    }

    @Override
    public void execute(int choice) {
        try {
            switch (choice) {
                case 1:
                    loginTrainee();
                    break;
                case 2:
                    loginCoach();
                    break;
                case 3:
                    loginBoss();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("An error occurred during login: " + e.getMessage());
            e.printStackTrace(); // Log the full exception stack trace for debugging
        }
    }

    public void showLoginScreen() {
        while (true) {
            display();
            System.out.print("Enter your choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                execute(choice);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
