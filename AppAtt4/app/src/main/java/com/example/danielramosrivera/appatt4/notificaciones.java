package com.example.danielramosrivera.appatt4;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.Calendar;

import static android.widget.Toast.LENGTH_SHORT;

public class notificaciones extends AppCompatActivity {

   int x=12;
    SwitchCompat swi;
    SharedPreferences sharedPref,sharedPref1;
    Spinner sp;
    Spinner sp2;
    int w=0,z=0;
    AlarmManager al;
    PendingIntent pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaciones);

        sp = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.time, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp.setAdapter(adapter);

         sharedPref = getSharedPreferences("sp", MODE_PRIVATE);
        sp.setSelection(sharedPref.getInt("selection",w));



        sp2 = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.time2, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp2.setAdapter(adapter1);

        sharedPref1 = getSharedPreferences("sp2", MODE_PRIVATE);
        sp2.setSelection(sharedPref1.getInt("selection", z));

        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0: {
                        x = 1;
                        w=0;
                        break;
                    }
                    case 1: {
                        x = 2;
                        w=1;
                        break;
                    }
                    case 2: {
                        x = 3;
                        w=2;
                        break;
                    }
                    case 3: {
                        x = 4;
                        w=3;
                        break;
                    }
                    case 4: {
                        x = 5;
                        w=4;
                        break;
                    }
                    case 5: {
                        x = 6;
                        w=5;
                        break;
                    }
                    case 6: {
                        x = 7;
                        w=6;
                        break;
                    }
                    case 7: {
                        x = 8;
                        w=7;
                        break;
                    }
                    case 8: {
                        x = 9;
                        w=8;
                        break;
                    }
                    case 9: {
                        x = 10;
                        w=9;
                        break;
                    }
                    case 10: {
                        x = 11;
                        w=10;
                        break;
                    }
                    case 11: {
                        x = 12;
                        w=11;
                        break;
                    }
                    default: {
                        x = 1;
                        break;
                    }

                }



               SharedPreferences.Editor edit = getSharedPreferences("sp",MODE_PRIVATE).edit();
                edit.putInt("selection",w);
                edit.commit();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                x = 12;
            }



        });

        sp2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {

                    case 0: {
                    //Toast.makeText(getApplicationContext(),x, Toast.LENGTH_SHORT).show();
                        z=0;
                        break;
                    }
                    case 1: {
                        x = x + 12;
                        z=1;
                        //Toast.makeText(getApplicationContext(), "HII" + x + "", LENGTH_SHORT).show();
                        break;
                    }
                    default: {
                        break;
                    }

                }


                SharedPreferences.Editor edit = getSharedPreferences("sp2",MODE_PRIVATE).edit();
                edit.putInt("selection",z);
                edit.commit();



            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }



        });

        swi = (SwitchCompat) findViewById(R.id.switch1);

        SharedPreferences sharedPrefs = getSharedPreferences("Boolean", MODE_PRIVATE);
        swi.setChecked(sharedPrefs.getBoolean("Save", false));






    }


    public void onClick(View v)
    {
        if (swi.isChecked())
        {
            SharedPreferences.Editor editor = getSharedPreferences("Boolean", MODE_PRIVATE).edit();
            editor.putBoolean("Save", true);
            editor.commit();

            Calendar calen = Calendar.getInstance();
            calen.setTimeInMillis(System.currentTimeMillis());
            calen.set(Calendar.HOUR_OF_DAY,x);
            calen.set(Calendar.MINUTE,00);

            Intent in = new Intent (getApplicationContext(),Notificaciones.class);

            pd = PendingIntent.getBroadcast(getApplicationContext(),0,in,0);


            al = (AlarmManager) getSystemService(ALARM_SERVICE);

            al.setRepeating(AlarmManager.RTC_WAKEUP, calen.getTimeInMillis(), 1000 * 60 * 10, pd);

            Toast.makeText(getApplicationContext(),"Jala "+x+"",LENGTH_SHORT).show();

        }
        else
        {
            SharedPreferences.Editor editor = getSharedPreferences("Boolean", MODE_PRIVATE).edit();
            editor.putBoolean("Save", false);
            editor.commit();


            if(al != null) {
                al.cancel(pd);
            }

        }
    }
}
