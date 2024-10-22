package model;

public class Workout {

    private String workoutId;
    private String workoutName;
    private int duration; // Thời gian tập luyện
    private String level; // Cấp độ
    private String instruction; // Hướng dẫn
    private String equipmentRequired; // Thiết bị cần thiết
   
    /**
     * @param workoutId
     * @param workoutName
     * @param duration
     * @param level
     * @param instruction
     * @param equipmentRequired
     */
    public Workout(String workoutId, String workoutName, int duration, String level, String instruction,
                   String equipmentRequired) {
        this.workoutId = workoutId;
        this.workoutName = workoutName;
        this.duration = duration;
        this.level = level;
        this.instruction = instruction;
        this.equipmentRequired = equipmentRequired;
    }
    public Workout() {
    	
    }
    public String getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(String workoutId) {
        this.workoutId = workoutId;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void setWorkoutName(String workoutName) {
        this.workoutName = workoutName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public String getEquipmentRequired() {
        return equipmentRequired;
    }

    public void setEquipmentRequired(String equipmentRequired) {
        this.equipmentRequired = equipmentRequired;
    }

    @Override
    public String toString() {
        return String.format("|%-10s|%-20s|%-10d|%-10s|%-50s|%-20s|", 
            workoutId, workoutName, duration, level, instruction, equipmentRequired);
    }
}
