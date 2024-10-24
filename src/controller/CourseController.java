package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import model.Course;
import service.CourseService;

public class CourseController {
    private static CourseService courseService;

    public CourseController() {
        this.courseService = new CourseService();
    }

    public  static void viewCourse() {
    	 try {
    	        List<Course> courses = courseService.getAllCourses(); // Lấy danh sách khóa học
    	        LocalDate currentDate = LocalDate.now(); // Lấy ngày hiện tại

    	        // Định dạng ngày
    	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    	        // In tiêu đề bảng
    	        System.out.println("================================================================================================");
    	        System.out.printf("| %-5s | %-25s | %-10s | %-12s | %-12s | %-10s |%n", 
    	                          "ID", "Course Name", "Coach ID", "Start Date", "End Date", "Price");
    	        System.out.println("================================================================================================");

    	        boolean courseFound = false; // Biến kiểm tra có khóa học nào phù hợp không

    	        // Lọc các khóa học đang diễn ra hoặc chưa diễn ra
    	        for (Course course : courses) {
    	            LocalDate startDate = course.getStartDate();
    	            LocalDate endDate = course.getEndDate();

    	            // Kiểm tra nếu khóa học đang diễn ra hoặc chưa diễn ra
    	            if ((startDate.isBefore(currentDate) || startDate.isEqual(currentDate)) && endDate.isAfter(currentDate)) {
    	                courseFound = true; // Có khóa học phù hợp
    	                System.out.printf("| %-5d | %-25s | %-10d | %-12s | %-12s | %-10.2f |%n",
    	                                  course.getCourseId(), course.getCourseName(), course.getCoachID(),
    	                                  startDate.format(formatter), endDate.format(formatter), course.getPrice());
    	            }
    	        }

    	        // Nếu không có khóa học nào phù hợp
    	        if (!courseFound) {
    	            System.out.println("|                                  No ongoing or upcoming courses found                                  |");
    	        }

    	        System.out.println("================================================================================================");
    	    } catch (NullPointerException e) {
    	        System.out.println("Error: Unable to retrieve course list. The course service might not be initialized.");
    	    } catch (Exception e) {
    	        System.out.println("An unexpected error occurred: " + e.getMessage());
    	    }
    	}

}
