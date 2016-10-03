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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/*
    This class is for the activity showing the adding of a new habit
*/
public class AddHabitActivity extends AppCompatActivity {

    private ArrayList<String> selectedDays = new ArrayList<String>();
    private EditText habitText;
    private Date dateEntered = null;
    private HabitList habitList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        // Finding the components in the xml design
        final DatePicker dateChooser = (DatePicker) findViewById(R.id.datePicker);
        Button addDate = (Button) findViewById(R.id.addDate);
        Button saveButton = (Button) findViewById(R.id.saveHabit);
        Button addDaysButton = (Button) findViewById(R.id.dayButton);
        habitText = (EditText) findViewById(R.id.habitName);
        habitList = new HabitList();

        // Add date action
        addDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, dateChooser.getYear());
                calendar.set(Calendar.MONTH, dateChooser.getMonth());
                calendar.set(Calendar.DAY_OF_MONTH, dateChooser.getDayOfMonth());
                // Get the date from the dateChooser widget
                dateEntered = calendar.getTime();
                Toast.makeText(getBaseContext(),dateEntered + "--", Toast.LENGTH_SHORT).show();
            }
        });

        // Save habit action
        saveButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                habitList = FileInputOutput.loadFromFile("file.sav",habitList,getBaseContext());
                setResult(RESULT_OK);
                String nameOfHabit =  habitText.getText().toString();

                Habit newHabit = new Habit(nameOfHabit,selectedDays);
                Habit newHabitDate = new Habit(dateEntered,nameOfHabit,selectedDays);

                // Choose which constructor to use depending if user picked a date or not
                if(dateEntered != null){
                    habitList.add(newHabitDate);
                } else {
                    habitList.add(newHabit);
                }

                FileInputOutput.saveInFile("file.sav", habitList, getBaseContext());
                Toast.makeText(getBaseContext(), "Habit has been added.",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        // Choosing the days for the habit using dialog
        addDaysButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                DaysOfTheWeek daysDialog = new DaysOfTheWeek();
                daysDialog.show(getFragmentManager(),"hii");
                selectedDays = daysDialog.getDaysList();
            }

        });


    }

}
