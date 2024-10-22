package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import model.Coach;
import model.Course;
import model.EnrollCourse;
import model.Feedback;
import model.Note;
import model.Schedule;
import model.Trainee;
import service.CoachService;
import service.CourseService;
import service.EnrollCourseService;
import service.FeedbackService;
import service.NoteService;
import service.ScheduleService;
import service.TraineeService;

public class TraineeController {
	ScheduleService scheduleService = new ScheduleService();
	TraineeService traineeServic = new TraineeService();
	CourseService courseService = new CourseService();
	CoachService coachService = new CoachService();
	FeedbackService feedbackService = new FeedbackService();

	
	// Lấy ra Trainee
	public Trainee getTrainee(String traineeID) {
		for (Trainee trainee : TraineeService.traineeList) {
			if (trainee.getTraineeId().equals(traineeID)) {
				return trainee;
			}

		}
		return null;
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
			String courseInfo = String.format("|%-10s| %-20s| %-30s| %-10s| %-20s| %-15s| %-15s| $%-10.2f|",
					course.getCourseId(), course.getCourseName(), course.getCourseDescription(), course.getCourseType(),
					coachName, (course.getStartDate() != null) ? course.getStartDate().toString() : "N/A",
					(course.getEndDate() != null) ? course.getEndDate().toString() : "N/A", course.getPrice());

			// Add formatted string to the result list
			result.add(courseInfo);
		}

		return result;
	}
    
	// Đăng ký Course
	public boolean enrollCourse(String traineeId) {
		// Get the list of courses the trainee has already enrolled in
		ArrayList<Course> enrolledCourses = getCoursesEnrolled(traineeId);

		// Get the list of all available courses
		ArrayList<Course> allCourses = new ArrayList<>(CourseService.courseCache); // Create a copy of courseCache

		// Subtract the enrolled courses from the list of all courses
		allCourses.removeAll(enrolledCourses); // allCourses now contains only the courses not enrolled in

		// Filter out courses that have already started
		LocalDate today = LocalDate.now();
		Iterator<Course> courseIterator = allCourses.iterator();

		while (courseIterator.hasNext()) {
			Course course = courseIterator.next();
			if (course.getStartDate().isBefore(today)) {
				courseIterator.remove(); // Safely remove the course while iterating
			}
		}

		// Check if there are available courses
		if (allCourses.isEmpty()) {
			System.out.println("No courses available for enrollment.");
			return false;
		}

		// Display the list of available courses
		System.out.println(String.format("|%-10s| %-20s | %-20s | %-15s | %-10s | %-12s | %-12s | %-10s|", "CourseID",
				"Course Name", "Coach Name", "Description", "CourseType", "StartDate", "EndDate", "TotalSections"));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		for (Course course : allCourses) {
			Coach coach = coachService.getCoachById(course.getCoachID());
			String coachName = coach.getFullName();

			System.out.println(String.format("|%-10s| %-20s | %-20s | %-15s | %-10s | %-12s | %-12s | %-10d|",
					course.getCourseId(), course.getCourseName(), coachName, course.getCourseDescription(),
					course.getCourseType(), course.getStartDate().format(formatter),
					course.getEndDate().format(formatter), course.getTotalSessions()));
		}

		// Ask user to choose a course by its ID
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the CourseID you want to enroll in: ");
		String chosenCourseId = scanner.nextLine();

		// Find the course by its ID
		for (Course course : allCourses) {
			if (course.getCourseId().equals(chosenCourseId)) {
				// Enroll the trainee in the chosen course
				System.out.println("Enrolling in course: " + course.getCourseName());
				// Add enrollment logic here (e.g., save to database, register, etc.)
				return true; // Successful enrollment
			}
		}

		// If the course ID is not found
		System.out.println("Invalid CourseID. Enrollment failed.");
		return false;
	}
     
	// Tạo feedback
	public boolean makeFeedback(String traineeId) {
		// Step 1: Display the list of courses the trainee is enrolled in
		ArrayList<Course> enrolledCourses = getCoursesEnrolled(traineeId);

		if (enrolledCourses.isEmpty()) {
			System.out.println("You are not enrolled in any courses.");
			return false;
		}

		System.out.println("List of courses you are enrolled in:");
		for (int i = 0; i < enrolledCourses.size(); i++) {
			Course course = enrolledCourses.get(i);
			System.out.println((i + 1) + ". " + course.getCourseName());
		}

		// Step 2: Choose a course to provide feedback
		Scanner scanner = new Scanner(System.in);
		System.out.print("Select a course to provide feedback (enter the course number): ");
		int courseIndex = scanner.nextInt() - 1;
		scanner.nextLine(); // Consume the newline character

		if (courseIndex < 0 || courseIndex >= enrolledCourses.size()) {
			System.out.println("Invalid selection.");
			return false;
		}

		Course selectedCourse = enrolledCourses.get(courseIndex);

		// Step 3: Enter feedback comments
		System.out.print("Enter your feedback: ");
		String feedbackComment = scanner.nextLine();

		int currentFeedbackCount = FeedbackService.feedbackCache.size();
		// Step 4: Generate feedback and save it to the feedback list
		String feedbackId = generateFeedbackId(currentFeedbackCount + 1); // Generate feedback ID, e.g., FB-1234
		String courseId = selectedCourse.getCourseId();
		String coachId = selectedCourse.getCoachID();

		// Create a new Feedback object
		Feedback feedback = new Feedback(feedbackId, traineeId, courseId, coachId, feedbackComment);

		// Save feedback to a feedback list (or database)
		feedbackService.addFeedback(feedback);

		System.out.println("Your feedback has been successfully submitted!");
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
		CourseService courseService = new CourseService(); // Dịch vụ khóa học

		// Bước 1: Lọc khóa học của trainee ở thời điểm hiện tại
		List<Course> currentCourses = getCurrentCoursesByTraineeId(traineeId); // Lấy danh sách khóa học hiện tại

		if (currentCourses.isEmpty()) {
			System.out.println("Không có khóa học nào đang diễn ra cho trainee với ID: " + traineeId);
			return false;
		} else {
			// Bước 2: Lấy lịch học (Schedule) từ khóa học
			List<Schedule> schedules = getSchedulesByCourses(currentCourses); // Lấy lịch học từ danh sách khóa học

			// Bước 3: Sắp xếp các lịch học theo thời gian (date và startTime)
			Collections.sort(schedules, Comparator.comparing(Schedule::getDate).thenComparing(Schedule::getStartTime));

			// Bước 4: In ra thông tin của mỗi lịch học
			System.out.println("Lịch học của trainee với ID: " + traineeId + ":");

			System.out.println(String.format("| %-12s | %-10s | %-20s | %-12s | %-12s | %-10s | %-10s |", "Schedule ID",
					"Course ID", "Course Name", "Course Type", "Date", "Start Time", "End Time"));

			System.out.println(
					"---------------------------------------------------------------------------------------------");

			for (Schedule schedule1 : schedules) {
				Course course1 = courseService.getCourseById(schedule1.getCourseID()); // Giả sử có phương thức này
				System.out.println(String.format("| %-12s | %-10s | %-20s | %-12s | %-12s | %-10s | %-10s |",
						schedule1.getScheduleId(), schedule1.getCourseID(), course1.getCourseName(),
						course1.getCourseType(), schedule1.getDate(), schedule1.getStartTime(),
						schedule1.getEndTime()));
			}

		}
		return true;
	}

}
