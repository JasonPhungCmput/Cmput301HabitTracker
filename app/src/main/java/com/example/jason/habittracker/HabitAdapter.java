package com.example.jason.habittracker;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jason on 29/09/2016.
 */
public class HabitAdapter extends ArrayAdapter<Habit>{
    private HabitList habitListsTest;
    private LogDates dateLogged;
    private ArrayList<Habit> addTimesCompleted;
    //List<Boolean> checkBoxState;
    //List<String> checkBoxStuff;

    public HabitAdapter(Context context,ArrayList<Habit> habitListView){
        super(context,0, habitListView);
        //this.checkBoxState = new ArrayList<Boolean>(Collections.nCopies(habitListView.size(), true));
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Habit habit = getItem(position);
        Habit habit1 = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_habit_layout, parent, false);
        }

        final TextView habitDate = (TextView) convertView.findViewById(R.id.habitAdapterDate);
        final TextView habitTitle = (TextView) convertView.findViewById(R.id.habitAdapterName);
        final TextView habitDays = (TextView) convertView.findViewById(R.id.habitAdapterDays);
        final TextView doneText = (TextView) convertView.findViewById(R.id.recentlyDone);
        Button deleteHabit = (Button) convertView.findViewById(R.id.deleteButton);
        Button completeHabit = (Button) convertView.findViewById(R.id.habitComplete);
        Button timesDoneHabit = (Button) convertView.findViewById(R.id.timesFinished);
        //final CheckBox habitComplete = (CheckBox) convertView.findViewById(R.id.isComplete);
        //habitListViewTest = (ListView) convertView.findViewById(R.id.habitList);

        //habitComplete.setChecked(checkBoxState.get(position));
        //habitComplete.setChecked(true);
        //habitComplete.setEnabled(false);
        habitDate.setText(habit.getDate());
        habitTitle.setText(habit.getHabitTitle());
        String habitDaysOfWeek = "";
        for(int i = 0; i < habit.sizeOfWeeks(); i ++){
            habitDaysOfWeek += habit.getDaysOfWeek().get(i) + ",";
        }
        habitDays.setText(habitDaysOfWeek);
        if(habit.isRecentlyCompleted() == true){
            doneText.setText("Activity recently completed.");
        } else {
            doneText.setText("Activity not completed.");
        }

        doneText.setTag(position);

        deleteHabit.setTag(position);
        completeHabit.setTag(position);
        timesDoneHabit.setTag(position);

        //changeColours.setTag(position);

        habitListsTest = new HabitList();
        dateLogged = new LogDates();
        deleteHabit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //loadFromFile();
                habitListsTest = FileInputOutput.loadFromFile("file.sav",habitListsTest,getContext());
                int position = (Integer) view.getTag();
                Habit habit = getItem(position);
                //Habit habit = (Habit) view.getTag();

                //Toast.makeText(getContext(), habit + "", Toast.LENGTH_LONG).show();
                Toast.makeText(getContext(),habit.getHabitTitle()+ " has been removed.",Toast.LENGTH_SHORT).show();
                habitListsTest.removeHabit(position);
                FileInputOutput.saveInFile("file.sav",habitListsTest,getContext());
                FileInputOutput.removeFile(habit.getHabitTitle(),getContext());
                //getContext().deleteFile(habit.getHabitTitle());
                //saveInFile();
                HabitAdapter adapter = new HabitAdapter(getContext(), habitListsTest.getHabitList());
                //habitListViewTest.setAdapter(adapter);
                //habitListViewTest.invalidateViews();
                MainActivity.habitListView.setAdapter(adapter);
                MainActivity.habitListView.invalidateViews();
                //HabitAdapter adapter = new HabitAdapter(getContext(), habitListsTest.getHabitList());
                //adapter.set(habitListView);
                //adapter.notifyDataSetChanged();

            }
        });

        completeHabit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                //loadFromFile();
                habitListsTest = FileInputOutput.loadFromFile("file.sav",habitListsTest,getContext());
                int position1 = (Integer) view.getTag();

                //Habit habitDone = getItem(position1);
                Habit habitDone;
                addTimesCompleted = habitListsTest.getHabitList();
                habitDone = addTimesCompleted.get(position1);
                habitDone.setTimesDone(habitDone.getTimesDone() + 1);
                habitDone.setRecentlyCompleted(true);
                addTimesCompleted.set(position1,habitDone);
                habitListsTest.setHabitList(addTimesCompleted);

                dateLogged = FileInputOutput.loadFromLogFile(habitDone.getHabitTitle(),dateLogged,getContext());

                Calendar dateTime = Calendar.getInstance();
                //Toast.makeText(getContext(), dateTime.getTime() + "", Toast.LENGTH_SHORT).show();

                dateLogged.add(dateTime.getTime());
                FileInputOutput.saveInFile("file.sav",habitListsTest,getContext());
                //saveInFile();
                FileInputOutput.saveInLogFile(habitDone.getHabitTitle(),dateLogged,getContext());
                //Toast.makeText(getContext(), dateLogged.getLogList() + "", Toast.LENGTH_SHORT).show();

                HabitAdapter adapter = new HabitAdapter(getContext(), habitListsTest.getHabitList());
                //habitListViewTest.setAdapter(adapter);
                //habitListViewTest.invalidateViews();
                MainActivity.habitListView.setAdapter(adapter);
                MainActivity.habitListView.invalidateViews();
                Toast.makeText(getContext(),habitDone.getHabitTitle() + " has been completed.",Toast.LENGTH_SHORT).show();
                //habitDone = addTimesCompleted[position1];
                //habitDone.setTimesDone(habitDone.getTimesDone() + 1);
                //habitDone.getTimesDone();
                //Toast.makeText(getContext(), habitDone.getTimesDone() + "", Toast.LENGTH_SHORT).show();
            }
        });


        timesDoneHabit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                habitListsTest = FileInputOutput.loadFromFile("file.sav",habitListsTest,getContext());
                //loadFromFile();
                int position = (Integer) view.getTag();
                Habit habitTimesComplete = getItem(position);
                Toast.makeText(getContext(),"Habit completed " + habitTimesComplete.getTimesDone() + " times.", Toast.LENGTH_SHORT).show();
            }

        });

        return convertView;

    }



    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

/*
    private void loadFromFile() {
        try {
            FileInputStream fis = getContext().openFileInput(MainActivity.FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            //Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            Type listType = new TypeToken<HabitList>() {
            }.getType();
            habitListsTest = gson.fromJson(in, listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            //habitList = new ArrayList<Habit>();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }


    private void saveInFile() {
        try {
            FileOutputStream fos = getContext().openFileOutput(MainActivity.FILENAME,
                    0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(habitListsTest, out);
            out.flush();

            fos.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

*/

}


