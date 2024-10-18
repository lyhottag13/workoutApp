import java.util.TimerTask;

public class WorkoutTask {
    // this countdown changes the workout displayed the countdown below it.
    static class CountdownBetter extends TimerTask {
        private int count = Workout.GUI.getPeriodSeconds();
        private int whichWorkout = 0;
        private String[] workoutList;

        public CountdownBetter(String[] list) {
            workoutList = list;
        }
        public void run() {
            // runs whenever the count finishes.
            if (count == -1) {
                // ends the workout.
                if (whichWorkout == Workout.GUI.getTotalWorkouts() - 1) {
                    Workout.timer.cancel();
                    Workout.GUI.setLabel(1, "You finished your workout!");
                    Workout.GUI.setLabel(2, "Congratulations!  ");
                    Workout.GUI.setBalloons(1);
                    return;
                } else {
                    // moves onto the next exercise if this exercise isn't the last one.
                    Workout.GUI.setLabel(1, workoutList[whichWorkout++]);
                    count = Workout.GUI.getPeriodSeconds();
                }
            }
            // updates the label every second, the first label is changed here because otherwise it doesn't replace the "Starting Workout!" text as intended.
            Workout.GUI.setLabel(1, workoutList[whichWorkout]);
            Workout.GUI.setLabel(2, "Time Remaining: " + Integer.toString(count--));
        }
    }
}
