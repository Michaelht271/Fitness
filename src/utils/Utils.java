package utils;

import java.io.Console;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;
import java.util.Scanner;

public abstract class Utils {
    public static final Scanner input = new Scanner(System.in);
    // Cập nhật định dạng ngày thành yyyy-MM-dd
    public static final DateTimeFormatter Date_Format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter Time_Format = DateTimeFormatter.ofPattern("HH:mm");
    public static final String PHONE_NUMBER_REGEX = "^0\\d{9}$";
    public static final String Email_Regex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

    // Hiển thị thông báo ra màn hình
    public static void show(String message) {
        System.out.println(message);
    }

    // Phương thức kiểm tra chữ in hoa đầu mỗi từ
    public static boolean isCapitalizedName(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        String[] words = str.split("\\s+");
        for (String word : words) {
            if (!word.isEmpty() && !Character.isUpperCase(word.charAt(0))) {
                return false;
            }
        }
        return true;
    }

    // Lấy giá trị từ người dùng (kiểu String)
    public static String getValue() {
        return input.nextLine().trim();
    }

    // Lấy số thực từ người dùng
    public static double readDouble(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Double.parseDouble(getValue());
            } catch (NumberFormatException e) {
                show("[-] Invalid input. Please enter a valid number.");
            }
        }
    }

    public static int readInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(getValue());
            } catch (NumberFormatException e) {
                show("[-] Invalid input. Please enter a valid number.");
            }
        }
    }

    // Lấy số thực dương từ người dùng
    public static double readPositiveDouble(String message) {
        while (true) {
            double num = readDouble(message);
            if (num > 0) {
                return num;
            } else {
                show("[-] Please enter a positive number.");
            }
        }
    }

    // Lấy ngày từ người dùng với định dạng yyyy-MM-dd
    public static LocalDate readDate(String message) {
        while (true) {
            System.out.print(message);
            String entry = getValue();
            try {
                return LocalDate.parse(entry, Date_Format);
            } catch (DateTimeParseException e) {
                show("Date is invalid. Please enter again (yyyy-MM-dd).");
            }
        }
    }

    public static LocalTime readTime(String message) {
        while (true) {
            System.out.print(message);
            String entry = getValue();
            try {
                return LocalTime.parse(entry, Time_Format);
            } catch (DateTimeParseException e) {
                show("[-] Time is invalid. Please enter again (HH:mm).");
            }
        }
    }

    // Lấy ngày giờ từ người dùng
    public static LocalDateTime readDateTime(String message) {
        LocalDate date = readDate("Enter Date (yyyy-MM-dd): ");
        LocalTime time = readTime("Enter Time (HH:mm): ");
        return LocalDateTime.of(date, time);
    }

    // Kiểm tra số điện thoại
    public static boolean isPhoneNumber(String phone) {
        phone = trimOrNull(phone);
        return phone.matches(PHONE_NUMBER_REGEX);
    }
    public static String readPhone(String message) {
        String phone;
        boolean isValid;
        
        do {
            phone = readString(message); // Gọi phương thức để đọc chuỗi từ người dùng
            isValid = isPhoneNumber(phone); // Kiểm tra tính hợp lệ của số điện thoại
            
            if (!isValid) {
                System.out.println("[-] Invalid phone number. Please enter a valid phone number (10-11 digits).");
            }
        } while (!isValid);
        
        return phone; // Trả về số điện thoại hợp lệ
    }
    
    // Xóa khoảng trắng đầu và cuối chuỗi, nếu chuỗi rỗng trả về null
    public static String trimOrNull(String str) {
        if (str == null)
            return null;
        String trimmed = str.trim();
        return trimmed.isEmpty() ? null : trimmed;
    }

    // Kiểm tra email
    public static boolean isValidateEmail(String email) {
        return email.matches(Email_Regex);
    }

    // Chuyển in hoa đầu mỗi từ
    public static String capitalizeWords(String str) {
        if (str == null || str.trim().isEmpty()) {
            return str;
        }
        String[] words = str.split("\\s+");
        StringBuilder capitalized = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                capitalized.append(Character.toUpperCase(word.charAt(0)))
                           .append(word.substring(1).toLowerCase()).append(" ");
            }
        }
        return capitalized.toString().trim();
    }

    // Kiểm tra chuỗi theo điều kiện
    public static boolean isValidPattern(String str, String regex, String message) {
        if (str.matches(regex)) {
            return true;
        }
        System.out.println(message);
        return false;
    }

    // Kiểm tra đủ 18 tuổi
    public static boolean isAdult(LocalDate birthDate) {
        Period age = Period.between(birthDate, LocalDate.now());
        return age.getYears() >= 18;
    }

    // Đóng scanner
    public static void closeScanner() {
        if (input != null) {
            input.close();
        }
    }

    // Đọc chuỗi từ người dùng
    public static String readString(String message) {
        System.out.print(message);
        String result = getValue();
        return result;
    }

    // Đọc giá trị boolean từ người dùng
    public static boolean readBoolean(String message) {
        while (true) {
            try {
                System.out.print(message);
                String res = getValue().trim().toLowerCase();
                if (res.equals("true") || res.equals("false"))
                    return Boolean.parseBoolean(res);
                else
                    System.out.println("Invalid input. Please enter true or false.");
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter true or false.");
            }
        }
    }

    // Kiểm tra số thực dương
    public static boolean isPositiveDouble(double num) {
        return num > 0;
    }

    // Tạo ID ngẫu nhiên theo định dạng đã yêu cầu
    private static final Random random = new Random();

    public static String generateId(String prefix) {
        int number = random.nextInt(9000) + 1000; // Tạo số ngẫu nhiên từ 1000 đến 9999
        return prefix + "-" + number;
    }

    // Tạo ID cho Course
    public static String generateCourseId() {
        return generateId("CS");
    }

    // Tạo ID cho Coach
    public static String generateCoachId() {
        return generateId("CO");
    }

    // Tạo ID cho Trainee
    public static String generateTraineeId() {
        return generateId("TR");
    }

    // Tạo ID cho Schedule
    public static String generateScheduleId() {
        return generateId("SC");
    }

    // Tạo ID cho Address
    public static String generateAddressId() {
        return generateId("AD");
    }

    // Tạo ID cho Note
    public static String generateNoteId() {
        return generateId("NO");
    }

    // Tạo ID cho Feedback
    public static String generateFeedbackId() {
        return generateId("FB");
    }
 // Hàm đọc CourseID
    public static String readCourseId(String message) {
        String regex = "^CS-\\d{4}$";
        return readIdWithRegex(message, regex, "[-] Invalid Course ID. Format must be CS-1234.");
    }

    // Hàm đọc CoachID
    public static String readCoachId(String message) {
        String regex = "^CO-\\d{4}$";
        return readIdWithRegex(message, regex, "[-] Invalid Coach ID. Format must be CO-1234.");
    }

    // Hàm đọc TraineeID
    public static String readTraineeId(String message) {
        String regex = "^TR-\\d{4}$";
        return readIdWithRegex(message, regex, "[-] Invalid Trainee ID. Format must be TR-1234.");
    }

    // Hàm đọc ScheduleID
    public static String readScheduleId(String message) {
        String regex = "^SC-\\d{4}$";
        return readIdWithRegex(message, regex, "[-] Invalid Schedule ID. Format must be SC-1234.");
    }

    // Hàm đọc AddressID
    public static String readAddressId(String message) {
        String regex = "^AD-\\d{4}$";
        return readIdWithRegex(message, regex, "[-] Invalid Address ID. Format must be AD-1234.");
    }

    // Hàm đọc NoteID
    public static String readNoteId(String message) {
        String regex = "^NO-\\d{4}$";
        return readIdWithRegex(message, regex, "[-] Invalid Note ID. Format must be NO-1234.");
    }

    // Hàm đọc FeedbackID
    public static String readFeedbackId(String message) {
        String regex = "^FB-\\d{4}$";
        return readIdWithRegex(message, regex, "[-] Invalid Feedback ID. Format must be FB-1234.");
    }

    // Hàm chung kiểm tra ID theo định dạng regex
    public static String readIdWithRegex(String message, String regex, String errorMessage) {
        while (true) {
            System.out.print(message);
            String id = getValue();
            if (isValidPattern(id, regex, id)) {
                return id;
            } else {
                show(errorMessage);
            }
        }
    }
    
    public static boolean isValidPassword(String password) {
        if (password.length() < 8) {
            return false; // Minimum length requirement
        }
        
        boolean hasUpperCase = !password.equals(password.toLowerCase());
        boolean hasLowerCase = !password.equals(password.toUpperCase());
        boolean hasDigit = password.matches(".*\\d.*");
        boolean hasSpecialChar = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        return hasUpperCase && hasLowerCase && hasDigit && hasSpecialChar;
    }

    // Method to read a password securely
    public static String readPassword(String message) {
    	System.out.print(message);
         String password = getValue();
        if (password == null) {
            throw new RuntimeException("No console available.");
        } else if(isValidPassword(password)){
        	
        	 return new String(password);
        } else {
        	System.out.println("[-] Password is invalid ");
        	return null;
        }
        
       // char[] passwordArray = console.readPassword("Enter your password: ");
       
    }
 // Hàm đọc Citizen Identification
    public static String readCitizenIdentification(String message) {
        String regex = "^\\d{9}$|^\\d{12}$"; // Định dạng cho số căn cước công dân (9 hoặc 12 chữ số)
        return readIdWithRegex(message, regex, "[-] Invalid Citizen Identification. Format must be 9 or 12 digits.");
    }

    public static String readName(String prompt) {
        System.out.print(prompt);
        String input = getValue();

        // Bước 1: Loại bỏ khoảng trắng thừa
        input = input.trim();

        // Bước 2: Chuyển viết hoa chữ cái đầu mỗi từ và viết thường cho các ký tự còn lại
        String[] words = input.split("\\s+"); // Tách các từ
        StringBuilder formattedName = new StringBuilder();
        for (String word : words) {
            if (isValidWord(word)) { // Kiểm tra ký tự hợp lệ
                formattedName.append(Character.toUpperCase(word.charAt(0))) // Viết hoa chữ cái đầu
                        .append(word.substring(1).toLowerCase()) // Viết thường các ký tự còn lại
                        .append(" "); // Thêm khoảng trắng
            }
        }

        // Bước 3: Trả về chuỗi đã được định dạng, loại bỏ khoảng trắng ở cuối
        return formattedName.toString().trim();
    }

    private static boolean isValidWord(String word) {
        // Kiểm tra nếu từ chỉ chứa các ký tự chữ cái và khoảng trắng
        return word.matches("[a-zA-Z]+");
    }
 // Phương thức đọc email từ người dùng
    public static String readEmail(String message) {
        while (true) {
            System.out.print(message);
            String email = getValue();
            if (isValidateEmail(email)) {
                return email; // Nếu email hợp lệ, trả về email đã nhập
            } else {
                show("[-] Invalid email format. Please enter again.");
            }
        }
    }

}
