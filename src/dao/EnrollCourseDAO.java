package dao;

import java.sql.*;
import java.util.ArrayList;
import model.EnrollCourse;

public class EnrollCourseDAO implements DAOInterface<EnrollCourse> {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=FITNESS;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "MichaelAn271";

    // Singleton pattern
    private static EnrollCourseDAO instance;

    private EnrollCourseDAO() {}

    public static EnrollCourseDAO getInstance() {
        if (instance == null) {
            instance = new EnrollCourseDAO();
        }
        return instance;
    }

    @Override
    public int insert(EnrollCourse enrollCourse) {
        String sql = "INSERT INTO EnrollCourses (traineeId, courseId, enrollmentDate) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, enrollCourse.getTraineeId());
            preparedStatement.setString(2, enrollCourse.getCourseId());
            preparedStatement.setDate(3, Date.valueOf(enrollCourse.getEnrollmentDate()));

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi chèn dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(EnrollCourse enrollCourse) {
        String sql = "UPDATE EnrollCourses SET courseId = ?, enrollmentDate = ? WHERE traineeId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, enrollCourse.getCourseId());
            preparedStatement.setDate(2, Date.valueOf(enrollCourse.getEnrollmentDate()));
            preparedStatement.setString(3, enrollCourse.getTraineeId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(EnrollCourse enrollCourse) {
        String sql = "DELETE FROM EnrollCourses WHERE traineeId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, enrollCourse.getTraineeId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public ArrayList<EnrollCourse> selectAll() {
        ArrayList<EnrollCourse> enrollCourses = new ArrayList<>();
        String sql = "SELECT * FROM EnrollCourses";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                EnrollCourse enrollCourse = new EnrollCourse(
                    resultSet.getString("traineeId"),
                    resultSet.getString("courseId"),
                    resultSet.getDate("enrollmentDate").toLocalDate()
                );
                enrollCourses.add(enrollCourse);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return enrollCourses;
    }

    @Override
    public EnrollCourse selectById(EnrollCourse enrollCourse) {
        String sql = "SELECT * FROM EnrollCourses WHERE traineeId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, enrollCourse.getTraineeId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                enrollCourse.setCourseId(resultSet.getString("courseId"));
                enrollCourse.setEnrollmentDate(resultSet.getDate("enrollmentDate").toLocalDate());
                return enrollCourse;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<EnrollCourse> selectByCondition(String condition) {
        ArrayList<EnrollCourse> enrollCourses = new ArrayList<>();
        String sql = "SELECT * FROM EnrollCourses WHERE " + condition;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                EnrollCourse enrollCourse = new EnrollCourse(
                    resultSet.getString("traineeId"),
                    resultSet.getString("courseId"),
                    resultSet.getDate("enrollmentDate").toLocalDate()
                );
                enrollCourses.add(enrollCourse);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return enrollCourses;
    }
}
