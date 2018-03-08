package com.example.katsigianni.pocketlocation;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.katsigianni.pocketlocation.Activities.LoggedActivity;

/**
 * Created by Dimitra on 12/3/2017.
 */

public class SaveSharedPreference {

    static final String PREF_USER_NAME= "username";
    static final String PREF_USER_PERSONAL_NUMBER= "personal_number";
    static final String LAST_LOCATION_LONGITUDE= "last_location_longitude";
    static final String LAST_LOCATION_LATITUDE= "last_location_latitude";
    static final String EXIT_BEDROOM = "exit_bedroom";
    static final String EXIT_LIVING_ROOM = "exit_living_room";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setExitLivingRoom (Class<LoggedActivity> ctx, String exitLivingRoom)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(EXIT_LIVING_ROOM, exitLivingRoom);
        editor.commit();
    }

    public static String getExitLivingRoom(Context ctx)
    {
        return getSharedPreferences(ctx).getString(EXIT_LIVING_ROOM, "");
    }

    public static void setExitBedroom (Class<LoggedActivity> ctx, String exitBedroom)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(EXIT_BEDROOM, exitBedroom);
        editor.commit();
    }

    public static String getExitBedroom(Context ctx)
    {
        return getSharedPreferences(ctx).getString(EXIT_BEDROOM, "");
    }

    public static void setLastLocationLongitude (Context ctx, String lastLocationLongitude)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(LAST_LOCATION_LONGITUDE, lastLocationLongitude);
        editor.commit();
    }

    public static String getLastLocationLongitude(Context ctx)
    {
        return getSharedPreferences(ctx).getString(LAST_LOCATION_LONGITUDE, "");
    }

    public static String getLastLocationLatitude(Context ctx)
    {
        return getSharedPreferences(ctx).getString(LAST_LOCATION_LATITUDE, "");
    }

    public static void setLastLocationLatitude (Context ctx, String lastLocationLatitude)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(LAST_LOCATION_LATITUDE, lastLocationLatitude);
        editor.commit();
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
