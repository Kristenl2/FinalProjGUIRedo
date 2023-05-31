import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Pomodoro extends JFrame implements ActionListener {
    private JButton pomodoro;
    private JButton shortBreak;
    private JButton longBreak;
    private JButton pause;
    private JPanel screen;
    private JPanel top;
    private JPanel middle;
    private JLabel countdown;
    private JPanel bottom;

    private int seconds;
    private Timer time;

    public Pomodoro(){
        setup();
        time = new Timer(1000, null);
        staticTimerLabel = new JLabel("Time Remaining");
        dynamicTimerLabel = new JLabel();
        // createUIComponents();


        this.time_label = new JLabel(hour_string + ":" + min_string + ":" + sec_string);
        this.time_label.setBounds(175,100,100,100);
        this.add(this.time_label);
    }

    private void setup(){
        setContentPane(screen);
        setTitle("PomodoroTimer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,320);
        setLocation(500,250);
        setVisible(true);

        pomodoro.addActionListener(this);
        shortBreak.addActionListener(this);
        longBreak.addActionListener(this);
        pause.addActionListener(this);
        time.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ea){
        Object source = ea.getSource();
        if(source instanceof JButton){
            JButton button = (JButton)source;
            String name = button.getText();

            if (name.equals("Pomodoro")) {
                seconds = 1500;
                timerFires();
            } else if (name.equals("Short Break")){
                seconds = 300;
                timerFires();
            } else if (name.equals("Long Break")) {
                seconds = 600;
                timerFires();
            }

        } else if (source instanceof Timer) {

        }
    }



//    private void createUIComponents(){
//        setContentPane(screen);
//        setTitle("PomodoroTimer");
//    }
}
