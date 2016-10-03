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

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

/*
    The first screen shown displaying an add habit button to start the habit tracker app
 */

public class MainActivity extends AppCompatActivity {

    public static final String FILENAME = "file.sav";
    private HabitList habitListsTest;
    public static ListView habitListView;

    // Call when first activity made
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button addHabit = (Button) findViewById(R.id.addNewHabit);
        habitListView = (ListView) findViewById(R.id.habitList);

        addHabit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, AddHabitActivity.class);
                startActivity(intent);
            }

        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        // Load habits to display
        habitListsTest = new HabitList();
        habitListsTest = FileInputOutput.loadFromFile(FILENAME, habitListsTest, this);
        final HabitAdapter adapter = new HabitAdapter(this, habitListsTest.getHabitList());
        habitListView.setAdapter(adapter);

        habitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,HabitLogActivity.class);
                // Store name of habit to be used in HabbitLogActivity to load/save correct file
                Habit habitLog = adapter.getItem(i);
                intent.putExtra("Name of habit:", habitLog.getHabitTitle());
                startActivity(intent);
            }
        });

    }

}
