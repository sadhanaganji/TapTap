package com.e.taptap;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
Button singlePlayer,doublePlayer,rateme;
ImageButton imageButton;
static String PACKAGE_NAME;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        singlePlayer = findViewById(R.id.singlePlayer);
        doublePlayer = findViewById(R.id.doublePlayer);
        imageButton = findViewById(R.id.imageButton);
        rateme = findViewById(R.id.rateme);
        PACKAGE_NAME = getApplicationContext().getPackageName();

        singlePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TapSingle.class));
            }
        });
        doublePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),TapActivity.class));
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                String value="https://play.google.com/store/apps/details?id="+PACKAGE_NAME;
                intent.putExtra(Intent.EXTRA_TEXT,value);
                startActivity(Intent.createChooser(intent,"Share via"));
            }
        });
        rateme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id="+getPackageName())));
                }
                catch (ActivityNotFoundException e)
                {
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("http://play.google.com/store/apps/details?id="+getPackageName())));
                }
            }
        });

    }
}