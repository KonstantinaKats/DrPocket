package com.example.katsigianni.pocketlocation.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.katsigianni.pocketlocation.R;
import com.example.katsigianni.pocketlocation.SaveSharedPreference;
import com.example.katsigianni.pocketlocation.Services.GPSService;

public class LanguageActivity extends AppCompatActivity {

    public ImageButton button2;

    public void init(){
        //startService(new Intent(this, GPSService.class));
        button2= (ImageButton)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(SaveSharedPreference.getUserName(LanguageActivity.this).length() == 0)
                {
                    Intent page = new Intent(LanguageActivity.this, LoginActivity.class);
                    startActivity(page);
                }
                else
                {
                    Intent intent = new Intent(LanguageActivity.this, LoggedActivity.class);

                    startActivity(intent);
                }


            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        init();

    }
}
