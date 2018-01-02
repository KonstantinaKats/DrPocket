package com.example.katsigianni.pocketlocation;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    UserDbHelper helper = new UserDbHelper(this);
    Button Loginbut;
    EditText name,surname,AMKA;
    Context context = this;
    UserDbHelper userDbHelper;
    SQLiteDatabase db;
    Cursor cursor;
    public Button doctorbut;



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
 // final   String nm = name.getText().toString();
  //  final  String amk = AMKA.getText().toString();

 // final String password= helper.searchPass(name.getText().toString());

        db = helper.getReadableDatabase();

      Loginbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String uname = surname.getText().toString();
                String unumb = AMKA.getText().toString();
                cursor = db.rawQuery("SELECT *FROM "+UserContract.NewUserInfo.TABLE_NAME+" WHERE "+UserContract.NewUserInfo.SURNAME+"=? AND "+UserContract.NewUserInfo.USER_NUMBER+"=?",new String[] {uname,unumb});
                if (cursor != null) {
                    if (cursor.getCount() > 0) {

                        cursor.moveToFirst();
                        //Retrieving User FullName and Email after successfull login and passing to LoginSucessActivity
                        String _fname = cursor.getString(cursor.getColumnIndex(UserContract.NewUserInfo.SURNAME));
                        String _fnumb = cursor.getString(cursor.getColumnIndex(UserContract.NewUserInfo.USER_NUMBER));
                        Toast.makeText(LoginActivity.this, "Login Succeeded", Toast.LENGTH_SHORT).show();
                        SaveSharedPreference.setUserName(LoginActivity.this, uname);
                        Intent intent = new Intent(LoginActivity.this, LoggedActivity.class);
                        intent.putExtra("fullname", surname.getText().toString());
                      //  intent.putExtra("usernumr", _fnumb);
                        startActivity(intent);

                        //Removing MainActivity[Login Screen] from the stack for preventing back button press.
                        finish();
                    }
           /*     if ( AMKA.getText().toString().equals("123"))
                {
                    Toast.makeText(getBaseContext(),"Login succeeded",Toast.LENGTH_LONG).show();
                    Intent myintent = new Intent(LoginActivity.this,LoggedActivity.class);
                   // myintent.putExtra("name",nm);
                    startActivity(myintent);


                } */
                    else
                        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
                }
            }
        });



    }

}
