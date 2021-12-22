/***
 *** 年設定者
 ***/
import java.awt.*;
import java.awt.event.*;

public class YearSetter implements ItemListener{
  private CalendarPanel cp;

  // 生成する
  public YearSetter(CalendarPanel cp){
    this.cp=cp; // カレンダーパネルを覚えておく
  }

  // アイテムリスナー
  public void itemStateChanged(ItemEvent ev){
    String it=(String)ev.getItem();            // 選ばれた項目
    int y=Integer.parseInt(it.substring(0,4)); // 先頭の4文字を数値へ変換
    cp.setYear(y);                             // 年をセット
  }
}