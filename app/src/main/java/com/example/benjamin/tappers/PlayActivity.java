package com.example.benjamin.tappers;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.view.View;
import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.TextView;
import android.media.MediaPlayer;
import android.widget.Toast;

import java.util.Random;


public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        final Button theButton = (Button) findViewById(R.id.theButton);
        theButton.setText("");

        final RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                                                               RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.setMargins(320,320,0,0);

        theButton.setLayoutParams(lp);
        theButton.setText("");


        Resources r = getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200,
                                                 r.getDisplayMetrics()
                                                );

        final TextView timer = (TextView) findViewById(R.id.timer);
        final TextView points = (TextView) findViewById(R.id.points);
        points.setText("Points = 0");


        class ButtonClick implements View.OnClickListener {
            int value = 0;
            @Override
            public void onClick(View v) {
                value++;
                changeLocation(theButton);
                points.setText("Points = " + value);
            }

            public int getValue() {
                return value;
            }
        }

        final ButtonClick buttonClicked = new ButtonClick();
        theButton.setOnClickListener(buttonClicked);



        new CountDownTimer(21000, 100) {

            public void onTick(long SecUntilFinished) {
                timer.setText("Seconds remaining: " + SecUntilFinished/1000);
            }

            public void onFinish() {

                theButton.setOnClickListener(null);

                SharedPreferences SP = getSharedPreferences("myData", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = SP.edit();
                boolean highScoreTick = false;

                int finalValue = buttonClicked.getValue();
                int timesPlayed = SP.getInt("timesPlayed", 0);

                if(SP.getInt("highscore", 0) < finalValue) {
                     highScoreTick = true;
                    editor.putInt("highscore", finalValue);
                    editor.apply();
                }

                timesPlayed++;
                editor.putInt("timesPlayed", timesPlayed);
                editor.apply();

                AlertDialog.Builder a_builder = new AlertDialog.Builder(PlayActivity.this)
                        .setMessage("You have earned " + finalValue + " points!\nDo you want to play again?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                reload();
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
                            }
                        });

                AlertDialog alert = a_builder.create();
                if (highScoreTick) { alert.setTitle("New High Score!"); }
                else{ alert.setTitle("Times up!"); }
                alert.show();

            }


        }.start();

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        Toast.makeText(getApplicationContext(), "Back Button Pressed!", Toast.LENGTH_SHORT).show();

        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);

    }

    public void changeLocation(Button b) {

        Button button = (Button) findViewById(R.id.theButton);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Random rand = new Random();

        int randWidth = rand.nextInt((int) Math.ceil(metrics.widthPixels*0.75));
        int randHeight = rand.nextInt((int) Math.ceil(metrics.heightPixels*0.70));
        
        RelativeLayout.LayoutParams l = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                                                                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        l.setMargins(randWidth, randHeight , 0, 0);
        b.setLayoutParams(l);
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
