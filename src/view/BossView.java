package view;
import java.lang.ModuleLayer.Controller;
import java.util.Scanner;

import controller.BossController;
import utils.Utils;

public class BossView extends Menu<String>{
    private BossController bossController;
   
   
    
    private final static String BossTitle =  "\"====================================\"\n"
    		                               + "\"              BOSS MENU             \" \n"
    		                               + "\"====================================\"";
    private final static String[] BossMenuChoice = { "Manage Coaches",
            "Manage Courses",
            "Manage Trainees",
            "View Notes",
            "View Feedback",
            "Exit"
        };
    
    public BossView() {
    	super(BossTitle,BossMenuChoice);
        this.bossController = new  BossController();
        
    }
	@Override 
	public void execute(int choice) {
		switch (choice) {
        case 1:
        	 bossController.viewAllCoaches();
            manageCoaches();
            break;
        case 2:
            manageCourses();
            break;
        case 3:
            manageTrainees();
            break;
        case 4:
            viewNotes();
            break;
        case 5:
            viewFeedback();
            break;
        case 0:
            System.out.println("Exiting...");
            return;  // Exit the menu
        default:
            System.out.println("Invalid option. Please try again.");
    }
		
	}
   

	private void manageCoaches() {
		
	    // Khởi tạo các tùy chọn cho Menu
	    String[] options = {
	        "Add Coach",
	        "Delete Coach",
	        "Update Coach",
	        
	    };

	    // Tạo một menu mới với tiêu đề và các tùy chọn
	    Menu<String> menuCoaches = new Menu<String>("=== Manage Coaches ===", options) {
	    	
	        @Override
	        public void execute(int choice) {
	            switch (choice) {
	                case 1:
	                    addCoach();
	                    break;
	                case 2:
	                    deleteCoach();
	                    break;
	                case 3:
	                    updateCoach();
	                    break;
	                case 4:
	                    return; // Trở lại menu chính
	                default:
	                    System.out.println("[-] Invalid option. Please try again.");
	            }
	        }
	       
	    };

	    // Chạy menu
	    menuCoaches.run();
	    
	}



	private void manageCourses() {
	    // Danh sách tùy chọn cho menu quản lý khóa học
	    String[] options = {
	        "Add Course",
	        "Delete Course",
	        "Update Course",
	        "Back to Main Menu"
	    };
	    
	    // Tạo menu
	    Menu<String> courseMenu = new Menu<>("=== Manage Courses ===", options) {
	        @Override
	        public void execute(int choice) {
	            switch (choice) {
	                case 1:
	                    addCourse();
	                    System.out.println("Course added successfully!\n");
	                    break;
	                case 2:
	                    deleteCourse();
	                    System.out.println("Course deleted successfully!\n");
	                    break;
	                case 3:
	                    updateCourse();
	                    System.out.println("Course updated successfully!\n");
	                    break;
	                case 0:
	                    System.out.println("Returning to main menu...\n");
	                    return;  // Quay lại menu chính
	                default:
	                    System.out.println("Invalid option. Please try again.");
	            }
	        }
	    };

	    // Hiển thị danh sách khóa học
	    System.out.println("\n=== Current Courses ===");
	    bossController.viewAllCourses(); // Hiển thị danh sách các khóa học
	    System.out.println("=========================\n");
	    
	    // Chạy menu
	    courseMenu.run(); // Chạy menu
	}

	private void manageTrainees() {
	    // Danh sách tùy chọn cho menu quản lý học viên
	    String[] options = {
	        "Add Trainee",
	        "Delete Trainee",
	        "Update Trainee",
	        "Back to Main Menu"
	    };

	    // Tạo menu
	    Menu<String> traineeMenu = new Menu<>("=== Manage Trainees ===", options) {
	        @Override
	        public void execute(int choice) {
	            switch (choice) {
	                case 1:
	                    addTrainee();
	                    System.out.println("Trainee added successfully!\n");
	                    break;
	                case 2:
	                    deleteTrainee();
	                    System.out.println("Trainee deleted successfully!\n");
	                    break;
	                case 3:
	                    updateTrainee();
	                    System.out.println("Trainee updated successfully!\n");
	                    break;
	                case 0:
	                    System.out.println("Returning to main menu...\n");
	                    return;  // Quay lại menu chính
	                default:
	                    System.out.println("Invalid option. Please try again.");
	            }
	        }
	    };

	    // Hiển thị danh sách học viên
	    System.out.println("\n=== Current Trainees ===");
	    bossController.viewAllTrainees(); // Hiển thị danh sách các học viên
	    System.out.println("=========================\n");

	    // Chạy menu
	    traineeMenu.run(); // Chạy menu
	}


    // Method to view notes
    private void viewNotes() {
        System.out.println("\n--- Notes ---");
  //     bossController.; // Call the controller to display notes
    }

    // Method to view feedback
    private void viewFeedback() {
        System.out.println("\n--- Feedback ---");
  //      controller.viewFeedback(); // Call the controller to display feedback
    }

    // Method to add a new coach
    private void addCoach() {
         boolean status =  bossController.addCoach();
        if (status ) {
            System.out.println("Coach added successfully!");
           // Trả về true nếu thêm thành công
        } else {
            System.out.println("Failed to add coach.");
           
        }
       
    }

    // Method to delete a coach by ID
    private void deleteCoach() {
    	boolean status =  bossController.deleteCoach();
    	 // Kiểm tra xem Coach có tồn tại không và xóa
        if (status ) {
            System.out.println(String.format("Coach with  deleted successfully."));
        } else {
            System.out.println(String.format("Coach with  not found."));
        }
    }

    // Method to update an existing coach
    private void updateCoach() {
       boolean status = bossController.updateCoach();
  	 // Kiểm tra xem Coach có tồn tại không và xóa
       if (status ) {
           System.out.println(String.format("Coach with  update  successfully."));
       } else {
           System.out.println(String.format("Coach with  not found."));
       }
    }

    // Method to add a new course
    private void addCourse() {
      
        // Call the controller to add a new course
        bossController.createCourse();
        System.out.println("Course added successfully.");
    }

    // Method to delete a course by ID
    private void deleteCourse() {
       
        boolean success = bossController.deleteCourse();
        if (success) {
            System.out.println("Course deleted successfully.");
        } else {
            System.out.println("Course with ID:  not found.");
        }
    }

    // Method to update an existing course
    private void updateCourse() {
      boolean status = bossController.updateCourse();
      if (status ) {
          System.out.println(String.format("Course is  update  successfully."));
      } else {
          System.out.println(String.format("Course is  not found."));
      }
    }

    // Method to add a new trainee
    private void addTrainee() {
      boolean status = bossController.addTrainee();
      if (status ) {
          System.out.println(String.format("Addition Trainee is  successfully."));
      } else {
          System.out.println(String.format("Addition Trainee false "));
      }
    }

    // Method to delete a trainee by ID
    private void deleteTrainee() {

        boolean success = bossController.deleteTrainee();
        if (success) {
            System.out.println("Course deleted successfully.");
        } else {
            System.out.println("Course with ID:  not found.");
        }
    }

    // Method to update an existing trainee
    private void updateTrainee() {
    	   boolean success = bossController.updateTrainee();
           if (success) {
               System.out.println("Trainee update successfully.");
           } else {
               System.out.println("Cannot Update Trainee ");
           }
}

}

