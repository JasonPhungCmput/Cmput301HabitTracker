package com.example.jason.habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

public class HabitLogActivity extends AppCompatActivity {
    private HabitList habitListsTest;
    private ArrayAdapter<Date> adapter;
    private ListView habitListViewLog;
    private String nameOfHabit;
    private LogDates logOfDates;
    private ArrayList<Date> dateList = new ArrayList<Date>();
    private ArrayList<Habit> changeCompletions;
    //private ArrayList<Habit> habitList = new ArrayList<Habit>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit_log);
        habitListViewLog = (ListView) findViewById(R.id.listViewLog);
        //nameOfHabit = getIntent().getStringExtra();
    }

    @Override
    protected void onStart() {
        super.onStart();
        habitListsTest = new HabitList();
        Bundle extras = getIntent().getExtras();
        final String names = extras.getString("Name of habit:");
        //Toast.makeText(this, names + "yayaya", Toast.LENGTH_LONG).show();

        //habitListsTest = new HabitList();
        //Date [] logDate = {
          //      new Date(), new Date()
        //};

        //logHabit = changeCompletions.contains(names);

/*
        addTimesCompleted = habitListsTest.getHabitList();
        habitDone = addTimesCompleted.get(position1);
        habitDone.setTimesDone(habitDone.getTimesDone() + 1);
        habitDone.setRecentlyCompleted(true);
        addTimesCompleted.set(position1,habitDone);
        habitListsTest.setHabitList(addTimesCompleted);
        */
        //logHabit = habitListsTest.getHabitList();
        logOfDates = FileInputOutput.loadFromLogFile(names,logOfDates,getBaseContext());
        //loadFromFile();
        dateList = logOfDates.getLogList();
        //Toast.makeText(getBaseContext(), logOfDates.getLogList() + "", Toast.LENGTH_SHORT).show();
        adapter = new ArrayAdapter<Date>(this,R.layout.list_habit_log, dateList);
        habitListViewLog.setAdapter(adapter);


        habitListViewLog.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Intent intent = new Intent(this,HabitLogActivity.class);
                //Habit habitLog = adapter.getItem(i);
                //positionClick = i;
                //intent.putExtra("Name of habit:", habitLog.getHabitTitle());
                //Toast.makeText(getBaseContext(), habitLog.getHabitTitle() + "yeyeye", Toast.LENGTH_SHORT).show();
                //adapter.getPosition();
                //Intent.putExtra(KEY, i );
                //startActivity(intent);
                //Toast.makeText(getBaseContext(), logOfDates.getLogList() + "", Toast.LENGTH_SHORT).show();

                habitListsTest = FileInputOutput.loadFromFile("file.sav",habitListsTest,getBaseContext());

                /*
        addTimesCompleted = habitListsTest.getHabitList();
        habitDone = addTimesCompleted.get(position1);
        habitDone.setTimesDone(habitDone.getTimesDone() + 1);
        habitDone.setRecentlyCompleted(true);
        addTimesCompleted.set(position1,habitDone);
        habitListsTest.setHabitList(addTimesCompleted);
        */

                /*
                Habit logHabit;
                changeCompletions = habitListsTest.getHabitList();
                int position = changeCompletions.indexOf(names);

                logHabit = changeCompletions.get(position);

                logHabit.setTimesDone(logHabit.getTimesDone() - 1);
                changeCompletions.set(position, logHabit);
                habitListsTest.setHabitList(changeCompletions);
                FileInputOutput.saveInFile("file.sav",habitListsTest,getBaseContext());

                Toast.makeText(getBaseContext(),"Past habit completion deleted.",Toast.LENGTH_SHORT).show();
                logOfDates.remove(i);
                FileInputOutput.saveInLogFile(names,logOfDates,getBaseContext());
                adapter.notifyDataSetChanged();
                */
            }
        });

    }

/*
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(MainActivity.FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            //Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            Type listType = new TypeToken<HabitList>(){}.getType();

            habitListsTest = gson.fromJson(in,listType);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            //habitList = new ArrayList<Habit>();
            HabitList habitListsTest;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }
    */
}
