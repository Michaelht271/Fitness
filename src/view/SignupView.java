package view;


import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import model.Address;
import model.Coach;
import model.Trainee;
import service.AddressService;
import service.TraineeService;
import utils.Utils;

public class SignupView extends Menu<String> {
    private TraineeService traineeService;

    private static final String SIGNUP_TITLE = "===== Sign Up Screen =====";
    private static final String[] SIGNUP_CHOICES = {
        "Sign Up as Coach",
        "Sign Up as Trainee",
       
    };

    public SignupView() {
        super(SIGNUP_TITLE, SIGNUP_CHOICES);
        this.traineeService = new TraineeService();
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1:
                signUpForCoach(); // Gọi phương thức đăng ký huấn luyện viên
                break;
            case 2:
                signTrainee(); // Gọi phương thức đăng ký học viên
                break;
            default:
                System.out.println("Invalid choice. Please choose again.");
                break;
        }
    }
    public void signTrainee() {
        try {
            System.out.println("===== Sign Up as Trainee =====");

            // Nhập ID cho Trainee với định dạng TR-0000
            String traineeId = Utils.readTraineeId("Enter trainee ID (format: TR-0000): ");
            
            // Nhập mật khẩu cho Trainee
            String password = Utils.readPassword("Enter Trainee Password: ");
            
            // Nhập tên đầy đủ cho Trainee, xử lý khoảng trắng và chuyển đổi chữ cái
            String fullName = Utils.readName("Enter full name: ");
            
            // Nhập giới tính cho Trainee
            String gender = Utils.readString("Enter gender (e.g., Male/Female): ");
            
            // Nhập vai trò cho Trainee
            String role = Utils.readString("Enter role (e.g., Trainee): ");
            
            // Nhập email cho Trainee
            String email = Utils.readString("Enter email (e.g., example@example.com): ");
            
            // Nhập số điện thoại cho Trainee
            String phone = Utils.readString("Enter phone (e.g., +84XXXXXXXXX): ");
            
            // Nhập ngày sinh cho Trainee, cần theo định dạng yyyy-MM-dd
            LocalDate birthday = Utils.readDate("Enter birthday (format: yyyy-MM-dd): ");
            
            // Nhập số định danh công dân cho Trainee
            String citizenIdentification = Utils.readString("Enter citizen identification: ");
            
            // Nhập chiều cao của Trainee (cm)
            int height = Utils.readInt("Enter height (in cm, e.g., 170): ");
            
            // Nhập cân nặng của Trainee (kg)
            double weight = Utils.readDouble("Enter weight (in kg, e.g., 65.5): ");
            
            // Nhập trình độ cho Trainee
            String level = Utils.readString("Enter level (e.g., Beginner/Intermediate/Advanced): ");

            // Nhập thông tin địa chỉ cho Trainee
            System.out.println("Enter Address Information:");
            
            // Nhập thành phố cho địa chỉ
            String city = Utils.readString("Enter city: ");
            
            // Nhập đường cho địa chỉ
            String street = Utils.readString("Enter street: ");
            
            // Nhập số nhà cho địa chỉ
            int houseNumber = Utils.readInt("Enter house number (e.g., 123): ");

            // Tạo ID ngẫu nhiên cho địa chỉ
            String addressId = Utils.generateAddressId(); 
            // Tạo đối tượng Address với thông tin đã nhập
            Address address = new Address(addressId, city, street, houseNumber);

            // Tạo đối tượng Trainee với các thông tin đã nhập
            Trainee trainee = new Trainee(fullName, gender, role, email, phone, birthday,
                    citizenIdentification, height, weight, traineeId, password, level, addressId);

            // Lưu thông tin Trainee vào hệ thống
            traineeService.addTrainee(trainee);
            
            // Tạo đối tượng AddressService và lưu thông tin địa chỉ
            AddressService addressService = new AddressService();
            addressService.addAddress(address);

            System.out.println("Trainee signed up successfully!");

        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please enter the birthday in the format yyyy-MM-dd.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please enter valid numbers for height, weight, and house number.");
        } catch (Exception e) {
            System.out.println("An error occurred during sign up: " + e.getMessage());
        }
    }


    public void signUpForCoach() {
        System.out.println("===== Sign Up for Coach =====");

        String fullName = null, gender = null, role = null, email = null, phone = null, citizenIdentification = null;
        int height = 0;
        double weight = 0.0;
        LocalDate birthday = null;
        String coachId = Utils.generateCoachId(); // ID ngẫu nhiên cho Coach
        String password = null, level = null;
        String addressId = Utils.generateAddressId();
        String city = null, street = null;
        int houseNumber = 0;

        try {
            // Nhập thông tin cá nhân
            fullName = Utils.readName("Full Name (e.g., John Doe): "); // Nhập tên đầy đủ
            gender = Utils.readString("Gender (e.g., Male/Female): "); // Nhập giới tính
            role = Utils.readString("Role (e.g., Coach): "); // Nhập vai trò
            email = Utils.readString("Email (e.g., example@example.com): "); // Nhập email
            phone = Utils.readString("Phone (e.g., +84XXXXXXXXX): "); // Nhập số điện thoại
            citizenIdentification = Utils.readString("Citizen Identification: "); // Nhập số định danh công dân
            height = Utils.readInt("Height (cm, e.g., 175): "); // Nhập chiều cao
            weight = Utils.readDouble("Weight (kg, e.g., 70.5): "); // Nhập cân nặng
            birthday = Utils.readDate("Birthday (format: yyyy-mm-dd): "); // Nhập ngày sinh
            password = Utils.readPassword("Password (minimum 6 characters): "); // Nhập mật khẩu
            level = Utils.readString("Level (e.g., Beginner/Intermediate/Advanced): "); // Nhập trình độ

            // Nhập thông tin địa chỉ
            city = Utils.readString("City (e.g., Ho Chi Minh City): "); // Nhập thành phố
            street = Utils.readString("Street (e.g., Nguyen Van Cu): "); // Nhập đường
            houseNumber = Utils.readInt("House Number (e.g., 123): "); // Nhập số nhà
            
            // Tạo đối tượng địa chỉ
            Address address = new Address(addressId, city, street, houseNumber);
            
            // Tạo đối tượng huấn luyện viên
            Coach coach = new Coach(fullName, gender, role, email, phone, citizenIdentification,
                    height, weight, birthday, coachId, password, level, addressId, 0.0); // 0.0 là mức lương khởi tạo
             
            System.out.println("Coach registered successfully!");
            System.out.println("We will contact you for an interview in the next few days.");
        } catch (NumberFormatException e) {
            System.out.println("Error: Please enter valid numeric values for height, weight, and house number.");
        } catch (DateTimeParseException e) {
            System.out.println("Error: Please enter the birthday in the correct format (yyyy-mm-dd).");
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }


    
    
}
