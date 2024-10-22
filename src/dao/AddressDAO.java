package dao;

import java.sql.*;
import java.util.ArrayList;
import model.Address;

public class AddressDAO implements DAOInterface<Address> {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=FITNESS;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "MichaelAn271";

    // Singleton pattern
    private static AddressDAO instance;

    private AddressDAO() {}

    public static AddressDAO getInstance() {
        if (instance == null) {
            instance = new AddressDAO();
        }
        return instance;
    }

    @Override
    public int insert(Address address) {
        String sql = "INSERT INTO Addresses (addressID, city, street, houseNumber) VALUES (?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, address.getAddressID());
            preparedStatement.setString(2, address.getCity());
            preparedStatement.setString(3, address.getStreet());
            preparedStatement.setInt(4, address.getHouseNumber());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi chèn dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(Address address) {
        String sql = "UPDATE Addresses SET city = ?, street = ?, houseNumber = ? WHERE addressID = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, address.getCity());
            preparedStatement.setString(2, address.getStreet());
            preparedStatement.setInt(3, address.getHouseNumber());
            preparedStatement.setString(4, address.getAddressID());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(Address address) {
        String sql = "DELETE FROM Addresses WHERE addressID = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, address.getAddressID());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public ArrayList<Address> selectAll() {
        ArrayList<Address> addresses = new ArrayList<>();
        String sql = "SELECT * FROM Addresses";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Address address = new Address(
                    resultSet.getString("addressID"),
                    resultSet.getString("city"),
                    resultSet.getString("street"),
                    resultSet.getInt("houseNumber")
                );
                addresses.add(address);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return addresses;
    }

    @Override
    public Address selectById(Address address) {
        String sql = "SELECT * FROM Addresses WHERE addressID = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, address.getAddressID());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Address(
                    resultSet.getString("addressID"),
                    resultSet.getString("city"),
                    resultSet.getString("street"),
                    resultSet.getInt("houseNumber")
                );
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Address> selectByCondition(String condition) {
        ArrayList<Address> addresses = new ArrayList<>();
        String sql = "SELECT * FROM Addresses WHERE " + condition;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Address address = new Address(
                    resultSet.getString("addressID"),
                    resultSet.getString("city"),
                    resultSet.getString("street"),
                    resultSet.getInt("houseNumber")
                );
                addresses.add(address);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return addresses;
    }
}
