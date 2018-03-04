package com.example.katsigianni.pocketlocation;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by Dimitra on 12/3/2017.
 */

public class SaveSharedPreference {

    static final String PREF_USER_NAME= "username";
    static final String PREF_USER_PERSONAL_NUMBER= "personal_number";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setUserName(Context ctx, String userName)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static String getUserName(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static void clearUserInfo(Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear(); //clear all stored data
        editor.commit();
    }

    public static void setPersonalNumber(Context ctx, String personal_number)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_PERSONAL_NUMBER, personal_number);
        editor.commit();
    }

    public static String getPersonalNumber(Context ctx)
    {
        return getSharedPreferences(ctx).getString(PREF_USER_PERSONAL_NUMBER, "");
    }


}
