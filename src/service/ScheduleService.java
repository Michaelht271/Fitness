package service;

import dao.ScheduleDAO;
import model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class ScheduleService {
    private static ScheduleDAO scheduleDAO;
   public static List<Schedule> scheduleCache; // Cache để lưu trữ các lịch tập
   static {
	   scheduleDAO = ScheduleDAO.getInstance();
       scheduleCache = new ArrayList<>();
       loadSchedules();
   }
    public ScheduleService() {
         // Tải tất cả các lịch tập từ cơ sở dữ liệu vào bộ nhớ cache khi khởi tạo
    }

    // Tải danh sách lịch tập từ cơ sở dữ liệu vào bộ nhớ cache
    private  static void loadSchedules() {
        scheduleCache = scheduleDAO.selectAll();
    }

    public int addSchedule(Schedule schedule) {
        int result = scheduleDAO.insert(schedule);
        if (result > 0) {
            scheduleCache.add(schedule); // Cập nhật cache nếu thêm thành công
        }
        return result;
    }

    public int updateSchedule(Schedule schedule) {
        int result = scheduleDAO.update(schedule);
        if (result > 0) {
            // Cập nhật lịch tập trong cache
            for (int i = 0; i < scheduleCache.size(); i++) {
                if (scheduleCache.get(i).getScheduleId().equals(schedule.getScheduleId())) {
                    scheduleCache.set(i, schedule);
                    break;
                }
            }
        }
        return result;
    }

    public int deleteSchedule(String scheduleId) {
        Schedule schedule = new Schedule();
        schedule.setScheduleId(scheduleId);
        int result = scheduleDAO.delete(schedule);
        if (result > 0) {
            // Xóa lịch tập khỏi cache
            scheduleCache.removeIf(s -> s.getScheduleId().equals(scheduleId));
        }
        return result;
    }

    public List<Schedule> getAllSchedules() {
        return new ArrayList<>(scheduleCache); // Trả về một bản sao của danh sách lịch tập
    }

    public Schedule getScheduleById(String scheduleId) {
        // Tìm lịch tập trong cache
        for (Schedule schedule : scheduleCache) {
            if (schedule.getScheduleId().equals(scheduleId)) {
                return schedule;
            }
        }
        return null; // Nếu không tìm thấy
    }

    public List<Schedule> getSchedulesByCondition(String condition) {
        // Lọc lịch tập theo điều kiện trong cache
        List<Schedule> filteredSchedules = new ArrayList<>();
        for (Schedule schedule : scheduleCache) {
            // Thêm logic lọc ở đây, ví dụ:
            if (schedule.getCourseID().contains(condition) || schedule.getDate().toString().contains(condition)) {
                filteredSchedules.add(schedule);
            }
        }
        return filteredSchedules;
    }
}
