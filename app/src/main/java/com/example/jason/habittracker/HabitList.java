package com.example.jason.habittracker;

import java.util.ArrayList;


/**
 * Created by Jason on 01/10/2016.
 */
public class HabitList {
    private ArrayList<Habit>habitList;

    public HabitList(){
       this.habitList = new ArrayList<Habit>();
    }

    public void add(Habit habit){
        habitList.add(habit);
    }

    public void removeHabit(int position){
        habitList.remove(position);
    }

    public ArrayList<Habit> getHabitList() {
        return habitList;
    }

    public void setHabitList(ArrayList<Habit> habitList) {
        this.habitList = habitList;
    }

}
