package com.example.jason.habittracker;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jason on 02/10/2016.
 */
public class LogDates {
    private ArrayList<Date> logList;

    public LogDates(){
        this.logList = new ArrayList<Date>();
    }

    public void add(Date date){
        logList.add(date);
    }

    public void remove(int position){
        logList.remove(position);
    }

    public ArrayList<Date> getLogList() {
        return logList;
    }

    public void setLogList(ArrayList<Date> logList) {
        this.logList = logList;
    }

}
