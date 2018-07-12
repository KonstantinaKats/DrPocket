package com.example.katsigianni.pocketlocation.Activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.katsigianni.pocketlocation.Common;
import com.example.katsigianni.pocketlocation.HTTPDataHandler;
import com.example.katsigianni.pocketlocation.R;
import com.example.katsigianni.pocketlocation.SaveSharedPreference;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity {
    Button Loginbut;
    EditText name,surname,AMKA;
    Context context = this;
    public Button doctorbut;
    boolean success = false;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void doctor(){
        doctorbut= (Button)findViewById(R.id.signupbut);
        doctorbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addinfopage = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(addinfopage);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        doctor();
        name = (EditText) findViewById(R.id.name);
        surname = (EditText) findViewById(R.id.surname);
        AMKA = (EditText) findViewById(R.id.AMKA);
        Loginbut = (Button) findViewById(R.id.Loginbut);

      Loginbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new GetData().execute(Common.getUsers(surname.getText().toString(),AMKA.getText().toString()));
            }
        });



    }

    private void checkSuccess(){
        if(success){
            FirebaseMessaging.getInstance().subscribeToTopic("PatientNews");
            //Retrieving Surname and Personal_number after successfull login and passing to LoginSucessActivity
            Toast.makeText(LoginActivity.this, "Login Succeeded", Toast.LENGTH_SHORT).show();
            SaveSharedPreference.setUserName(LoginActivity.this, surname.getText().toString());
            SaveSharedPreference.setPersonalNumber(LoginActivity.this, AMKA.getText().toString());
            Intent intent = new Intent(LoginActivity.this, LoggedActivity.class);
            intent.putExtra("fullname", surname.getText().toString());
            startActivity(intent);
            //Removing MainActivity[Login Screen] from the stack for preventing back button press.
            finish();
        }else{
            Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        }
    }


    //function process data
    private class GetData extends AsyncTask<String,Void,String> {
        ProgressDialog pd = new ProgressDialog(LoginActivity.this);

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
            //Pre Process
            pd.setTitle("Please wait");
            pd.show();
        }


        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            //done process

            //use Gson to parse Json to Class
            Gson gson = new Gson();
            //Type user = new TypeToken<User>(){}.getType();
            //user = gson.fromJson(s,user);
            if(!"[  ]".equals(s)){
                setSuccess(true);
            }
            checkSuccess();

            pd.dismiss();
        }


        @Override
        protected String doInBackground(String... params) {
            //running process...
            String stream=null;
            String urlString = params[0];

            HTTPDataHandler http = new HTTPDataHandler();
            stream = http.GetHTTPData(urlString);
            return stream;

        }

    }
}
