/***
 *** 年月変更者
 ***/
import java.awt.*;
import java.awt.event.*;

public class YearMonthChanger implements ActionListener{
  private CalendarPanel cp;

  // 生成する
  public YearMonthChanger(CalendarPanel cp){
    this.cp=cp; // カレンダーパネルを覚えておく
  }

  // アクションリスナー
  public void actionPerformed(ActionEvent ev){
    switch(ev.getActionCommand()){
    case "<<": cp.prevYear(); break;  // <<なら年を戻す
    case "<" : cp.prevMonth(); break; // < なら月を戻す
    case ">" : cp.nextMonth(); break; // > なら月を進める
    case ">>": cp.nextYear(); break;  // >>なら年を進める
    }
  }
}