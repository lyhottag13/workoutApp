import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


//create a panel for the workout app.
public class WorkoutGUI extends JFrame implements ActionListener {
    
    private JFrame frame;
    public JPanel panel;
    private JLabel label1, label2, balloonLabel;
    private JTextField field;
    private JButton addWorkoutsButton, beginWorkoutButton, setWorkoutsButton;
    private String[] workoutList;
    private int periodSeconds;
    private int totalWorkouts;

    // needed to count how many workouts are added to the list.
    private int index = 0;
    public WorkoutGUI() {
        
        frame = new JFrame("7-Minute Workouts");
        panel = new JPanel();
        label1 = new JLabel("Welcome to the 7-Minute Workout App!");
        label2 = new JLabel("How many exercises would you like to do?");
        
        // creates the balloon for later :)
        ImageIcon balloon = new ImageIcon("Balloons.png");
        balloonLabel = new JLabel(balloon);

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

        // creates the buttons and gives them the shortcut of alt + enter, though this is pretty awkward and I hate it.
        addWorkoutsButton.addActionListener(this);
        beginWorkoutButton.addActionListener(this);
        setWorkoutsButton.addActionListener(this);
        addWorkoutsButton.setMnemonic('\n');
        beginWorkoutButton.setMnemonic('\n');
        setWorkoutsButton.setMnemonic('\n');
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == setWorkoutsButton) {
            // creates workoutList with length of totalWorkouts and sets the screen to ask for the workouts.
            // if the input isn't a positive number, you will be reprimanded.
            try {
                totalWorkouts = Integer.parseInt(field.getText());
            } catch (Exception bad) {
                label1.setText("Enter a positive whole number!");
            }
            if (totalWorkouts <= 0) {
                label1.setText("Enter a positive whole number!");

                return; 
            }
            workoutList = new String[totalWorkouts];
            panel.remove(setWorkoutsButton);
            panel.remove(label2);
            panel.add(addWorkoutsButton);
            field.setText("");
            label1.setText("Enter each workout, one at a time.");
            cleanup();
        } else if (event.getSource() == addWorkoutsButton) {
            // sets the screen to ask for the length of the exercises.
            workoutList[index++] = field.getText();
            field.setText("");
            // changes the screen to the next once you input all workouts.
            if (index == totalWorkouts) {
                // removing then adding field is necessary to keep all components in the same order.
                panel.remove(addWorkoutsButton);
                panel.remove(field);
                label1.setText("Enter each exercise's length in seconds.");
                label2.setText("");
                panel.add(label2);
                panel.add(field);
                panel.add(beginWorkoutButton);
                cleanup();
            }
        } else if (event.getSource() == beginWorkoutButton) {
            try {
                periodSeconds = Integer.parseInt(field.getText());
                
            } catch (Exception bad) {
                label2.setText("Enter a positive whole number!");
            }
            if (periodSeconds <= 0) {
                label2.setText("Enter a positive whole number!");
                return; 
            }
            panel.remove(beginWorkoutButton);
            panel.remove(field);
            panel.add(label2);
            label2.setText("");
            cleanup();
            
            label1.setText("Starting workout!");
            Workout.task = new WorkoutTask.CountdownBetter(workoutList);
            Workout.timer.schedule(Workout.task, 4000, 1000);
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
    
    public int getPeriodSeconds() {
        return periodSeconds;
    }


    public void setBalloons(int on) {
        // on is 1, off is 0.
        switch (on) {
            case 0:
                panel.remove(balloonLabel);
                cleanup();
                break;
            case 1:
                panel.add(balloonLabel);
                cleanup();
                break;
        }
    }
}
