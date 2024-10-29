package controller;

import java.time.LocalDate;

import java.util.List;

import model.Course;
import model.EnrollCourse;

import service.CourseService;
import service.EnrollCourseService;

public class CourseController {
    private static CourseService courseService = new CourseService();
    EnrollCourseService enrollCourseService = new EnrollCourseService();
    public CourseController() {
       
    }

    public static void viewCourse() {
        try {
            List<Course> courses = courseService.getAllCourses(); // Lấy danh sách khóa học
            LocalDate currentDate = LocalDate.now(); // Lấy ngày hiện tại

            // Định dạng ngày
          //  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            // Định nghĩa tiêu đề và định dạng bảng
            String formatTitle = "| %-10s | %-50s | %-10s | %-12s | %-12s | %-10s |%n";
            String formatCourse = "| %-10s | %-50s | %-10s | %-12s | %-12s | %-10.2f |%n";

            String title = "=====================================\n"
                    + "             COURSE TABLE           \"\n"
                    + "=====================================";

            // Định nghĩa đường viền cho bảng
          //  String border = "+----------+-------------------------+------------+--------------+--------------+----------+";
            String doubleBorder = "==========================================================================================================================|";

            // In đường viền tiêu đề
            System.out.println("\n" + title);
            System.out.println(doubleBorder);
            System.out.printf(formatTitle, "ID", "Course Name", "Coach ID", "Start Date", "End Date", "Price");
            System.out.println(doubleBorder);

            boolean courseFound = false; // Biến kiểm tra có khóa học nào phù hợp không

            // Lọc và in các khóa học đang diễn ra hoặc chưa diễn ra
            for (Course course : courses) {
                LocalDate startDate = course.getStartDate();
                LocalDate endDate = course.getEndDate();
              
                // Kiểm tra nếu khóa học đang diễn ra hoặc chưa diễn ra
                if (currentDate.isBefore(startDate) ||  (currentDate.isEqual(startDate) || currentDate.isAfter(startDate)) && currentDate.isBefore(endDate)) {
                    courseFound = true; // Có khóa học phù hợp
                  //  System.out.println(course.toString());
                    System.out.printf(formatCourse,
                                      course.getCourseId(), 
                                      course.getCourseName(), 
                                      course.getCoachID(),
                                      course.getStartDate() != null ? course.getStartDate().toString() : "N/A", 
                                      course.getEndDate() != null ? course.getEndDate().toString() : "N/A", 
                                      course.getPrice());

               }
            }

            // Nếu không có khóa học nào phù hợp
            if (!courseFound) {
                System.out.printf("| %-100s |%n", "🚫 No ongoing or upcoming courses found 🚫");
            }

            // In đường viền kết thúc bảng
            System.out.println(doubleBorder);

            // Cung cấp thông tin thêm
            System.out.println("💡 Note: Check back often for new courses!");
            System.out.println("📞 For inquiries, contact us at info@example.com");

        } catch (NullPointerException e) {
            System.out.println("⚠️ Error: Unable to retrieve course list. The course service might not be initialized.");
        } catch (Exception e) {
            System.out.println("⚠️ An unexpected error occurred: " + e.getMessage());
        }
    }
    
    public boolean enrollCourse(String courseID, String traineeID) {
        // Tạo một đối tượng EnrollCourse mới
        EnrollCourse enrollCourse = new EnrollCourse();
        enrollCourse.setCourseId(courseID);
        enrollCourse.setTraineeId(traineeID);
        enrollCourse.setEnrollmentDate(LocalDate.now());

        int isEnrolled = enrollCourseService.addEnrollCourse(enrollCourse);
        

        // Kiểm tra kết quả thêm và trả về thông báo
        if (isEnrolled > 0) {
                      return true;
        } else {
          
            return false;
        }
    }


}
