package com.example.reminderapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TimePicker TP;
    EditText inputDescription;
    Button buttonSet;
    Button buttonCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TP = (TimePicker) findViewById(R.id.simpleTP);
        inputDescription = (EditText) findViewById(R.id.inpbox);
        buttonSet = (Button) findViewById(R.id.btt);
        buttonCancel = (Button) findViewById(R.id.btcancel);
        final AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        PendingIntent pi;

        buttonSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int htime = TP.getCurrentHour();
                int mtime = TP.getCurrentMinute();
                String description = inputDescription.getText().toString();
                PendingIntent pi;

                Calendar ca = Calendar.getInstance();
                ca.set(Calendar.HOUR_OF_DAY,htime);
                ca.set(Calendar.MINUTE,mtime);

                Toast.makeText(getApplicationContext(),"Alarm will go off in "+ (TP.getCurrentHour() - Calendar.getInstance().get(Calendar.HOUR_OF_DAY))  + " hours and " + (TP.getCurrentMinute() - Calendar.getInstance().get(Calendar.MINUTE)) + " minutes.",Toast.LENGTH_LONG).show();

                Intent i = new Intent(getApplicationContext(), Alarm.class);
                i.putExtra("des", description);
                //am = (AlarmManager) getSystemService(ALARM_SERVICE);
                pi = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);

                am.set(AlarmManager.RTC_WAKEUP, ca.getTimeInMillis(), pi);

            }
        });

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), Alarm.class);
                //am = (AlarmManager) getSystemService(ALARM_SERVICE);
                PendingIntent pi = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);

                Toast.makeText(getApplicationContext(),"Alarm Cancelled",Toast.LENGTH_LONG).show();
                am.cancel(pi);
            }
        });


    }

}


