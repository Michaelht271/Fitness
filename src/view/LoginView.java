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
    };

    public LoginView() {
        super(LoginTitle, LoginMenuChoices);
    }

    @Override
    public void execute(int choice) {
        Scanner scanner = new Scanner(System.in);
        try {
            switch (choice) {
                case 1:
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
                    break;
                case 2:
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
                    break;
                case 3:
                    System.out.println("===== Login Boss =====");
                    String bossID = Utils.readString("Enter Boss ID: ");
                    System.out.println();
                    String bossPassword = Utils.readString("Enter Boss Password: ");
                    boolean status = AuthController.checkBossCredentials(bossID, bossPassword);
                    if (status) {
                        BossView bossView = new BossView();
                        bossView.run();
                    } else {
                        System.out.println("Invalid Boss ID or password.");
                    }
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
        }
    }

    public void showLoginScreen() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            display();
            System.out.print("Enter your choice: ");
            int choice = Integer.parseInt(scanner.nextLine());
            execute(choice);
        }
    }
}
