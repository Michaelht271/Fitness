package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.time.DayOfWeek;

import java.time.temporal.TemporalAdjusters;
import model.Coach;
import model.Course;
import model.EnrollCourse;
import model.Feedback;
import model.Note;
import model.Schedule;
import model.Trainee;
import service.AddressService;
import service.CoachService;
import service.CourseService;
import service.EnrollCourseService;
import service.FeedbackService;
import service.NoteService;
import service.ScheduleService;
import service.TraineeService;
import utils.Utils;

public class TraineeController {
	ScheduleService scheduleService = new ScheduleService();
	TraineeService traineeService = new TraineeService();
	CourseService courseService = new CourseService();
	CoachService coachService = new CoachService();
	FeedbackService feedbackService = new FeedbackService();
    EnrollCourseService enrollCourseService = new EnrollCourseService();
    AddressService addressService = new AddressService();
    CourseController courseController = new CourseController();
	
	/**
	 * 
	 */
	public TraineeController() {
		super();
	}


	public String getTrainee(String traineeID) {
	    Trainee trainee = traineeService.getTraineeById(traineeID);
	    if (trainee == null) {
	        return "Trainee not found.";
	    }
	    String address = addressService.getAddressById(trainee.getAddressId()).toString();
	    return trainee.toString()  + address.toString();
	}

	
	// Lấy ra danh sách course Enroll
	public ArrayList<Course> getCoursesEnrolled(String traineeId) {
		ArrayList<Course> enrolledCourses = new ArrayList<>();

		if (traineeId == null || traineeId.isEmpty()) {
			// Return empty list or handle invalid trainee ID scenario
			return enrolledCourses;
		}

		for (EnrollCourse enrollCourse : EnrollCourseService.enrollCourseCache) {
			if (enrollCourse != null && traineeId.equals(enrollCourse.getTraineeId())) {
				Course course = courseService.getCourseById(enrollCourse.getCourseId());
                
				if (course != null) {
					enrolledCourses.add(course);
				}
			}
		}

		return enrolledCourses;
	}
    
	
	// Trả về danh sách course với thuộc tính có thể display
	public ArrayList<String> viewCoursesEnrolled(String traineeId) {
		// Get the list of courses the trainee has enrolled in
		ArrayList<Course> courseList = getCoursesEnrolled(traineeId);
		ArrayList<String> result = new ArrayList<>();

		for (Course course : courseList) {
			Coach coach = coachService.getCoachById(course.getCoachID());
			String coachName = (coach != null) ? coach.getFullName() : "Unknown Coach";

			// Format the course information
			String courseInfo = String.format("| %-10s | %-30s | %-50s | %-20s | %-20s | %20s | $%-10.2f|",
					course.getCourseId(),
					coachName ,
					course.getCourseDescription(), 
					course.getCourseType(), 
					(course.getStartDate() != null) ? course.getStartDate().toString() : "N/A",
					(course.getEndDate() != null) ? course.getEndDate().toString() : "N/A",
					course.getPrice());

			// Add formatted string to the result list
			result.add(courseInfo);
		}

		return result;
	}
    
	public boolean enrollCourse(String traineeId) {
	    // Lấy danh sách khóa học học viên đã đăng ký
	    ArrayList<Course> enrolledCourses = getCoursesEnrolled(traineeId);

	    // Lấy danh sách tất cả các khóa học có sẵn
	    ArrayList<Course> allCourses = new ArrayList<>(CourseService.courseCache);

	    // Loại bỏ các khóa học đã đăng ký
	    allCourses.removeAll(enrolledCourses);

	    // Loại bỏ các khóa học đã bắt đầu
	    LocalDate today = LocalDate.now();
	    allCourses.removeIf(course -> course.getStartDate().isBefore(today));

	    // Kiểm tra nếu không còn khóa học nào có sẵn
	    if (allCourses.isEmpty()) {
	        System.out.println("No courses available for enrollment.");
	        return false;
	    }

	    // Hiển thị danh sách các khóa học có sẵn
	    System.out.printf("|%-10s| %-20s | %-20s | %-15s | %-10s | %-12s | %-12s | %-10s|\n", 
	                      "CourseID", "Course Name", "Coach Name", "Description", 
	                      "CourseType", "StartDate", "EndDate", "TotalSections");
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	    for (Course course : allCourses) {
	        Coach coach = coachService.getCoachById(course.getCoachID());
	        String coachName = (coach != null) ? coach.getFullName() : "N/A";

	        System.out.printf("|%-10s| %-20s | %-20s | %-15s | %-10s | %-12s | %-12s | %-10d|\n",
	                          course.getCourseId(), course.getCourseName(), coachName, 
	                          course.getCourseDescription(), course.getCourseType(), 
	                          course.getStartDate().format(formatter),
	                          course.getEndDate().format(formatter), course.getTotalSessions());
	    }

	    // Yêu cầu người dùng nhập CourseID
	    String chosenCourseId = Utils.readCourseId("Enter the CourseID you want to enroll in:");

	    // Kiểm tra tính hợp lệ của CourseID
	    if (chosenCourseId == null || chosenCourseId.isEmpty()) {
	        System.out.println("Invalid input. Enrollment failed.");
	        return false;
	    }

	    // Hỏi xác nhận từ người dùng trước khi đăng ký
	    boolean confirmEnrollment = Utils.readBoolean("Are you sure you want to enroll in this course? (true/false): ");
	    if (!confirmEnrollment) {
	        System.out.println("Enrollment canceled by user.");
	        return false;
	    }

	    // Xác nhận đăng ký
	
	    boolean enrollmentSuccess = courseController.enrollCourse(chosenCourseId, traineeId);

	    if (enrollmentSuccess) {
	        System.out.println("Successfully enrolled in course.");
	        return true;
	    } else {
	        System.out.println("Enrollment failed. CourseID may be invalid or already enrolled.");
	        return false;
	    }
	}

     
	// Tạo feedback
	public boolean makeFeedback(String traineeId) {
	    // Step 1: Display the list of courses the trainee is enrolled in
	    ArrayList<Course> enrolledCourses = getCoursesEnrolled(traineeId);

	    if (enrolledCourses == null || enrolledCourses.isEmpty()) {
	        System.out.println("You are not enrolled in any courses.");
	        return false;
	    }

	    System.out.println("List of courses you are enrolled in:");
	    for (int i = 0; i < enrolledCourses.size(); i++) {
	        Course course = enrolledCourses.get(i);
	        System.out.println((i + 1) + ". " + course.getCourseName());
	    }

	    // Step 2: Choose a course to provide feedback
	   
	    int courseIndex = -1;

	    while (courseIndex < 0 || courseIndex >= enrolledCourses.size()) {
	        System.out.print("Select a course to provide feedback (enter the course number): ");
	        try {
	            courseIndex = Utils.readInt("Select a course to provide feedback (enter the course number):")-1;
	           // Consume the newline character
	            if (courseIndex < 0 || courseIndex >= enrolledCourses.size()) {
	                System.out.println("Invalid selection. Please try again.");
	            }
	        } catch (InputMismatchException e) {
	            System.out.println("Invalid input. Please enter a valid number.");
	           // Clear the invalid input
	        }
	    }
      
	    Course selectedCourse = enrolledCourses.get(courseIndex);

	    // Step 3: Enter feedback comments
	    String feedbackComment = Utils.readString("Enter your feedback: ");

	    int currentFeedbackCount = FeedbackService.feedbackCache.size();
	    // Step 4: Generate feedback and save it to the feedback list
	    String feedbackId = generateFeedbackId(currentFeedbackCount + 1); // Generate feedback ID
	    String courseId = selectedCourse.getCourseId();
	    String coachId = selectedCourse.getCoachID();

	    // Create a new Feedback object
	    Feedback feedback = new Feedback(feedbackId, traineeId, courseId, coachId, feedbackComment);

	    // Check if feedbackService is initialized
	    if (feedbackService != null) {
	        feedbackService.addFeedback(feedback);
	        System.out.println("Your feedback has been successfully submitted!");
	    } else {
	        System.out.println("Error: Feedback service is not available.");
	    }

	    // Note: Do not close the scanner here if it's used elsewhere
	    return true;
	}


	// Helper method to generate feedback ID
	public String generateFeedbackId(int feedbackCount) {
		String feedbackIdPrefix = "FB-";
		// Tạo ID với định dạng FB-xxxx, với xxxx là số từ 0001 đến 9999
		return feedbackIdPrefix + String.format("%04d", feedbackCount);
	}

	// Xem các ghi chú 
	public boolean viewNote(String traineeId) {
		NoteService noteService = new NoteService(); // Khởi tạo dịch vụ ghi chú
		ArrayList<Note> traineeNotes = (ArrayList<Note>) noteService.getNotesByCondition(traineeId); // Lọc ghi chú theo
																										// traineeId

		if (traineeNotes.isEmpty()) {
			System.out.println("Không có ghi chú nào cho trainee với ID: " + traineeId);
			return false; // Trả về false nếu không có ghi chú
		} else {
			System.out.println("Danh sách ghi chú cho trainee với ID: " + traineeId + ":");
			for (Note note : traineeNotes) {
				System.out.println(note.toString());
				return true; // Trả về true nếu có ghi chú
			}
		}
		return false;

	}

	public List<Course> getCurrentCoursesByTraineeId(String traineeId) {
		// Giả định là danh sách khóa học đã được lấy từ cơ sở dữ liệu
		ArrayList<Course> allCourses = getCoursesEnrolled(traineeId);
		List<Course> currentCourses = new ArrayList<>();

		// Giả sử kiểm tra ngày hiện tại và thêm vào currentCourses nếu khóa học đang
		// diễn ra
		LocalDate today = LocalDate.now();
		for (Course course : allCourses) {
			if (course.isOngoing(today)) { // Giả sử có phương thức isOngoing() kiểm tra ngày
				currentCourses.add(course);
			}
		}
		return currentCourses;
	}

	

	public List<Schedule> getSchedulesForCurrentWeek(List<Schedule> schedules) {
	    LocalDate today = LocalDate.now();
	    LocalDate startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
	    LocalDate endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));

	    return schedules.stream()
	        .filter(schedule -> !schedule.getDate().isBefore(startOfWeek) && !schedule.getDate().isAfter(endOfWeek))
	        .sorted(Comparator.comparing(Schedule::getDate).thenComparing(Schedule::getStartTime))
	        .collect(Collectors.toList());
	}

	public List<Schedule> getSchedulesByCourses(List<Course> courses) {
		List<Schedule> allSchedules = scheduleService.getAllSchedules();
		List<Schedule> selectedSchedules = new ArrayList<>();

		for (Schedule schedule : allSchedules) {
			for (Course course : courses) {
				if (schedule.getCourseID().equals(course.getCourseId())) {
					selectedSchedules.add(schedule);
				}
			}
		}
		return selectedSchedules;
	}

	public boolean viewSchedule(String traineeId) {
	    List<Course> currentCourses = getCurrentCoursesByTraineeId(traineeId);
	    if (currentCourses.isEmpty()) {
	        System.out.println("Không có khóa học nào đang diễn ra cho trainee với ID: " + traineeId);
	        return false;
	    }

	    List<Schedule> allSchedules = getSchedulesByCourses(currentCourses);
	    List<Schedule> schedulesForCurrentWeek = getSchedulesForCurrentWeek(allSchedules);

	    if (schedulesForCurrentWeek.isEmpty()) {
	        System.out.println("Không có lịch tập trong tuần hiện tại cho khóa học của bạn.");
	        return false;
	    }

	    displaySchedules(schedulesForCurrentWeek, traineeId);
	    return true;
	}


	private void displaySchedules(List<Schedule> schedules, String traineeId) {
	    System.out.printf("%-12s %-10s %-20s %-12s %-12s %-10s %-10s\n", 
	                      "Schedule ID", "Course ID", "Course Name", "Course Type", "Date", "Start Time", "End Time");
	    System.out.println("---------------------------------------------------------------------------------------------");

	    for (Schedule schedule : schedules) {
	        Course course = courseService.getCourseById(schedule.getCourseID());
	        String courseName = (course != null) ? course.getCourseName() : "Unknown Course";
	        String courseType = (course != null) ? course.getCourseType() : "Unknown Type";

	        System.out.printf("%-12s %-10s %-20s %-12s %-12s %-10s %-10s\n",
	                          schedule.getScheduleId(), schedule.getCourseID(), 
	                          courseName, courseType,
	                          schedule.getDate(), schedule.getStartTime(), schedule.getEndTime());
	    }
	}



}
