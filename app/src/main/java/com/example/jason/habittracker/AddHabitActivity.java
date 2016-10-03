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

public class AddHabitActivity extends AppCompatActivity {

    private ArrayList<String> mSelectedItems = new ArrayList<String>();
    private EditText habitText;
    private Date dateEntered = null;
    private HabitList habitListsTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_habit);

        final DatePicker dateChooser = (DatePicker) findViewById(R.id.datePicker);
        Button addDate = (Button) findViewById(R.id.addDate);
        Button saveButton = (Button) findViewById(R.id.saveHabit);
        Button addDaysButton = (Button) findViewById(R.id.dayButton);
        habitText = (EditText) findViewById(R.id.habitName);
        habitListsTest = new HabitList();


        addDate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, dateChooser.getYear());
                calendar.set(Calendar.MONTH, dateChooser.getMonth());
                calendar.set(Calendar.DAY_OF_MONTH, dateChooser.getDayOfMonth());

                dateEntered = calendar.getTime();
                Toast.makeText(getBaseContext(),dateEntered + "--", Toast.LENGTH_SHORT).show();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){
                habitListsTest = FileInputOutput.loadFromFile("file.sav",habitListsTest,getBaseContext());
                setResult(RESULT_OK);
                String nameOfHabit =  habitText.getText().toString();

                Habit newHabit = new Habit(nameOfHabit,mSelectedItems);
                Habit newHabitDate = new Habit(dateEntered,nameOfHabit,mSelectedItems);

                //test
                if(dateEntered != null){
                    habitListsTest.add(newHabitDate);
                } else {
                    habitListsTest.add(newHabit);
                }

                FileInputOutput.saveInFile("file.sav", habitListsTest, getBaseContext());
                Toast.makeText(getBaseContext(), "Habit has been added.",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        addDaysButton.setOnClickListener(new View.OnClickListener(){

            public void onClick(View v){

                DaysOfTheWeek daysDialog = new DaysOfTheWeek();
                daysDialog.show(getFragmentManager(),"hii");
                mSelectedItems = daysDialog.getDaysList();
            }

        });


    }

}
