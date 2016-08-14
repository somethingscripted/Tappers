package com.example.benjamin.tappers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.view.View;
import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.TextView;

import java.util.Random;


public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        final Button theButton = (Button) findViewById(R.id.theButton);
        theButton.setText("");

        final RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
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

        final int finalValue = 1;

         final Button.OnClickListener ButtonClicked = new Button.OnClickListener() {
             public int value = 0;
             public void onClick(View v) {
                value++;
                changeLocation(theButton);
                points.setText("Points = " + value);
            }
        };


        theButton.setOnClickListener(ButtonClicked);


        new CountDownTimer(15000, 100) {

            public void onTick(long millisUntilFinished) {
                timer.setText("Seconds remaining: " + millisUntilFinished / 100);
            }



            public void onFinish() {
                AlertDialog.Builder a_builder = new AlertDialog.Builder(PlayActivity.this);
                //HOW THE HELL DO I FIGURE OUT HOW TO GET THE VALUE FROM THE PLAY SCREEN TO THE POP UP SCREEN
                a_builder.setMessage("You have earned points but I don't know how much because I can't program!!\nDo you want to play again? ")
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
                alert.setTitle("Time's up!");
                alert.show();

            }
        }.start();



    }

    public void changeLocation(Button b) {


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Random rand = new Random();

        // I took 25% of the phone's display and that seems to work (for now) Need to figure out how to acquire activity's display range in pixels.
        // This does not work properly on all device screen which confuses me :(
        int randWidth = rand.nextInt((int) Math.ceil(metrics.widthPixels*0.75));
        int randHeight = rand.nextInt((int) Math.ceil(metrics.heightPixels*0.70));


        RelativeLayout.LayoutParams l = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
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
