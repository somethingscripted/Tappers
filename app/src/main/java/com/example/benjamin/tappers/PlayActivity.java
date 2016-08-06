package com.example.benjamin.tappers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.view.View;

import java.util.Random;
import android.content.res.Resources;
import android.util.TypedValue;


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
        float logicalDensity = metrics.density;


        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        int hdp = (int) Math.ceil(height / logicalDensity);
        int wdp = (int) Math.ceil(width / logicalDensity);

        Random rand = new Random();
        int leftv = rand.nextInt(width);
        int topv = rand.nextInt(height);

        String leftstring = Integer.toString(height);
        String topstring = Integer.toString(width);
        b.setText(leftstring + " " + topstring);

        l.setMargins(leftv, topv, 0, 0);
        b.setLayoutParams(l);

    }


}
