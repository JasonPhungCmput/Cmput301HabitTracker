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
public class DaysOfTheWeek extends DialogFragment {
    ArrayList<String> daysList = new ArrayList<>();
    public Dialog onCreateDialog(Bundle savedInstanceState){
        // Use the Builder class for convenient dialog construction
        final String[] saveDays = getResources().getStringArray(R.array.daysintheweek);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Pick the days for the habit")
                .setMultiChoiceItems(R.array.daysintheweek, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if(b){
                            daysList.add(saveDays[i]);
                        } else if(daysList.contains(saveDays[i])) {
                            daysList.remove(saveDays[i]);
                        }


                    }
                }) .setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });


        return builder.create();
    }

    public ArrayList<String> storedArrayList(){
        return daysList;
    }

    public ArrayList<String> getDaysList() {
        return daysList;
    }

    public void setDaysList(ArrayList<String> daysList) {
        this.daysList = daysList;
    }
}
