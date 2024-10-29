Use FITNESS;

delete from  Coaches;

INSERt INTO Addresses values ('AD-1234', 'Da Nang', 'Ngo May', 36);
INSERT INTO Coaches values ('CO-1234', 'Ngo Hoang Bach','Female', 'Coach', 'bachngo@gmail.com','0916345789','1234567893456',179,65.4,'2004-10-16','Bach@1234','Beginer','AD-1234', 600000.5);
select * from Coaches;

-- Inserting new records into Addresses
INSERT INTO Addresses VALUES ('AD-5678', 'Hanoi', 'Tran Quoc Toan', 15);
INSERT INTO Addresses VALUES ('AD-7890', 'Ho Chi Minh City', 'Le Loi', 100);

-- Inserting new records into Coaches
INSERT INTO Coaches VALUES ('CO-5678', 'Tran Thi Thanh','Female', 'Coach', 'thanhtran@gmail.com','0911223344','7894561237896',165,58.2,'1990-05-20','Thanh@1234','Intermediate','AD-5678', 700000.75);
INSERT INTO Coaches VALUES ('CO-7890', 'Le Van Minh','Male', 'Coach', 'minhle@gmail.com','0987123456','9876543210123',180,80.5,'1985-08-12','Minh@1234','Advanced','AD-7890', 850000.90);
-- Inserting additional records into Addresses
INSERT INTO Addresses VALUES ('AD-3456', 'Hue', 'Le Thanh Ton', 50);
INSERT INTO Addresses VALUES ('AD-4567', 'Da Nang', 'Phan Chau Trinh', 120);
INSERT INTO Addresses VALUES ('AD-6789', 'Can Tho', 'Ly Tu Trong', 65);

-- Inserting additional records into Coaches
INSERT INTO Coaches VALUES ('CO-3456', 'Nguyen Thi Lan','Female', 'Coach', 'lannguyen@gmail.com','0901223344','3216549870123',160,55.5,'1987-11-30','Lan@1234','Beginner','AD-3456', 550000.00);
INSERT INTO Coaches VALUES ('CO-4567', 'Pham Quoc Binh','Male', 'Coach', 'binhpham@gmail.com','0911223345','6547893216547',175,75.0,'1982-07-22','Binh@1234','Intermediate','AD-4567', 750000.25);
INSERT INTO Coaches VALUES ('CO-6789', 'Le Hoang Son','Male', 'Coach', 'sonle@gmail.com','0922334455','9638527413698',185,82.3,'1992-04-15','Son@1234','Advanced','AD-6789', 850000.90);
INSERT INTO Coaches VALUES ('CO-7891', 'Hoang Thi Thu','Female', 'Coach', 'thuthu@gmail.com','0933445566','8521479632587',165,60.0,'1989-09-10','Thu@1234','Advanced','AD-3456', 880000.75);


INSERT INTO Addresses VALUES ('AD-0001', 'Da Nang', 'Hoang Sam', 54);
INSERT INTO Addresses VALUES ('AD-0002', 'Da Nang', 'Tran Hung Dao', 23);
INSERT INTO Addresses VALUES ('AD-0003', 'Da Nang', 'Nguyen Van Troi', 77);
INSERT INTO Addresses VALUES ('AD-0004', 'Da Nang', 'Le Duan', 12);
INSERT INTO Addresses VALUES ('AD-0005', 'Da Nang', 'Khai Son', 89);
INSERT INTO Addresses VALUES ('AD-0006', 'Da Nang', 'Xuan Thieu', 34);
INSERT INTO Addresses VALUES ('AD-0007', 'Da Nang', 'Phan Chau Trinh', 101);
INSERT INTO Addresses VALUES ('AD-0008', 'Da Nang', 'Nguyen Hue', 15);
INSERT INTO Addresses VALUES ('AD-0009', 'Da Nang', 'Hoang Hoa Tham', 67);
INSERT INTO Addresses VALUES ('AD-0010', 'Da Nang', 'Hai Phong', 8);


INSERT INTO Trainees VALUES ('TR-0001', 'Trainee@1234', 'Nguyen Van A', 'Male', 'Beginner', 'User', 'vana@gmail.com', '0912345678', '123456789', 175.0, 70.0, '2000-01-15', 'AD-0001');
INSERT INTO Trainees VALUES ('TR-0002', 'Trainee@1234', 'Tran Thi B', 'Female', 'Intermediate', 'User', 'btran@gmail.com', '0912345679', '987654321', 160.0, 55.0, '1995-02-20', 'AD-0002');
INSERT INTO Trainees VALUES ('TR-0003', 'Trainee@1234', 'Le Van C', 'Male', 'Advanced', 'User', 'cle@gmail.com', '0912345680', '456123789', 180.0, 80.0, '1992-03-25', 'AD-0003');
INSERT INTO Trainees VALUES ('TR-0004', 'Trainee@1234', 'Pham Thi D', 'Female', 'Beginner', 'User', 'dpham@gmail.com', '0912345681', '321654987', 165.0, 58.0, '1998-04-30', 'AD-0004');
INSERT INTO Trainees VALUES ('TR-0005', 'Trainee@1234', 'Hoang Van E', 'Male', 'Intermediate', 'User', 'ehoang@gmail.com', '0912345682', '987123654', 172.0, 76.0, '1991-05-05', 'AD-0005');
INSERT INTO Trainees VALUES ('TR-0006', 'Trainee@1234', 'Nguyen Thi F', 'Female', 'Advanced', 'User', 'fnguyen@gmail.com', '0912345683', '654789321', 158.0, 52.0, '1993-06-10', 'AD-0006');
INSERT INTO Trainees VALUES ('TR-0007', 'Trainee@1234', 'Vu Van G', 'Male', 'Beginner', 'User', 'gvuvan@gmail.com', '0912345684', '123789456', 177.0, 68.0, '1990-07-15', 'AD-0007');
INSERT INTO Trainees VALUES ('TR-0008', 'Trainee@1234', 'Nguyen Van H', 'Male', 'Intermediate', 'User', 'hnguyen@gmail.com', '0912345685', '789456123', 165.0, 63.0, '1989-08-20', 'AD-0008');
INSERT INTO Trainees VALUES ('TR-0009', 'Trainee@1234', 'Tran Thi I', 'Female', 'Advanced', 'User', 'itran@gmail.com', '0912345686', '456789321', 170.0, 57.0, '1994-09-25', 'AD-0009');
INSERT INTO Trainees VALUES ('TR-0010', 'Trainee@1234', 'Pham Van J', 'Male', 'Beginner', 'User', 'jpham@gmail.com', '0912345687', '321789456', 178.0, 75.0, '1988-10-30', 'AD-0010');


INSERT INTO Workouts VALUES ('WO-1234', 'Squats', 30, 'Beginner', 'A fundamental exercise for building lower body strength.', 'None');
INSERT INTO Workouts VALUES ('WO-1235', 'Plank', 15, 'Beginner', 'An isometric core strength exercise that targets the abdominals.', 'None');
INSERT INTO Workouts VALUES ('WO-1236', 'Lunges', 25, 'Intermediate', 'A lower body exercise that works the legs and glutes.', 'None');
INSERT INTO Workouts VALUES ('WO-1237', 'Burpees', 20, 'Advanced', 'A full-body exercise that combines squats, jumps, and push-ups.', 'None');
INSERT INTO Workouts VALUES ('WO-1238', 'Push-Ups', 20, 'Intermediate', 'A bodyweight exercise that strengthens the chest, shoulders, and triceps.', 'None');
INSERT INTO Workouts VALUES ('WO-1239', 'Deadlifts', 45, 'Advanced', 'A weightlifting exercise that targets the back, glutes, and hamstrings.', 'Barbell');
INSERT INTO Workouts VALUES ('WO-1240', 'Mountain Climbers', 15, 'Intermediate', 'A cardio move that also strengthens the core and legs.', 'None');
INSERT INTO Workouts VALUES ('WO-1241', 'Jumping Jacks', 10, 'All Levels', 'A full-body warm-up exercise that increases heart rate.', 'None');
INSERT INTO Workouts VALUES ('WO-1242', 'Bicycle Crunches', 20, 'Intermediate', 'A core exercise that targets the obliques and rectus abdominis.', 'None');
INSERT INTO Workouts VALUES ('WO-1243', 'Tricep Dips', 15, 'Beginner', 'An upper body exercise that targets the triceps using a bench or chair.', 'Bench or chair');
INSERT INTO Workouts VALUES ('WO-1241', 'Jumping Jacks', 10, 'All Levels', 'A full-body warm-up exercise that increases heart rate.', 'None');
INSERT INTO Workouts VALUES ('WO-1242', 'Bicycle Crunches', 20, 'Intermediate', 'A core exercise that targets the obliques and rectus abdominis.', 'None');
INSERT INTO Workouts VALUES ('WO-1243', 'Tricep Dips', 15, 'Beginner', 'An upper body exercise that targets the triceps using a bench or chair.', 'Bench or chair');
INSERT INTO Workouts VALUES ('WO-1244', 'Russian Twists', 20, 'Intermediate', 'A rotational exercise targeting the obliques.', 'Medicine ball');
INSERT INTO Workouts VALUES ('WO-1245', 'Kettlebell Swings', 30, 'Intermediate', 'A dynamic exercise that works the entire body, focusing on the hips and core.', 'Kettlebell');
INSERT INTO Workouts VALUES ('WO-1246', 'Bench Press', 40, 'Advanced', 'A weightlifting exercise that targets the chest, shoulders, and triceps.', 'Barbell');
INSERT INTO Workouts VALUES ('WO-1247', 'Dead Bug', 15, 'Beginner', 'A core exercise that improves coordination and stability.', 'None');
INSERT INTO Workouts VALUES ('WO-1248', 'Box Jumps', 25, 'Advanced', 'A plyometric exercise that increases power and explosiveness.', 'Box or platform');
INSERT INTO Workouts VALUES ('WO-1249', 'Yoga Sun Salutation', 20, 'All Levels', 'A series of poses that stretch and strengthen the body.', 'Yoga mat');
INSERT INTO Workouts VALUES ('WO-1250', 'Pull-Ups', 20, 'Advanced', 'An upper body exercise that targets the back and biceps.', 'Pull-up bar');
INSERT INTO Workouts VALUES ('WO-1251', 'Side Lunges', 25, 'Intermediate', 'A variation of lunges that targets the inner thighs.', 'None');
INSERT INTO Workouts VALUES ('WO-1252', 'Wall Sit', 15, 'Beginner', 'An isometric exercise that strengthens the legs and glutes.', 'None');
INSERT INTO Workouts VALUES ('WO-1253', 'Skaters', 20, 'Intermediate', 'A lateral movement that increases agility and coordination.', 'None');
INSERT INTO Workouts VALUES ('WO-1254', 'Hip Thrusts', 30, 'Intermediate', 'A glute-focused exercise that enhances hip strength.', 'Bench');
INSERT INTO Workouts VALUES ('WO-1255', 'Seated Rows', 30, 'Intermediate', 'A strength training exercise targeting the back muscles.', 'Resistance band or cable machine');
INSERT INTO Workouts VALUES ('WO-1256', 'Chest Fly', 30, 'Intermediate', 'A weightlifting exercise that targets the chest muscles.', 'Dumbbells');
INSERT INTO Workouts VALUES ('WO-1257', 'Leg Raises', 15, 'Beginner', 'An abdominal exercise that strengthens the lower abs.', 'None');
INSERT INTO Workouts VALUES ('WO-1258', 'Burpee Broad Jump', 25, 'Advanced', 'A combination of burpees and broad jumps for full-body conditioning.', 'None');
INSERT INTO Workouts VALUES ('WO-1259', 'Single-Leg Deadlift', 30, 'Intermediate', 'A balance-focused exercise that targets the hamstrings.', 'Dumbbells');
INSERT INTO Workouts VALUES ('WO-1260', 'Face Pulls', 20, 'Intermediate', 'An exercise that strengthens the upper back and improves posture.', 'Resistance band or cable machine');
INSERT INTO Workouts VALUES ('WO-1261', 'Cable Woodchoppers', 25, 'Intermediate', 'A rotational exercise that targets the core.', 'Cable machine');
INSERT INTO Workouts VALUES ('WO-1262', 'Sprints', 15, 'All Levels', 'High-intensity running intervals for cardio conditioning.', 'None');
INSERT INTO Workouts VALUES ('WO-1263', 'Tire Flips', 30, 'Advanced', 'A full-body workout that builds strength and power.', 'Tire');
INSERT INTO Workouts VALUES ('WO-1264', 'Agility Ladder Drills', 20, 'All Levels', 'A series of footwork drills that improve agility and coordination.', 'Agility ladder');
INSERT INTO Workouts VALUES ('WO-1265', 'Pistol Squats', 25, 'Advanced', 'A single-leg squat that requires balance and strength.', 'None');
INSERT INTO Workouts VALUES ('WO-1266', 'Farmer’s Walk', 30, 'Intermediate', 'A strength exercise that involves carrying weights over distance.', 'Dumbbells or kettlebells');
INSERT INTO Workouts VALUES ('WO-1267', 'Dumbbell Snatch', 30, 'Advanced', 'A powerful full-body movement that enhances explosiveness.', 'Dumbbell');
INSERT INTO Workouts VALUES ('WO-1268', 'Leg Press', 40, 'Intermediate', 'A strength training exercise that targets the quads and glutes.', 'Leg press machine');
INSERT INTO Workouts VALUES ('WO-1269', 'Sledgehammer Workout', 30, 'Advanced', 'A full-body workout that enhances power and endurance.', 'Sledgehammer, Tire');
INSERT INTO Workouts VALUES ('WO-1270', 'Inchworm', 15, 'Beginner', 'A dynamic stretch that warms up the entire body.', 'None');
INSERT INTO Workouts VALUES ('WO-1271', 'Step-Ups', 20, 'Beginner', 'An exercise that works the legs and improves balance.', 'Bench or step');
INSERT INTO Workouts VALUES ('WO-1272', 'Jump Squats', 20, 'Intermediate', 'A plyometric exercise that increases lower body power.', 'None');
INSERT INTO Workouts VALUES ('WO-1273', 'Glute Bridges', 15, 'Beginner', 'An exercise that strengthens the glutes and lower back.', 'None');
INSERT INTO Workouts VALUES ('WO-1274', 'Lateral Raises', 20, 'Beginner', 'An isolation exercise that targets the shoulder muscles.', 'Dumbbells');
INSERT INTO Workouts VALUES ('WO-1275', 'Sitting Knee Tucks', 15, 'Beginner', 'A core exercise that focuses on the abdominals.', 'None');
INSERT INTO Workouts VALUES ('WO-1276', 'Windshield Wipers', 20, 'Intermediate', 'A rotational core exercise that enhances stability.', 'None');
INSERT INTO Workouts VALUES ('WO-1277', 'Bear Crawl', 20, 'All Levels', 'A full-body movement that engages multiple muscle groups.', 'None');
INSERT INTO Workouts VALUES ('WO-1278', 'Crunches', 15, 'Beginner', 'A basic abdominal exercise that targets the upper abs.', 'None');
INSERT INTO Workouts VALUES ('WO-1279', 'Flutter Kicks', 20, 'Intermediate', 'An abdominal exercise that targets the lower abs.', 'None');
INSERT INTO Workouts VALUES ('WO-1280', 'Y-T-W Raises', 25, 'Intermediate', 'A shoulder exercise that strengthens the upper back.', 'Light dumbbells');
INSERT INTO Workouts VALUES ('WO-1281', 'Chest Press', 30, 'Intermediate', 'A strength training exercise that targets the chest.', 'Dumbbells or barbell');
INSERT INTO Workouts VALUES ('WO-1282', 'Goblet Squat', 30, 'Intermediate', 'A squat variation that engages the core and lower body.', 'Kettlebell or dumbbell');
INSERT INTO Workouts VALUES ('WO-1283', 'Reverse Crunches', 20, 'Beginner', 'A core exercise that targets the lower abs.', 'None');
INSERT INTO Workouts VALUES ('WO-1284', 'Overhead Press', 30, 'Intermediate', 'A strength exercise that targets the shoulders and triceps.', 'Dumbbells or barbell');
INSERT INTO Workouts VALUES ('WO-1285', 'Ski Jumps', 20, 'Intermediate', 'A cardio exercise that increases heart rate and coordination.', 'None');


SELECT * FROM Workouts;
SELECT * FROM Coaches;
INSERT INTO Courses VALUES ('CS-1234', 'Beginner Yoga', 'A relaxing course focusing on basic yoga poses and breathing techniques.', 'CO-1234', 'Yoga', 20, '2024-11-01', '2024-12-01', 150.00, '8 sessions');
INSERT INTO Courses VALUES ('CS-1235', 'Advanced Pilates', 'An intensive Pilates course designed for those with experience in core workouts.', 'CO-3456', 'Pilates', 15, '2024-11-05', '2024-12-05', 200.00, '10 sessions');
INSERT INTO Courses VALUES ('CS-1236', 'High-Intensity Interval Training', 'A challenging course combining cardio and strength training for maximum results.', 'CO-6789', 'HIIT', 25, '2024-11-10', '2024-12-10', 180.00, '12 sessions');
INSERT INTO Courses VALUES ('CS-1237', 'Kickboxing Basics', 'An energetic course that introduces participants to kickboxing techniques and fitness.', 'CO-7890', 'Martial Arts', 20, '2024-11-15', '2024-12-15', 160.00, '8 sessions');
INSERT INTO Courses VALUES ('CS-1238', 'Zumba Dance Party', 'A fun and engaging dance workout that combines Latin and international music.', 'CO-5678', 'Dance', 30, '2024-11-20', '2024-12-20', 120.00, '10 sessions');


-- Beginner Yoga
INSERT INTO TakeWorkouts VALUES ('CS-1234', 'WO-1234'); -- Squat
INSERT INTO TakeWorkouts VALUES ('CS-1234', 'WO-1235'); -- Plank
INSERT INTO TakeWorkouts VALUES ('CS-1234', 'WO-1244'); -- Bridge
INSERT INTO TakeWorkouts VALUES ('CS-1234', 'WO-1245'); -- Child's Pose
INSERT INTO TakeWorkouts VALUES ('CS-1234', 'WO-1246'); -- Warrior I
INSERT INTO TakeWorkouts VALUES ('CS-1234', 'WO-1247'); -- Tree Pose
INSERT INTO TakeWorkouts VALUES ('CS-1234', 'WO-1248'); -- Cat-Cow Stretch

-- Advanced Pilates
INSERT INTO TakeWorkouts VALUES ('CS-1235', 'WO-1236'); -- Deadlift
INSERT INTO TakeWorkouts VALUES ('CS-1235', 'WO-1240'); -- Leg Raises
INSERT INTO TakeWorkouts VALUES ('CS-1235', 'WO-1252'); -- Side Plank
INSERT INTO TakeWorkouts VALUES ('CS-1235', 'WO-1253'); -- Teaser
INSERT INTO TakeWorkouts VALUES ('CS-1235', 'WO-1254'); -- Roll Up
INSERT INTO TakeWorkouts VALUES ('CS-1235', 'WO-1255'); -- Pilates Push-Up
INSERT INTO TakeWorkouts VALUES ('CS-1235', 'WO-1256'); -- Scissors

-- HIIT
INSERT INTO TakeWorkouts VALUES ('CS-1236', 'WO-1238'); -- Jumping Jacks
INSERT INTO TakeWorkouts VALUES ('CS-1236', 'WO-1239'); -- Burpees
INSERT INTO TakeWorkouts VALUES ('CS-1236', 'WO-1242'); -- High Knees
INSERT INTO TakeWorkouts VALUES ('CS-1236', 'WO-1243'); -- Mountain Climbers
INSERT INTO TakeWorkouts VALUES ('CS-1236', 'WO-1249'); -- Tuck Jumps
INSERT INTO TakeWorkouts VALUES ('CS-1236', 'WO-1250'); -- Battle Ropes
INSERT INTO TakeWorkouts VALUES ('CS-1236', 'WO-1251'); -- Box Jumps

-- Kickboxing Basics
INSERT INTO TakeWorkouts VALUES ('CS-1237', 'WO-1240'); -- Shadowboxing
INSERT INTO TakeWorkouts VALUES ('CS-1237', 'WO-1241'); -- Roundhouse Kick
INSERT INTO TakeWorkouts VALUES ('CS-1237', 'WO-1244'); -- Jab Cross Combo
INSERT INTO TakeWorkouts VALUES ('CS-1237', 'WO-1245'); -- Hook Punch
INSERT INTO TakeWorkouts VALUES ('CS-1237', 'WO-1246'); -- Front Kick
INSERT INTO TakeWorkouts VALUES ('CS-1237', 'WO-1247'); -- Knee Strikes
INSERT INTO TakeWorkouts VALUES ('CS-1237', 'WO-1248'); -- Defensive Moves

-- Zumba Dance Party
INSERT INTO TakeWorkouts VALUES ('CS-1238', 'WO-1238'); -- Salsa
INSERT INTO TakeWorkouts VALUES ('CS-1238', 'WO-1239'); -- Reggaeton
INSERT INTO TakeWorkouts VALUES ('CS-1238', 'WO-1242'); -- Cumbia
INSERT INTO TakeWorkouts VALUES ('CS-1238', 'WO-1243'); -- Merengue
INSERT INTO TakeWorkouts VALUES ('CS-1238', 'WO-1249'); -- Hip Hop
INSERT INTO TakeWorkouts VALUES ('CS-1238', 'WO-1250'); -- Bachata
INSERT INTO TakeWorkouts VALUES ('CS-1238', 'WO-1251'); -- Dance Fitness


SELECT *From Courses;

-- Course 1: Beginner Yoga
INSERT INTO Courses VALUES ('CS-1001', 'Beginner Yoga Foundations', 'A comprehensive course focusing on the fundamentals of yoga with an emphasis on breathing and alignment.', 'CO-1234', 'Yoga', 20, '2024-10-01', '2024-10-30', 150.00, '8 sessions');

-- Course 2: Advanced Pilates
INSERT INTO Courses VALUES ('CS-1005', 'Advanced Pilates Challenge', 'An intensive course designed for advanced practitioners to deepen their Pilates practice.', 'CO-4567', 'Pilates', 15, '2024-10-05', '2024-11-05', 200.00, '10 sessions');

-- Course 3: HIIT for All Levels
INSERT INTO Courses VALUES ('CS-1010', 'HIIT for All Levels', 'A high-intensity interval training course that accommodates all fitness levels.', 'CO-7891', 'HIIT', 25, '2024-10-10', '2024-11-10', 180.00, '12 sessions');

-- Course 4: Kickboxing Fundamentals
INSERT INTO Courses VALUES ('CS-1015', 'Kickboxing Fundamentals', 'Learn the basics of kickboxing in a fun and energetic environment.', 'CO-3456', 'Martial Arts', 20, '2024-10-15', '2024-11-15', 160.00, '8 sessions');

-- Course 5: Zumba for Beginners
INSERT INTO Courses VALUES ('CS-1020', 'Zumba for Beginners', 'Join this fun and engaging dance workout designed for beginners.', 'CO-5678', 'Dance', 30, '2024-10-20', '2024-11-20', 120.00, '10 sessions');


-- Taking workouts for Beginner Yoga Foundations (CS-1001)
INSERT INTO TakeWorkouts VALUES ('CS-1001', 'WO-1234');  -- Squats
INSERT INTO TakeWorkouts VALUES ('CS-1001', 'WO-1235');  -- Plank
INSERT INTO TakeWorkouts VALUES ('CS-1001', 'WO-1241');  -- Jumping Jacks
INSERT INTO TakeWorkouts VALUES ('CS-1001', 'WO-1243');  -- Tricep Dips
INSERT INTO TakeWorkouts VALUES ('CS-1001', 'WO-1247');  -- Dead Bug
INSERT INTO TakeWorkouts VALUES ('CS-1001', 'WO-1273');  -- Glute Bridges
INSERT INTO TakeWorkouts VALUES ('CS-1001', 'WO-1278');  -- Crunches

-- Taking workouts for Advanced Pilates Challenge (CS-1005)
INSERT INTO TakeWorkouts VALUES ('CS-1005', 'WO-1236');  -- Lunges
INSERT INTO TakeWorkouts VALUES ('CS-1005', 'WO-1238');  -- Push-Ups
INSERT INTO TakeWorkouts VALUES ('CS-1005', 'WO-1244');  -- Russian Twists
INSERT INTO TakeWorkouts VALUES ('CS-1005', 'WO-1245');  -- Kettlebell Swings
INSERT INTO TakeWorkouts VALUES ('CS-1005', 'WO-1250');  -- Pull-Ups
INSERT INTO TakeWorkouts VALUES ('CS-1005', 'WO-1254');  -- Hip Thrusts
INSERT INTO TakeWorkouts VALUES ('CS-1005', 'WO-1258');  -- Burpee Broad Jump

-- Taking workouts for HIIT for All Levels (CS-1010)
INSERT INTO TakeWorkouts VALUES ('CS-1010', 'WO-1237');  -- Burpees
INSERT INTO TakeWorkouts VALUES ('CS-1010', 'WO-1240');  -- Mountain Climbers
INSERT INTO TakeWorkouts VALUES ('CS-1010', 'WO-1246');  -- Bench Press
INSERT INTO TakeWorkouts VALUES ('CS-1010', 'WO-1251');  -- Side Lunges
INSERT INTO TakeWorkouts VALUES ('CS-1010', 'WO-1262');  -- Sprints
INSERT INTO TakeWorkouts VALUES ('CS-1010', 'WO-1263');  -- Tire Flips
INSERT INTO TakeWorkouts VALUES ('CS-1010', 'WO-1280');  -- Y-T-W Raises

-- Taking workouts for Kickboxing Fundamentals (CS-1015)
INSERT INTO TakeWorkouts VALUES ('CS-1015', 'WO-1239');  -- Deadlifts
INSERT INTO TakeWorkouts VALUES ('CS-1015', 'WO-1242');  -- Bicycle Crunches
INSERT INTO TakeWorkouts VALUES ('CS-1015', 'WO-1264');  -- Agility Ladder Drills
INSERT INTO TakeWorkouts VALUES ('CS-1015', 'WO-1266');  -- Farmer’s Walk
INSERT INTO TakeWorkouts VALUES ('CS-1015', 'WO-1272');  -- Jump Squats
INSERT INTO TakeWorkouts VALUES ('CS-1015', 'WO-1282');  -- Goblet Squat
INSERT INTO TakeWorkouts VALUES ('CS-1015', 'WO-1284');  -- Overhead Press

-- Taking workouts for Zumba for Beginners (CS-1020)
INSERT INTO TakeWorkouts VALUES ('CS-1020', 'WO-1241');  -- Jumping Jacks
INSERT INTO TakeWorkouts VALUES ('CS-1020', 'WO-1271');  -- Step-Ups
INSERT INTO TakeWorkouts VALUES ('CS-1020', 'WO-1279');  -- Flutter Kicks
INSERT INTO TakeWorkouts VALUES ('CS-1020', 'WO-1281');  -- Chest Press
INSERT INTO TakeWorkouts VALUES ('CS-1020', 'WO-1283');  -- Reverse Crunches
INSERT INTO TakeWorkouts VALUES ('CS-1020', 'WO-1285');  -- Ski Jumps
INSERT INTO TakeWorkouts VALUES ('CS-1020', 'WO-1277');  -- Bear Crawl

delete from TakeWorkouts;
delete from Courses;
SELECT *FROM Coaches;
INSERT INTO [dbo].[Courses] (courseId, courseName, courseDescription, coachID, courseType, maxParticipants, startDate, endDate, price, totalSession)
VALUES 
('CS-1001', 'Yoga Basics', 'An introductory course to Yoga, focusing on fundamental poses.', 'CO-1234', 'Yoga', 20, '2024-01-01', '2024-01-30', 150.00, 8),
('CS-1002', 'Beginner Strength Training', 'Strength training for beginners with basic exercises.', 'CO-3456', 'Strength', 15, '2024-02-01', '2024-02-28', 200.00, 10),
('CS-1003', 'Intermediate Cardio', 'An intermediate level cardio program to boost endurance.', 'CO-4567', 'Cardio', 25, '2024-03-01', '2024-03-30', 180.00, 12),
('CS-1004', 'Advanced HIIT', 'High-Intensity Interval Training for advanced athletes.', 'CO-7890', 'HIIT', 10, '2024-04-01', '2024-04-30', 250.00, 15);

-- Khóa học CS-1001 (Yoga Basics) - Cấp độ: Beginner
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1001', 'WO-1234'); -- Squats
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1001', 'WO-1235'); -- Plank
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1001', 'WO-1243'); -- Tricep Dips
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1001', 'WO-1247'); -- Dead Bug
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1001', 'WO-1252'); -- Wall Sit
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1001', 'WO-1257'); -- Leg Raises
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1001', 'WO-1270'); -- Inchworm

-- Khóa học CS-1002 (Beginner Strength Training) - Cấp độ: Beginner
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1002', 'WO-1234'); -- Squats
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1002', 'WO-1235'); -- Plank
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1002', 'WO-1243'); -- Tricep Dips
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1002', 'WO-1247'); -- Dead Bug
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1002', 'WO-1252'); -- Wall Sit
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1002', 'WO-1257'); -- Leg Raises
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1002', 'WO-1270'); -- Inchworm

-- Khóa học CS-1003 (Intermediate Cardio) - Cấp độ: Intermediate
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1003', 'WO-1236'); -- Lunges
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1003', 'WO-1238'); -- Push-Ups
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1003', 'WO-1240'); -- Mountain Climbers
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1003', 'WO-1242'); -- Bicycle Crunches
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1003', 'WO-1244'); -- Russian Twists
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1003', 'WO-1245'); -- Kettlebell Swings
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1003', 'WO-1248'); -- Box Jumps

-- Khóa học CS-1004 (Advanced HIIT) - Cấp độ: Advanced
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1004', 'WO-1237'); -- Burpees
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1004', 'WO-1239'); -- Deadlifts
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1004', 'WO-1246'); -- Bench Press
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1004', 'WO-1258'); -- Burpee Broad Jump
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1004', 'WO-1263'); -- Tire Flips
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1004', 'WO-1265'); -- Pistol Squats
INSERT INTO [dbo].[TakeWorkouts] (courseID, workoutID) VALUES ('CS-1004', 'WO-1267'); -- Dumbbell Snatch


SELECT * FROM Courses;

INSERT INTO [dbo].[Courses] (courseId, courseName, courseDescription, coachID, courseType, maxParticipants, startDate, endDate, price, totalSession)
VALUES 
('CS-2001', 'Full Body Workout', 'A comprehensive full-body workout course.', 'CO-5678', 'Strength', 25, '2024-10-30', '2024-12-30', 200.00, 10),
('CS-2002', 'High-Intensity Interval Training', 'An intense workout to burn calories quickly.', 'CO-4567', 'Cardio', 20, '2024-11-01', '2024-12-15', 220.00, 10),
('CS-2003', 'Pilates for Beginners', 'A beginner-friendly Pilates course focusing on core strength.', 'CO-3456', 'Pilates', 15, '2024-11-05', '2024-12-20', 180.00, 10);

-- Inserting TakeWorkout for Full Body Workout (CS-2001)
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1234', 'CS-2001');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1235', 'CS-2001');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1236', 'CS-2001');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1238', 'CS-2001');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1240', 'CS-2001');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1241', 'CS-2001');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1245', 'CS-2001');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1250', 'CS-2001');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1261', 'CS-2001');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1273', 'CS-2001');

-- Inserting TakeWorkouts for High-Intensity Interval Training (CS-2002)
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1237', 'CS-2002');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1242', 'CS-2002');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1243', 'CS-2002');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1244', 'CS-2002');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1254', 'CS-2002');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1265', 'CS-2002');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1271', 'CS-2002');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1280', 'CS-2002');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1279', 'CS-2002');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1284', 'CS-2002');

-- Inserting TakeWorkouts for Pilates for Beginners (CS-2003)
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1251', 'CS-2003');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1252', 'CS-2003');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1257', 'CS-2003');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1260', 'CS-2003');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1268', 'CS-2003');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1278', 'CS-2003');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1282', 'CS-2003');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1281', 'CS-2003');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1272', 'CS-2003');
INSERT INTO TakeWorkouts (workoutID, courseID) VALUES ('WO-1249', 'CS-2003');

use FITNESS;
SELECT * FROM Trainees;

Select * From Courses;
-- Inserting Schedules for Full Body Workout (CS-2001)
INSERT INTO [dbo].[Schedules] (scheduleID, courseID, date, startTime, endTime)
VALUES ('SC-1001', 'CS-2001', '2024-11-01', '08:00:00', '09:00:00');

INSERT INTO [dbo].[Schedules] (scheduleID, courseID, date, startTime, endTime)
VALUES ('SC-1002', 'CS-2001', '2024-11-10', '08:00:00', '09:00:00');

INSERT INTO [dbo].[Schedules] (scheduleID, courseID, date, startTime, endTime)
VALUES ('SC-1003', 'CS-2001', '2024-11-25', '08:00:00', '09:00:00');

-- Inserting Schedules for High-Intensity Interval Training (CS-2002)
INSERT INTO [dbo].[Schedules] (scheduleID, courseID, date, startTime, endTime)
VALUES ('SC-1004', 'CS-2002', '2024-11-02', '10:00:00', '11:00:00');

INSERT INTO [dbo].[Schedules] (scheduleID, courseID, date, startTime, endTime)
VALUES ('SC-1005', 'CS-2002', '2024-11-15', '10:00:00', '11:00:00');

INSERT INTO [dbo].[Schedules] (scheduleID, courseID, date, startTime, endTime)
VALUES ('SC-1006', 'CS-2002', '2024-11-24', '10:00:00', '11:00:00');

-- Inserting Schedules for Pilates for Beginners (CS-2003)
INSERT INTO [dbo].[Schedules] (scheduleID, courseID, date, startTime, endTime)
VALUES ('SC-1007', 'CS-2003', '2024-11-01', '12:00:00', '13:00:00');

INSERT INTO [dbo].[Schedules] (scheduleID, courseID, date, startTime, endTime)
VALUES ('SC-1008', 'CS-2003', '2024-11-14', '12:00:00', '13:00:00');

INSERT INTO [dbo].[Schedules] (scheduleID, courseID, date, startTime, endTime)
VALUES ('SC-1009', 'CS-2003', '2024-11-27', '12:00:00', '13:00:00');


SELECT * From Trainees;
-- Enroll trainees in Full Body Workout (CS-2001)
INSERT INTO [dbo].[EnrollCourses] (traineeID, courseID, enrollmentDate)
VALUES ('TR-0001', 'CS-2001', GETDATE());

INSERT INTO [dbo].[ErrollCourses] (traineeID, courseID, enrollmentDate)
VALUES ('TR-0002', 'CS-2001', GETDATE());

INSERT INTO [dbo].[ErrollCourses] (traineeID, courseID, enrollmentDate)
VALUES ('TR-0003', 'CS-2001', GETDATE());

-- Enroll trainees in High-Intensity Interval Training (CS-2002)
INSERT INTO [dbo].[ErrollCourses] (traineeID, courseID, enrollmentDate)
VALUES ('TR-0004', 'CS-2002', GETDATE());

INSERT INTO [dbo].[ErrollCourses] (traineeID, courseID, enrollmentDate)
VALUES ('TR-0005', 'CS-2002', GETDATE());

INSERT INTO [dbo].[ErrollCourses] (traineeID, courseID, enrollmentDate)
VALUES ('TR-0006', 'CS-2002', GETDATE());

-- Enroll trainees in Pilates for Beginners (CS-2003)
INSERT INTO [dbo].[ErrollCourses] (traineeID, courseID, enrollmentDate)
VALUES ('TR-0007', 'CS-2003', GETDATE());

INSERT INTO [dbo].[ErrollCourses] (traineeID, courseID, enrollmentDate)
VALUES ('TR-0008', 'CS-2003', GETDATE());

INSERT INTO [dbo].[ErrollCourses] (traineeID, courseID, enrollmentDate)
VALUES ('TR-0009', 'CS-2003', GETDATE());


SELECT * From EnrollCourses;


SELECT * FROM Addresses;
SELECT * FROM Appointments;
