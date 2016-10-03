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

/**
 * Created by Jason on 02/10/2016.
 */
/*
    FileInputOutput class made to allow other classes in habit tracker to save and load data files
    The code is from lonelyTwitter in the labs
*/
public class FileInputOutput {
    // This method is to load from the file that stores all the habits
    public static HabitList loadFromFile(String FILENAME, HabitList listOfHabits, Context context  ) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<HabitList>(){}.getType();

            listOfHabits = gson.fromJson(in,listType);
            return listOfHabits;

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            listOfHabits = new HabitList();
            return listOfHabits;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    // This method differs from the above because the habit history is stored in another file
    public static LogDates loadFromLogFile(String FILENAME, LogDates date, Context context  ) {
        try {
            FileInputStream fis = context.openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));

            Gson gson = new Gson();

            // Code from http://stackoverflow.com/questions/12384064/gson-convert-from-json-to-a-typed-arraylistt
            Type listType = new TypeToken<LogDates>(){}.getType();

            date = gson.fromJson(in,listType);
            return date;

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            date = new LogDates();
            return date;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    // Method to save to habit file
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

    // This method is for habit completions
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
