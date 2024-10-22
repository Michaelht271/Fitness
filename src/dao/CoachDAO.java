package dao;

import java.sql.*;

import java.util.ArrayList;
import model.Coach;

public class CoachDAO implements DAOInterface<Coach> {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=FITNESS;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "MichaelAn271";

    // Singleton pattern
    private static CoachDAO instance;

    private CoachDAO() {}

    public static CoachDAO getInstance() {
        if (instance == null) {
            instance = new CoachDAO();
        }
        return instance;
    }

    @Override
    public int insert(Coach coach) {
        String sql = "INSERT INTO Coaches (coachID, fullName, gender, role, email, phone, citizenIdentification, height, weight, birthday, password, level, addressId, salary) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, coach.getCoachId());
            preparedStatement.setString(2, coach.getFullName());
            preparedStatement.setString(3, coach.getGender());
            preparedStatement.setString(4, coach.getRole());
            preparedStatement.setString(5, coach.getEmail());
            preparedStatement.setString(6, coach.getPhone());
            preparedStatement.setString(7, coach.getCitizenIdentification());
            preparedStatement.setInt(8, coach.getHeight());
            preparedStatement.setDouble(9, coach.getWeight());
            preparedStatement.setDate(10, Date.valueOf(coach.getBirthday()));
            preparedStatement.setString(11, coach.getPassword());
            preparedStatement.setString(12, coach.getLevel());
            preparedStatement.setString(13, coach.getAddressId());
            preparedStatement.setDouble(14, coach.getSalary());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi chèn dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(Coach coach) {
        String sql = "UPDATE Coaches SET fullName = ?, gender = ?, role = ?, email = ?, phone = ?, citizenIdentification = ?, height = ?, weight = ?, birthday = ?, password = ?, level = ?, addressId = ?, salary = ? WHERE coachId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, coach.getFullName());
            preparedStatement.setString(2, coach.getGender());
            preparedStatement.setString(3, coach.getRole());
            preparedStatement.setString(4, coach.getEmail());
            preparedStatement.setString(5, coach.getPhone());
            preparedStatement.setString(6, coach.getCitizenIdentification());
            preparedStatement.setInt(7, coach.getHeight());
            preparedStatement.setDouble(8, coach.getWeight());
            preparedStatement.setDate(9, Date.valueOf(coach.getBirthday()));
            preparedStatement.setString(10, coach.getPassword());
            preparedStatement.setString(11, coach.getLevel());
            preparedStatement.setString(12, coach.getAddressId());
            preparedStatement.setDouble(13, coach.getSalary());
            preparedStatement.setString(14, coach.getCoachId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(Coach coach) {
        String sql = "DELETE FROM Coaches WHERE coachId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, coach.getCoachId());
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public ArrayList<Coach> selectAll() {
        ArrayList<Coach> coaches = new ArrayList<>();
        String sql = "SELECT * FROM Coaches";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Coach coach = new Coach();
                coach.setCoachId(resultSet.getString("coachId"));
                coach.setFullName(resultSet.getString("fullName"));
                coach.setGender(resultSet.getString("gender"));
                coach.setRole(resultSet.getString("role"));
                coach.setEmail(resultSet.getString("email"));
                coach.setPhone(resultSet.getString("phone"));
                coach.setCitizenIdentification(resultSet.getString("citizenIdentification"));
                coach.setHeight(resultSet.getInt("height"));
                coach.setWeight(resultSet.getDouble("weight"));
                coach.setBirthday(resultSet.getDate("birthday").toLocalDate());
                coach.setPassword(resultSet.getString("password"));
                coach.setLevel(resultSet.getString("level"));
                coach.setAddressId(resultSet.getString("addressId"));
                coach.setSalary(resultSet.getDouble("salary"));
                coaches.add(coach);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return coaches;
    }

    @Override
    public Coach selectById(Coach coach) {
        String sql = "SELECT * FROM Coaches WHERE coachId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, coach.getCoachId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                coach.setCoachId(resultSet.getString("coachId"));
                coach.setFullName(resultSet.getString("fullName"));
                coach.setGender(resultSet.getString("gender"));
                coach.setRole(resultSet.getString("role"));
                coach.setEmail(resultSet.getString("email"));
                coach.setPhone(resultSet.getString("phone"));
                coach.setCitizenIdentification(resultSet.getString("citizenIdentification"));
                coach.setHeight(resultSet.getInt("height"));
                coach.setWeight(resultSet.getDouble("weight"));
                coach.setBirthday(resultSet.getDate("birthday").toLocalDate());
                coach.setPassword(resultSet.getString("password"));
                coach.setLevel(resultSet.getString("level"));
                coach.setAddressId(resultSet.getString("addressId"));
                coach.setSalary(resultSet.getDouble("salary"));
                return coach;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Coach> selectByCondition(String condition) {
        ArrayList<Coach> coaches = new ArrayList<>();
        String sql = "SELECT * FROM Coaches WHERE " + condition;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Coach coach = new Coach();
                coach.setCoachId(resultSet.getString("coachId"));
                coach.setFullName(resultSet.getString("fullName"));
                coach.setGender(resultSet.getString("gender"));
                coach.setRole(resultSet.getString("role"));
                coach.setEmail(resultSet.getString("email"));
                coach.setPhone(resultSet.getString("phone"));
                coach.setCitizenIdentification(resultSet.getString("citizenIdentification"));
                coach.setHeight(resultSet.getInt("height"));
                coach.setWeight(resultSet.getDouble("weight"));
                coach.setBirthday(resultSet.getDate("birthday").toLocalDate());
                coach.setPassword(resultSet.getString("password"));
                coach.setLevel(resultSet.getString("level"));
                coach.setAddressId(resultSet.getString("addressId"));
                coach.setSalary(resultSet.getDouble("salary"));
                coaches.add(coach);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return coaches;
    }
}
