import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.text.*;
import java.util.*;

/***
 *** カレンダーシステムSP6
 ***/
public class CalendarSystem extends JFrame implements WindowListener {
  private CalendarPanel cp;

  //生成する
  public CalendarSystem() {
    super("カレンダーシステムSP6");
    setSize(600, 500);
    setLocation(100, 100);
    addWindowListener(this);
    cp=new CalendarPanel();
    WeekPanel wp=new WeekPanel();
    ControlPanel cop=new ControlPanel(cp,this);
    cp.setControlPanel(cop);
    cp.setYearMonth(2020,6);
    JPanel p=new JPanel();
    p.setLayout(new BorderLayout());
    p.add(wp,BorderLayout.NORTH);
    p.add(cp);
    add(p);
    add(cop,BorderLayout.NORTH);
  }
  public void windowOpened(WindowEvent ev){}
  public void windowClosing(WindowEvent ev){
    cp.saveSchedules();
    dispose();
  }
  public void windowClosed(WindowEvent ev){}
  public void windowActivated(WindowEvent ev){}
  public void windowDeactivated(WindowEvent ev){}
  public void windowIconified(WindowEvent ev){}
  public void windowDeiconified(WindowEvent ev){}
  
  public static void main(String[] args){
    (new CalendarSystem()).setVisible(true);
  }
}