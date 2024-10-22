package dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnector {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=FITNESS;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "MichaelAn271";

    private Connection connection;

    //Bước 1:  Khởi tạo kết nối
    public DatabaseConnector() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Kết nối thành công!");
        } catch (SQLException e) {
            System.out.println("Kết nối thất bại: " + e.getMessage());
        }
    }
     
    // Bước 2: Tạo đối tượng Statement
          
    
    // Bươc 3 Thực hiện truy vấn dữ liệu
    public ResultSet executeQuery(String query) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            return preparedStatement.executeQuery();
        } catch (SQLException e) {
            System.out.println("Lỗi truy vấn: " + e.getMessage());
            return null;
        }
    }

    // Thực hiện câu lệnh INSERT, UPDATE, DELETE
    public int executeUpdate(String query) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi cập nhật: " + e.getMessage());
            return 0;
        }
    }

    // Đóng kết nối
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Kết nối đã được đóng.");
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi đóng kết nối: " + e.getMessage());
        }
    }
}
