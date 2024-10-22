package model;

public class Appointment {
	private String traineeID;
	private String scheduleId; // Cần nhất quán tên biến, dùng camelCase cho scheduleId
	private boolean status;

	/**
	 * @param traineeID
	 * @param scheduleId
	 * @param status
	 */
	public Appointment() {}
	
	public Appointment(String traineeID, String scheduleId, boolean status) {
		this.traineeID = traineeID;
		this.scheduleId = scheduleId; // Thêm giá trị cho scheduleId
		this.status = status;
	}

	public String getTraineeID() {
		return traineeID;
	}

	public void setTraineeID(String traineeID) {
		this.traineeID = traineeID; // Gán lại đúng cách vào thuộc tính this.traineeID
	}

	public String getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(String scheduleId) {
		this.scheduleId = scheduleId;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format("|%-10s|%-10s|%-10s|", traineeID, scheduleId, status ? "Confirmed" : "Pending");
	}
}
