package com.example.jason.habittracker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jason on 28/09/2016.
 */
public class Habit {
    private Date date;
    private String habitTitle;
    private int timesDone;
    private ArrayList<String>daysOfWeek;
    private boolean recentlyCompleted;

    public Habit(String habitTitle, ArrayList<String> daysOfWeek){
        this.date = new Date();
        this.habitTitle = habitTitle;
        this.daysOfWeek = daysOfWeek;
        this.timesDone = 0;
        this.recentlyCompleted = false;
    }

    public Habit(Date date, String habitTitle, ArrayList<String> daysOfWeek){
        this.date = date;
        this.habitTitle = habitTitle;
        this.daysOfWeek = daysOfWeek;
        this.timesDone = 0;
        this.recentlyCompleted = false;
    }

    public String getDate() {
        SimpleDateFormat newDate = new SimpleDateFormat("yyyy-MM-dd");
        //String duhDate = newDate.format(date);
        //return duhDate;
        return newDate.format(date);
        //return date;
    }

    public Date normalDateFormat(){
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHabitTitle() {
        return habitTitle;
    }

    public void setHabitTitle(String habitTitle) {
        this.habitTitle = habitTitle;
    }

    public int getTimesDone() {
        return timesDone;
    }

    public void setTimesDone(int timesDone) {
        this.timesDone = timesDone;
    }

    public ArrayList<String> getDaysOfWeek() {
        return daysOfWeek;
    }

    public int sizeOfWeeks(){
        return daysOfWeek.size();
    }

    public void setDaysOfWeek(ArrayList<String> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
/*
    public String dateString(){
        SimpleDateFormat newDate = new SimpleDateFormat("yyyy-MM-dd");
        return newDate.format(date);
    }
*/
    public String toString(){
        //return date.toString() + "|" + habitTitle + "|" + daysOfWeek + "|" + timesDone;
        return getDate() + " \n| " + habitTitle + " \n| " + daysOfWeek;
    }

    public boolean isRecentlyCompleted() {
        return recentlyCompleted;
    }

    public void setRecentlyCompleted(boolean recentlyCompleted) {
        this.recentlyCompleted = recentlyCompleted;
    }
}
