package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Schedule {
	  private String scheduleId;
    private String courseID;
  
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
  
   

    /**
     * @param courseID
     * @param scheduleId
     * @param date
     * @param startTime
     * @param endTime
     * @param dateWorkouts
     * @param appointment
     */
    
    public Schedule () {
    	
    }
    public Schedule(String scheduleId,String courseID,  LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.courseID = courseID;
        this.scheduleId = scheduleId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
       
    }

    

    public String getCourseID() {
        return courseID;
    }

    public void setCourseID(String courseID) {
        this.courseID = courseID;
    }

    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }


    @Override
    public String toString() {
        return String.format("|%-10s|%-12s|%-10s|%-10s|%-10s|", 
            courseID, date, startTime, endTime);
    }
}
