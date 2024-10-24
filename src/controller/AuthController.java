package controller;

import java.util.ArrayList;

import model.Coach;
import model.Trainee;
import service.CoachService;
import service.TraineeService;

public class AuthController {
	
	private static  TraineeService traineeService = new TraineeService();
	 private static CoachService coachService = new CoachService();
	 
	public static Trainee checkPassWordTrainee(String traineeId, String password) {
        try {
            // B1: Lấy trainee từ TraineeService dựa trên traineeId
            Trainee trainee = traineeService.getTraineeById(traineeId);

            // Kiểm tra nếu trainee tồn tại
            if (trainee == null) {
                System.out.println("Trainee with ID: " + traineeId + " does not exist.");
                return null; // Hoặc có thể ném ngoại lệ tùy thuộc vào yêu cầu.
            }

            // B2: Kiểm tra mật khẩu có khớp không
            if (trainee.getPassword().equals(password)) {
                return trainee; // Mật khẩu đúng, trả về đối tượng Trainee
            } else {
                System.out.println("Incorrect password.");
                return null; // Mật khẩu sai
            }
        } catch (Exception e) {
            // Xử lý các ngoại lệ chung, ví dụ như lỗi null hoặc lỗi khác
            System.out.println("An error occurred while checking the password: " + e.getMessage());
            e.printStackTrace();
            return null; // Trả về null trong trường hợp lỗi
        }
    }
		
		
	

	    public static Coach checkPassWordCoach(String coachId, String password) {
	        try {
	            // B1: Lấy coach từ CoachService dựa trên coachId
	            Coach coach = coachService.getCoachById(coachId);

	            // Kiểm tra nếu coach tồn tại
	            if (coach == null) {
	                System.out.println("Coach with ID: " + coachId + " does not exist.");
	                return null; // Hoặc có thể ném ngoại lệ tùy thuộc vào yêu cầu.
	            }

	            // B2: Kiểm tra mật khẩu có khớp không
	            if (coach.getPassword().equals(password)) {
	                return coach; // Mật khẩu đúng, trả về đối tượng Coach
	            } else {
	                System.out.println("Incorrect password.");
	                return null; // Mật khẩu sai
	            }
	        } catch (Exception e) {
	            // Xử lý các ngoại lệ chung, ví dụ như lỗi null hoặc lỗi khác
	            System.out.println("An error occurred while checking the password: " + e.getMessage());
	            e.printStackTrace();
	            return null; // Trả về null trong trường hợp lỗi
	        }
	    }
	    
	    
	    private static final String BOSS_USERNAME = "boss";
	    private static final String BOSS_PASSWORD = "admin123";

	    // Phương thức kiểm tra thông tin đăng nhập của Boss
	    public static boolean checkBossCredentials(String username, String password) {
	      
	        if (BOSS_USERNAME.equals(username) && BOSS_PASSWORD.equals(password)) {
	            return true; 
	        } else {
	            System.out.println("Incorrect boss credentials.");
	            return false; 
	        }
	    }

}
