package com.e.taptap;

import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TapSingle extends AppCompatActivity {
    Button tapbutton, reset;
    TextView timetext, maxcount;
    int num = 0;
    public static final String PREF_NAME = "SOMENAME";
    private CountDownTimer countDownTimer;
    //private long timeLeftInMIlliseconds = 10000;
    private Boolean timeRunning = false;
    int coins;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap_single);

        tapbutton = findViewById(R.id.tapbutton);
        reset = findViewById(R.id.reset);
        timetext = findViewById(R.id.timetext);
        maxcount = findViewById(R.id.maxcount);



        SharedPreferences settings = getSharedPreferences(PREF_NAME, 0);
        coins = settings.getInt("coins", coins);
        maxcount.setText("High Scrore: " + coins);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset.setText("RESET");
                tapbutton.setEnabled(true);
                num =0;
                if(timeRunning){
                    countDownTimer.cancel();
                }
                timeRunning=false;
                timetext.setText("Seconds\n10");
                tapbutton.setText("Tap here\n as fast as you can");
            }
        });
        tapbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (timeRunning) {
                    num++;
                    tapbutton.setText(""+num);


                } else {
                    StartTimer();

                }
            }
        });


    }


    private void StartTimer() {
        timeRunning = true;
        num++;
        tapbutton.setText(""+num);

        countDownTimer = new CountDownTimer(10000, 1000) {//needed to change 10000 to wanted or setted timer
            @Override
            public void onTick(long l) {
                int seconds = (int) (l / 1000) ;
                String timeLeft = "" + seconds;
                timetext.setText(timeLeft);
            }

            @Override
            public void onFinish() {
                if(num > coins){ coins = num;}
                    reset.setText("play again");

                tapbutton.setText("Your Score\n"+num);
                num=0;
                timeRunning=false;
                tapbutton.setEnabled(false);
                timetext.setText("seconds\n10");
                SharedPreferences settings = getSharedPreferences(PREF_NAME,0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putInt("coins",coins);
                editor.commit();
                maxcount.setText("High Score: "+coins);



            }
        }.start();
    }


}