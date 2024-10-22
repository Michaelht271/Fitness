package controller;

import java.time.LocalDate;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

import model.Coach;
import model.Course;
import model.EnrollCourse;
import model.Feedback;
import model.Note;
import model.Schedule;
import model.TakeWorkout;
import model.Trainee;
import model.Workout;
import service.CoachService;
import service.CourseService;
import service.EnrollCourseService;
import service.FeedbackService;
import service.NoteService;
import service.ScheduleService;
import service.TakeWorkoutService;
import service.TraineeService;
import service.WorkoutService;
import utils.Utils;

public class CoachController {
	CoachService coachService = new CoachService();
	CourseService courseService = new CourseService();
	TraineeService traineeService = new TraineeService();
	FeedbackService feedbackService = new FeedbackService();
	ScheduleService scheduleService = new ScheduleService();
	NoteService noteService = new NoteService();
	TakeWorkoutService takeWorkoutService = new TakeWorkoutService();
	WorkoutService workoutSerVice = new WorkoutService();

	// Lấy ra Coach theo coachID
	public Coach getCoach(String coachID) {
		for (Coach coach : CoachService.coachList) {
			if (coach.getCoachId().equals(coachID)) {
				return coach;
			}
		}
		return null;
	}

	public boolean createCourse(String coachId) {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter Course Name: ");
		String courseName = scanner.nextLine();

		System.out.print("Enter Course Description: ");
		String description = scanner.nextLine();

		System.out.print("Enter Course Type: ");
		String courseType = scanner.nextLine();

		System.out.print("Enter Course Start Date (yyyy-MM-dd): ");
		LocalDate startDate = LocalDate.parse(scanner.nextLine());

		System.out.print("Enter Course End Date (yyyy-MM-dd): ");
		LocalDate endDate = LocalDate.parse(scanner.nextLine());

		System.out.print("Enter Course Price: ");
		double price = scanner.nextDouble();

		System.out.print("Enter Max Participants: ");
		int maxParticipants = scanner.nextInt();
		scanner.nextLine(); // Consume newline character

		int totalSessions = Utils.readInt("Enter Total Session : ");

		Course newCourse = new Course(generateCourseId(), courseName, description, coachId, courseType, maxParticipants,
				startDate, endDate, price, totalSessions);

		// Tạo lịch tập cho khóa học
		List<Schedule> schedules = new ArrayList<>();
		System.out.print("Enter number of schedules for this course: ");
		int numSchedules = scanner.nextInt();
		scanner.nextLine(); // Consume newline character

		for (int i = 0; i < numSchedules; i++) {
			System.out.print("Enter Schedule Date (yyyy-MM-dd): ");
			LocalDate date = LocalDate.parse(scanner.nextLine());

			System.out.print("Enter Start Time (HH:mm): ");
			LocalTime startTime = LocalTime.parse(scanner.nextLine());

			System.out.print("Enter End Time (HH:mm): ");
			LocalTime endTime = LocalTime.parse(scanner.nextLine());

			Schedule schedule = new Schedule(generateScheduleId(), newCourse.getCourseId(), date, startTime, endTime);
			schedules.add(schedule);
			scheduleService.addSchedule(schedule);// Thêm vào danh sách ScheduleService
		}

		// Tạo TakeWorkout cho khóa học
		List<TakeWorkout> takeWorkouts = new ArrayList<>();

		// Hiển thị workout
		System.out.println("Available Workouts:");
		List<Workout> workouts = workoutSerVice.getAllWorkouts(); // Giả định bạn có phương thức này
		System.out.printf("%-10s %-20s %-10s %-10s %-30s %-20s%n", "ID", "Name", "Duration", "Level", "Instruction",
				"Equipment");
		System.out.println("-------------------------------------------------------------");
		for (Workout workout : workouts) {
			System.out.printf("%-10s %-20s %-10d %-10s %-30s %-20s%n", workout.getWorkoutId(), workout.getWorkoutName(),
					workout.getDuration(), workout.getLevel(), workout.getInstruction(),
					workout.getEquipmentRequired());
		}

		// Tạo TakeWorkout
		System.out.print("Enter Workout IDs for this course (separated by commas): ");
		String workoutIdsInput = scanner.nextLine();
		String[] workoutIds = workoutIdsInput.split(",");

		for (String workoutId : workoutIds) {
			workoutId = workoutId.trim(); // Loại bỏ khoảng trắng
			TakeWorkout takeWorkout = new TakeWorkout(newCourse.getCourseId(), workoutId);
			takeWorkouts.add(takeWorkout);
			takeWorkoutService.addTakeWorkout(takeWorkout);
		}
		courseService.addCourse(newCourse);
		System.out.println("Course created successfully!");
		return true;
	}

	private static AtomicInteger courseCount = new AtomicInteger(0);
	private static AtomicInteger scheduleCount = new AtomicInteger(0);

	// Helper methods to generate IDs
	private String generateCourseId() {
		int count = courseCount.incrementAndGet(); // Tăng số lượng khóa học
		return String.format("CS-%04d", count); // Tạo ID theo định dạng CS-1234
	}

	private String generateScheduleId() {
		int count = scheduleCount.incrementAndGet(); // Tăng số lượng lịch tập
		return String.format("SC-%04d", count); // Tạo ID theo định dạng SC-1234
	}

	// Xem danh sách khóa học của Coach
	public List<Course> viewMyCourseList(String coachId) {
		List<Course> myCourses = new ArrayList<>();

		for (Course course : CourseService.courseCache) {
			if (course.getCoachID().equals(coachId)) {
				myCourses.add(course);
			}
		}

		if (myCourses.isEmpty()) {
			System.out.println("You don't have any courses.");
			return myCourses;
		}

		System.out.println("Your courses:");
		System.out.printf("%-15s %-30s %-15s %-10s %-15s %-15s%n", "Course ID", "Course Name", "Course Type", "Price",
				"Start Date", "End Date");
		System.out.println("-----------------------------------------------------------------");

		for (Course course : myCourses) {
			System.out.printf("%-15s %-30s %-15s %-10.2f %-15s %-15s%n", course.getCourseId(), course.getCourseName(),
					course.getCourseType(), course.getPrice(), course.getStartDate().toString(), // Chuyển đổi LocalDate
																									// thành String
					course.getEndDate().toString()); // Chuyển đổi LocalDate thành String
		}

		return myCourses;
	}

	// Xem danh sách học viên của Coach
	public List<Trainee> viewMyTraineeList(String coachId) {
		List<Trainee> traineeList = new ArrayList<>();

		for (EnrollCourse enrollCourse : EnrollCourseService.enrollCourseCache) {
			Course course = courseService.getCourseById(enrollCourse.getCourseId());
			if (course != null && course.getCoachID().equals(coachId)) {
				Trainee trainee = traineeService.getTraineeById(enrollCourse.getTraineeId());
				if (trainee != null && !traineeList.contains(trainee)) {
					traineeList.add(trainee);
				}
			}
		}

		if (traineeList.isEmpty()) {
			System.out.println("You don't have any trainees.");
		} else {
			System.out.printf("%-15s %-30s %-10s %-15s %-25s %-10s %-10s %-10s%n", "Trainee ID", "Full Name", "Level",
					"Phone", "Email", "Height", "Weight", "Gender");
			System.out.println(
					"------------------------------------------------------------------------------------------------------");

			for (Trainee trainee : traineeList) {
				System.out.printf("%-15s %-30s %-10s %-15s %-25s %-10.2f %-10.2f %-10s%n", trainee.getTraineeId(),
						trainee.getFullName(), trainee.getLevel(), trainee.getPhone(), trainee.getEmail(),
						trainee.getHeight(), trainee.getWeight(), trainee.getGender());
			}
		}

		return traineeList;
	}

	// Xem danh sách feedback cho Coach
	public List<Feedback> viewMyFeedback(String coachId) {
		List<Feedback> feedbackList = new ArrayList<>();

		for (Feedback feedback : FeedbackService.feedbackCache) {
			if (feedback.getCoachId().equals(coachId)) {
				feedbackList.add(feedback);
			}
		}

		if (feedbackList.isEmpty()) {
			System.out.println("You have no feedback.");
		} else {
			System.out.printf("%-15s %-30s %-15s %-15s %-50s%n", "Feedback ID", "Trainee ID", "Course ID", "Coach ID",
					"Comment");
			System.out.println(
					"------------------------------------------------------------------------------------------------------");

			for (Feedback feedback : feedbackList) {
				System.out.printf("%-15s %-30s %-15s %-15s %-50s%n", feedback.getFeedbackId(), feedback.getTraineeId(),
						feedback.getCourseId(), feedback.getCoachId(), feedback.getComment());
			}
		}

		return feedbackList;
	}

	// Tạo ghi chú cho khóa học
	public boolean createNote() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter Trainee ID: ");
		String traineeId = scanner.nextLine();

		System.out.print("Enter Course ID: ");
		String courseId = scanner.nextLine();

		System.out.print("Enter Workout ID: ");
		String workoutId = scanner.nextLine();

		System.out.print("Enter Note Content: ");
		String noteContent = scanner.nextLine();

		String noteId = generateNoteId();
		// Lưu thời gian tạo ghi chú

		Note newNote = new Note(noteId, traineeId, courseId, workoutId, noteContent);

		noteService.addNote(newNote);
		System.out.println("Note created successfully!");
		return true;
	}

	// Xem lịch dạy của Coach
	public boolean viewMySchedule(String coachId) {
		List<Course> myCourses = viewMyCourseList(coachId);

		if (myCourses.isEmpty()) {
			System.out.println("You don't have any courses, so no schedule available.");
			return false;
		}

		List<Schedule> schedules = new ArrayList<>();

		// Lấy lịch dạy từ các khóa học của coach
		for (Course course : myCourses) {
			schedules.addAll(scheduleService.getSchedulesByCondition(course.getCourseId()));
		}

		if (schedules.isEmpty()) {
			System.out.println("No schedule available for your courses.");
		} else {
			// Sắp xếp theo ngày và giờ bắt đầu
			Collections.sort(schedules, Comparator.comparing(Schedule::getDate).thenComparing(Schedule::getStartTime));

			System.out.println("Your schedule:");
			for (Schedule schedule : schedules) {
				Course course = courseService.getCourseById(schedule.getCourseID());
				System.out.println(String.format("Course: %s | Date: %s | Start: %s | End: %s", course.getCourseName(),
						schedule.getDate(), schedule.getStartTime(), schedule.getEndTime()));
			}
		}

		return true;
	}

	// Helper method to generate Note ID
	private String generateNoteId() {
		return "NO-" + (NoteService.noteCache.size() + 1);
	}
}
