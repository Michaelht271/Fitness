package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Workout;

public class WorkoutDAO implements DAOInterface<Workout> {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=FITNESS;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "MichaelAn271";

    // Singleton pattern
    private static WorkoutDAO instance;

    private WorkoutDAO() {}

    public static WorkoutDAO getInstance() {
        if (instance == null) {
            instance = new WorkoutDAO();
        }
        return instance;
    }

    @Override
    public int insert(Workout workout) {
        String sql = "INSERT INTO Workouts (workoutId, workoutName, duration, level, instruction, equipmentRequired) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, workout.getWorkoutId());
            preparedStatement.setString(2, workout.getWorkoutName());
            preparedStatement.setInt(3, workout.getDuration());
            preparedStatement.setString(4, workout.getLevel());
            preparedStatement.setString(5, workout.getInstruction());
            preparedStatement.setString(6, workout.getEquipmentRequired());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logError("Error inserting data: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(Workout workout) {
        String sql = "UPDATE Workouts SET workoutName = ?, duration = ?, level = ?, instruction = ?, equipmentRequired = ? WHERE workoutId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, workout.getWorkoutName());
            preparedStatement.setInt(2, workout.getDuration());
            preparedStatement.setString(3, workout.getLevel());
            preparedStatement.setString(4, workout.getInstruction());
            preparedStatement.setString(5, workout.getEquipmentRequired());
            preparedStatement.setString(6, workout.getWorkoutId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logError("Error updating data: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(Workout workout) {
        String sql = "DELETE FROM Workouts WHERE workoutId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, workout.getWorkoutId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logError("Error deleting data: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public ArrayList<Workout> selectAll() {
        ArrayList<Workout> workouts = new ArrayList<>();
        String sql = "SELECT * FROM Workouts";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Workout workout = new Workout(
                    resultSet.getString("workoutId"),
                    resultSet.getString("workoutName"),
                    resultSet.getInt("duration"),
                    resultSet.getString("level"),
                    resultSet.getString("instruction"),
                    resultSet.getString("equipmentRequired")
                );
                workouts.add(workout);
            }
        } catch (SQLException e) {
            logError("Error querying data: " + e.getMessage());
        }
        return workouts;
    }

    @Override
    public Workout selectById(Workout workout) {
        String sql = "SELECT * FROM Workouts WHERE workoutId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, workout.getWorkoutId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Workout(
                    resultSet.getString("workoutId"),
                    resultSet.getString("workoutName"),
                    resultSet.getInt("duration"),
                    resultSet.getString("level"),
                    resultSet.getString("instruction"),
                    resultSet.getString("equipmentRequired")
                );
            }
        } catch (SQLException e) {
            logError("Error querying data: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Workout> selectByCondition(String condition) {
        ArrayList<Workout> workouts = new ArrayList<>();
        String sql = "SELECT * FROM Workouts WHERE " + condition;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Workout workout = new Workout(
                    resultSet.getString("workoutId"),
                    resultSet.getString("workoutName"),
                    resultSet.getInt("duration"),
                    resultSet.getString("level"),
                    resultSet.getString("instruction"),
                    resultSet.getString("equipmentRequired")
                );
                workouts.add(workout);
            }
        } catch (SQLException e) {
            logError("Error querying data: " + e.getMessage());
        }
        return workouts;
    }

    private void logError(String message) {
        // Ghi lại lỗi vào console, bạn có thể thay đổi để ghi vào tệp hoặc sử dụng thư viện logging
        System.err.println(message);
    }
}
