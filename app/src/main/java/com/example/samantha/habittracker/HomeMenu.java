package com.example.samantha.habittracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import java.util.HashMap;


public class HomeMenu extends Activity {

    private HashMap<String, Habit> habitHash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        //mTextMessage = (TextView) findViewById(R.id.message);
        habitHash = new HashMap<>();
        fillHabitHash();
        final EditText et = findViewById(R.id.habitName);
        //adding a habit
        final Button button = findViewById(R.id.add_habbit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                String content = et.getText().toString();
                System.out.print(content);
                //make a new habit and add to hashmap
                String name = "name";
                Habit newHabit = new Habit(name, 0);
                habitHash.put(name, newHabit);
                //make new button

            }
        });

        //going to habit page
        final Button habitbutton = findViewById(R.id.habitButton1);
        habitbutton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Intent intent = new Intent(this, habitScreen.class);

            }
        });
    }

    public void addCount(String name)
    {
        Habit current = habitHash.get(name);
        current.add();
        habitHash.put(name, current);
    }

    private void fillHabitHash()
    {
        //loop trough all database
        //make a new habit for each one pulling name and count
        //add habit to hashMap with string as key and habit as value
    }

}