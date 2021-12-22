import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ScheduleLabel extends JLabel{
  private Schedule sc;

  public ScheduleLabel(Schedule s){
    sc=s;
    setText(sc.toShortString());
    setBackground(Color.white);
    setOpaque(true);
  }
}