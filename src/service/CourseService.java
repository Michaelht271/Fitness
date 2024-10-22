package service;

import dao.CourseDAO;
import model.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseService {
    private static CourseDAO courseDAO;
    public static  List<Course> courseCache; // Cache để lưu trữ các khóa học
    static {
    	courseDAO = CourseDAO.getInstance();
    	courseCache = new ArrayList<>();
    	loadCourses();
    }
    public CourseService() {
      
    }

    // Tải danh sách khóa học từ cơ sở dữ liệu vào bộ nhớ cache
    private static void loadCourses() {
        courseCache = courseDAO.selectAll();
    }

    public int addCourse(Course course) {
        int result = courseDAO.insert(course);
        if (result > 0) {
            courseCache.add(course); // Cập nhật cache nếu thêm thành công
        }
        return result;
    }

    public int updateCourse(Course course) {
        int result = courseDAO.update(course);
        if (result > 0) {
            // Cập nhật khóa học trong cache
            for (int i = 0; i < courseCache.size(); i++) {
                if (courseCache.get(i).getCourseId().equals(course.getCourseId())) {
                    courseCache.set(i, course);
                    break;
                }
            }
        }
        return result;
    }

    public int deleteCourse(String courseId) {
        Course course = new Course();
        course.setCourseId(courseId);
        int result = courseDAO.delete(course);
        if (result > 0) {
            // Xóa khóa học khỏi cache
            courseCache.removeIf(c -> c.getCourseId().equals(courseId));
        }
        return result;
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courseCache); // Trả về một bản sao của danh sách khóa học
    }

    public Course getCourseById(String courseId) {
        // Tìm kiếm khóa học trong cache
        for (Course course : courseCache) {
            if (course.getCourseId().equals(courseId)) {
                return course;
            }
        }
        return null; // Nếu không tìm thấy
    }

    public List<Course> getCoursesByCondition(String condition) {
        // Lọc khóa học theo điều kiện trong cache
        List<Course> filteredCourses = new ArrayList<>();
        for (Course course : courseCache) {
            // Thêm logic lọc ở đây, ví dụ:
            if (course.getCourseName().contains(condition)) {
                filteredCourses.add(course);
            }
        }
        return filteredCourses;
    }
}
