package service;

import java.util.ArrayList;

import dao.TraineeDAO;
import model.Trainee;

public class TraineeService {
    private static TraineeDAO traineeDAO;
   public static ArrayList<Trainee> traineeList; // Cấu trúc dữ liệu để lưu trữ danh sách người học
  
   static {
	   traineeDAO = TraineeDAO.getInstance(); // Lấy instance của TraineeDAO
       traineeList = new ArrayList<>(); // Khởi tạo danh sách người học
       loadTrainees(); // Tải danh sách người học từ cơ sở dữ liệu
   }
   
    public TraineeService() {
       traineeDAO = TraineeDAO.getInstance(); // Lấy instance của TraineeDAO
        traineeList = new ArrayList<>(); // Khởi tạo danh sách người học
        loadTrainees(); // Tải danh sách người học từ cơ sở dữ liệu
    }

    // Tải danh sách người học từ cơ sở dữ liệu
    private static void loadTrainees() {
        traineeList = traineeDAO.selectAll(); // Lấy danh sách từ DAO
    }

    // Thêm người học
    public int addTrainee(Trainee trainee) {
        int result = traineeDAO.insert(trainee); // Gọi phương thức insert từ TraineeDAO
        if (result > 0) {
            traineeList.add(trainee); // Thêm vào danh sách nếu thêm thành công
        }
        return result;
    }

    // Cập nhật thông tin người học
    public int updateTrainee(Trainee trainee) {
        int result = traineeDAO.update(trainee); // Gọi phương thức update từ TraineeDAO
        if (result > 0) {
            // Cập nhật danh sách người học
            for (int i = 0; i < traineeList.size(); i++) {
                if (traineeList.get(i).getTraineeId().equals(trainee.getTraineeId())) {
                    traineeList.set(i, trainee); // Cập nhật thông tin trong danh sách
                    break;
                }
            }
        }
        return result;
    }

    // Xóa người học theo ID
    public int deleteTrainee(String traineeId) {
        Trainee trainee = new Trainee();
        trainee.setTraineeId(traineeId);
        int result = traineeDAO.delete(trainee); // Gọi phương thức delete từ TraineeDAO
        if (result > 0) {
            traineeList.removeIf(t -> t.getTraineeId().equals(traineeId)); // Xóa khỏi danh sách
        }
        return result;
    }

    // Lấy tất cả người học
    public ArrayList<Trainee> getAllTrainees() {
        return traineeList; // Trả về danh sách người học đã lưu
    }

    // Tìm người học theo ID
    public Trainee getTraineeById(String traineeId) {
        for (Trainee trainee : traineeList) {
            if (trainee.getTraineeId().equals(traineeId)) {
                return trainee; // Trả về người học nếu tìm thấy
            }
        }
        return null; // Trả về null nếu không tìm thấy
    }

    // Lấy người học theo điều kiện
    public ArrayList<Trainee> getTraineesByCondition(String condition) {
        // Nếu cần lấy lại từ cơ sở dữ liệu, bạn có thể gọi lại traineeDAO.selectByCondition(condition);
        // Hoặc có thể lọc từ traineeList
        ArrayList<Trainee> filteredList = new ArrayList<>();
        for (Trainee trainee : traineeList) {
            // Giả sử condition là tên đầy đủ
            if (trainee.getFullName().contains(condition)) {
                filteredList.add(trainee);
            }
        }
        return filteredList;
    }
}
