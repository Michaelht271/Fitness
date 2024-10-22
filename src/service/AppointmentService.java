package service;

import dao.AppointmentDAO;
import model.Appointment;

import java.util.ArrayList;
import java.util.List;

public class AppointmentService {
    private static AppointmentDAO appointmentDAO = new AppointmentDAO();
    private static  List<Appointment> appointmentCache; // Cache để lưu trữ các cuộc hẹn
    
    static {
    	 try {
			appointmentDAO = AppointmentDAO.getInstance();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         appointmentCache = new ArrayList<>();
         loadAppointments(); 
    }
    public AppointmentService() {
      
    }
  
    // Tải danh sách cuộc hẹn từ cơ sở dữ liệu vào bộ nhớ cache
    private static void loadAppointments() {
        appointmentCache = appointmentDAO.selectAll();
    }

    public int addAppointment(Appointment appointment) {
        int result = appointmentDAO.insert(appointment);
        if (result > 0) {
            appointmentCache.add(appointment); // Cập nhật cache nếu thêm thành công
        }
        return result;
    }

    public int updateAppointment(Appointment appointment) {
        int result = appointmentDAO.update(appointment);
        if (result > 0) {
            // Cập nhật cuộc hẹn trong cache
            for (int i = 0; i < appointmentCache.size(); i++) {
                if (appointmentCache.get(i).getTraineeID().equals(appointment.getTraineeID()) &&
                    appointmentCache.get(i).getScheduleId().equals(appointment.getScheduleId())) {
                    appointmentCache.set(i, appointment);
                    break;
                }
            }
        }
        return result;
    }

    public int deleteAppointment(String traineeId, String scheduleId) {
        Appointment appointment = new Appointment();
        appointment.setTraineeID(traineeId);
        appointment.setScheduleId(scheduleId);
        int result = appointmentDAO.delete(appointment);
        if (result > 0) {
            // Xóa cuộc hẹn khỏi cache
            appointmentCache.removeIf(a -> a.getTraineeID().equals(traineeId) && a.getScheduleId().equals(scheduleId));
        }
        return result;
    }

    public List<Appointment> getAllAppointments() {
        return new ArrayList<>(appointmentCache); // Trả về một bản sao của danh sách cuộc hẹn
    }

    public Appointment getAppointmentById(String traineeId, String scheduleId) {
        // Tìm kiếm cuộc hẹn trong cache
        for (Appointment appointment : appointmentCache) {
            if (appointment.getTraineeID().equals(traineeId) && appointment.getScheduleId().equals(scheduleId)) {
                return appointment;
            }
        }
        return null; // Nếu không tìm thấy
    }

    public List<Appointment> getAppointmentsByCondition(String condition) {
        // Lọc cuộc hẹn theo điều kiện trong cache
        List<Appointment> filteredAppointments = new ArrayList<>();
        for (Appointment appointment : appointmentCache) {
            // Thêm logic lọc ở đây, ví dụ:
            if (appointment.getTraineeID().contains(condition) || appointment.getScheduleId().contains(condition)) {
                filteredAppointments.add(appointment);
            }
        }
        return filteredAppointments;
    }
}
