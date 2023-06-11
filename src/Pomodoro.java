import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

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
    private JPanel Setting;
    private JButton setting;
    private Timer time;
    private int seconds;
    private boolean isOn;
    private ArrayList<String> allTasks;
    private SettingScreen trial;
    private static int pMin = 25;
    private static int sMin = 5;
    private static int lMin = 10;



    public Pomodoro() {
        time = new Timer(1000, null);
        allTasks = new ArrayList<>();

        trial = new SettingScreen();

        ImageIcon image = new ImageIcon("src/gear.png");
        ImageIcon resized = resize(image,20,20);
        setting.setIcon(resized);
        validate();

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
        setting.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ea) {
        Object source = ea.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            String name = button.getText();
            if (name.equals("Pomodoro")) {
                isOn = false;
                start.setText("Start");
                seconds = pMin*60;
                countdown.setText(pMin + ":00");
            } else if (name.equals("Short Break")){
                isOn = false;
                start.setText("Start");
                seconds = sMin*60;
                countdown.setText(sMin + ":00");
            } else if (name.equals("Long Break")) {
                isOn = false;
                start.setText("Start");
                seconds = lMin*60;
                countdown.setText(lMin + ":00");
            } else if (name.equals("Start")){
                start.setText("Pause");
                isOn = true;
                time.start();
            } else if (name.equals("Pause")){
                time.stop();
                countdown.setText(seconds/60 + ":" + seconds%60);
                start.setText("Start");
                isOn = false;
            } else if (name.equals("Add")){
                if(textField1.getText().length()>0){
                    addTasks();
                }
            } else if (name.equals("Remove")) {
                removeTasks();
            } else if (name.equals("")){
                trial.setVisible(true);
            }
        } else if (source instanceof Timer) {
            if(isOn) {
                timerFires();
            }
            changeColor();
        }
    }

    public static void changeTime(int time,String type){
        if(type == "Pomodoro"){
            pMin = time;
        }else if (type == "Short"){
            sMin = time;
        }else if (type == "Long"){
            lMin = time;
        }
    }

    private void changeColor(){
        Color bg = SettingScreen.getBg();
        Color tc = SettingScreen.getTc();
        screen.setBackground(bg);
        Setting.setBackground(bg);
        top.setBackground(bg);
        middle.setBackground(bg);
        bottom.setBackground(bg);
        Tasks.setBackground(bg);
        insert.setBackground(bg);
        pomodoro.setBackground(tc);
        shortBreak.setBackground(tc);
        longBreak.setBackground(tc);
        start.setBackground(tc);
        add.setBackground(tc);
        remove.setBackground(tc);
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

    private static ImageIcon resize(ImageIcon image, int width, int height) {
        BufferedImage bi = new BufferedImage(width, height, BufferedImage.TRANSLUCENT);
        Graphics2D g2d = (Graphics2D) bi.createGraphics();
        g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
        g2d.drawImage(image.getImage(), 0, 0, width, height, null);
        g2d.dispose();
        return new ImageIcon(bi);
    }
}