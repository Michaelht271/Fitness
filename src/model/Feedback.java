package model;

public class Feedback {
   private String feedbackId;
   private String traineeId;
   private String courseId;
   private String coachId;
   private String comment;
/**
 * @param feedbackId
 * @param trainneeId
 * @param courseId
 * @param coachId
 * @param comment
 */
public Feedback(String feedbackId, String trainneeId, String courseId, String coachId, String comment) {
	super();
	this.feedbackId = feedbackId;
	this.traineeId = trainneeId;
	this.courseId = courseId;
	this.coachId = coachId;
	this.comment = comment;
}
public String getFeedbackId() {
	return feedbackId;
}
public void setFeedbackId(String feedbackId) {
	this.feedbackId = feedbackId;
}
public String getTraineeId() {
	return traineeId;
}
public void setTraineeId(String trainneeId) {
	this.traineeId = trainneeId;
}
public String getCourseId() {
	return courseId;
}
public void setCourseId(String courseId) {
	this.courseId = courseId;
}
public String getCoachId() {
	return coachId;
}
public void setCoachId(String coachId) {
	this.coachId = coachId;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
@Override
public String toString() {
    return String.format("|%-10s|%-12s|%-10s|%-10s|%-20s|", 
                         feedbackId, traineeId, courseId, coachId, comment);
}

   
}
