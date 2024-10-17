import java.util.TimerTask;

public class WorkoutTask extends TimerTask {
    private String[] workoutList;
    public static int count = 0;
    public WorkoutTask(String[] list) {
        workoutList = list;
    }
    public void run() {

        // if statement to determine whether or not the workout should finish.
        // if not, continue the list.
        if (count == Workout.panel.getTotalWorkouts()) {
            Workout.timer.cancel();

            Workout.panel.setLabel(1, "You finished your workout!");
            Workout.panel.setLabel(2, "Congratulations!  ");
            Workout.panel.makeLabelVisible(2);
        } else {
            Workout.panel.setLabel(1, workoutList[count++]);
        }
    }
}
