package view;

import utils.Utils;

public class MainInterface extends Menu<String> {

	private Utils utils = new Utils();

    static String[] showMain = {
        "Sign in account",
        "Sign up account",
        "Introducing us",
        "Exit"
    };

    public MainInterface() 
		
	{
        super("------------Fitness----------------", showMain);
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1:
                showSignIn();
                break;
            case 2:
                showSignUp();
                break;
            case 3:
                System.out.println("Welcome to our Fitness program!");
                break;
            case 4:
                System.out.println("Exiting...");
                System.exit(0);
            default:
                System.out.println("Invalid choice, please try again.");
        }
    }
    
    
    private void showSignIn() {
        String[] signInOptions = {
            "Sign in by Guest's Account",
            "Sign in by Trainee's Account",
            "Sign in by Coach's Account",
            "Sign in by Admin's Account",
            "Return to main menu"
        };

        Menu signInMenu = new Menu("------------Sign In----------------", signInOptions) {
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1:
                        login.signInGuest();
                        break;
                    case 2:
                        signInTrainee();
                        break;
                    case 3:
                        signInCoach();
                        break;
                    case 4:
                        signInAdmin();
                        break;
                    case 5:
                        main(showMain);
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }
        };
        signInMenu.run();
    }
    
    
    private void showSignUp() {
        String[] signUpOptions = {
            "Sign up for Trainee account",
            "Sign up for Coach account",
            "Return to main menu"
        };

        Menu signUpMenu = new Menu("------------Sign Up----------------", signUpOptions) {
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1:
                        signUpTrainee();
                        break;
                    case 2:
                        signUpCoach();
                        break;
                    case 3:
                        main(showMain);
                        break;
                    default:
                        throw new AssertionError("Unknown option selected.");
                }
            }
        };
        signUpMenu.run();
    }

}
