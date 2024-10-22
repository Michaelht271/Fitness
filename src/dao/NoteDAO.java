package dao;

import java.sql.*;

import java.util.ArrayList;
import model.Note;

public class NoteDAO implements DAOInterface<Note> {
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=FITNESS;encrypt=true;trustServerCertificate=true";
    private static final String USER = "sa";
    private static final String PASSWORD = "MichaelAn271";

    // Singleton pattern
    private static NoteDAO instance;

    private NoteDAO() {}

    public static NoteDAO getInstance() {
        if (instance == null) {
            instance = new NoteDAO();
        }
        return instance;
    }

    @Override
    public int insert(Note note) {
        String sql = "INSERT INTO Notes (noteId, traineeId, courseId, workoutId, content, createDate) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, note.getNoteId());
            preparedStatement.setString(2, note.getTraineeId());
            preparedStatement.setString(3, note.getCourseId());
            preparedStatement.setString(4, note.getWorkoutId());
            preparedStatement.setString(5, note.getContent());
            preparedStatement.setTimestamp(6, Timestamp.valueOf(note.getCreateDate()));

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi chèn dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int update(Note note) {
        String sql = "UPDATE Notes SET TraineeId = ?, courseId = ?, workoutId = ?, content = ?, createDate = ? WHERE noteId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, note.getTraineeId());
            preparedStatement.setString(2, note.getCourseId());
            preparedStatement.setString(3, note.getWorkoutId());
            preparedStatement.setString(4, note.getContent());
            preparedStatement.setTimestamp(5, Timestamp.valueOf(note.getCreateDate()));
            preparedStatement.setString(6, note.getNoteId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi cập nhật dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public int delete(Note note) {
        String sql = "DELETE FROM Notes WHERE noteId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, note.getNoteId());

            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Lỗi khi xóa dữ liệu: " + e.getMessage());
            return 0;
        }
    }

    @Override
    public ArrayList<Note> selectAll() {
        ArrayList<Note> notes = new ArrayList<>();
        String sql = "SELECT * FROM Notes";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Note note = new Note();
                note.setNoteId(resultSet.getString("noteId"));
                note.setTraineeId(resultSet.getString("TraineeId"));
                note.setCourseId(resultSet.getString("courseId"));
                note.setWorkoutId(resultSet.getString("workoutId"));
                note.setContent(resultSet.getString("content"));
                note.setCreateDate(resultSet.getTimestamp("createDate").toLocalDateTime());
                notes.add(note);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return notes;
    }

    @Override
    public Note selectById(Note note) {
        String sql = "SELECT * FROM Notes WHERE noteId = ?";
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, note.getNoteId());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                note.setNoteId(resultSet.getString("noteId"));
                note.setTraineeId(resultSet.getString("TraineeId"));
                note.setCourseId(resultSet.getString("courseId"));
                note.setWorkoutId(resultSet.getString("workoutId"));
                note.setContent(resultSet.getString("content"));
                note.setCreateDate(resultSet.getTimestamp("createDate").toLocalDateTime());
                return note;
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return null;
    }

    @Override
    public ArrayList<Note> selectByCondition(String condition) {
        ArrayList<Note> notes = new ArrayList<>();
        String sql = "SELECT * FROM Notes WHERE " + condition;
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Note note = new Note();
                note.setNoteId(resultSet.getString("noteId"));
                note.setTraineeId(resultSet.getString("TraineeId"));
                note.setCourseId(resultSet.getString("courseId"));
                note.setWorkoutId(resultSet.getString("workoutId"));
                note.setContent(resultSet.getString("content"));
                note.setCreateDate(resultSet.getTimestamp("createDate").toLocalDateTime());
                notes.add(note);
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi truy vấn dữ liệu: " + e.getMessage());
        }
        return notes;
    }
}
