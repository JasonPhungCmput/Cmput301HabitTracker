/*
   Copyright 2016 Jason Phung

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package com.example.jason.habittracker;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Jason on 28/09/2016.
 */
/*
    Class Habit which stores the characteristics chosen for the habits to be stored
    Two different constructors where one is when the date is automatically inputted
    or one where the date is inputted by user
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
        return newDate.format(date);
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

    public String toString(){
        return getDate() + " \n| " + habitTitle + " \n| " + daysOfWeek;
    }

    public boolean isRecentlyCompleted() {
        return recentlyCompleted;
    }

    public void setRecentlyCompleted(boolean recentlyCompleted) {
        this.recentlyCompleted = recentlyCompleted;
    }
}
