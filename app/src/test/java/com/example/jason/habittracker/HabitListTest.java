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
import junit.framework.TestCase;

import java.util.ArrayList;

/**
 * Created by Jason on 02/10/2016.
 */
public class HabitListTest extends TestCase {
    public void testHabitList(){
        HabitList habitList = new HabitList();
        assertTrue("Empty habit list", habitList.size() == 0);
    }

    public void testAddHabitList(){
        HabitList habitList = new HabitList();
        String habit = "Running";
        ArrayList<String>daysOfWeek = new ArrayList<String>();
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Wednesday");
        Habit testHabit = new Habit(habit,daysOfWeek);
        habitList.add(testHabit);
        ArrayList<Habit> habits = habitList.getHabitList();
        assertTrue("The size of habit list", habits.size() == 1);
        assertTrue("There is habit in here", habits.contains(testHabit));
    }

    public void testCheckHabitListSize(){
        HabitList habitList = new HabitList();
        String habit = "Running";
        ArrayList<String>daysOfWeek = new ArrayList<String>();
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Wednesday");
        Habit testHabit = new Habit(habit,daysOfWeek);
        habitList.add(testHabit);
        assertTrue("The size of habit list", habitList.size() == 1);
        assertTrue("There is habit in here", habitList.contains(testHabit));
    }

    public void testRemoveHabitList(){
        HabitList habitList = new HabitList();
        String habit = "Running";
        ArrayList<String>daysOfWeek = new ArrayList<String>();
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Wednesday");
        Habit testHabit = new Habit(habit,daysOfWeek);
        habitList.add(testHabit);
        ArrayList<Habit> habits = habitList.getHabitList();
        int index = habits.indexOf(testHabit);
        habitList.removeHabit(index);
        assertTrue("The size of habit list", habitList.size() == 0);
        assertFalse("There is no habit in there", habitList.contains(testHabit));

    }

    public void testSetHabitList(){
        HabitList habitList = new HabitList();
        String habit = "Running";
        String habit1 = "Flying";
        ArrayList<String>daysOfWeek = new ArrayList<String>();
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Wednesday");
        Habit testHabit = new Habit(habit,daysOfWeek);
        Habit testHabit1 = new Habit(habit1,daysOfWeek);
        habitList.add(testHabit);
        ArrayList<Habit> habits = habitList.getHabitList();
        habitList.add(testHabit1);
        habitList.setHabitList(habits);
        assertTrue("The habit lists are equal", habits.equals(habitList.getHabitList()));
    }
}
