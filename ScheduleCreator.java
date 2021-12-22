import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ScheduleCreator implements ActionListener{
  private CalendarSystem cs;
  private CalendarPanel cp;

  public ScheduleCreator(CalendarPanel cp,CalendarSystem cs){
    this.cp=cp;
    this.cs=cs;
  }
  
  public void actionPerformed(ActionEvent ev){
    ScheduleEntry se=new ScheduleEntry(cp,cs);
    se.setVisible(true);
  }
}