import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.text.*;
import java.util.*;

public class ScheduleEntry extends JDialog implements ActionListener{
  private CalendarPanel cal_panel;
  private JSpinner sp_date,sp_time;
  private JCheckBox cb_allday;
  private JTextField tf_title;
  private JTextField tf_place;
  private JTextArea ta_memo;
  private JSpinner.DateEditor de_date,de_time;

  public ScheduleEntry(CalendarPanel cp,CalendarSystem cs){
    super(cs,"予定の作成",true);
    setSize(400,400);
    Dimension d=Toolkit.getDefaultToolkit().getScreenSize();
    setLocation((d.width-400)/2,(d.height-400)/2);
    cal_panel=cp;

    SpinnerDateModel sdm=new SpinnerDateModel();
    sp_date=new JSpinner(sdm);
    de_date=new JSpinner.DateEditor(sp_date,"yyyy/MM/dd");
    sp_date.setEditor(de_date);

    sdm=new SpinnerDateModel();
    sp_time=new JSpinner(sdm);
    de_time=new JSpinner.DateEditor(sp_time,"HH:mm");
    sp_time.setEditor(de_time);


    JPanel p1=new JPanel();
    p1.setLayout(new GridLayout(4,1));

    JPanel p2=new JPanel();
    p2.setLayout(new FlowLayout(FlowLayout.LEFT));
    p2.add(new JLabel("名称："));
    p2.add(tf_title=new JTextField(30));
    p1.add(p2);

    p2=new JPanel();
    p2.setLayout(new FlowLayout(FlowLayout.LEFT));
    p2.add(new JLabel("日付："));
    p2.add(sp_date);
    p1.add(p2);


    p2=new JPanel();
    p2.setLayout(new FlowLayout(FlowLayout.LEFT));
    p2.add(new JLabel("時刻："));
    p2.add(sp_time);
    p2.add(cb_allday=new JCheckBox("終日"));
    cb_allday.addActionListener(this);
    p1.add(p2);

    p2=new JPanel();
    p2.setLayout(new FlowLayout(FlowLayout.LEFT));
    p2.add(new JLabel("場所："));
    p2.add(tf_place=new JTextField(30));
    p1.add(p2);

    setLayout(new BorderLayout());
    add(p1,BorderLayout.NORTH);

    p1=new JPanel();
    p1.setLayout(new BorderLayout());

    p1.add(new JLabel("メモ："),BorderLayout.WEST);
    p1.add(ta_memo=new JTextArea());
    add(p1);

    p1=new JPanel();

    JButton bt=new JButton("作成");
    bt.addActionListener(this);
    p1.add(bt);
    bt=new JButton("キャンセル");
    bt.addActionListener(this);
    p1.add(bt);
    add(p1,BorderLayout.SOUTH);
  }


  public void actionPerformed(ActionEvent ev){
    switch(ev.getActionCommand()){
    case "終日": changeAllDay(); break;
    case "作成": doCreate(); break;
    case "キャンセル": doCancel(); break; 
    }
  }

  private void changeAllDay(){
    boolean ck=cb_allday.isSelected();
    sp_time.setEnabled(true);
  }

  private void doCreate(){
    String ti=tf_title.getText();
    String dt=de_date.getFormat().format(sp_date.getValue());
    String tm=de_time.getFormat().format(sp_time.getValue());
    boolean ad=cb_allday.isSelected();
    String pl=tf_place.getText();
    String me=ta_memo.getText();
    try{
      Schedule sc=new Schedule(ti, dt, tm, ad, pl, me);
      cal_panel.addSchedule(sc);
      dispose(); 
    }
    catch(ParseException e){
      System.err.println("エラー：日時が不正です。");
    }
  }

  private void doCancel(){
    dispose ();
  }




}