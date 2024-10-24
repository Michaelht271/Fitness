package view;

import controller.CoachController;
import model.Coach;
import utils.Utils;

import java.util.Scanner;

public class CoachView extends Menu<String> {
    private CoachController coachController =new CoachController();
    
    private Coach coach;
   

    private final static String CoachTitle =   "\"=====================================\"\n"
                                             + "\"              COACH MENU             \" \n"
                                             + "\"=====================================\"";
    private final static String[] CoachMenuChoices = { 
        "View Profile",
        "View Course List",
        "Make Note",
        "View Feedback",
        "Create Course",
        "View Schedule",
       
    };
    

    public CoachView(Coach coach ) {
    	super(CoachTitle, CoachMenuChoices);
      
        this.coach = coach;
       
    }
    @Override
	public void execute(int choice) {
    	  String   coachId = coach.getCoachId();
		// TODO Auto-generated method stub
    	 switch (choice) {
         case 1:
             viewProfile(coachId);
             break;
         case 2:
             viewCourseList(coachId);
             break;
         case 3:
             makeNote();
             break;
         case 4:
             viewFeedback(coachId);
             break;
         case 5:
             createCourse(coachId);
             break;
         case 6:
             viewSchedule(coachId);
             break;
         default:
             System.out.println("Invalid choice. Please select a valid option.");
             break;
     }
		
	}
 
 // Method to view coach profile
    private void viewProfile(String coachId) {
       
        if (coach == null) {
            System.out.println("Coach with ID: " + coachId + " not found.");
            return;
        }

        // Hiển thị tiêu đề cho thông tin hồ sơ
        System.out.println("=== Coach Profile ===");
        
        // Sử dụng phương thức toString để hiển thị thông tin huấn luyện viên
        System.out.println(coach.toString());
    }


    // Method to view the course list
    private void viewCourseList(String coachId) {
        coachController.viewMyCourseList(coachId);
    }

    // Method to make a note
    private void makeNote() {
        coachController.createNote();
    }

    // Method to view feedback
    private void viewFeedback(String coachId) {
        coachController.viewMyFeedback(coachId);
    }

    // Method to create a new course
    private void createCourse(String coachId) {
        coachController.createCourse(coachId);
    }

    // Method to view the coach's schedule
    private void viewSchedule(String coachId) {
        coachController.viewMySchedule(coachId);
    }

	
}
