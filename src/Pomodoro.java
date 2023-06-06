import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.DefaultListModel;

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
    private JList tasks;
    private JLabel task;
    private JTextField textField1;
    private JPanel Tasks;
    private JButton add;
    private JButton remove;
    private JPanel insert;
    private Timer time;
    private int seconds;
    private boolean isOn;
    private ArrayList<String> allTasks;



    public Pomodoro() {
        time = new Timer(1000, null);
        allTasks = new ArrayList<>();
        setup();
        createUIComponents();
    }

    private void setup() {
        setContentPane(screen);
        setTitle("PomodoroTimer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600);
        setLocation(500, 250);
        setVisible(true);

        countdown.setFont(new Font("Helvetica", Font.BOLD,20));
        countdown.setText("00:00");

        task.setText("Type your task in the box below and click Add: ");

        pomodoro.addActionListener(this);
        shortBreak.addActionListener(this);
        longBreak.addActionListener(this);
        start.addActionListener(this);
        time.addActionListener(this);
        time.start();
        add.addActionListener(this);
        remove.addActionListener(this);
        //tasks = new DefaultListModel<>();
    }

    public void actionPerformed(ActionEvent ea) {
        Object source = ea.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            String name = button.getText();

            if (name.equals("Pomodoro")) {
                isOn = false;
                start.setText("Start");
                seconds = 1500;
                countdown.setText("25:00");
            } else if (name.equals("Short Break")){
                isOn = false;
                start.setText("Start");
                seconds = 300;
                countdown.setText("5:00");
            } else if (name.equals("Long Break")) {
                isOn = false;
                start.setText("Start");
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
            }else if(name.equals("Add")){
                if(textField1.getText().length()>0){
                    addTasks();
                }

            }else if(name.equals("Remove")){
              removeTasks();

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

    private void addTasks(){
        allTasks.add(textField1.getText());
        String t[] = new String[allTasks.size()];
        for(int i = 0; i<allTasks.size(); i++){
            t[i] = allTasks.get(i);
        }
        textField1.setText("");
        if (allTasks != null && allTasks.size() > 0) {
            tasks.setListData(t);
        }
    }

    private void removeTasks(){
        int selectedIndex = tasks.getSelectedIndex();
        if (selectedIndex != -1) {
            allTasks.remove(selectedIndex);
            String t[] = new String[allTasks.size()];
            for(int i = 0; i<allTasks.size(); i++){
                t[i] = allTasks.get(i);
            }
            tasks.setListData(t);
        }
    }


    private void createUIComponents(){
        setContentPane(screen);
        setTitle("PomodoroTimer");
    }

}