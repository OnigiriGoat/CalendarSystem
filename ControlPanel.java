/***
 *** 制御パネル
 ***/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ControlPanel extends JPanel {
  private JComboBox cb_year,cb_month; // 選択項目

  // 生成する
  public ControlPanel(CalendarPanel cp,CalendarSystem cs){
    // 選択項目用の年と月の配列
    String[] year={ "2016年","2017年","2018年","2019年","2020年",
                    "2021年","2022年","2023年","2024年","2025年" };
    String[] month={ "1月","2月","3月","4月","5月","6月",
                     "7月","8月","9月","10月","11月","12月" };

    YearMonthChanger ymc=new YearMonthChanger(cp); // 年月変更者を生成
    YearSetter ys=new YearSetter(cp);     // 年設定者を生成
    MonthSetter ms=new MonthSetter(cp);   // 月設定者を生成
    TodaySetter ts=new TodaySetter(cp);
    ScheduleCreator sc=new ScheduleCreator(cp,cs);

    addButton("<<",ymc);                  // 年戻るボタンを生成して置く
    addButton("<",ymc);                   // 月戻るボタンを生成して置く
    
    JComboBox cb;                         // 選択項目の変数を宣言
    cb_year=new JComboBox<String>(year);  // 年用の選択項目を生成
    add(cb_year);                         // 制御パネルに置く
    cb_year.addItemListener(ys);          // 年選択者を登録
    
    cb_month=new JComboBox<String>(month);// 月用の選択項目を生成
    add(cb_month);                        // 制御パネルに置く
    cb_month.addItemListener(ms);         // 月選択者を登録
    
    // ts => ymc
    addButton("Today",ts); 
    
    addButton(">",ymc);                   // 月進むボタンを生成して置く
    addButton(">>",ymc);                  // 年進むボタンを生成して置く
    addButton("新規作成",sc); 
  }

  // ボタンを追加する
  private void addButton(String l,ActionListener al){
    JButton bt=new JButton(l);
    bt.addActionListener(al);
    add(bt);
  }

  // 年と月のセット
  public void setYearMonth(int y, int m){
    cb_year.setSelectedItem(y+"年");
    cb_month.setSelectedItem(m+"月");
  }
}