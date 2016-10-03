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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Jason on 29/09/2016.
 */
/*
    Custom array adapter to show the habits in a list view. The habit date, name and days can be
    seen with buttons like delete habit, complete habit and showing habit completions. The habit
    can also be clicked on to go to the habit history.
 */
public class HabitAdapter extends ArrayAdapter<Habit>{
    private HabitList habitList;
    private LogDates dateLogged;
    private ArrayList<Habit> addTimesCompleted;

    public HabitAdapter(Context context,ArrayList<Habit> habitListView){
        super(context,0, habitListView);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Habit habit = getItem(position);

        // Inflate view if there is none
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_habit_layout, parent, false);
        }

        // Get the components from the xml design
        final TextView habitDate = (TextView) convertView.findViewById(R.id.habitAdapterDate);
        final TextView habitTitle = (TextView) convertView.findViewById(R.id.habitAdapterName);
        final TextView habitDays = (TextView) convertView.findViewById(R.id.habitAdapterDays);
        final TextView doneText = (TextView) convertView.findViewById(R.id.recentlyDone);
        Button deleteHabit = (Button) convertView.findViewById(R.id.deleteButton);
        Button completeHabit = (Button) convertView.findViewById(R.id.habitComplete);
        Button timesDoneHabit = (Button) convertView.findViewById(R.id.timesFinished);

        habitDate.setText(habit.getDate());
        habitTitle.setText(habit.getHabitTitle());
        String habitDaysOfWeek = "";
        // Get the days of the week
        for(int i = 0; i < habit.sizeOfWeeks(); i ++){
            habitDaysOfWeek += habit.getDaysOfWeek().get(i) + ",";
        }
        habitDays.setText(habitDaysOfWeek);

        if(habit.isRecentlyCompleted() == true){
            doneText.setText("Activity recently completed.");
        } else {
            doneText.setText("Activity not completed.");
        }
        // Get the row position
        doneText.setTag(position);
        deleteHabit.setTag(position);
        completeHabit.setTag(position);
        timesDoneHabit.setTag(position);

        habitList = new HabitList();
        dateLogged = new LogDates();

        deleteHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                habitList = FileInputOutput.loadFromFile("file.sav",habitList,getContext());
                int position = (Integer) view.getTag();
                Habit habit = getItem(position);
                // Show message saying habit deleted
                Toast.makeText(getContext(),habit.getHabitTitle()+ " has been removed.",Toast.LENGTH_SHORT).show();
                habitList.removeHabit(position);
                FileInputOutput.saveInFile("file.sav",habitList,getContext());
                FileInputOutput.removeFile(habit.getHabitTitle(),getContext());
                // Update adapter to show new data
                HabitAdapter adapter = new HabitAdapter(getContext(), habitList.getHabitList());
                MainActivity.habitListView.setAdapter(adapter);
                MainActivity.habitListView.invalidateViews();
            }
        });

        completeHabit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                habitList = FileInputOutput.loadFromFile("file.sav",habitList,getContext());
                int position = (Integer) view.getTag();
                Habit habitDone;
                // Getting the habits completion number and increasing it then setting it back
                addTimesCompleted = habitList.getHabitList();
                habitDone = addTimesCompleted.get(position);
                habitDone.setTimesDone(habitDone.getTimesDone() + 1);
                habitDone.setRecentlyCompleted(true);
                addTimesCompleted.set(position,habitDone);
                habitList.setHabitList(addTimesCompleted);

                dateLogged = FileInputOutput.loadFromLogFile(habitDone.getHabitTitle(),dateLogged,getContext());
                // Get current date
                Calendar dateTime = Calendar.getInstance();
                dateLogged.add(dateTime.getTime());
                FileInputOutput.saveInFile("file.sav",habitList,getContext());
                FileInputOutput.saveInLogFile(habitDone.getHabitTitle(),dateLogged,getContext());

                HabitAdapter adapter = new HabitAdapter(getContext(), habitList.getHabitList());
                MainActivity.habitListView.setAdapter(adapter);
                MainActivity.habitListView.invalidateViews();
                Toast.makeText(getContext(),habitDone.getHabitTitle() + " has been completed.",Toast.LENGTH_SHORT).show();
            }
        });


        timesDoneHabit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Display the times a habit has been completed in a window
                habitList = FileInputOutput.loadFromFile("file.sav",habitList,getContext());
                int position = (Integer) view.getTag();
                Habit habitTimesComplete = getItem(position);
                Toast.makeText(getContext(),"Habit completed " + habitTimesComplete.getTimesDone() + " times.", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;

    }

}


