package com.example.jason.habittracker;

import android.content.Context;

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
import java.util.Date;

/**
 * Created by Jason on 02/10/2016.
 */
public class FileInputOutput {

    //private HabitList listOfHabits;
    public static HabitList loadFromFile(String FILENAME, HabitList listOfHabits, Context context  ) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            //Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            Type listType = new TypeToken<HabitList>(){}.getType();

            listOfHabits = gson.fromJson(in,listType);
            return listOfHabits;

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            //habitList = new ArrayList<Habit>();
            listOfHabits = new HabitList();
            return listOfHabits;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    public static LogDates loadFromLogFile(String FILENAME, LogDates date, Context context  ) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            //Type listType = new TypeToken<ArrayList<Habit>>(){}.getType();
            Type listType = new TypeToken<LogDates>(){}.getType();

            date = gson.fromJson(in,listType);
            return date;

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            //habitList = new ArrayList<Habit>();
            date = new LogDates();
            //Date newDate[] = date;
            return date;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    public static void saveInFile(String FILENAME, HabitList listOfHabits, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,
                    0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(listOfHabits, out);
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
    public static void saveInLogFile(String FILENAME, LogDates date, Context context) {
        try {
            FileOutputStream fos = context.openFileOutput(FILENAME,
                    0);

            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));

            Gson gson = new Gson();
            gson.toJson(date, out);
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

    public static void removeFile(String FILENAME, Context context){
        context.deleteFile(FILENAME);
    }
}
