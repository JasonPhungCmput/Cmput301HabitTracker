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

import java.util.ArrayList;


/**
 * Created by Jason on 01/10/2016.
 */
/*
    Class for the habits that are stored in a list
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

    public int size() {
        return habitList.size();
    }

    public boolean contains(Habit testHabit) {
        return habitList.contains(testHabit);
    }
}
