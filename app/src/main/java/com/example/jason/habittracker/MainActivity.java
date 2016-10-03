package com.example.jason.habittracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
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

public class MainActivity extends AppCompatActivity {

    public static final String FILENAME = "file.sav";
    //test
    //private HabitList habitList;
    //Habit newHabit = new Habit(nameOfHabit,mSelectedItems);
    private ArrayList<Habit> habitList = new ArrayList<Habit>();
    private HabitList habitListsTest;
    private ArrayAdapter<Habit> adapter;
    //private ListView habitListView;
    public static ListView habitListView;
    private int positionClick;

    // Call when first activity made
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button addHabit = (Button) findViewById(R.id.addNewHabit);
        //Button deleteHabit = (Button) findViewById(R.id.deleteHabit);
        habitListView = (ListView) findViewById(R.id.habitList);






        addHabit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v){
                //deleteFile(FILENAME);
                Intent intent = new Intent(MainActivity.this, AddHabitActivity.class);
                startActivity(intent);
            }

        });

       // deleteHabit.setOnClickListener(new View.OnClickListener(){

            //public void onClick(View v){
              //  deleteFile(FILENAME);
          //  }
        //});

        /*
        public void newHabitScreen(View view){
            Intent intent = new Intent(this, AddHabitActivity.class);
            startActivity(intent);
        }
        */

    }

    @Override
    protected void onStart() {
        super.onStart();
        habitListsTest = new HabitList();
        habitListsTest = FileInputOutput.loadFromFile(FILENAME, habitListsTest, this);
        //ArrayList<Habit> arrayOfHabits = new ArrayList<Habit>();
        //HabitList habitLists = new HabitList(habitList);
        final HabitAdapter adapter = new HabitAdapter(this, habitListsTest.getHabitList());
        //HabitAdapter adapter = new HabitAdapter(this, habitList);
        habitListView.setAdapter(adapter);
        //habitListView.invalidateViews();
    /*
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                habitListView.deferNotifyDataSetChanged();
            }
        });
*/
        habitListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this,HabitLogActivity.class);
                Habit habitLog = adapter.getItem(i);
                positionClick = i;
                intent.putExtra("Name of habit:", habitLog.getHabitTitle());
                //Toast.makeText(getBaseContext(), habitLog.getHabitTitle() + "yeyeye", Toast.LENGTH_SHORT).show();
                //adapter.getPosition();
                //Intent.putExtra(KEY, i );
                startActivity(intent);
            }
        });


        //adapter.notifyDataSetChanged();
    }
    /*
    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            //Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            Type listType = new TypeToken <HabitList>(){}.getType();

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

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
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
