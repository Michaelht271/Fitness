package model;

import java.util.LinkedHashMap;
import java.util.ArrayList;

public class TakeWorkout {
    private String courseId; // ID của khóa học
    private String workoutId;
    
	/**
	 * 
	 */
	public TakeWorkout() {
		super();
	}

	/**
	 * @param courseId
	 * @param workoutId
	 */
	public TakeWorkout(String courseId, String workoutId) {
		super();
		this.courseId = courseId;
		this.workoutId = workoutId;
	}

	public String getCourseId() {
		return courseId;
	}

	public void setCourseId(String courseId) {
		this.courseId = courseId;
	}

	public String getWorkoutId() {
		return workoutId;
	}

	public void setWorkoutId(String workoutId) {
		this.workoutId = workoutId;
	}

	@Override
	public String toString() {
		return String.format("%-10s|%-10s|", courseId, workoutId);
	}
   
}
