import java.io.*;
import java.text.*;
import java.util.*;

/***
 *** 予定
 ***/
public class Schedule {
  private String title;
  private Date date;
  private boolean allday;
  private String place;
  private String memo;

  //生成する
  public Schedule(String ti, String dt, String tm, boolean ad, String pl, String dl) throws ParseException {
    title = ti;
    SimpleDateFormat sdf;
    sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    date = sdf.parse(dt + " " + tm);
    allday = ad;
    place = pl;
    memo = dl;
  }

  //csvから予定を生成する
  public Schedule(String csv) throws ParseException{
    fromCSV(csv);
  }

  public int getYear(){
    Calendar cl=Calendar.getInstance();
    cl.setTime(date);
    return cl.get(Calendar.YEAR);
  }

  public int getMonth(){
    Calendar cl=Calendar.getInstance();
    cl.setTime(date);
    return cl.get(Calendar.MONTH)+1;
  }

  public int getDate(){
    Calendar cl=Calendar.getInstance();
    cl.setTime(date);
    return cl.get(Calendar.DATE);
  }

  //文字列化
  public String toString() {
    return "名称：" + title + "¥n" +
      "日時：" + date + "¥n" +
      "終日：" + (allday ? "〇" : "×") + "¥n" +
      "場所：" + place + "¥n" +
      "メモ：" + memo + "¥n";
  }

  public String toShortString(){
    if(allday)
      return title;
    SimpleDateFormat sdf;
    sdf=new SimpleDateFormat("HH:mm");
    return sdf.format(date)+" "+title;
  }

  //予定をcsv化
  public String toCSV() {
    String memo2 = memo.replace("¥n", "<<NEWLINE>>");
    SimpleDateFormat sdf;
    sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm");
    return title+","
        +sdf.format(date)+","
        +(allday?"1":"0")+","
        +place+","
        +memo2;
  } 

  //csvから予定を設定
  public void fromCSV(String csv) throws ParseException{
      String[] tok = csv.split(",");
      title = tok[0];
      SimpleDateFormat sdf;
      sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
      date = sdf.parse(tok[1]);
      allday =tok[2].equals("1");
      place = tok[3];
      memo = tok[4].replace("<<NEWLINE>>", "¥n");
  }
}
 
