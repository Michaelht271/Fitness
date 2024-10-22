package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Trainee;

public class TraineeDAO implements DAOInterface<Trainee> {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=FITNESS;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "MichaelAn271";

    // Singleton instance
    private static TraineeDAO instance;

    private TraineeDAO() {}

    public static TraineeDAO getInstance() {
        if (instance == null) {
            instance = new TraineeDAO();
        }
        return instance;
    }

    @Override
    public int insert(Trainee t) {
        String sql = "INSERT INTO Trainees (traineeId, password, fullName, gender, role, email, phone, citizenIdentification, height, weight, birthday, addressID) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, t.getTraineeId()); // Sửa lại thứ tự
            preparedStatement.setString(2, t.getPassword());
            preparedStatement.setString(3, t.getFullName());
            preparedStatement.setString(4, t.getGender());
            preparedStatement.setString(5, t.getRole());
            preparedStatement.setString(6, t.getEmail());
            preparedStatement.setString(7, t.getPhone());
            preparedStatement.setString(8, t.getCitizenIdentification());
            preparedStatement.setDouble(9, t.getHeight());
            preparedStatement.setDouble(10, t.getWeight());
            preparedStatement.setDate(11, Date.valueOf(t.getBirthday())); // Đảm bảo chuyển đổi sang java.sql.Date
            preparedStatement.setString(12, t.getAddressId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi chèn dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(Trainee t) {
        String sql = "UPDATE Trainees SET password = ?, fullName = ?, gender = ?, role = ?, email = ?, phone = ?, citizenIdentification = ?, height = ?, weight = ?, birthday = ?, addressID = ? WHERE traineeId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, t.getPassword());
            preparedStatement.setString(2, t.getFullName());
            preparedStatement.setString(3, t.getGender());
            preparedStatement.setString(4, t.getRole());
            preparedStatement.setString(5, t.getEmail());
            preparedStatement.setString(6, t.getPhone());
            preparedStatement.setString(7, t.getCitizenIdentification());
            preparedStatement.setDouble(8, t.getHeight());
            preparedStatement.setDouble(9, t.getWeight());
            preparedStatement.setDate(10, Date.valueOf(t.getBirthday())); // Đảm bảo chuyển đổi sang java.sql.Date
            preparedStatement.setString(11, t.getAddressId());
            preparedStatement.setString(12, t.getTraineeId()); // Sửa lại thứ tự
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(Trainee t) {
        String sql = "DELETE FROM Trainees WHERE traineeId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, t.getTraineeId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public ArrayList<Trainee> selectAll() {
        ArrayList<Trainee> trainees = new ArrayList<>();
        String sql = "SELECT * FROM Trainees";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Trainee trainee = new Trainee();
                trainee.setTraineeId(resultSet.getString("traineeId"));
                trainee.setPassword(resultSet.getString("password"));
                trainee.setFullName(resultSet.getString("fullName"));
                trainee.setGender(resultSet.getString("gender"));
                trainee.setRole(resultSet.getString("role"));
                trainee.setEmail(resultSet.getString("email"));
                trainee.setPhone(resultSet.getString("phone"));
                trainee.setCitizenIdentification(resultSet.getString("citizenIdentification"));
                trainee.setHeight(resultSet.getDouble("height"));
                trainee.setWeight(resultSet.getDouble("weight"));
                trainee.setBirthday(resultSet.getDate("birthday").toLocalDate()); // Chuyển đổi sang LocalDate
                trainee.setAddressId(resultSet.getString("addressID"));
                trainees.add(trainee);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return trainees;
    }

    @Override
    public Trainee selectById(Trainee t) {
        String sql = "SELECT * FROM Trainees WHERE traineeId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, t.getTraineeId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Trainee trainee = new Trainee();
                trainee.setTraineeId(resultSet.getString("traineeId"));
                trainee.setPassword(resultSet.getString("password"));
                trainee.setFullName(resultSet.getString("fullName"));
                trainee.setGender(resultSet.getString("gender"));
                trainee.setRole(resultSet.getString("role"));
                trainee.setEmail(resultSet.getString("email"));
                trainee.setPhone(resultSet.getString("phone"));
                trainee.setCitizenIdentification(resultSet.getString("citizenIdentification"));
                trainee.setHeight(resultSet.getDouble("height"));
                trainee.setWeight(resultSet.getDouble("weight"));
                trainee.setBirthday(resultSet.getDate("birthday").toLocalDate());
                trainee.setAddressId(resultSet.getString("addressID"));
                return trainee;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Trainee> selectByCondition(String condition) {
        ArrayList<Trainee> trainees = new ArrayList<>();
        String sql = "SELECT * FROM Trainees WHERE " + condition;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Trainee trainee = new Trainee();
                trainee.setTraineeId(resultSet.getString("traineeId"));
                trainee.setPassword(resultSet.getString("password"));
                trainee.setFullName(resultSet.getString("fullName"));
                trainee.setGender(resultSet.getString("gender"));
                trainee.setRole(resultSet.getString("role"));
                trainee.setEmail(resultSet.getString("email"));
                trainee.setPhone(resultSet.getString("phone"));
                trainee.setCitizenIdentification(resultSet.getString("citizenIdentification"));
                trainee.setHeight(resultSet.getDouble("height"));
                trainee.setWeight(resultSet.getDouble("weight"));
                trainee.setBirthday(resultSet.getDate("birthday").toLocalDate());
                trainee.setAddressId(resultSet.getString("addressID"));
                trainees.add(trainee);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return trainees;
    }
}
