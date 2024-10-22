package model;

import java.time.LocalDateTime;

public class Note {
	
	private String noteId;
	private String traineeId;
	private String courseId;
	private String workoutId;
	private String content;
	private LocalDateTime createDate;
	/**
	 * @param noteId
	 * @param traineeId
	 * @param courseId
	 * @param workoutId
	 * @param content
	 * @param createDate
	 */
	
	public Note() {
		
	}
	public Note(String noteId, String traineeId, String courseId, String workoutId, String content) {
		super();
		this.noteId = noteId;
		this.traineeId = traineeId;
		this.courseId = courseId;
		this.workoutId = workoutId;
		this.content = content;
		this.createDate = LocalDateTime.now();
	}
	public String getNoteId() {
		return noteId;
	}
	public void setNoteId(String noteId) {
		this.noteId = noteId;
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
	public String getWorkoutId() {
		return workoutId;
	}
	public void setWorkoutId(String workoutId) {
		this.workoutId = workoutId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public LocalDateTime getCreateDate() {
		return createDate;
	}
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}
	
	@Override
	public String toString() {
	    return String.format("|%-10s|%-10s|%-10s|%-10s|%-50s|%-20s|", 
	        noteId, traineeId, courseId, workoutId, content, createDate);
	}

	

}
