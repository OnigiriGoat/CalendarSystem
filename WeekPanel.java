/***
 *** 週パネル
 ***/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class WeekPanel extends JPanel {

  // 生成する
  public WeekPanel(){
    String[] week={ "日","月","火","水","木","金","土" }; // 曜日文字
    setLayout(new GridLayout(1,7,1,1));         // 1行7列で、最後の1,1は隙間
    for(String s:week){                                 // すべての曜日について
      JLabel l=new JLabel(s,SwingConstants.CENTER);     // ラベルを生成し、
      if(s.equals("日")) l.setForeground(Color.red);    // 日曜は赤
      if(s.equals("土")) l.setForeground(Color.blue);   // 土曜は青
      add(l);    // このパネルに追加する
    }
  }
}