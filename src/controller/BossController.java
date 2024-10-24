package controller;

import model.Address;
import model.Coach;
import model.Course;
import model.Schedule;
import model.TakeWorkout;
import model.Trainee;
import model.Workout;
import service.AddressService;
import service.CoachService;
import service.CourseService;
import service.ScheduleService;
import service.TakeWorkoutService;
import service.TraineeService;
import service.WorkoutService;
import utils.Utils;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class BossController {
    private CoachService coachService;
    private TraineeService traineeService;
    private CourseService courseService;
    private TakeWorkoutService takeWorkoutService;
    private AddressService addressService;
    private WorkoutService workoutService;
    private ScheduleService scheduleService;
    private static AtomicInteger courseCount = new AtomicInteger(0);
	private static AtomicInteger scheduleCount = new AtomicInteger(0);
    public BossController() {
        this.coachService = new CoachService();
        this.traineeService = new TraineeService();
        this.courseService = new CourseService();
        this.scheduleService = new ScheduleService();
        this.addressService = new AddressService();
        this.workoutService = new WorkoutService();
        this.takeWorkoutService = new TakeWorkoutService();
    }

 // View danh sách Coach
    public void viewAllCoaches() {
        List<Coach> coachList = coachService.getAllCoaches();

        if (coachList.isEmpty()) {
            System.out.println("No coaches available.");
        } else {
            // Tiêu đề bảng
            String header = String.format("%-10s | %-20s | %-6s | %-10s | %-20s | %-15s | %-12s | %-6s | %-8s | %-10s | %-15s | %-10s | %-10s | %-10s", 
                                          "ID", "Name", "Gender", "Role", "Email", "Phone", "ID Number", 
                                          "Height", "Weight", "Birthday", "Level", "Salary", "Address ID", "Password");
            System.out.println(header);
            System.out.println("=".repeat(header.length()));

            // In thông tin Coach theo định dạng
            for (Coach coach : coachList) {
                String coachInfo = String.format("%-10s | %-20s | %-6s | %-10s | %-20s | %-15s | %-12s | %-6d | %-8.2f | %-10s | %-15s | %-10.2f | %-10s | %-10s", 
                                                 coach.getCoachId(), coach.getFullName(), coach.getGender(), coach.getRole(), 
                                                 coach.getEmail(), coach.getPhone(), coach.getCitizenIdentification(), 
                                                 coach.getHeight(), coach.getWeight(), coach.getBirthday(), 
                                                 coach.getLevel(), coach.getSalary(), coach.getAddressId(), coach.getPassword());
                System.out.println(coachInfo);
            }
        }
    }


 // View danh sách Trainee
    public void viewAllTrainees() {
        List<Trainee> traineeList = traineeService.getAllTrainees();
        if (traineeList.isEmpty()) {
            System.out.println("No trainees available.");
        } else {
            // Tiêu đề bảng
            String header = String.format("%-10s | %-20s | %-6s | %-10s | %-15s | %-20s | %-6s | %-8s | %-10s | %-10s | %-15s | %-10s | %-20s | %-20s",
                                          "ID", "Name", "Gender", "Role", "Email", "Phone", "Birthday", "Height", "Weight", "Level", "Number House", "Street", "City");
            System.out.println(header);
            System.out.println("=".repeat(header.length()));

            // In thông tin Trainee theo định dạng
            for (Trainee trainee : traineeList) {
                Address address = addressService.getAddressById(trainee.getAddressId());
                 int  numberHouse = address != null ? address.getHouseNumber() : -1;
                String street = address != null ? address.getStreet() : "Unknown";
                String city = address != null ? address.getCity() : "Unknown";

                String traineeInfo = String.format("%-10s | %-20s | %-6s | %-10s | %-15s | %-20s | %-6s | %-8.2f | %-10.2f | %-10s | %-10d | %-20s | %-20s",
                                                   trainee.getTraineeId(), trainee.getFullName(), trainee.getGender(), 
                                                   trainee.getRole(), trainee.getEmail(), trainee.getPhone(), 
                                                   trainee.getBirthday(), trainee.getHeight(), trainee.getWeight(), 
                                                   trainee.getLevel(), numberHouse, street, city);
                System.out.println(traineeInfo);
            }
        }
    }


  
 // View danh sách khóa học
    public void viewAllCourses() {
        List<Course> courseList = courseService.getAllCourses();

        if (courseList.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            // Tiêu đề bảng
            String header = String.format("%-10s | %-20s | %-30s | %-10s | %-15s | %-10s | %-15s | %-15s | %-10s | %-10s", 
                                          "Course ID", "Course Name", "Description", "Coach ID", "Type", "Max", 
                                          "Start Date", "End Date", "Price", "Sessions");
            System.out.println(header);
            System.out.println("=".repeat(header.length()));

            // In thông tin Course theo định dạng
            for (Course course : courseList) {
                String courseInfo = String.format("%-10s | %-20s | %-30s | %-10s | %-15s | %-10d | %-15s | %-15s | %-10.2f | %-10d", 
                                                  course.getCourseId(), course.getCourseName(), course.getCourseDescription(), 
                                                  course.getCoachID(), course.getCourseType(), course.getMaxParticipants(), 
                                                  course.getStartDate(), course.getEndDate(), course.getPrice(), 
                                                  course.getTotalSessions());
                System.out.println(courseInfo);
            }
        }
    }


    
    public boolean addCoach() {
        Scanner scanner = new Scanner(System.in);
        
        // Hiển thị tiêu đề
        System.out.println("************* Add Coach *************\n");

        // Tạo ID huấn luyện viên ngẫu nhiên
        String coachId = Utils.generateCoachId(); 

        // Kiểm tra xem coach có tồn tại không
        if (coachService.getCoachById(coachId) != null) {
            System.out.println("*****************************************");
            System.out.println("🚫 Coach with this ID already exists!");
            System.out.println("*****************************************");
            return false; // Trả về false nếu coach đã tồn tại
        }

        System.out.println("===== Coach Registration =====");
        
        // Nhập thông tin cá nhân
        String fullName = Utils.readName("🔹 Enter Full Name: "); 
        String gender = Utils.readString("🔹 Enter Gender: "); 
        String role = Utils.readString("🔹 Enter Role: "); 
        String email = Utils.readEmail("🔹 Enter Email: "); 
        String phone = Utils.readString("🔹 Enter Phone: "); 
        String citizenIdentification = Utils.readCitizenIdentification("🔹 Enter Citizen Identification: "); 
        int height = Utils.readInt("🔹 Enter Height (in cm): "); 
        double weight = Utils.readDouble("🔹 Enter Weight (in kg): "); 
        LocalDate birthday = Utils.readDate("🔹 Enter Birthday (yyyy-MM-dd): "); 
        
        // Nhập và xác nhận mật khẩu
        String password = Utils.readPassword("🔹 Enter Password: "); 
        String confirmPassword = Utils.readPassword("🔹 Confirm Password: "); 

        // Kiểm tra xem mật khẩu có khớp không
        if (!password.equals(confirmPassword)) {
            System.out.println("*****************************************");
            System.out.println("⚠️ Error: Passwords do not match.");
            System.out.println("*****************************************");
            return false; // Trả về false nếu mật khẩu không khớp
        }

        String level = Utils.readString("🔹 Enter Level: "); 
        String addressId = Utils.readString("🔹 Enter Address ID: "); 
        double salary = Utils.readDouble("🔹 Enter Salary: "); 

        // Tạo đối tượng Coach mới
        Coach newCoach = new Coach(fullName, gender, role, email, phone, citizenIdentification, 
                                   height, weight, birthday, coachId, password, level, addressId, salary);

        // Thêm coach vào hệ thống
        int success = coachService.addCoach(newCoach); 

        // Kiểm tra kết quả thêm coach
        if (success > 0) {
            System.out.println("*****************************************");
            System.out.println("✅ Coach registered successfully!");
            System.out.println("*****************************************");
            return true; // Trả về true nếu thêm thành công
        } else {
            System.out.println("*********************************************************");
            System.out.println("⚠️ Error: Could not register the coach. Please try again.");
            System.out.println("*********************************************************");
            return false; // Trả về false nếu có lỗi
        }
    }



 // Xóa Coach
    public boolean deleteCoach() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter Coach ID to delete: ");
        String coachId = scanner.nextLine();

        // Kiểm tra xem Coach có tồn tại không và xóa
        if (coachService.deleteCoach(coachId)>0) {
            return true;
        } else {
            return false;
        }
    }


    public boolean updateCoach() {
    	
    	String coachID =  Utils.readString("Enter Coach ID: ");
        Coach coach = coachService.getCoachById(coachID);
        if (coach != null) {
            Scanner scanner = new Scanner(System.in);
            
            // Hiển thị thông tin coach trước khi chỉnh sửa
            System.out.println("Current Coach Information:");
            System.out.println(String.format("ID: %s | Name: %s | Gender: %s | Role: %s | Email: %s | Phone: %s | Citizen ID: %s | Height: %d | Weight: %.2f | Birthday: %s | Level: %s | Address ID: %s | Salary: %.2f",
                    coach.getCoachId(), coach.getFullName(), coach.getGender(), coach.getRole(), 
                    coach.getEmail(), coach.getPhone(), coach.getCitizenIdentification(), 
                    coach.getHeight(), coach.getWeight(), coach.getBirthday(), 
                    coach.getLevel(), coach.getAddressId(), coach.getSalary()));
            
            boolean isUpdating = true;

            while (isUpdating) {
                System.out.println("\nSelect the attribute to update:");
                System.out.println("1. Full Name");
                System.out.println("2. Gender");
                System.out.println("3. Role");
                System.out.println("4. Email");
                System.out.println("5. Phone");
                System.out.println("6. Citizen Identification");
                System.out.println("7. Height");
                System.out.println("8. Weight");
                System.out.println("9. Birthday");
                System.out.println("10. Level");
                System.out.println("11. Address ID");
                System.out.println("12. Salary");
                System.out.println("13. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        
                        String fullName = Utils.readName("Enter new Full Name: ");
                        coach.setFullName(fullName);
                        break;
                    case 2:
                        System.out.print("Enter new Gender: ");
                        String gender = scanner.nextLine();
                        coach.setGender(gender);
                        break;
                    case 3:
                        System.out.print("Enter new Role: ");
                        String role = scanner.nextLine();
                        coach.setRole(role);
                        break;
                    case 4:
                        System.out.print("Enter new Email: ");
                        String email = scanner.nextLine();
                        coach.setEmail(email);
                        break;
                    case 5:
                        System.out.print("Enter new Phone: ");
                        String phone = scanner.nextLine();
                        coach.setPhone(phone);
                        break;
                    case 6:
                        System.out.print("Enter new Citizen Identification: ");
                        String citizenId = scanner.nextLine();
                        coach.setCitizenIdentification(citizenId);
                        break;
                    case 7:
                        System.out.print("Enter new Height: ");
                        int height = scanner.nextInt();
                        coach.setHeight(height);
                        break;
                    case 8:
                        System.out.print("Enter new Weight: ");
                        double weight = scanner.nextDouble();
                        coach.setWeight(weight);
                        break;
                    case 9:
                        System.out.print("Enter new Birthday (yyyy-MM-dd): ");
                        LocalDate birthday = LocalDate.parse(scanner.nextLine());
                        coach.setBirthday(birthday);
                        break;
                    case 10:
                        System.out.print("Enter new Level: ");
                        String level = scanner.nextLine();
                        coach.setLevel(level);
                        break;
                    case 11:
                        System.out.print("Enter new Address ID: ");
                        String addressId = scanner.nextLine();
                        coach.setAddressId(addressId);
                        break;
                    case 12:
                        System.out.print("Enter new Salary: ");
                        double salary = scanner.nextDouble();
                        coach.setSalary(salary);
                        break;
                    case 13:
                        isUpdating = false; // Thoát khỏi vòng lặp
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

                // Cập nhật Coach sau mỗi lần chỉnh sửa
                if (choice >= 1 && choice <= 12) {
                    coachService.updateCoach(coach);
                    return true;
                }
            }
        } else {
          return false;
        }
		return false;
    }


 // Thêm mới Trainee
    public boolean addTrainee() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter Trainee ID: ");
        String traineeId = scanner.nextLine();
        
        // Kiểm tra xem trainee có tồn tại không (giả sử bạn có một phương thức để kiểm tra)
        if (traineeService.getTraineeById(traineeId) != null) {
            System.out.println("Trainee with this ID already exists!");
            return false; // Trả về false nếu trainee đã tồn tại
        }
        
        System.out.print("Enter Full Name: ");
        String fullName = scanner.nextLine();
        
        System.out.print("Enter Gender: ");
        String gender = scanner.nextLine();
        
        System.out.print("Enter Role: ");
        String role = scanner.nextLine();
        
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter Phone: ");
        String phone = scanner.nextLine();
        
        System.out.print("Enter Birthday (yyyy-MM-dd): ");
        LocalDate birthday = LocalDate.parse(scanner.nextLine());
        
        System.out.print("Enter Citizen Identification: ");
        String citizenIdentification = scanner.nextLine();
        
        System.out.print("Enter Height: ");
        int height = scanner.nextInt();
        
        System.out.print("Enter Weight: ");
        double weight = scanner.nextDouble();
        
        System.out.print("Enter Password: ");
        String password = scanner.next(); // Giả sử không có khoảng trắng trong mật khẩu
        
        System.out.print("Enter Level: ");
        String level = scanner.next();
        
        System.out.print("Enter Address ID: ");
        String addressId = scanner.nextLine();
        
        Trainee newTrainee = new Trainee(fullName, gender, role, email, phone, birthday, 
                                           citizenIdentification, height, weight, 
                                           traineeId, password, level, addressId);
                                           
        int success = traineeService.addTrainee(newTrainee); // Giả sử phương thức này trả về true/false
        
        if (success > 0) {
            System.out.println("Trainee added successfully!");
            return true; // Trả về true nếu thêm thành công
        } else {
            System.out.println("Failed to add trainee.");
            return false; // Trả về false nếu có lỗi
        }
    }



    // Xóa Trainee
    public boolean deleteTrainee() {
    	String traineeId = Utils.readString("Enter Trainee ID: ");
        if (traineeService.deleteTrainee(traineeId)>0) {
           return true;
        } else {
         return false;
        }
    }

    // Cập nhật Trainee
    public boolean updateTrainee() {
    	String traineeId = Utils.readString("Enter Trainee ID: ");
        Trainee trainee = traineeService.getTraineeById(traineeId);
        if (trainee != null) {
            Scanner scanner = new Scanner(System.in);
            
            // Hiển thị thông tin trainee trước khi chỉnh sửa
            System.out.println("Current Trainee Information:");
            System.out.println(String.format("ID: %s | Name: %s | Gender: %s | Role: %s | Email: %s | Phone: %s | Citizen ID: %s | Height: %d | Weight: %.2f | Birthday: %s",
                    trainee.getTraineeId(), trainee.getFullName(), trainee.getGender(), 
                    trainee.getRole(), trainee.getEmail(), trainee.getPhone(), 
                    trainee.getCitizenIdentification(), trainee.getHeight(), 
                    trainee.getWeight(), trainee.getBirthday()));
            
            boolean isUpdating = true;

            while (isUpdating) {
                System.out.println("\nSelect the attribute to update:");
                System.out.println("1. Full Name");
                System.out.println("2. Gender");
                System.out.println("3. Role");
                System.out.println("4. Email");
                System.out.println("5. Phone");
                System.out.println("6. Citizen Identification");
                System.out.println("7. Height");
                System.out.println("8. Weight");
                System.out.println("9. Birthday");
                System.out.println("10. Level");
                System.out.println("11. Address ID");
                System.out.println("12. Exit");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        System.out.print("Enter new Full Name: ");
                        String fullName = scanner.nextLine();
                        trainee.setFullName(fullName);
                        break;
                    case 2:
                        System.out.print("Enter new Gender: ");
                        String gender = scanner.nextLine();
                        trainee.setGender(gender);
                        break;
                    case 3:
                        System.out.print("Enter new Role: ");
                        String role = scanner.nextLine();
                        trainee.setRole(role);
                        break;
                    case 4:
                        System.out.print("Enter new Email: ");
                        String email = scanner.nextLine();
                        trainee.setEmail(email);
                        break;
                    case 5:
                        System.out.print("Enter new Phone: ");
                        String phone = scanner.nextLine();
                        trainee.setPhone(phone);
                        break;
                    case 6:
                        System.out.print("Enter new Citizen Identification: ");
                        String citizenId = scanner.nextLine();
                        trainee.setCitizenIdentification(citizenId);
                        break;
                    case 7:
                        System.out.print("Enter new Height: ");
                        int height = scanner.nextInt();
                        trainee.setHeight(height);
                        break;
                    case 8:
                        System.out.print("Enter new Weight: ");
                        double weight = scanner.nextDouble();
                        trainee.setWeight(weight);
                        break;
                    case 9:
                        System.out.print("Enter new Birthday (yyyy-MM-dd): ");
                        LocalDate birthday = LocalDate.parse(scanner.nextLine());
                        trainee.setBirthday(birthday);
                        break;
                    case 10:
                        System.out.print("Enter new Level: ");
                        String level = scanner.nextLine();
                        trainee.setLevel(level);
                        break;
                    case 11:
                        System.out.print("Enter new Address ID: ");
                        String addressId = scanner.nextLine();
                        trainee.setAddressId(addressId);
                        break;
                    case 12:
                        isUpdating = false; // Thoát khỏi vòng lặp
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }

                // Cập nhật Trainee sau mỗi lần chỉnh sửa
                if (choice >= 1 && choice <= 11) {
                    traineeService.updateTrainee(trainee);
                    System.out.println("Trainee updated successfully.");
                }
            }
        } else {
            System.out.println("Trainee not found.");
        }
		return false;
    }

    // Thêm mới Course
    public boolean createCourse() {
        Scanner scanner = new Scanner(System.in);

        // Hiển thị danh sách các Coach và yêu cầu người dùng chọn
        System.out.println("List of Coaches:");
        List<Coach> coaches = coachService.getAllCoaches(); // Giả định bạn có phương thức này
        if (coaches.isEmpty()) {
            System.out.println("No coaches available. Please add a coach first.");
            return false;
        }

        // Hiển thị danh sách Coach
        System.out.printf("%-10s %-20s %-10s %-10s%n", "ID", "Name", "Level", "Phone");
        System.out.println("-------------------------------------------------");
        for (Coach coach : coaches) {
            System.out.printf("%-10s %-20s %-10s %-10s%n", coach.getCoachId(), coach.getFullName(), 
                              coach.getLevel(), coach.getPhone());
        }

        System.out.print("Enter Coach ID: ");
        String coachId = scanner.nextLine();

        // Kiểm tra xem coachId có hợp lệ không
        if (coaches.stream().noneMatch(coach -> coach.getCoachId().equals(coachId))) {
            System.out.println("Invalid Coach ID. Please try again.");
            return false;
        }

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

        int totalSessions = Utils.readInt("Enter Total Sessions: ");

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
            scheduleService.addSchedule(schedule); // Thêm vào danh sách ScheduleService
        }

        // Tạo TakeWorkout cho khóa học
        List<TakeWorkout> takeWorkouts = new ArrayList<>();

        // Hiển thị workout
        System.out.println("Available Workouts:");
        List<Workout> workouts = workoutService.getAllWorkouts(); // Giả định bạn có phương thức này
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
        
        // Thêm khóa học vào CourseService
        if(courseService.addCourse(newCourse)> 1) {
        	return true;
        }
       
        return true;
    }


    // Xóa Course
    public boolean deleteCourse() {
    	
    	String courseId = Utils.readString("Enter Course ID: ");
        if (courseService.deleteCourse(courseId)> 1) {
           return true;
        } else {
           return false;
        }
    }
    // update Course
    public boolean updateCourse() {
    	String courseId = Utils.readString("Enter Course ID: ");
        Course course = courseService.getCourseById(courseId);
        if (course == null) {
            System.out.println("Course not found.");
            return false;
        }

        Scanner scanner = new Scanner(System.in);
        
        // Hiển thị thông tin hiện tại của khóa học
        System.out.println("Current Course Information:");
        System.out.printf("Course ID: %s%n", course.getCourseId());
        System.out.printf("Course Name: %s%n", course.getCourseName());
        System.out.printf("Description: %s%n", course.getCourseDescription());
        System.out.printf("Coach ID: %s%n", course.getCoachID());
        System.out.printf("Course Type: %s%n", course.getCourseType());
        System.out.printf("Max Participants: %d%n", course.getMaxParticipants());
        System.out.printf("Start Date: %s%n", course.getStartDate());
        System.out.printf("End Date: %s%n", course.getEndDate());
        System.out.printf("Price: %.2f%n", course.getPrice());
        System.out.printf("Total Sessions: %d%n", course.getTotalSessions());

        System.out.println("Select which attribute you want to update:");
        System.out.println("1. Course Name");
        System.out.println("2. Description");
        System.out.println("3. Coach ID");
        System.out.println("4. Course Type");
        System.out.println("5. Max Participants");
        System.out.println("6. Start Date");
        System.out.println("7. End Date");
        System.out.println("8. Price");
        System.out.println("9. Total Sessions");
        System.out.print("Enter your choice (1-9): ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (choice) {
            case 1:
                System.out.print("Enter new Course Name: ");
                String newCourseName = scanner.nextLine();
                course.setCourseName(newCourseName);
                break;
            case 2:
                System.out.print("Enter new Description: ");
                String newDescription = scanner.nextLine();
                course.setCourseDescription(newDescription);
                break;
            case 3:
                
                String newCoachId = Utils.readCoachId("Enter new Coach ID:");
                course.setCoachID(newCoachId);
                break;
            case 4:
                System.out.print("Enter new Course Type: ");
                String newCourseType = scanner.nextLine();
                course.setCourseType(newCourseType);
                break;
            case 5:
                System.out.print("Enter new Max Participants: ");
                int newMaxParticipants = scanner.nextInt();
                course.setMaxParticipants(newMaxParticipants);
                break;
            case 6:
                System.out.print("Enter new Start Date (yyyy-MM-dd): ");
                LocalDate newStartDate = LocalDate.parse(scanner.nextLine());
                course.setStartDate(newStartDate);
                break;
            case 7:
                System.out.print("Enter new End Date (yyyy-MM-dd): ");
                LocalDate newEndDate = LocalDate.parse(scanner.nextLine());
                course.setEndDate(newEndDate);
                break;
            case 8:
                System.out.print("Enter new Price: ");
                double newPrice = scanner.nextDouble();
                course.setPrice(newPrice);
                break;
            case 9:
                System.out.print("Enter new Total Sessions: ");
                int newTotalSessions = scanner.nextInt();
                course.setTotalSessions(newTotalSessions);
                break;
            default:
                System.out.println("Invalid choice. No updates made.");
                return false;
        }

        // Cập nhật thông tin khóa học
        courseService.updateCourse(course);
        return true;
    }

	

 // Helper methods to generate IDs
 	private String generateCourseId() {
 		int count = courseCount.incrementAndGet(); // Tăng số lượng khóa học
 		return String.format("CS-%04d", count); // Tạo ID theo định dạng CS-1234
 	}

 	private String generateScheduleId() {
 		int count = scheduleCount.incrementAndGet(); // Tăng số lượng lịch tập
 		return String.format("SC-%04d", count); // Tạo ID theo định dạng SC-1234
 	}
 	
 	
}
