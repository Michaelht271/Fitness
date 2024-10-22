package view;

import model.Address;
import model.Coach;
import model.Trainee;
import view.Menu;
import utils.Utils;

public class FitnessMain extends Menu<String> {

    private Utils utils = new Utils();

    static String[] showMain = {
        "Sign in account",
        "Sign up account",
        "Introducing us",
        "Exit"
    };

    public FitnessMain() {
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
                        signInGuest();
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

    private void signInGuest() {
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

    private void signInTrainee() {
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

    private boolean validatePassword(String userId, String password) {
        return password.equals("validPassword");
    }

    private void recoverPassword() {
        String citizenId = utils.checkCitizenId("Enter your citizen ID: ");
        System.out.println("A recovery process has been initiated for citizen ID: " + citizenId);
    }

    private void recoverPasswordForCoach() {
        String citizenId = utils.checkCitizenId("Enter your citizen ID: ");
        System.out.println("A recovery process has been initiated for citizen ID: " + citizenId);
    }

    private void signInAccountTrainee() {
        String[] traineeOptions = {
            "Show profile",
            "Enroll new course",
            "View coach",
            "Show my schedule",
            "Sign out"
        };

        Menu traineeMenu = new Menu("----------Sign In Trainee--------------", traineeOptions) {
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1:
                        System.out.println("Displaying trainee's profile...");
                        break;
                    case 2:
                        System.out.println("Enrolling in a new course...");
                        break;
                    case 3:
                        System.out.println("Viewing coaches...");
                        break;
                    case 4:
                        showSchedule();
                        break;
                    case 5:
                        showSignIn();
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }
        };
        traineeMenu.run();
    }

    private void signInAccountCoach() {
        String[] coachOptions = {
            "Show profile",
            "Create new course",
            "View course list",
            "Show schedule",
            "Sign out"
        };

        Menu coachMenu = new Menu("----------Sign In Coach--------------", coachOptions) {
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1:
                        System.out.println("Displaying coach profile...");
                        break;
                    case 2:
                        System.out.println("Creating new course...");
                        break;
                    case 3:
                        System.out.println("Viewing course list...");
                        break;
                    case 4:
                        showSchedule();
                        break;
                    case 5:
                        showSignIn();
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }
        };
        coachMenu.run();
    }

    private void signInAdmin() {
        String[] adminOptions = {
            "Manage trainees",
            "Manage coaches",
            "Manage courses",
            "Sign out"
        };

        Menu adminMenu = new Menu("----------Sign In Admin--------------", adminOptions) {
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1:
                        System.out.println("Managing trainees...");
                        break;
                    case 2:
                        System.out.println("Managing coaches...");
                        break;
                    case 3:
                        System.out.println("Managing courses...");
                        break;
                    case 4:
                        showSignIn();
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }
        };
        adminMenu.run();
    }

    private void showSchedule() {
        String[] studyOptions = {
            "Continue current course",
            "Start new course",
            "Exit to sign in menu"
        };

        Menu studyMenu = new Menu("----------Study Course--------------", studyOptions) {
            @Override
            public void execute(int choice) {
                switch (choice) {
                    case 1:
                        System.out.println("Continuing current course...");
                        break;
                    case 2:
                        System.out.println("Starting new course...");
                        break;
                    case 3:
                        signInAccountTrainee();
                        break;
                    default:
                        System.out.println("Invalid choice, please try again.");
                }
            }
        };
        studyMenu.run();
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

    private void signUpTrainee() {
        String traineeId = utils.checkIdITrainee("Enter Trainee ID: ");
        String password = utils.checkPassword("Enter password (at least 6 characters and 1 special character): ");
        String name = utils.checkName("Enter the trainee's name: ");
        String gender = utils.checkGender("Enter gender (male/female): ");
        String birthdate = utils.checkDate("Enter your birthdate: ");
        String level = utils.checkLevel("Enter level: ");
        String citizenId = utils.checkCitizenId("Enter your citizen ID: ");
        String phoneNumb = utils.checkPhoneNumber("Enter your phone number: ");
        String email = utils.checkEmail("Enter your email: ");

        System.out.println("Enter your address (city, street, house number)");
        String city = utils.checkName("Enter city: ");
        String street = utils.checkStreetName("Enter street: ");
        int houseNumber = Integer.parseInt(utils.checkHouseNumber("Enter house number (3 digits): "));
        Address address = new Address(city, street, houseNumber);

        int height = utils.checkInt("Enter your height (cm): ");
        double weight = utils.checkDouble("Enter your weight (kg): ");

        Trainee trainee = new Trainee(name, gender, "Trainee", email, phoneNumb, citizenId, height, weight, birthdate, level);
        trainee.setTraineeId(traineeId);
        trainee.setPassword(password);
        trainee.setAddress(address);

        System.out.println("Trainee information registered successfully.");
        System.out.println(trainee);
    }

    private void signUpCoach() {
        String name = utils.checkName("Enter the coach's name: ");
        String gender = utils.checkGender("Enter gender (male/female): ");
        String birthdate = utils.checkDate("Enter your birthdate: ");
        String level = utils.checkLevel("Enter level: ");
        String citizenId = utils.checkCitizenId("Enter your citizen ID: ");
        String phoneNumb = utils.checkPhoneNumber("Enter your phone number: ");
        String email = utils.checkEmail("Enter your email: ");
        System.out.println("Enter your address (city, street, house number)");
        String city = utils.checkName("Enter city: ");
        String street = utils.checkStreetName("Enter street: ");
        int houseNumber = Integer.parseInt(utils.checkHouseNumber("Enter house number (3 digits): "));
        Address address = new Address(city, street, houseNumber);

        double salary = utils.checkDouble("Enter your salary: ");

        Coach coach = new Coach(name, gender, "Coach", email, phoneNumb, citizenId, 0, 0, birthdate, level, salary);
        coach.setAddress(address);

        System.out.println("Coach information registered successfully.");
    }

    public static void main(String[] args) {
        new FitnessMain().run();
    }
}
