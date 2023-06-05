import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Pomodoro extends JFrame implements ActionListener {
    private JButton pomodoro;
    private JButton shortBreak;
    private JButton longBreak;
    private JButton start;
    private JPanel screen;
    private JPanel top;
    private JPanel middle;
    private JLabel countdown;
    private JPanel bottom;
    private Timer time;
    private int seconds;
    private boolean isOn;


    public Pomodoro() {
        time = new Timer(1000, null);
        setup();
        createUIComponents();
    }

    private void setup() {
        setContentPane(screen);
        setTitle("PomodoroTimer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 320);
        setLocation(500, 250);
        setVisible(true);

        countdown.setFont(new Font("Helvetica", Font.BOLD,20));
        countdown.setText("0:00");

        pomodoro.addActionListener(this);
        shortBreak.addActionListener(this);
        longBreak.addActionListener(this);
        start.addActionListener(this);
        time.addActionListener(this);
        time.start();
    }

    public void actionPerformed(ActionEvent ea) {
        Object source = ea.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            String name = button.getText();

            if (name.equals("Pomodoro")) {
                seconds = 1500;
                countdown.setText("25:00");
            } else if (name.equals("Short Break")){
                seconds = 300;
                countdown.setText("5:00");
            } else if (name.equals("Long Break")) {
                seconds = 600;
                countdown.setText("10:00");
            } else if (name.equals("Start")){
                start.setText("Pause");
                isOn = true;
                time.start();
            } else if (name.equals("Pause")){
                time.stop();
                countdown.setText(seconds/60 + ":" + seconds%60);
                start.setText("Start");
                isOn = false;
            }
        } else if (source instanceof Timer) {
            if(isOn) {
                timerFires();
            }
        }
    }

    private void timerFires() {
        if(seconds <= 0){
            time.stop();
            countdown.setText("Time's up!");
        }else{
            seconds--;
            int mins = seconds/60;
            int secs = seconds%60;
            countdown.setText(mins + ":" + secs);
        }
    }

    private void createUIComponents(){
        setContentPane(screen);
        setTitle("PomodoroTimer");
    }
}