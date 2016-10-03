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

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jason on 28/09/2016.
 */
/*
    Class that allows which dates to be checked from a pop-up screen
 */
public class DaysOfTheWeek extends DialogFragment {
    ArrayList<String> daysList = new ArrayList<>();
    public Dialog onCreateDialog(Bundle savedInstanceState){
        // Use the Builder class for convenient dialog construction
        final String[] saveDays = getResources().getStringArray(R.array.daysintheweek);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Pick the days for the habit")
                .setMultiChoiceItems(R.array.daysintheweek, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    // Choose which days to save
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            daysList.add(saveDays[i]);
                        } else if(daysList.contains(saveDays[i])) {
                            daysList.remove(saveDays[i]);
                        }


                    }
                    // To have a yes button to exit the window
                }) .setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });


        return builder.create();
    }

    public ArrayList<String> getDaysList() {
        return daysList;
    }

    public void setDaysList(ArrayList<String> daysList) {
        this.daysList = daysList;
    }
}
