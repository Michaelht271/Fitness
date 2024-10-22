package dataBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DatabaseConnector dbConnector = new DatabaseConnector();

        // Ví dụ: Thực hiện truy vấn để lấy dữ liệu
        String selectQuery = "SELECT * FROM Trainee"; // Thay đổi theo bảng bạn cần
        ResultSet resultSet = dbConnector.executeQuery(selectQuery);

        try {
            while (resultSet != null && resultSet.next()) {
                // Xử lý dữ liệu
                System.out.println("Trainee ID: " + resultSet.getString("traineeID")); // Thay đổi theo cột bạn cần
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            dbConnector.close(); // Đóng kết nối sau khi hoàn thành
        }
    }
}

