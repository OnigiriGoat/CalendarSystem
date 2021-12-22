/***
 *** 月設定者
 ***/
import java.awt.*;
import java.awt.event.*;

public class MonthSetter implements ItemListener{
  private CalendarPanel cp;

  // 生成する
  public MonthSetter(CalendarPanel cp){
    this.cp=cp;  // カレンダーパネルを覚えておく
  }

  // アイテムリスナー
  public void itemStateChanged(ItemEvent ev){
    String it=(String)ev.getItem();            // 選ばれた項目
    int l=it.indexOf('月');                    // 月の位置を得る
    int m=Integer.parseInt(it.substring(0,l)); // 月の前の文字を数値に変換
    cp.setMonth(m);                            // 月をセット
  }
}