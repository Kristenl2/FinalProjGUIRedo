import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingScreen extends JFrame implements ActionListener, ChangeListener {
    private JSlider slider1;
    private JSlider slider2;
    private JSlider slider3;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JPanel whole;
    private JLabel time1;
    private JLabel time2;
    private JLabel time3;
    private static Color Bg = new Color(137,154,156);
    private static Color Tc = new Color(228,207,203);

    public SettingScreen() {
    setTitle("Settings");
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    setSize(650,400);
    setLocation(500,250);
    createUIComponents();

    button1.addActionListener(this);
    button2.addActionListener(this);
    button3.addActionListener(this);
    button4.addActionListener(this);
    slider1.addChangeListener(this);
    slider2.addChangeListener(this);
    slider3.addChangeListener(this);
    }

    private void createUIComponents(){
        setContentPane(whole);
        setTitle("PomodoroTimer");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            String name = button.getText();
            if(name.equals("Green")){
                Bg = new Color(204,214,166);
                Tc = new Color(244,234,213);
            } else if(name.equals("Light")){
                Bg = new Color(137,154,156);
                Tc = new Color(228,207,203);
            } else if(name.equals("Blue")){
                Bg = new Color(169,220,227);
                Tc = new Color(136,167,222);
            }else if(name.equals("Pink")){
                Bg = new Color(242,190,209);
                Tc = new Color(249,245,246);
            }
        }
    }

    public static Color getBg(){
        return Bg;
    }

    public static Color getTc(){
        return Tc;
    }

    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        JSlider slider = (JSlider) source;
        int value = slider.getValue();
        if(source == slider1){
            Pomodoro.changeTime(value,"Pomodoro");
            time1.setText(value + "");
        } else if(source == slider2){
            Pomodoro.changeTime(value,"Short");
            time2.setText(value + "");
        } else if(source == slider3){
            Pomodoro.changeTime(value,"Long");
            time3.setText(value + "");
        }
    }

}
