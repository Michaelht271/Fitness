package service;

import dao.FeedbackDAO;
import model.Feedback;

import java.util.ArrayList;
import java.util.List;

public class FeedbackService {
    private static FeedbackDAO feedbackDAO;
    public static List<Feedback> feedbackCache; // Cache để lưu trữ các feedback

    static {
        feedbackDAO = FeedbackDAO.getInstance();
        feedbackCache = new ArrayList<>();
        loadFeedbacks();
    }

    public FeedbackService() {

    }

    // Tải danh sách feedback từ cơ sở dữ liệu vào bộ nhớ cache
    private static void loadFeedbacks() {
        feedbackCache = feedbackDAO.selectAll();
    }

    public int addFeedback(Feedback feedback) {
        int result = feedbackDAO.insert(feedback);
        if (result > 0) {
            feedbackCache.add(feedback); // Cập nhật cache nếu thêm thành công
        }
        return result;
    }

    public int updateFeedback(Feedback feedback) {
        int result = feedbackDAO.update(feedback);
        if (result > 0) {
            // Cập nhật feedback trong cache
            for (int i = 0; i < feedbackCache.size(); i++) {
                if (feedbackCache.get(i).getFeedbackId().equals(feedback.getFeedbackId())) {
                    feedbackCache.set(i, feedback);
                    break;
                }
            }
        }
        return result;
    }

    public int deleteFeedback(String feedbackId) {
        Feedback feedback = new Feedback();
        feedback.setFeedbackId(feedbackId);
        int result = feedbackDAO.delete(feedback);
        if (result > 0) {
            // Xóa feedback khỏi cache
            feedbackCache.removeIf(f -> f.getFeedbackId().equals(feedbackId));
        }
        return result;
    }

    public List<Feedback> getAllFeedbacks() {
        return new ArrayList<>(feedbackCache); // Trả về một bản sao của danh sách feedback
    }

    public Feedback getFeedbackById(String feedbackId) {
        // Tìm kiếm feedback trong cache
        for (Feedback feedback : feedbackCache) {
            if (feedback.getFeedbackId().equals(feedbackId)) {
                return feedback;
            }
        }
        return null; // Nếu không tìm thấy
    }

    public List<Feedback> getFeedbacksByCondition(String condition) {
        // Lọc feedback theo điều kiện trong cache
        List<Feedback> filteredFeedbacks = new ArrayList<>();
        for (Feedback feedback : feedbackCache) {
            // Thêm logic lọc ở đây, ví dụ:
            if (feedback.getComment().contains(condition)) {
                filteredFeedbacks.add(feedback);
            }
        }
        return filteredFeedbacks;
    }

   

}
