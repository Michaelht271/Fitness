package model;

import java.time.LocalDate;

public class Course {
    private String courseId;
    private String courseName;
    private String courseDescription;
    private String coachID;
    private String courseType;
    private int maxParticipants;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;  // Thêm thuộc tính price
    private int totalSessions; // Thêm thuộc tính tổng số buổi

    /**
     * @param courseId
     * @param courseName
     * @param courseDescription
     * @param coachID
     * @param courseType
     * @param maxParticipants
     * @param startDate
     * @param endDate
     * @param price
     * @param totalSessions
     */
    
    public Course() {}
    public Course(String courseId, String courseName, String courseDescription,
                  String coachID, String courseType, int maxParticipants,
                  LocalDate startDate, LocalDate endDate, double price, int totalSessions) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.coachID = coachID;
        this.courseType = courseType;
        this.maxParticipants = maxParticipants;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;  // Gán giá trị price
        this.totalSessions = totalSessions; // Gán giá trị totalSessions
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCoachID() {
        return coachID;
    }

    public void setCoachID(String coachID) {
        this.coachID = coachID;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public double getPrice() {
        return price;  // Getter cho price
    }

    public void setPrice(double price) {
        this.price = price;  // Setter cho price
    }

    public int getTotalSessions() {
        return totalSessions; // Getter cho totalSessions
    }

    public void setTotalSessions(int totalSessions) {
        this.totalSessions = totalSessions; // Setter cho totalSessions
    }
    public boolean isOngoing(LocalDate currentDate) {
        return (currentDate.isAfter(startDate) || currentDate.isEqual(startDate)) &&
               (currentDate.isBefore(endDate) || currentDate.isEqual(endDate));
    }
    @Override
    public String toString() {
        return String.format("|%-10s|%-20s|%-50s|%-10s|%-10s|%-10d|%-10s|%-10s|%-10.2f|%-15d|", 
            courseId, courseName, courseDescription, coachID, courseType, maxParticipants, 
            startDate, endDate, price, totalSessions);  // Thêm totalSessions vào phương thức toString
    }
}
