package model;

import java.time.LocalDate;

public class EnrollCourse {
    private String traineeId;      // ID của người học
    private String courseId;       // ID của khóa học
    private LocalDate enrollmentDate; // Ngày đăng ký

    /**
     * Constructor mặc định.
     */
    public EnrollCourse() {
        super();
    }

    /**
     * Constructor với tham số.
     * @param traineeId ID của người học
     * @param courseId ID của khóa học
     * @param enrollmentDate Ngày đăng ký
     */
    public EnrollCourse(String traineeId, String courseId, LocalDate enrollmentDate) {
        super();
        this.traineeId = traineeId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
    }

    public String getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(String traineeId) {
        this.traineeId = traineeId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    @Override
    public String toString() {
        return String.format(
            "| %-15s | %-15s | %-15s |",
            traineeId,                // Căn trái, độ rộng 15
            courseId,                 // Căn trái, độ rộng 15
            enrollmentDate.toString() // Căn trái, độ rộng 15 (định dạng ngày có thể cần điều chỉnh nếu cần)
        );
    }
}
