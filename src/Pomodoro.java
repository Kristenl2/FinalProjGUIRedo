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
    private JButton stop;

    private Timer time;

    private int seconds;


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
                timerFires();
            } else if (name.equals("Short Break")){
                seconds = 300;
                timerFires();
            } else if (name.equals("Long Break")) {
                seconds = 600;
                timerFires();
            }

        } else if (source instanceof Timer) {
            timerFires();
        }
    }

    private void timerFires() {
        seconds--;
        int mins = seconds/60;
        int secs = seconds%60;
        countdown.setText(mins + ":" + secs);
        if(seconds == 0){
            time.stop();
            countdown.setText("Time's up!");
        }
    }





    private void createUIComponents(){
        setContentPane(screen);
        setTitle("PomodoroTimer");
    }
}
