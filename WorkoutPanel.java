import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


//create a panel for the workout app.
public class WorkoutPanel extends JFrame implements ActionListener {
    private JFrame frame;
    private JPanel panel;
    private JLabel label1, label2;
    private JTextField field;
    private JButton addWorkoutsButton, beginWorkoutButton, setWorkoutsButton;
    private String[] workoutList;
    private double periodSeconds;
    private int totalWorkouts;

    // needed to count how many workouts are added to the list.
    private int index = 0;

    public WorkoutPanel() {
        frame = new JFrame("7-Minute Workouts");
        panel = new JPanel();
        label1 = new JLabel("Welcome to the 7-Minute Workout App!");
        label2 = new JLabel("How many exercises would you like to do?");
        label1.setFont(new Font("Arial", Font.BOLD, 30));
        label2.setFont(new Font("Arial", Font.PLAIN, 25));
        field = new JTextField(10);
        addWorkoutsButton = new JButton("Add Exercise");
        beginWorkoutButton = new JButton("Begin");
        setWorkoutsButton = new JButton("Set Workout Size");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setPreferredSize(new Dimension(603, 300));
        frame.pack();
        frame.setVisible(true);
        frame.add(panel);

        panel.add(label1);
        panel.add(label2);
        panel.add(field);
        panel.add(setWorkoutsButton);

        addWorkoutsButton.addActionListener(this);
        beginWorkoutButton.addActionListener(this);
        setWorkoutsButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == setWorkoutsButton) {
            // creates the String[] with length of totalWorkouts.
            totalWorkouts = Integer.parseInt(field.getText());
            workoutList = new String[totalWorkouts];
            panel.remove(setWorkoutsButton);
            panel.remove(label2);
            panel.add(addWorkoutsButton);
            field.setText("");
            label1.setText("Enter each workout, one at a time.");
            cleanup();
            // sets the screen to input the workouts to the list.
        } else if (e.getSource() == addWorkoutsButton) {
            workoutList[index++] = field.getText();
            field.setText("");
            if (index == totalWorkouts) {
                panel.remove(addWorkoutsButton);
                label1.setText("Enter exercise length in seconds.");
                panel.add(beginWorkoutButton);
                cleanup();
            }
        } else if (e.getSource() == beginWorkoutButton) {
            panel.remove(beginWorkoutButton);
            panel.remove(field);
            cleanup();
            periodSeconds = Double.parseDouble(field.getText());
            label1.setText("Starting workout!");
            Workout.task = new WorkoutTask(workoutList);
            // Workout.timer.schedule(Workout.task, 2000,(int) (period * 1000));
            Workout.timer.schedule(Workout.task, 4000, (int) (periodSeconds * 1000));
        }
    }

    // used by the TimerTask
    public void setLabel(int select, String text) {
        switch (select) {
            case 1:
                label1.setText(text);
                break;
            case 2:
                label2.setText(text);
                break;
            default:
                break;
        }
    }

    public int getTotalWorkouts() {
        return totalWorkouts;
    }

    public void cleanup() {
        panel.revalidate();
        panel.repaint();
    }

    public void makeLabelVisible(int select) {
        switch (select) {
            case 1:
                panel.add(label1);
                cleanup();
                break;
            case 2:
                panel.add(label2);
                cleanup();
                break;
        }
    }
}
