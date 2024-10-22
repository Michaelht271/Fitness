package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Appointment;

public class AppointmentDAO implements DAOInterface<Appointment> {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=FITNESS;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "MichaelAn271";

    // Singleton pattern
    private static AppointmentDAO instance;

   public AppointmentDAO() {}

    public static AppointmentDAO getInstance() {
        if (instance == null) {
            instance = new AppointmentDAO();
        }
        return instance;
    }

    @Override
    public int insert(Appointment appointment) {
        String sql = "INSERT INTO Appointments (traineeID, scheduleID, status) VALUES (?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, appointment.getTraineeID());
            preparedStatement.setString(2, appointment.getScheduleId());
            preparedStatement.setBoolean(3, appointment.isStatus());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi chèn dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(Appointment appointment) {
        String sql = "UPDATE Appointments SET status = ? WHERE traineeID = ? AND scheduleID = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setBoolean(1, appointment.isStatus());
            preparedStatement.setString(2, appointment.getTraineeID());
            preparedStatement.setString(3, appointment.getScheduleId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(Appointment appointment) {
        String sql = "DELETE FROM Appointments WHERE traineeID = ? AND scheduleID = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, appointment.getTraineeID());
            preparedStatement.setString(2, appointment.getScheduleId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public ArrayList<Appointment> selectAll() {
        ArrayList<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM Appointments";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setTraineeID(resultSet.getString("traineeID"));
                appointment.setScheduleId(resultSet.getString("scheduleID"));
                appointment.setStatus(resultSet.getBoolean("status"));
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return appointments;
    }

    @Override
    public Appointment selectById(Appointment appointment) {
        String sql = "SELECT * FROM Appointments WHERE traineeID = ? AND scheduleID = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, appointment.getTraineeID());
            preparedStatement.setString(2, appointment.getScheduleId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                appointment.setTraineeID(resultSet.getString("traineeID"));
                appointment.setScheduleId(resultSet.getString("scheduleID"));
                appointment.setStatus(resultSet.getBoolean("status"));
                return appointment;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Appointment> selectByCondition(String condition) {
        ArrayList<Appointment> appointments = new ArrayList<>();
        String sql = "SELECT * FROM Appointments WHERE " + condition;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Appointment appointment = new Appointment();
                appointment.setTraineeID(resultSet.getString("traineeID"));
                appointment.setScheduleId(resultSet.getString("scheduleID"));
                appointment.setStatus(resultSet.getBoolean("status"));
                appointments.add(appointment);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return appointments;
    }
}
