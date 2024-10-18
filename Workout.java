import java.util.Timer;
import java.util.TimerTask;
// import java.awt.GraphicsEnvironment;

// this class is kind of useless LOL.
// everything's kind of already done inside the
// WorkoutPanel class, but I feel like a non-panel
// main-type class should still be in here.

public class Workout {
    public static Timer timer = new Timer();
    public static TimerTask task;
    public static WorkoutGUI GUI = new WorkoutGUI();
    public static void main(String[] args) {
    }
}