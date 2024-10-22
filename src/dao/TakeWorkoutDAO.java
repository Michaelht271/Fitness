package dao;

import java.sql.*;
import java.util.ArrayList;
import model.TakeWorkout;

public class TakeWorkoutDAO implements DAOInterface<TakeWorkout> {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=FITNESS;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "MichaelAn271";

    // Singleton pattern
    private static TakeWorkoutDAO instance;

    private TakeWorkoutDAO() {}

    public static TakeWorkoutDAO getInstance() {
        if (instance == null) {
            instance = new TakeWorkoutDAO();
        }
        return instance;
    }

    @Override
    public int insert(TakeWorkout takeWorkout) {
        String sql = "INSERT INTO TakeWorkouts (courseId, workoutId) VALUES (?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, takeWorkout.getCourseId());
            preparedStatement.setString(2, takeWorkout.getWorkoutId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi chèn dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(TakeWorkout takeWorkout) {
        String sql = "UPDATE TakeWorkouts SET workoutId = ? WHERE courseId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, takeWorkout.getWorkoutId());
            preparedStatement.setString(2, takeWorkout.getCourseId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(TakeWorkout takeWorkout) {
        String sql = "DELETE FROM TakeWorkouts WHERE courseId = ? AND workoutId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, takeWorkout.getCourseId());
            preparedStatement.setString(2, takeWorkout.getWorkoutId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public ArrayList<TakeWorkout> selectAll() {
        ArrayList<TakeWorkout> takeWorkouts = new ArrayList<>();
        String sql = "SELECT * FROM TakeWorkouts";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                TakeWorkout takeWorkout = new TakeWorkout();
                takeWorkout.setCourseId(resultSet.getString("courseId"));
                takeWorkout.setWorkoutId(resultSet.getString("workoutId"));
                takeWorkouts.add(takeWorkout);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return takeWorkouts;
    }

    @Override
    public TakeWorkout selectById(TakeWorkout takeWorkout) {
        String sql = "SELECT * FROM TakeWorkouts WHERE courseId = ? AND workoutId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, takeWorkout.getCourseId());
            preparedStatement.setString(2, takeWorkout.getWorkoutId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                takeWorkout.setCourseId(resultSet.getString("courseId"));
                takeWorkout.setWorkoutId(resultSet.getString("workoutId"));
                return takeWorkout;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<TakeWorkout> selectByCondition(String condition) {
        ArrayList<TakeWorkout> takeWorkouts = new ArrayList<>();
        String sql = "SELECT * FROM TakeWorkouts WHERE " + condition;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                TakeWorkout takeWorkout = new TakeWorkout();
                takeWorkout.setCourseId(resultSet.getString("courseId"));
                takeWorkout.setWorkoutId(resultSet.getString("workoutId"));
                takeWorkouts.add(takeWorkout);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return takeWorkouts;
    }
}
