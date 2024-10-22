

package utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public abstract class Utils {
	public static final Scanner input = new Scanner(System.in);
	public static final DateTimeFormatter Date_Format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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

	// Lấy ngày từ người dùng
	public static LocalDate readDate(String message) {
		while (true) {
			System.out.print(message);
			String entry = getValue();
			try {
				return LocalDate.parse(entry, Date_Format);
			} catch (DateTimeParseException e) {
				show("Date is invalid. Please enter again (dd/MM/yyyy) .");
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
		LocalDate date = readDate("Enter Date (dd/MM/yyyy): ");
		LocalTime time = readTime("Enter Time (HH:mm): ");
		return LocalDateTime.of(date, time);
	}

	// Kiểm tra số điện thoại
	public static boolean isPhoneNumber(String phone) {
        phone = trimOrNull(phone);
       
		return phone.matches(PHONE_NUMBER_REGEX);
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
					capitalized.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase())
							.append(" ");
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
    
	public static boolean isValidPattern(String str, String regex) {
		return str.matches(regex);
	}
	

	// Kiểm tra đủ 18 tuổi
	public static boolean isAdult(LocalDate birthDate) {

		Period age = Period.between(birthDate, LocalDate.now());

		return age.getYears() >= 18;
	}
	public static void closeScanner() {
		if (input != null) {
			input.close();
		}
	}
    public static String readString(String message) {
    	System.out.print(message );
    	String str = getValue();
    	return str;
    }
    
    public static boolean readBoolean(String message) {

		while (true) {
			try {
				System.out.print(message);
				String res = input.nextLine().trim().toLowerCase();
				if (res.equals("true") || res.equals("false"))
					return Boolean.parseBoolean(res);
				else
					System.out.println("Invalid input. Please enter true or false.");

			} catch (Exception e) {
				System.out.println("Invalid input. Please enter true or false.");
				
			}
		}
	}
	public static boolean isPositiveDouble(double num) {
		return (num > 0) ? true : false;
	}
}



	