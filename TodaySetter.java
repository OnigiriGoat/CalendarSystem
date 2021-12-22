/***
 *** 月設定者
 ***/
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import java.io.*;   // 例外処理
import java.util.Calendar;   // 現在の年・月を取得

public class TodaySetter implements ActionListener{

  private CalendarPanel cp;

  // 生成する
  public TodaySetter(CalendarPanel cp){
    this.cp=cp;  // カレンダーパネルを覚えておく
  }

  // アイテムリスナー
  public void actionPerformed(ActionEvent ev){
    try{
      //System.out.println(ev);

      // 年・月を取得
      // 参考URL : https://www.javadrive.jp/start/calendar/index2.html
      Calendar calendar = Calendar.getInstance();
      int year = calendar.get(Calendar.YEAR);
      int month = calendar.get(Calendar.MONTH) + 1;
    
      cp.setYear(year);// 今日のをセット
      cp.setMonth(month);

      
    }catch(Exception e){
      System.out.println("TodaySetter : ERROR");
    }
  }
}