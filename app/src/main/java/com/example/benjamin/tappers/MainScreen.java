package com.example.benjamin.tappers;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;

import java.util.Locale;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class MainScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/Roboto-ThinItalic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    public void playButtonClick(View v){
        Intent i = new Intent(this, PlayActivity.class);
        startActivity(i);
    }

    public void statButtonClick(View v) {
        Intent i = new Intent(this, StatActivity.class);
        startActivity(i);
    }

}