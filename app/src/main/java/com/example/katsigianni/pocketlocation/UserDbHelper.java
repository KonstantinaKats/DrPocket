package com.example.katsigianni.pocketlocation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Katsigianni on 23/2/2017.
 */

public class UserDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MALAKISMENO";
    public static final int DATABASE_VERSION = 1;
    SQLiteDatabase db;
    public static final String CREATE_QUERY =
            "CREATE TABLE "+ UserContract.NewUserInfo.TABLE_NAME+"("+ UserContract.NewUserInfo.NAME+" TEXT,"
                    + UserContract.NewUserInfo.SURNAME+" TEXT,"+UserContract.NewUserInfo.USER_NUMBER+" TEXT,"+ UserContract.NewUserInfo.BLOOD+" TEXT,"+ UserContract.NewUserInfo.OXYGEN+" TEXT);";

    public UserDbHelper(Context context)
    {
        super (context,DATABASE_NAME,null,DATABASE_VERSION);
        Log.e("DATABASE OPERATIONS","Database created / opened...");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.e("DATABASE OPERATIONS","Table created...");


    }

    public void addInformation(String name,String surname,String user_number,String blood,String oxygen,SQLiteDatabase db){
        ContentValues contentValues= new ContentValues();
        contentValues.put(UserContract.NewUserInfo.NAME,name);
        contentValues.put(UserContract.NewUserInfo.SURNAME,surname);
        contentValues.put(UserContract.NewUserInfo.USER_NUMBER,user_number);
        contentValues.put(UserContract.NewUserInfo.BLOOD,blood);
        contentValues.put(UserContract.NewUserInfo.OXYGEN,oxygen);
        db.insert(UserContract.NewUserInfo.TABLE_NAME,null,contentValues);
        Log.e("DATABASE OPERATIONS","One row inserted...");
    }

    public Cursor getInformation(SQLiteDatabase db){
        Cursor cursor;
        String[] projections = {UserContract.NewUserInfo.NAME, UserContract.NewUserInfo.SURNAME, UserContract.NewUserInfo.USER_NUMBER,UserContract.NewUserInfo.BLOOD, UserContract.NewUserInfo.OXYGEN};
        cursor= db.query(UserContract.NewUserInfo.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;
    }

 /* public String searchPass(String name)
    {

        db = this.getReadableDatabase();
        Cursor cursor=db.query(UserContract.NewUserInfo.TABLE_NAME, null, " name=?", new String[]{name}, null, null, null);
        if(cursor.getCount()<1) // UserName Not Exist
        {
            cursor.close();
            return "NOT EXIST";
        }
        cursor.moveToFirst();
        String unumber= cursor.getString(cursor.getColumnIndex("USER_NUMBER"));
        cursor.close();
        return unumber;
    }

*/
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    //   String query= "DROP TABLE IF EXISTS "+UserContract.NewUserInfo.TABLE_NAME;
        //db.execSQL(query);
        //this.onCreate(db);
    }
}

