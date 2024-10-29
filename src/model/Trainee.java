package model;

import java.time.LocalDate;

public class Trainee extends Person {
    private String traineeId; // ID của người học
    private String password;   // Mật khẩu của người học
    private String level;      // Cấp độ của người học
    private String addressId;  // Địa chỉ của người học

    public static int totalTrainee; // Tổng số người học

    /**
     * Constructor của Trainee.
     * @param fullName
     * @param gender
     * @param role
     * @param email
     * @param phone
     * @param birthday
     * @param citizenIdentification
     * @param height
     * @param weight
     * @param traineeId
     * @param password
     * @param level
     * @param addressId
     */
    public Trainee() {}
   
    public Trainee(String fullName, String gender, String role, String email, String phone, LocalDate birthday,
                   String citizenIdentification, int height, double weight, String traineeId, 
                   String password, String level, String addressId) {
        super(fullName, gender, role, email, phone, citizenIdentification, height, weight, birthday);
        this.traineeId = traineeId;
        this.password = password;
        this.level = level;
        this.addressId = addressId;
        totalTrainee++; // Tăng số lượng trainee khi khởi tạo
    }

    // Getter và setter cho traineeId
    public String getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(String traineeId) {
        this.traineeId = traineeId;
    }

    // Getter và setter cho password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter và setter cho level
    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    // Getter và setter cho addressId
    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    // Getter cho tổng số trainee
    public static int getTotalTrainee() {
        return totalTrainee; // Trả về tổng số người học
    }

    @Override
    public String toString() {
        return String.format(
            "| %-15s | %-15s | %-10s | %-10s | %-50s | %-15s | %-15s | %-20s | %-10d | %-10.2f | %-15s |",
            getTraineeId(),                                   // ID người học
            getFullName(),                               // Tên đầy đủ
            getGender(),                                 // Giới tính
            getRole(),                                   // Vai trò
            getEmail(),                                       // Email
            getPhone(),                                  // Số điện thoại
            getBirthday() != null ? getBirthday().toString() : "N/A", // Ngày sinh
            getCitizenIdentification(),                  // CMND/CCCD
            getHeight(),                                      // Chiều cao
            getWeight(),                                      // Cân nặng
            getLevel()                                    // Cấp độ người học
                                
        );
    }

}
