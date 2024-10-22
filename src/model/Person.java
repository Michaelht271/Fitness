package model;

import java.time.LocalDate;

public abstract class Person {
    private String fullName;
    private String gender;
    private String role;
    private String email;
    private String phone;
    private String citizenIdentification;
    private int height;
    protected double weight; // Cân nặng
    private LocalDate birthday; // Thêm thuộc tính birthday
    public Person() {
	  
      }
    public Person(String fullName, String gender, String role, String email, String phone,
                  String citizenIdentification, int height, double weight, LocalDate birthday) {
        super();
        this.fullName = fullName;
        this.gender = gender;
        this.role = role;
        this.email = email;
        this.phone = phone;
        this.citizenIdentification = citizenIdentification;
        this.height = height;
        this.weight = weight;
        this.birthday = birthday; // Gán giá trị birthday
    }

    // Các getter và setter

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCitizenIdentification() {
        return citizenIdentification;
    }

    public void setCitizenIdentification(String citizenIdentification) {
        this.citizenIdentification = citizenIdentification;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(double d) {
        this.height = (int) d;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public LocalDate getBirthday() {
        return birthday;  // Getter cho birthday
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;  // Setter cho birthday
    }

    // Phương thức toString sử dụng String.format để hiển thị thông tin
    @Override
    public String toString() {
        return String.format(
            "|%-30s|%-10s|%-10s|%-30s|%-15s|%-20s|%-5d|%-6.2f|%-10s|\n",
            fullName,                          // Căn trái, độ rộng 30
            gender,                            // Căn trái, độ rộng 10
            role,                              // Căn trái, độ rộng 10
            email,                             // Căn trái, độ rộng 30
            phone,                             // Căn trái, độ rộng 15
            citizenIdentification,             // Căn trái, độ rộng 20
            height,                            // Căn trái, độ rộng 5 (chiều cao)
            weight,                            // Căn trái, độ rộng 6 với 2 chữ số sau dấu thập phân (cân nặng)
            birthday                           // Căn trái, độ rộng 10 (ngày sinh)
        );
    }
}
