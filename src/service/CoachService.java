package service;


import dao.CoachDAO;
import model.Coach;

import java.util.ArrayList;

public class CoachService {
    private static CoachDAO coachDAO;
    public static  ArrayList<Coach> coachList; // Cấu trúc dữ liệu để lưu trữ danh sách huấn luyện viên
     
    static {
    	coachDAO = CoachDAO.getInstance(); // Lấy instance của CoachDAO
        coachList = new ArrayList<>(); // Khởi tạo danh sách huấn luyện viên
        loadCoaches(); // Tải danh sách huấn luyện viên từ cơ sở dữ liệu
    }
    public CoachService() {
      
    }

    // Tải danh sách huấn luyện viên từ cơ sở dữ liệu
    private static void loadCoaches() {
        coachList = coachDAO.selectAll(); // Lấy danh sách từ DAO
    }

    // Thêm huấn luyện viên
    public int addCoach(Coach coach) {
        int result = coachDAO.insert(coach); // Gọi phương thức insert từ CoachDAO
        if (result > 0) {
            coachList.add(coach); // Thêm vào danh sách nếu thêm thành công
        }
        return result;
    }

    // Cập nhật thông tin huấn luyện viên
    public int updateCoach(Coach coach) {
        int result = coachDAO.update(coach); // Gọi phương thức update từ CoachDAO
        if (result > 0) {
            // Cập nhật danh sách huấn luyện viên
            for (int i = 0; i < coachList.size(); i++) {
                if (coachList.get(i).getCoachId().equals(coach.getCoachId())) {
                    coachList.set(i, coach); // Cập nhật thông tin trong danh sách
                    break;
                }
            }
        }
        return result;
    }

    // Xóa huấn luyện viên theo ID
    public int deleteCoach(String coachId) {
        Coach coach = new Coach();
        coach.setCoachId(coachId);
        int result = coachDAO.delete(coach); // Gọi phương thức delete từ CoachDAO
        if (result > 0) {
            coachList.removeIf(c -> c.getCoachId().equals(coachId)); // Xóa khỏi danh sách
        }
        return result;
    }

    // Lấy tất cả huấn luyện viên
    public ArrayList<Coach> getAllCoaches() {
        return coachList; // Trả về danh sách huấn luyện viên đã lưu
    }

    // Tìm huấn luyện viên theo ID
    public Coach getCoachById(String coachId) {
        for (Coach coach : coachList) {
            if (coach.getCoachId().equals(coachId)) {
                return coach; // Trả về huấn luyện viên nếu tìm thấy
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    // Lấy huấn luyện viên theo điều kiện
    public ArrayList<Coach> getCoachesByCondition(String condition) {
        // Nếu cần lấy lại từ cơ sở dữ liệu, bạn có thể gọi lại coachDAO.selectByCondition(condition);
        // Hoặc có thể lọc từ coachList
        ArrayList<Coach> filteredList = new ArrayList<>();
        for (Coach coach : coachList) {
            // Giả sử condition là tên đầy đủ
            if (coach.getFullName().contains(condition)) {
                filteredList.add(coach);
            }
        }
        return filteredList;
    }
}

