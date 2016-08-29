package com.example.benjamin.tappers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.vstechlab.easyfonts.EasyFonts;


public class StatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_screen);

        TextView highScoreValue = (TextView) findViewById(R.id.highScoreValue);
        TextView timesPlayedValue = (TextView) findViewById(R.id.timesPlayedValue);

        Typeface customFont = Typeface.createFromAsset(getAssets(), "fonts/Roboto-ThinItalic.ttf");
        highScoreValue.setTypeface(customFont);
        timesPlayedValue.setTypeface(customFont);

        SharedPreferences SP = getSharedPreferences("myData", Context.MODE_PRIVATE);

        int highscore = SP.getInt("highscore", 0);
        if (highscore == 1) {
            highScoreValue.setText(highscore + " point");
        } else {
            highScoreValue.setText(highscore + " points");
        }
        int timesPlayed = SP.getInt("timesPlayed", 0);
        if (timesPlayed == 1) {
            timesPlayedValue.setText(timesPlayed + " time");
        } else {
            timesPlayedValue.setText(timesPlayed + " times");
        }
    }


    public void clear(View v) {
        final AlertDialog.Builder a_builder = new AlertDialog.Builder(StatActivity.this)
                .setMessage("Are you sure you want to clear your data?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences SP = getSharedPreferences("myData", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = SP.edit();
                        editor.clear();
                        editor.apply();

                        Toast.makeText(getApplicationContext(),
                                "Your data has been cleared.",
                                Toast.LENGTH_SHORT).show();

                        Intent k = new Intent(getApplicationContext(), MainScreen.class);
                        startActivity(k);

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });
        AlertDialog alert = a_builder.create();
        alert.show();
    }

    public void reload() {
        Intent intent = getIntent();
        overridePendingTransition(0, 0);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
    }
}
