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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

	        // Định dạng tiêu đề với icon
        	
	        String title =  "\"                                                      🎓 COACH TABLE                                                                           \"";
	                    

            // Tiêu đề bảng
            String header = String.format("%-10s | %-20s | %-6s | %-10s | %-20s | %-15s | %-12s | %-6s | %-8s | %-10s | %-15s | %-10s | %-10s | %-10s", 
                                          "ID", "Name", "Gender", "Role", "Email", "Phone", "ID Number", 
                                          "Height", "Weight", "Birthday", "Level", "Salary", "Address ID", "Password");
            System.out.println("=".repeat(header.length()));
            System.out.println(title);
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
        	String title =   "===============================================================================================================================\n"
                    + "\"                                                🎓 TRAINEE TABLE                                                               \"";
            // Tiêu đề bảng
            String header = String.format("%-10s | %-20s | %-6s | %-10s | %-15s | %-20s | %-6s | %-8s | %-10s | %-10s | %-15s | %-10s | %-20s | %-20s",
                                          "ID", "Name", "Gender", "Role", "Email", "Phone", "Birthday", "Height", "Weight", "Level", "Number House", "Street", "City");
            System.out.println(title);
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
        	String title =   "===============================================================================================================================\n"
                    + "\"                                                🎓 COURSE TABLE                                                               \"";
           
            String header = String.format("%-10s | %-20s | %-30s | %-10s | %-15s | %-10s | %-15s | %-15s | %-10s | %-10s", 
                                          "Course ID", "Course Name", "Description", "Coach ID", "Type", "Max", 
                                          "Start Date", "End Date", "Price", "Sessions");
            System.out.println(title);
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
        System.out.println("************* Add Coach *************\n");

        // Tạo ID huấn luyện viên ngẫu nhiên
        String coachId = Utils.generateCoachId();

        // Kiểm tra xem coach có tồn tại không
        if (isCoachIdDuplicate(coachId)) {
            System.out.println("🚫 Coach with this ID already exists!");
            return false;
        }

        System.out.println("===== Coach Registration =====");

        try {
            // Thu thập thông tin từ người dùng
            Coach newCoach = collectCoachInfo(coachId);
            
            // Thêm coach vào hệ thống
            int success = coachService.addCoach(newCoach);

            // Kiểm tra kết quả thêm coach
            if (success > 0) {
                System.out.println("✅ Coach registered successfully!");
                return true;
            } else {
                System.out.println("⚠️ Error: Could not register the coach. Please try again.");
                return false;
            }

        } catch (Exception e) {
            System.out.println("⚠️ Error: " + e.getMessage());
            return false;
        }
    }

    // Kiểm tra trùng lặp ID
    private boolean isCoachIdDuplicate(String coachId) {
        return coachService.getCoachById(coachId) != null;
    }

    // Phương thức thu thập thông tin
    private Coach collectCoachInfo(String coachId) {
        String fullName = Utils.readName("🔹 Enter Full Name: ");
        String gender = Utils.readString("🔹 Enter Gender: ");
        String role = Utils.readString("🔹 Enter Role: ");
        String email = Utils.readEmail("🔹 Enter Email: ");
        String phone = Utils.readPhone("🔹 Enter Phone: ");
        String citizenIdentification = Utils.readCitizenIdentification("🔹 Enter Citizen Identification: ");
        int height = Utils.readInt("🔹 Enter Height (in cm): ");
        double weight = Utils.readPositiveDouble("🔹 Enter Weight (in kg): ");
        LocalDate birthday = Utils.readDate("🔹 Enter Birthday (yyyy-MM-dd): ");

        // Xác nhận mật khẩu
        String password = promptForPassword();

        String level = Utils.readString("🔹 Enter Level: ");
        String addressId = Utils.readString("🔹 Enter Address ID: ");
        double salary = Utils.readDouble("🔹 Enter Salary: ");

        // Tạo và trả về đối tượng Coach
        return new Coach(fullName, gender, role, email, phone, citizenIdentification,
                         height, weight, birthday, coachId, password, level, addressId, salary);
    }

    // Nhập và xác nhận mật khẩu
    private String promptForPassword() {
        String password;
        String confirmPassword;
        do {
            password = Utils.readPassword("🔹 Enter Password: ");
            confirmPassword = Utils.readPassword("🔹 Confirm Password: ");
            if (!password.equals(confirmPassword)) {
                System.out.println("⚠️ Error: Passwords do not match. Please try again.");
            }
        } while (!password.equals(confirmPassword));
        return password;
    }




 // Xóa Coach
    public boolean deleteCoach() {
        
        
        String coachId = Utils.readCoachId("Enter Coach ID to Delete: ");
        // Kiểm tra xem Coach có tồn tại không và xóa
        if (coachService.deleteCoach(coachId)>0) {
            return true;
        } else {
            return false;
        }
    }


    public boolean updateCoach() {
        String coachID = Utils.readString("🆔 Enter Coach ID: ");
        Coach coach = coachService.getCoachById(coachID);

        if (coach == null) {
            System.out.println("❌ Coach not found.");
            return false;
        }

        // Display current coach details
        System.out.println("\n===== 🏋️ Current Coach Information 🏋️ =====");
        System.out.printf("🆔 ID: %s%n👤 Name: %s%n🧑‍⚖️ Gender: %s%n📝 Role: %s%n📧 Email: %s%n📞 Phone: %s%n🔐 Citizen ID: %s%n📏 Height: %d cm%n⚖️ Weight: %.2f kg%n🎂 Birthday: %s%n🎓 Level: %s%n🏠 Address ID: %s%n💰 Salary: %.2f%n",
                coach.getCoachId(), coach.getFullName(), coach.getGender(), coach.getRole(), 
                coach.getEmail(), coach.getPhone(), coach.getCitizenIdentification(), 
                coach.getHeight(), coach.getWeight(), coach.getBirthday(), 
                coach.getLevel(), coach.getAddressId(), coach.getSalary());
        System.out.println("==========================================\n");

        // Map actions to user choices
        Map<Integer, Runnable> updateActions = new HashMap<>();
        updateActions.put(1, () -> coach.setFullName(Utils.readName("✏️ Enter new Full Name: ")));
        updateActions.put(2, () -> coach.setGender(Utils.readString("✏️ Enter new Gender: ")));
        updateActions.put(3, () -> coach.setEmail(Utils.readEmail("✏️ Enter new Email: ")));
        updateActions.put(4, () -> coach.setPhone(Utils.readPhone("✏️ Enter new Phone: ")));
        updateActions.put(5, () -> coach.setCitizenIdentification(Utils.readCitizenIdentification("✏️ Enter new Citizen ID: ")));
        updateActions.put(6, () -> coach.setHeight(Utils.readInt("✏️ Enter new Height (cm): ")));
        updateActions.put(7, () -> coach.setWeight(Utils.readPositiveDouble("✏️ Enter new Weight (kg): ")));
        updateActions.put(8, () -> coach.setBirthday(Utils.readDate("✏️ Enter new Birthday (yyyy-MM-dd): ")));
        updateActions.put(9, () -> coach.setLevel(Utils.readString("✏️ Enter new Level: ")));
        updateActions.put(10, () -> coach.setAddressId(Utils.readString("✏️ Enter new Address ID: ")));
        updateActions.put(11, () -> coach.setSalary(Utils.readDouble("✏️ Enter new Salary: ")));

        boolean isUpdating = true;
        boolean hasUpdated = false;

        while (isUpdating) {
            System.out.println("\n🌟 Select the attribute to update:");
            System.out.println("------------------------------------------");
            System.out.println("1️⃣  Full Name\n2️⃣  Gender\n3️⃣  Email\n4️⃣  Phone\n5️⃣  Citizen ID");
            System.out.println("6️⃣  Height\n7️⃣  Weight\n8️⃣  Birthday\n9️⃣  Level\n🔟  Address ID");
            System.out.println("1️⃣1️⃣ Salary\n1️⃣2️⃣ Exit");
            System.out.println("------------------------------------------");

            int choice = Utils.readInt("👉 Enter your choice to continue: ");
            
            if (choice == 12) {
                isUpdating = false;
            } else if (updateActions.containsKey(choice)) {
                updateActions.get(choice).run();
                hasUpdated = true;
                System.out.println("✅ Attribute updated successfully.");
            } else {
                System.out.println("❌ Invalid choice. Please try again.");
            }
        }

        if (hasUpdated) {
            coachService.updateCoach(coach);
            System.out.println("\n💾 Coach details updated successfully!");
            return true;
        }

        System.out.println("⚠️ No changes were made.");
        return false;
    }

    public boolean addTrainee() {
        String traineeId = Utils.generateTraineeId();

        // Check if the trainee already exists
        if (traineeService.getTraineeById(traineeId) != null) {
            System.out.println("❌ Trainee with ID '" + traineeId + "' already exists!");
            return false; // Return false if trainee already exists
        }

        // Collecting trainee information
        System.out.println("\n📋 Enter Trainee Information:");
        String fullName = Utils.readName("👤 Enter Trainee Full Name: ");
        String gender = Utils.readString("🔢 Enter Trainee Gender: ");
        String role = "TRAINEE";
        String email = Utils.readEmail("📧 Enter Trainee Email: ");
        String phone = Utils.readPhone("📞 Enter Trainee Phone Number: ");
        LocalDate birthday = Utils.readDate("🎂 Enter Birthday (yyyy-MM-dd): ");
        String citizenIdentification = Utils.readCitizenIdentification("🔑 Enter Citizen Identification: ");
        int height = Utils.readInt("📏 Enter Trainee Height (cm): ");
        double weight = Utils.readPositiveDouble("⚖️ Enter Trainee Weight (kg): ");
        
        // Using a method for password entry
        String password = Utils.readPassword("🔒 Enter Trainee Password: ");
        String level = Utils.readString("🌟 Enter Trainee Level: ");
        String addressId = Utils.generateAddressId(); // Generate address ID automatically

        // Create the new Trainee object
        Trainee newTrainee = new Trainee(fullName, gender, role, email, phone, 
                                           birthday, citizenIdentification, height, 
                                           weight, traineeId, password, level, addressId);

        // Attempt to add the trainee
        if (traineeService.addTrainee(newTrainee) > 0) {
            System.out.println("✅ Trainee added successfully!");
            return true; // Return true if added successfully
        } else {
            System.out.println("❌ Failed to add trainee. Please check your input.");
            return false; // Return false if there was an error
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

    public boolean updateTrainee() {
        String traineeId = Utils.readString("📇 Enter Trainee ID: ");
        Trainee trainee = traineeService.getTraineeById(traineeId);
        
        if (trainee == null) {
            System.out.println("❌ Trainee not found.");
            return false; // Return false if trainee does not exist
        }

        // Display current trainee information
        displayCurrentTraineeInfo(trainee);

        // Update trainee attributes
        boolean isUpdating = true;

        while (isUpdating) {
            isUpdating = updateTraineeAttributes(trainee);
        }

        return true; // Return true after successful updates
    }

    private void displayCurrentTraineeInfo(Trainee trainee) {
        System.out.println("\n📋 Current Trainee Information:");
        System.out.println(String.format("ID: %s | Name: %s | Gender: %s | Role: %s | Email: %s | Phone: %s | Citizen ID: %s | Height: %d cm | Weight: %.2f kg | Birthday: %s",
                trainee.getTraineeId(), trainee.getFullName(), trainee.getGender(),
                trainee.getRole(), trainee.getEmail(), trainee.getPhone(),
                trainee.getCitizenIdentification(), trainee.getHeight(),
                trainee.getWeight(), trainee.getBirthday()));
    }

    private boolean updateTraineeAttributes(Trainee trainee) {
        System.out.println("\n🔄 Select the attribute to update:");
        System.out.println("1. Full Name");
        System.out.println("2. Gender");
        System.out.println("3. Role");
        System.out.println("4. Email");
        System.out.println("5. Phone");
        System.out.println("6. Citizen Identification");
        System.out.println("7. Height (cm)");
        System.out.println("8. Weight (kg)");
        System.out.println("9. Birthday (yyyy-MM-dd)");
        System.out.println("10. Level");
        System.out.println("11. Address ID");
        System.out.println("12. Exit");

        int choice = Utils.readInt("👉 Your choice: ");

        switch (choice) {
            case 1:
                trainee.setFullName(Utils.readString("Enter new Full Name: "));
                break;
            case 2:
                trainee.setGender(Utils.readString("Enter new Gender: "));
                break;
            case 3:
                trainee.setRole(Utils.readString("Enter new Role: "));
                break;
            case 4:
                trainee.setEmail(Utils.readEmail("Enter new Email: "));
                break;
            case 5:
                trainee.setPhone(Utils.readPhone("Enter new Phone: "));
                break;
            case 6:
                trainee.setCitizenIdentification(Utils.readString("Enter new Citizen Identification: "));
                break;
            case 7:
                trainee.setHeight(Utils.readInt("Enter new Height (cm): "));
                break;
            case 8:
                trainee.setWeight(Utils.readPositiveDouble("Enter new Weight (kg): "));
                break;
            case 9:
                LocalDate birthday = Utils.readDate("Enter new Birthday (yyyy-MM-dd): ");
                trainee.setBirthday(birthday);
                break;
            case 10:
                trainee.setLevel(Utils.readString("Enter new Level: "));
                break;
            case 11:
                trainee.setAddressId(Utils.readString("Enter new Address ID: "));
                break;
            case 12:
                return false; // Exit the update loop
            default:
                System.out.println("❌ Invalid choice. Please try again.");
                return true; // Stay in the loop for a valid choice
        }

        // Update the trainee in the service after each change
        traineeService.updateTrainee(trainee);
        System.out.println("✅ Trainee updated successfully.");
        
        return true; // Continue updating
    }

 // Thêm mới Course
    public boolean createCourse() {
       
        
        // Hiển thị danh sách các Coach và yêu cầu người dùng chọn
        List<Coach> coaches = coachService.getAllCoaches();
        if (!displayCoaches(coaches)) {
            return false; // Không có coach nào
        }

        String coachId = Utils.readString("Enter Coach ID: ");
        if (!isValidCoach(coachId, coaches)) {
            return false; // Coach ID không hợp lệ
        }

        String courseName = Utils.readString("Enter Course Name: ");
        String description = Utils.readString("Enter Course Description: ");
        String courseType = Utils.readString("Enter Course Type: ");
        LocalDate startDate = Utils.readDate("Enter Course Start Date (yyyy-MM-dd): ");
        LocalDate endDate = Utils.readDate("Enter Course End Date (yyyy-MM-dd): ");
        double price = Utils.readDouble("Enter Course Price: ");
        int maxParticipants = Utils.readInt("Enter Max Participants: ");
        int totalSessions = Utils.readInt("Enter Total Sessions: ");

        Course newCourse = new Course(generateCourseId(), courseName, description, coachId, courseType, maxParticipants, startDate, endDate, price, totalSessions);
        
        // Tạo lịch tập cho khóa học
        createSchedules(newCourse);
        
        // Tạo TakeWorkout cho khóa học
        createTakeWorkouts(newCourse);
        
        // Thêm khóa học vào CourseService
        if (courseService.addCourse(newCourse) > 1) {
            System.out.println("✅ Course created successfully!");
            return true;
        }
        
        System.out.println("❌ Failed to create the course.");
        return false;
    }

    // Hiển thị danh sách các Coach
    private boolean displayCoaches(List<Coach> coaches) {
        if (coaches.isEmpty()) {
            System.out.println("🚫 No coaches available. Please add a coach first.");
            return false;
        }
        
        System.out.printf("%-10s %-20s %-10s %-10s%n", "ID", "Name", "Level", "Phone");
        System.out.println("-------------------------------------------------");
        for (Coach coach : coaches) {
            System.out.printf("%-10s %-20s %-10s %-10s%n", 
                              coach.getCoachId(), 
                              coach.getFullName(), 
                              coach.getLevel(), 
                              coach.getPhone());
        }
        return true;
    }

    // Kiểm tra Coach ID hợp lệ
    private boolean isValidCoach(String coachId, List<Coach> coaches) {
        return coaches.stream().anyMatch(coach -> coach.getCoachId().equals(coachId));
    }

    // Tạo lịch cho khóa học
    private void createSchedules(Course course) {
        List<Schedule> schedules = new ArrayList<>();
        int numSchedules = Utils.readInt("Enter number of schedules for this course: ");
        
        for (int i = 0; i < numSchedules; i++) {
            LocalDate date = Utils.readDate("Enter Schedule Date (yyyy-MM-dd): ");
            LocalTime startTime = Utils.readTime("Enter Start Time (HH:mm): ");
            LocalTime endTime = Utils.readTime("Enter End Time (HH:mm): ");
            
            Schedule schedule = new Schedule(generateScheduleId(), course.getCourseId(), date, startTime, endTime);
            schedules.add(schedule);
            scheduleService.addSchedule(schedule);
        }
    }

    // Tạo TakeWorkout cho khóa học
    private void createTakeWorkouts(Course course) {
        List<TakeWorkout> takeWorkouts = new ArrayList<>();
        
        System.out.println("📋 Available Workouts:");
        List<Workout> workouts = workoutService.getAllWorkouts();
        displayWorkouts(workouts);
        
        String workoutIdsInput = Utils.readString("Enter Workout IDs for this course (separated by commas): ");
        String[] workoutIds = workoutIdsInput.split(",");
        
        for (String workoutId : workoutIds) {
            workoutId = workoutId.trim();
            TakeWorkout takeWorkout = new TakeWorkout(course.getCourseId(), workoutId);
            takeWorkouts.add(takeWorkout);
            takeWorkoutService.addTakeWorkout(takeWorkout);
        }
    }

    // Hiển thị danh sách các Workout
    private void displayWorkouts(List<Workout> workouts) {
        System.out.printf("%-10s %-20s %-10s %-10s %-30s %-20s%n", "ID", "Name", "Duration", "Level", "Instruction", "Equipment");
        System.out.println("-------------------------------------------------------------");
        for (Workout workout : workouts) {
            System.out.printf("%-10s %-20s %-10d %-10s %-30s %-20s%n", 
                              workout.getWorkoutId(), 
                              workout.getWorkoutName(),
                              workout.getDuration(), 
                              workout.getLevel(), 
                              workout.getInstruction(),
                              workout.getEquipmentRequired());
        }
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
            String newCourseName = Utils.readString("Enter new Course Name: ");
            course.setCourseName(newCourseName);
            break;
        case 2:
            String newDescription = Utils.readString("Enter new Description: ");
            course.setCourseDescription(newDescription);
            break;
        case 3:
            String newCoachId = Utils.readCoachId("Enter new Coach ID: ");
            course.setCoachID(newCoachId);
            break;
        case 4:
            String newCourseType = Utils.readString("Enter new Course Type: ");
            course.setCourseType(newCourseType);
            break;
        case 5:
            int newMaxParticipants = Utils.readInt("Enter new Max Participants: ");
            course.setMaxParticipants(newMaxParticipants);
            break;
        case 6:
            LocalDate newStartDate = Utils.readDate("Enter new Start Date (yyyy-MM-dd): ");
            course.setStartDate(newStartDate);
            break;
        case 7:
            LocalDate newEndDate = Utils.readDate("Enter new End Date (yyyy-MM-dd): ");
            course.setEndDate(newEndDate);
            break;
        case 8:
            double newPrice = Utils.readPositiveDouble("Enter new Price: ");
            course.setPrice(newPrice);
            break;
        case 9:
            int newTotalSessions = Utils.readInt("Enter new Total Sessions: ");
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
