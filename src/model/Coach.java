package model;

import java.time.LocalDate;

public class Coach extends Person {
    private String coachId;        // ID của huấn luyện viên
    private String password;        // Mật khẩu của huấn luyện viên
    private String level;           // Cấp độ của huấn luyện viên
    private String addressId;       // ID địa chỉ của huấn luyện viên
    private double salary;          // Mức lương của huấn luyện viên
    public static int totalCoach;   // Tổng số huấn luyện viên

    /**
     * Constructor của Coach.
     * @param fullName
     * @param gender
     * @param role
     * @param email
     * @param phone
     * @param citizenIdentification
     * @param height
     * @param weight
     * @param birthday
     * @param coachId
     * @param password
     * @param level
     * @param addressId
     * @param salary
     */
    
    public Coach() {}
    public Coach(String fullName, String gender, String role, String email, String phone, String citizenIdentification,
                 int height, double weight, LocalDate birthday, String coachId, String password, String level, String addressId, double salary) {
        super(fullName, gender, role, email, phone, citizenIdentification, height, weight, birthday); // Gọi constructor của Person
        this.coachId = coachId;
        this.password = password;
        this.level = level;
        this.addressId = addressId; // Gán địa chỉ ID
        this.salary = salary; // Gán mức lương
        totalCoach++; // Tăng số lượng huấn luyện viên khi khởi tạo
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getAddressId() {
        return addressId; // Trả về địa chỉ ID
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId; // Cập nhật địa chỉ ID
    }

    public double getSalary() {
        return salary; // Trả về mức lương
    }

    public void setSalary(double salary) {
        this.salary = salary; // Cập nhật mức lương
    }

    public static int getTotalCoach() {
        return totalCoach; // Trả về tổng số huấn luyện viên
    }

    @Override
    public String toString() {
        return String.format(
            "| %-20s | %-10s | %-10s | %-30s | %-15s | %-20s | %-10s | %-15s | %-10.2f |",
            getFullName(),               // Căn trái, độ rộng 20
            getGender(),                 // Căn trái, độ rộng 10
            getRole(),                   // Căn trái, độ rộng 10
            getEmail(),                  // Căn trái, độ rộng 30
            getPhone(),                  // Căn trái, độ rộng 15
            getCitizenIdentification(),  // Căn trái, độ rộng 20
            coachId,                     // Căn trái, độ rộng 10
            level,                       // Căn trái, độ rộng 15
            addressId,                   // Hiển thị địa chỉ ID
            salary                        // Mức lương, hiển thị với 2 chữ số thập phân
        );
    }
}
