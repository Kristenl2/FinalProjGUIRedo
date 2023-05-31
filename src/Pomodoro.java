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

    public Pomodoro(){
        setup();
       // createUIComponents();
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
    }

    public void actionPerformed(ActionEvent ea){
        Object source = ea.getSource();
        if(source instanceof JButton){
            JButton button = (JButton)source;
            String name = button.getText();

            if(name.equals(pomodoro)){

            }
        }
    }



//    private void createUIComponents(){
//        setContentPane(screen);
//        setTitle("PomodoroTimer");
//    }
}
