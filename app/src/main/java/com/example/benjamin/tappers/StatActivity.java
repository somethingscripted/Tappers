package com.example.benjamin.tappers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.content.SharedPreferences;


public class StatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_screen);

        TextView highScoreValue = (TextView) findViewById(R.id.highScoreValue);
        TextView timesPlayedValue = (TextView) findViewById(R.id.timesPlayedValue);
        SharedPreferences SP = getSharedPreferences("myData", Context.MODE_PRIVATE);

        int highscore = SP.getInt("highscore", 0);
        highScoreValue.setText(highscore + "");
        int timesPlayed = SP.getInt("timesPlayed", 0);
        timesPlayedValue.setText(timesPlayed + "");

    }
}
