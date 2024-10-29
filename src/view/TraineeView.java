package view;
import java.util.ArrayList;
import java.util.Scanner;

import controller.CoachController;

import controller.TraineeController;
import model.Trainee;
import utils.Utils;

public class TraineeView extends Menu<String>{
	private Trainee trainee;
    private TraineeController traineeController = new TraineeController();
  
    

    private final static String TraineeTitle = "\"=====================================\"\n"
                                             + "\"           TRAINEE MENU             \" \n"
                                             + "\"=====================================\"";
    private final static String[] TraineeMenuChoices = { 
        "View Profile",
        "View Enrolled Courses",
        "View Coach List",
        "Enroll in a Course",
        "View Notes",
        "Make Feedback",
        "View Workout Schedule",
    };

    public TraineeView(Trainee trainee) {
    	
        super(TraineeTitle, TraineeMenuChoices);
        this.trainee = trainee;
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1:
                viewProfile();
                break;
            case 2:
                displayEnrolledCourses(trainee.getTraineeId());
                break;
            case 3:
                viewCoachList();
                break;
            case 4:
                enrollInCourse();
                break;
            case 5:
                viewNotes();
                break;
            case 6:
                provideFeedback();
                break;
            case 7:
                viewSchedule();
                break;
            
            default:
                System.out.println("Invalid choice. Please select a valid option.");
                break;
        }
    }

    private void viewProfile() {
    	
    	   
    	 System.out.println("=============== TRAINEE INFORMATION ===============");
    	    System.out.println(String.format(
    	        "| %-15s | %-15s | %-10s | %-10s | %-50s | %-15s | %-15s | %-20s | %-10s | %-10s | %-15s | %-15s | %-20s | %-10s |",
    	        "Trainee ID", "Full Name", "Gender", "Role", "Email", "Phone", "Birthday", 
    	        "Citizen ID", "Height", "Weight", "Level", "City", "Street", "House Number"
    	    ));
    	        System.out.println(traineeController.getTrainee(trainee.getTraineeId()));
    	        System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
    	    }

    	    

     
    
    public void displayEnrolledCourses(String traineeId) {
        ArrayList<String> courseDetails = traineeController.viewCoursesEnrolled(traineeId);
        if (courseDetails.isEmpty()) {
            System.out.println("You are not enrolled in any courses.");
        } else {
        	
        	    System.out.println("=============== COURSE INFORMATION ===============");
        	    
        	    System.out.println(String.format(
        	        "| %-10s | %-30s | %-50s | %-20s | %-20s | %-20s | %-10s |",
        	        "Course ID", "Course Name", "Description", "Type", "Start Date", "End Date", "Price"
        	    ));
        	    
        

            for (String course : courseDetails) {
                System.out.println(course);
            }
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        }
    }

    private void viewCoachList() {
        // In tiêu đề cho danh sách huấn luyện viên
        System.out.println("===== Coach List =====");
        // Gọi phương thức để hiển thị danh sách huấn luyện viên
        CoachController.viewCoaches();
    }


    private void enrollInCourse() {
      
     
        String traineeId = trainee.getTraineeId();

        // Gọi phương thức enrollCourse từ lớp CoachController
        boolean isEnrolled = traineeController.enrollCourse(traineeId);

        // Hiển thị kết quả
        if (isEnrolled) {
            System.out.println("Enrollment successful!");
        } else {
            System.out.println("Enrollment failed. Please try again.");
        }
    }
    private void viewNotes() {
      
       
        String traineeId = trainee.getTraineeId();

        // Call the controller's method to view notes
        boolean hasNotes = traineeController.viewNote(traineeId);

        // Check if notes were found
        if (!hasNotes) {
            System.out.println("No notes available for Trainee ID: " + traineeId);
        }
    }


    public void provideFeedback( ) {
        boolean success = traineeController.makeFeedback(trainee.getTraineeId());
        if (success) {
            System.out.println("Feedback submitted successfully!");
        } else {
            System.out.println("Feedback submission failed.");
        }
    }


    private void viewSchedule() {
     
        String traineeId = trainee.getTraineeId();

        // Call the controller's method to view the schedule
        boolean hasSchedule = traineeController.viewSchedule(traineeId);

        // Check if the schedule was found
        if (!hasSchedule) {
            System.out.println("No schedule available for Trainee ID: " + traineeId);
        }
    }

}
