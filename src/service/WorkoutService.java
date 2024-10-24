package service;

import dao.WorkoutDAO;
import model.Workout;

import java.util.ArrayList;

public class WorkoutService {
    private static WorkoutDAO workoutDAO;
    public static ArrayList<Workout> workoutList;
    
    
    static {
    	  workoutDAO = WorkoutDAO.getInstance();
          workoutList = new ArrayList<>();
           loadAllWorkouts();
    }
    
    public WorkoutService() {
     
    }

    // Tải tất cả workouts vào danh sách
    private static void loadAllWorkouts() {
        workoutList.addAll(workoutDAO.selectAll());
    }

    public int addWorkout(Workout workout) {
        // Kiểm tra tính hợp lệ của workout trước khi thêm
        if (workout == null || workout.getWorkoutId() == null || workout.getWorkoutId().isEmpty()) {
            System.out.println("Workout không hợp lệ.");
            return 0;
        }
        int result = workoutDAO.insert(workout);
        if (result > 0) {
            workoutList.add(workout); // Thêm workout vào danh sách
        }
        return result;
    }

    public int updateWorkout(Workout workout) {
        // Kiểm tra tính hợp lệ của workout trước khi cập nhật
        if (workout == null || workout.getWorkoutId() == null || workout.getWorkoutId().isEmpty()) {
            System.out.println("Workout không hợp lệ.");
            return 0;
        }
        int result = workoutDAO.update(workout);
        if (result > 0) {
            // Cập nhật workout trong danh sách
            int index = findWorkoutIndexById(workout.getWorkoutId());
            if (index != -1) {
                workoutList.set(index, workout); // Cập nhật workout
            }
        }
        return result;
    }

    public int deleteWorkout(String workoutId) {
        int index = findWorkoutIndexById(workoutId);
        if (index != -1) {
            Workout workout = workoutList.remove(index); // Xóa khỏi danh sách
            return workoutDAO.delete(workout);
        } else {
            System.out.println("Workout không tồn tại trong danh sách.");
            return 0;
        }
    }

    public ArrayList<Workout> getAllWorkouts() {
        return new ArrayList<>(workoutList); // Trả về danh sách workouts
    }

    public Workout getWorkoutById(String workoutId) {
        int index = findWorkoutIndexById(workoutId);
        return (index != -1) ? workoutList.get(index) : null; // Lấy workout từ danh sách
    }

    static int findWorkoutIndexById(String workoutId) {
        for (int i = 0; i < workoutList.size(); i++) {
            if (workoutList.get(i).getWorkoutId().equals(workoutId)) {
                return i; // Trả về chỉ số của workout nếu tìm thấy
            }
        }
        return -1; // Trả về -1 nếu không tìm thấy
    }

    public ArrayList<Workout> getWorkoutsByCondition(String condition) {
        // Tùy chọn: Có thể thực hiện truy vấn và cache lại kết quả nếu cần
        return workoutDAO.selectByCondition(condition);
    }
}
