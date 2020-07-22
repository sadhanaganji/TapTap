package com.e.taptap;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.e.taptap.TapSingle.PREF_NAME;

public class TapActivity extends AppCompatActivity {
Button tapbtn,reset,tapbtn2,dialog;
TextView time;
private CountDownTimer countDownTimer;
private Boolean timeRunning = false;
    int num1 =0,num2 =0;
    int value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tap);
        tapbtn = findViewById(R.id.tapbtn);
        reset = findViewById(R.id.resetbtn);
        tapbtn2 = findViewById(R.id.tapbtn2);
        time = findViewById(R.id.timedouble);


        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset.setText("RESET");
                tapbtn.setEnabled(true);
                tapbtn2.setEnabled(true);
                num1 = 0;
                num2 = 0;
                if(timeRunning){
                    countDownTimer.cancel();
                }
                timeRunning = false;
                time.setText("seconds\n10");
                tapbtn.setText("Tap to start");
                tapbtn2.setText("Tap to start");
            }
        });

        tapbtn.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View v) {
                                          if (timeRunning) {
                                              num1++;
                                              tapbtn.setText("" + num1);



                                          } else {
                                              StartTimer(1);

                                          }
                                      }
                                  });


        tapbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timeRunning) {
                    num2++;
                    tapbtn2.setText(""+num2);
                }
                else {
                    StartTimer(2);

                }
            }
        });
    }



    private void StartTimer(int n) {
        timeRunning = true;
if(n==1){num1++;tapbtn.setText(""+num1);}
else{
    num2++;tapbtn2.setText(""+num2);
}
        countDownTimer = new CountDownTimer(10000, 1000) {//needed to change 10000 to wanted or setted timer
            @Override
            public void onTick(long l) {
                int seconds = (int) (l / 1000) ;
                String timeLeft = "" + seconds;
                time.setText(timeLeft);
            }

            @Override
            public void onFinish() {
                tapbtn.setEnabled(false);
                tapbtn2.setEnabled(false);
                if(num1 > num2){
                    tapbtn.setText("WINNER:"+num1);
                    tapbtn2.setText("LOSER:"+num2);
                }
                else{
                    tapbtn.setText("LOSER:"+num1);
                    tapbtn2.setText("WINNER:"+num2);
                }
                reset.setText("play again");


                num1=0;
                num2=0;
                timeRunning=false;
                time.setText("seconds\n10");
            }
        }.start();
    }
}