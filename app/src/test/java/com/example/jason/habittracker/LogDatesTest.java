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
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Jason on 02/10/2016.
 */
public class LogDatesTest extends TestCase {
    public void testLogDates(){
        LogDates logList = new LogDates();
        assertTrue("Empty habit list", logList.size() == 0);
    }

    public void testAddLogDates(){
        LogDates logList = new LogDates();
        Date date;
        Calendar calendar = Calendar.getInstance();
        date = calendar.getTime();

        logList.add(date);
        assertTrue("The size of habit list", logList.size() == 1);
        assertTrue("There is habit in here", logList.contains(date));
    }

    public void testRemoveLogDates(){
        LogDates logList = new LogDates();
        Date date;
        Calendar calendar = Calendar.getInstance();
        date = calendar.getTime();
        logList.add(date);

        ArrayList<Date> dates = logList.getLogList();
        int index = dates.indexOf(date);
        logList.remove(index);
        assertTrue("The size of habit list", logList.size() == 0);
        assertFalse("There is no habit in there", logList.contains(date));

    }

    public void testSetLogDates(){
        LogDates logList = new LogDates();
        Date date;
        Date date1;
        Calendar calendar = Calendar.getInstance();
        date = calendar.getTime();
        date1 = calendar.getTime();

        logList.add(date);
        ArrayList<Date> dates = logList.getLogList();
        logList .add(date1);
        logList.setLogList(dates);
        assertTrue("The habit lists are equal", dates.equals(logList.getLogList()));
    }
}
