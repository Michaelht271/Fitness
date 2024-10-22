package view;

import utils.Utils;

public class Login {
	static Utils utils = new Utils();
	 private static  void signInGuest() {
	        String[] guestOptions = {
	            "Display all courses",
	            "View Coach",
	            "Sign up Trainee's Account",
	            "Exit to main menu"
	        };

	        Menu guestMenu = new Menu("----------Sign In Guest--------------", guestOptions) {
	            @Override
	            public void execute(int choice) {
	                switch (choice) {
	                    case 1:
	                        System.out.println("Displaying all available courses...");
	                        break;
	                    case 2:
	                        System.out.println("Viewing coaches...");
	                        break;
	                    case 3:
	                        signUpTrainee();
	                        break;
	                    case 4:
	                        main(showMain);
	                        break;
	                    default:
	                        System.out.println("Invalid choice, please try again.");
	                }
	            }
	        };
	        guestMenu.run();
	    }

	    private static  void signInTrainee() {
	        boolean isPasswordCorrect = false;

	        while (!isPasswordCorrect) {
	            String rememberPassword = utils.getAnswer("Do you remember your password? (Y/N): ");

	            if (rememberPassword.equals("Y")) {
	                String traineeId = utils.checkIdITrainee("Enter Trainee ID: ");
	                String password = utils.checkPassword("Enter password (at least 6 characters and 1 special character): ");

	                if (validatePassword(traineeId, password)) {
	                    System.out.println("Sign in successful!");
	                    isPasswordCorrect = true;
	                    signInAccountTrainee();
	                } else {
	                    System.out.println("Incorrect password. Please try again.");
	                }
	            } else if (rememberPassword.equals("N")) {
	                recoverPassword();
	                break;
	            }
	        }
	    }

	    private static boolean validatePassword(String traineeId, String password) {
			// TODO Auto-generated method stub
			return false;
		}

		private void signInCoach() {
	        boolean isPasswordCorrect = false;

	        while (!isPasswordCorrect) {
	            String rememberPassword = utils.getAnswer("Do you remember your password? (Y/N): ");

	            if (rememberPassword.equals("Y")) {
	                String coachId = utils.checkIdCoach("Enter Coach ID: ");
	                String password = utils.checkPassword("Enter password (at least 6 characters and 1 special character): ");

	                if (validatePassword(coachId, password)) {
	                    System.out.println("Sign in successful!");
	                    isPasswordCorrect = true;
	                    signInAccountCoach();
	                } else {
	                    System.out.println("Incorrect password. Please try again.");
	                }
	            } else if (rememberPassword.equals("N")) {
	                recoverPasswordForCoach();
	                break;
	            }
	        }
	    }
}
