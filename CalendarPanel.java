import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.*;
import java.io.*;
import java.text.*;



public class CalendarPanel extends JPanel {
    private CalendarCell[][] cc;
    private CalendarCell[][] cells;
    private ControlPanel cop;
    private int current_year,current_month;
    private ArrayList<Schedule> sc_list;

    public CalendarPanel(){
        setLayout(new GridLayout(6,7,1,1));
        cc=new CalendarCell[6][7];
        for(int i=0;i<6;i++){
            for(int j=0;j<7;j++){
                add(cc[i][j]=new CalendarCell());
            }
        }
        sc_list=new ArrayList<Schedule>();
        // B : ファイルから予定を読み込む
        loadSchedules();
    }

    public void setControlPanel(ControlPanel cop){
        this.cop=cop;
    }

    public void setYearMonth(int y,int m){
        Calendar cal=Calendar.getInstance();
        cal.set(y,m-1,1);
        int dow=cal.get(Calendar.DAY_OF_WEEK);
        int d_max=cal.getActualMaximum(Calendar.DATE);
        int d=1;
        clearAllCells();
        for(int i=0;i<6;i++){
            for(int j=0;j<7;j++){
                if(i==0 && Calendar.SUNDAY+j<dow){
                    continue;
                }
                if(d>d_max){
                    continue;
                }
                cc[i][j].setDate(y,m,d);
                d++;
            }
        }

        current_year=y;
        current_month=m;
        addAllSchedulesToCell();
        cop.setYearMonth(y,m);
        validate();
        repaint();
    }

    private void clearAllCells(){
        for(int i=0;i<6;i++){
            for(int j=0;j<7;j++){
                // C
                cc[i][j].clear();
            }
        }
    }

    public void setYear(int y){
        setYearMonth(y,current_month);
    }

    public void setMonth(int m){
        setYearMonth(current_year,m);
    }

    public void nextYear(){
        setYearMonth(++current_year,current_month);
    }

    public void prevYear(){
        setYearMonth(--current_year,current_month);
    }

    public void nextMonth(){
        if(++current_month>12){
            current_year++;
            current_month=1;
        }
        setYearMonth(current_year,current_month);
    }

    public void prevMonth(){
        if(--current_month<1){
            current_year--;
            current_month=12;
        }
        setYearMonth(current_year,current_month);
    }

    public void addSchedule(Schedule sc){
        sc_list.add(sc);
        CalendarCell cc=getCell(sc);
        if(cc!=null){
            cc.addSchedule(sc);
        }
    }

    private CalendarCell getCell(Schedule sc){
        int m=sc.getMonth();
        int d=sc.getDate();
        if(m!=current_month) return null;
        Calendar cal=Calendar.getInstance();
        cal.set(current_year,m-1,1);
        int dow=cal.get(Calendar.DAY_OF_WEEK);
        int d_max=cal.getActualMaximum(Calendar.DATE);
        int p=dow+d-Calendar.SUNDAY-1;
        int i=p/7;
        int j=p%7;
        return cc[i][j];
    }

    private void addAllSchedulesToCell(){
        for(Schedule sc:sc_list){
            CalendarCell cc=getCell(sc);
            if(cc==null) continue;
            cc.addSchedule(sc);
        }
    }

    private String file_name="schedules.csv";

    public void saveSchedules(){
        try{
            PrintWriter pw=new PrintWriter(new FileWriter(file_name));
            pw.println(sc_list.size());
            for(Schedule sc:sc_list){
                pw.println(sc.toCSV());
            }
            pw.close();
        }catch(IOException e){
            System.err.println(e);
        }
    }

    public void loadSchedules(){
        try{
            sc_list.clear();
            BufferedReader br=new BufferedReader(new FileReader(file_name));
            int n=Integer.parseInt(br.readLine());
            for(int i=0;i<n;i++){
                Schedule sc=new Schedule(br.readLine());
                sc_list.add(sc);
            }
            br.close();
        }catch(IOException e){
            System.err.println(e);
        }catch(ParseException e){
            System.err.println(e);
        }

    }

}
