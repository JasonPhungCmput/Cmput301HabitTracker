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

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Date;
/*
    Class showing the activity that displays all the habit completions based on the date completed
 */
public class HabitLogActivity extends AppCompatActivity {
    private HabitList habitList;
    private ArrayAdapter<Date> adapter;
    private ListView habitListViewLog;
    private LogDates logOfDates;
    private ArrayList<Date> dateList = new ArrayList<Date>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_log);
        habitListViewLog = (ListView) findViewById(R.id.listViewLog);
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Load habit completion log files and show them using an array adapter
        habitList = new HabitList();
        Bundle extras = getIntent().getExtras();
        final String names = extras.getString("Name of habit:");
        logOfDates = FileInputOutput.loadFromLogFile(names,logOfDates,getBaseContext());
        dateList = logOfDates.getLogList();
        adapter = new ArrayAdapter<Date>(this,R.layout.list_habit_log, dateList);
        habitListViewLog.setAdapter(adapter);


        habitListViewLog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // Delete a habit completion entry when clicked
                habitList = FileInputOutput.loadFromFile("file.sav",habitList,getBaseContext());
                Toast.makeText(getBaseContext(),"Past habit completion deleted.",Toast.LENGTH_SHORT).show();
                logOfDates.remove(i);
                FileInputOutput.saveInLogFile(names,logOfDates,getBaseContext());
                adapter.notifyDataSetChanged();
            }
        });

    }

}
