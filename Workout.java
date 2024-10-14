import java.util.Timer;
import java.util.TimerTask;
import java.util.Scanner;

public class Workout {
    public static int totalWorkouts = 5;
    public static Timer timer = new Timer();
    public static TimerTask task;
    public static void main(String[] args) {
        
        Scanner scan = new Scanner(System.in);
        double period;
        System.out.println("Welcome to the 7-Minute Workout Program!\n");
        System.out.println("How many seconds would you like each exercise to be?");
        period = scan.nextDouble();
        System.out.println("Create a list of exercises to perform (" + totalWorkouts + " in total):");

        String[] workouts = new String[totalWorkouts];
        for (int i = 0; i < totalWorkouts; i++) {
            workouts[i] = scan.nextLine();
        }
        task = new WorkoutTask(workouts);

        System.out.println("Here's your workout, good luck!");
        //begins the workout using the timer
        timer.schedule(task, 2000, (int) (period * 1000));
        scan.close();
    }
}