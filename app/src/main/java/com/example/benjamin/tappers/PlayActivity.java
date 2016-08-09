package com.example.benjamin.tappers;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.view.View;

import java.util.Random;
import android.content.res.Resources;
import android.util.TypedValue;
import android.widget.TextView;


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
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                timer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                timer.setText("done!");
            }
        }.start();



        theButton.setOnClickListener(new Button.OnClickListener() {
            int n = 0;
            public void onClick(View v)
            {
                String num =  Integer.toString(n);
                changeLocation(theButton, lp);
                // theButton.setText(num);
                // n++;

            }
        });


    }

    public void changeLocation(Button b, RelativeLayout.LayoutParams l) {


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        Random rand = new Random();

        // I took 25% of the phone's display and that seems to work (for now) Need to figure out how to acquire activity's display range in pixels.
        int randWidth = rand.nextInt((int) Math.ceil(metrics.widthPixels*0.80));
        int randHeight = rand.nextInt((int) Math.ceil(metrics.heightPixels*0.80));

        l.setMargins(randWidth, randHeight, 0, 0);
        b.setLayoutParams(l);
    }


}
