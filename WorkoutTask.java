import java.util.TimerTask;

public class WorkoutTask extends TimerTask {
    private String[] workoutList;
    public static int count = 0;
    public WorkoutTask(String[] list) {
        workoutList = list;
    }
    public void run() {
        //if statement to determine whether or not the workout should finish 
        if (count == Workout.totalWorkouts) {
            Workout.timer.cancel();
            System.out.println("You finished your workouts! Congratulations!");
        } else {
            System.out.println(workoutList[count++]);
        }
    }
}
