package dao;

import java.sql.*;

import java.util.ArrayList;
import model.Schedule;

public class ScheduleDAO implements DAOInterface<Schedule> {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=FITNESS;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "MichaelAn271";

    // Singleton pattern
    private static ScheduleDAO instance;

    private ScheduleDAO() {}

    public static ScheduleDAO getInstance() {
        if (instance == null) {
            instance = new ScheduleDAO();
        }
        return instance;
    }

    @Override
    public int insert(Schedule schedule) {
        String sql = "INSERT INTO Schedules (scheduleId, courseID, date, startTime, endTime) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, schedule.getScheduleId());
            preparedStatement.setString(2, schedule.getCourseID());
            preparedStatement.setDate(3, Date.valueOf(schedule.getDate()));
            preparedStatement.setTime(4, Time.valueOf(schedule.getStartTime()));
            preparedStatement.setTime(5, Time.valueOf(schedule.getEndTime()));

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi chèn dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(Schedule schedule) {
        String sql = "UPDATE Schedules SET courseID = ?, date = ?, startTime = ?, endTime = ? WHERE scheduleId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, schedule.getCourseID());
            preparedStatement.setDate(2, Date.valueOf(schedule.getDate()));
            preparedStatement.setTime(3, Time.valueOf(schedule.getStartTime()));
            preparedStatement.setTime(4, Time.valueOf(schedule.getEndTime()));
            preparedStatement.setString(5, schedule.getScheduleId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(Schedule schedule) {
        String sql = "DELETE FROM Schedules WHERE scheduleId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, schedule.getScheduleId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public ArrayList<Schedule> selectAll() {
        ArrayList<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM Schedules";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setScheduleId(resultSet.getString("scheduleId"));
                schedule.setCourseID(resultSet.getString("courseID"));
                schedule.setDate(resultSet.getDate("date").toLocalDate());
                schedule.setStartTime(resultSet.getTime("startTime").toLocalTime());
                schedule.setEndTime(resultSet.getTime("endTime").toLocalTime());
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return schedules;
    }

    @Override
    public Schedule selectById(Schedule schedule) {
        String sql = "SELECT * FROM Schedules WHERE scheduleId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, schedule.getScheduleId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                schedule.setScheduleId(resultSet.getString("scheduleId"));
                schedule.setCourseID(resultSet.getString("courseID"));
                schedule.setDate(resultSet.getDate("date").toLocalDate());
                schedule.setStartTime(resultSet.getTime("startTime").toLocalTime());
                schedule.setEndTime(resultSet.getTime("endTime").toLocalTime());
                return schedule;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Schedule> selectByCondition(String condition) {
        ArrayList<Schedule> schedules = new ArrayList<>();
        String sql = "SELECT * FROM Schedules WHERE " + condition;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Schedule schedule = new Schedule();
                schedule.setScheduleId(resultSet.getString("scheduleId"));
                schedule.setCourseID(resultSet.getString("courseID"));
                schedule.setDate(resultSet.getDate("date").toLocalDate());
                schedule.setStartTime(resultSet.getTime("startTime").toLocalTime());
                schedule.setEndTime(resultSet.getTime("endTime").toLocalTime());
                schedules.add(schedule);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return schedules;
    }
}
