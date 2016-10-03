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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jason on 02/10/2016.
 */

public class HabitTest extends TestCase {
    public void testHabitConstruct(){
        String habitName = "Running";
        ArrayList<String>daysOfWeek = new ArrayList<String>();
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Wednesday");
        Date date;
        String formattedDate;
        Calendar calendar = Calendar.getInstance();
        date = calendar.getTime();
        SimpleDateFormat newDate = new SimpleDateFormat("yyyy-MM-dd");
        formattedDate = newDate.format(date);
        Habit habit = new Habit(date,habitName,daysOfWeek);
        assertTrue("Habit name is not equal", habitName.equals(habit.getHabitTitle()));
        assertTrue("Days of week array is not equal", daysOfWeek.equals(habit.getDaysOfWeek()));
        assertTrue("Date is not equal", formattedDate.equals(habit.getDate()));
    }

    public void testTimesDone(){
        int timesTest = 0;
        String habitName = "Running";
        ArrayList<String>daysOfWeek = new ArrayList<String>();
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Wednesday");
        Date date;
        Calendar calendar = Calendar.getInstance();
        date = calendar.getTime();
        Habit habit = new Habit(date,habitName,daysOfWeek);
        habit.setTimesDone(timesTest);
        assertTrue("Times done 0", habit.getTimesDone() == 0);
    }

    public void testSizeOfWeeks(){
        String habitName = "Running";
        ArrayList<String>daysOfWeek = new ArrayList<String>();
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Wednesday");
        Date date;
        Calendar calendar = Calendar.getInstance();
        date = calendar.getTime();
        Habit habit = new Habit(date,habitName,daysOfWeek);
        assertTrue("Size of weeks is 3", habit.sizeOfWeeks() == 3);
        ArrayList<String>daysOfWeek2 = new ArrayList<String>();
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Thursday");
        habit.setDaysOfWeek(daysOfWeek2);
        assertTrue("Array days of week is equal",habit.getDaysOfWeek().equals(daysOfWeek2));
    }

    public void testIsRecentComplete(){
        String habitName = "Running";
        ArrayList<String>daysOfWeek = new ArrayList<String>();
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Wednesday");
        Date date;
        Calendar calendar = Calendar.getInstance();
        date = calendar.getTime();
        Habit habit = new Habit(date,habitName,daysOfWeek);
        boolean isTrue = true;
        habit.setRecentlyCompleted(isTrue);
        assertTrue("Recently completed",habit.isRecentlyCompleted() == isTrue );
    }

    public void testSetDateName(){
        String habitName = "Running";
        String habitName1 = "Flying";
        ArrayList<String>daysOfWeek = new ArrayList<String>();
        daysOfWeek.add("Monday");
        daysOfWeek.add("Tuesday");
        daysOfWeek.add("Wednesday");
        Date date;
        Date date1;
        Calendar calendar = Calendar.getInstance();
        date = calendar.getTime();
        date1 = calendar.getTime();
        Habit habit = new Habit(date,habitName,daysOfWeek);
        Habit habit2 = new Habit(date1, habitName1, daysOfWeek);
        habit.setHabitTitle(habitName1);
        assertTrue("Names are equal", habit2.getHabitTitle().equals(habit.getHabitTitle()));
        habit.setDate(date1);
        assertTrue("Dates are equal", habit2.getDate().equals(habit.getDate()));

    }
}
