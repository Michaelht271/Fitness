package dao;

import model.Feedback;

import java.sql.*;
import java.util.ArrayList;

public class FeedbackDAO implements DAOInterface<Feedback> {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=FITNESS;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "MichaelAn271";

    // Singleton pattern
    private static FeedbackDAO instance;

    private FeedbackDAO() {}

    public static FeedbackDAO getInstance() {
        if (instance == null) {
            instance = new FeedbackDAO();
        }
        return instance;
    }

    @Override
    public int insert(Feedback feedback) {
        String sql = "INSERT INTO Feedbacks (feedbackID, traineeID, courseID, coachID, comment) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, feedback.getFeedbackId());
            preparedStatement.setString(2, feedback.getTrainneeId());
            preparedStatement.setString(3, feedback.getCourseId());
            preparedStatement.setString(4, feedback.getCoachId());
            preparedStatement.setString(5, feedback.getComment());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi chèn dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(Feedback feedback) {
        String sql = "UPDATE Feedbacks SET traineeID = ?, courseID = ?, coachID = ?, comment = ? WHERE feedbackID = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, feedback.getTrainneeId());
            preparedStatement.setString(2, feedback.getCourseId());
            preparedStatement.setString(3, feedback.getCoachId());
            preparedStatement.setString(4, feedback.getComment());
            preparedStatement.setString(5, feedback.getFeedbackId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(Feedback feedback) {
        String sql = "DELETE FROM Feedbacks WHERE feedbackID = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, feedback.getFeedbackId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public ArrayList<Feedback> selectAll() {
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM Feedbacks";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Feedback feedback = new Feedback(
                    resultSet.getString("feedbackID"),
                    resultSet.getString("traineeID"),
                    resultSet.getString("courseID"),
                    resultSet.getString("coachID"),
                    resultSet.getString("comment")
                );
                feedbacks.add(feedback);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return feedbacks;
    }

    @Override
    public Feedback selectById(Feedback feedback) {
        String sql = "SELECT * FROM Feedbacks WHERE feedbackID = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, feedback.getFeedbackId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return new Feedback(
                    resultSet.getString("feedbackID"),
                    resultSet.getString("traineeID"),
                    resultSet.getString("courseID"),
                    resultSet.getString("coachID"),
                    resultSet.getString("comment")
                );
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Feedback> selectByCondition(String condition) {
        ArrayList<Feedback> feedbacks = new ArrayList<>();
        String sql = "SELECT * FROM Feedbacks WHERE " + condition;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Feedback feedback = new Feedback(
                    resultSet.getString("feedbackID"),
                    resultSet.getString("traineeID"),
                    resultSet.getString("courseID"),
                    resultSet.getString("coachID"),
                    resultSet.getString("comment")
                );
                feedbacks.add(feedback);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return feedbacks;
    }
}
