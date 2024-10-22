package service;

import dao.TakeWorkoutDAO;
import model.TakeWorkout;

import java.util.ArrayList;
import java.util.List;

public class TakeWorkoutService {
    private static TakeWorkoutDAO takeWorkoutDAO;
    private static List<TakeWorkout> takeWorkoutCache; // Cache to store TakeWorkout records

    static {
        takeWorkoutDAO = TakeWorkoutDAO.getInstance();
        takeWorkoutCache = new ArrayList<>();
        loadTakeWorkouts(); // Load all TakeWorkout records from the database into the cache on initialization
    }

    // Load the list of TakeWorkout records from the database into the cache
    private static void loadTakeWorkouts() {
        takeWorkoutCache = takeWorkoutDAO.selectAll();
    }

    public int addTakeWorkout(TakeWorkout takeWorkout) {
        int result = takeWorkoutDAO.insert(takeWorkout);
        if (result > 0) {
            takeWorkoutCache.add(takeWorkout); // Update cache if insertion is successful
        }
        return result;
    }

    public int updateTakeWorkout(TakeWorkout takeWorkout) {
        int result = takeWorkoutDAO.update(takeWorkout);
        if (result > 0) {
            // Update the TakeWorkout in the cache
            for (int i = 0; i < takeWorkoutCache.size(); i++) {
                if (takeWorkoutCache.get(i).getCourseId().equals(takeWorkout.getCourseId()) &&
                    takeWorkoutCache.get(i).getWorkoutId().equals(takeWorkout.getWorkoutId())) {
                    takeWorkoutCache.set(i, takeWorkout);
                    break;
                }
            }
        }
        return result;
    }

    public int deleteTakeWorkout(String courseId, String workoutId) {
        TakeWorkout takeWorkout = new TakeWorkout();
        takeWorkout.setCourseId(courseId);
        takeWorkout.setWorkoutId(workoutId);
        int result = takeWorkoutDAO.delete(takeWorkout);
        if (result > 0) {
            // Remove TakeWorkout from cache
            takeWorkoutCache.removeIf(tw -> 
                tw.getCourseId().equals(courseId) && tw.getWorkoutId().equals(workoutId));
        }
        return result;
    }

    public List<TakeWorkout> getAllTakeWorkouts() {
        return new ArrayList<>(takeWorkoutCache); // Return a copy of the list of TakeWorkouts
    }

    public TakeWorkout getTakeWorkoutById(String courseId, String workoutId) {
        // Find TakeWorkout in the cache
        for (TakeWorkout takeWorkout : takeWorkoutCache) {
            if (takeWorkout.getCourseId().equals(courseId) && takeWorkout.getWorkoutId().equals(workoutId)) {
                return takeWorkout;
            }
        }
        return null; // If not found
    }

    public List<TakeWorkout> getTakeWorkoutsByCondition(String condition) {
        // Filter TakeWorkout records by condition in the cache
        List<TakeWorkout> filteredTakeWorkouts = new ArrayList<>();
        for (TakeWorkout takeWorkout : takeWorkoutCache) {
            // Add filtering logic here, e.g.:
            if (takeWorkout.getCourseId().contains(condition) || takeWorkout.getWorkoutId().contains(condition)) {
                filteredTakeWorkouts.add(takeWorkout);
            }
        }
        return filteredTakeWorkouts;
    }
}
