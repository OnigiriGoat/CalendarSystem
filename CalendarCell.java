/***
 *** カレンダーのセル
 ***/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class CalendarCell extends JPanel {
  private Calendar date;  // このセルが表す日付
  private JPanel panel;

  // 生成する
  public CalendarCell(){
    setBackground(Color.white);                  // 背景は白
    setLayout(new FlowLayout(FlowLayout.LEFT));  // 左詰めのFlowLayout
    panel=new JPanel();
    panel.setLayout(new GridLayout(0,1));
    setLayout(new BorderLayout());
    add(panel,BorderLayout.NORTH);
  }

  // 日付をセットする
  public void setDate(int y,int m,int d){
    date=Calendar.getInstance();             // 日付を生成する
    date.set(y,m-1,d);                       // 指定された日付にする
    int dow=date.get(Calendar.DAY_OF_WEEK);  // その日の曜日を得る
    JLabel l=new JLabel(""+d);               // 日のラベルを生成する
    if(dow==Calendar.SUNDAY) l.setForeground(Color.red);    // 日曜日なら赤
    if(dow==Calendar.SATURDAY) l.setForeground(Color.blue); // 土曜日なら青
    panel.add(l);  // ラベルを置く
  }

  public void clear(){
    panel.removeAll();
  }

  public void addSchedule(Schedule sc){
    ScheduleLabel a=new ScheduleLabel(sc);
    panel.add(a);
  }
}