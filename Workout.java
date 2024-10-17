import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;
// import java.awt.GraphicsEnvironment;

// this class is kind of useless LOL

public class Workout {
    public static int totalWorkouts = 5;
    public static Timer timer = new Timer();
    public static TimerTask task;
    public static WorkoutPanel panel = new WorkoutPanel();
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        scan.close();
        // String[] fonts = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        // for (String font : fonts) {
        //     System.out.println(font);
        // }
    }
}