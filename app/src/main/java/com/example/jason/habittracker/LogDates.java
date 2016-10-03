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
import java.util.Date;

/**
 * Created by Jason on 02/10/2016.
 */
/*
    Class for habit completions stored as the dates they are completed in a list
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

    public int size() {
        return logList.size();
    }

    public boolean contains(Date date) {
        return logList.contains(date);
    }
}
