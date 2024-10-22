package dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Course;

public class CourseDAO implements DAOInterface<Course> {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=FITNESS;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "MichaelAn271";

    // Singleton pattern
    private static CourseDAO instance;

    private CourseDAO() {}

    public static CourseDAO getInstance() {
        if (instance == null) {
            instance = new CourseDAO();
        }
        return instance;
    }

    @Override
    public int insert(Course course) {
        String sql = "INSERT INTO Courses (courseId, courseName, courseDescription, coachID, courseType, maxParticipants, startDate, endDate, price, totalSessions) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, course.getCourseId());
            preparedStatement.setString(2, course.getCourseName());
            preparedStatement.setString(3, course.getCourseDescription());
            preparedStatement.setString(4, course.getCoachID());
            preparedStatement.setString(5, course.getCourseType());
            preparedStatement.setInt(6, course.getMaxParticipants());
            preparedStatement.setDate(7, Date.valueOf(course.getStartDate()));
            preparedStatement.setDate(8, Date.valueOf(course.getEndDate()));
            preparedStatement.setDouble(9, course.getPrice());
            preparedStatement.setInt(10, course.getTotalSessions());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi chèn dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(Course course) {
        String sql = "UPDATE Courses SET courseName = ?, courseDescription = ?, coachID = ?, courseType = ?, maxParticipants = ?, startDate = ?, endDate = ?, price = ?, totalSessions = ? WHERE courseId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, course.getCourseName());
            preparedStatement.setString(2, course.getCourseDescription());
            preparedStatement.setString(3, course.getCoachID());
            preparedStatement.setString(4, course.getCourseType());
            preparedStatement.setInt(5, course.getMaxParticipants());
            preparedStatement.setDate(6, Date.valueOf(course.getStartDate()));
            preparedStatement.setDate(7, Date.valueOf(course.getEndDate()));
            preparedStatement.setDouble(8, course.getPrice());
            preparedStatement.setInt(9, course.getTotalSessions());
            preparedStatement.setString(10, course.getCourseId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(Course course) {
        String sql = "DELETE FROM Courses WHERE courseId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, course.getCourseId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public ArrayList<Course> selectAll() {
        ArrayList<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Courses";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Course course = new Course();
                course.setCourseId(resultSet.getString("courseId"));
                course.setCourseName(resultSet.getString("courseName"));
                course.setCourseDescription(resultSet.getString("courseDescription"));
                course.setCoachID(resultSet.getString("coachID"));
                course.setCourseType(resultSet.getString("courseType"));
                course.setMaxParticipants(resultSet.getInt("maxParticipants"));
                course.setStartDate(resultSet.getDate("startDate").toLocalDate());
                course.setEndDate(resultSet.getDate("endDate").toLocalDate());
                course.setPrice(resultSet.getDouble("price"));
                course.setTotalSessions(resultSet.getInt("totalSessions"));
                courses.add(course);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return courses;
    }

    @Override
    public Course selectById(Course course) {
        String sql = "SELECT * FROM Courses WHERE courseId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, course.getCourseId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                course.setCourseId(resultSet.getString("courseId"));
                course.setCourseName(resultSet.getString("courseName"));
                course.setCourseDescription(resultSet.getString("courseDescription"));
                course.setCoachID(resultSet.getString("coachID"));
                course.setCourseType(resultSet.getString("courseType"));
                course.setMaxParticipants(resultSet.getInt("maxParticipants"));
                course.setStartDate(resultSet.getDate("startDate").toLocalDate());
                course.setEndDate(resultSet.getDate("endDate").toLocalDate());
                course.setPrice(resultSet.getDouble("price"));
                course.setTotalSessions(resultSet.getInt("totalSessions"));
                return course;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Course> selectByCondition(String condition) {
        ArrayList<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM Courses WHERE " + condition;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Course course = new Course();
                course.setCourseId(resultSet.getString("courseId"));
                course.setCourseName(resultSet.getString("courseName"));
                course.setCourseDescription(resultSet.getString("courseDescription"));
                course.setCoachID(resultSet.getString("coachID"));
                course.setCourseType(resultSet.getString("courseType"));
                course.setMaxParticipants(resultSet.getInt("maxParticipants"));
                course.setStartDate(resultSet.getDate("startDate").toLocalDate());
                course.setEndDate(resultSet.getDate("endDate").toLocalDate());
                course.setPrice(resultSet.getDouble("price"));
                course.setTotalSessions(resultSet.getInt("totalSessions"));
                courses.add(course);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return courses;
    }
}
