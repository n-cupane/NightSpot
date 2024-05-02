package com.nighter.nightspot.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import com.nighter.nightspot.models.User;

public class SharedPref {

    public static void saveCredentials(Context context, User credentials) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "credentials",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putString("username", credentials.getUsername());
        editor.putString("password", credentials.getPassword());
        editor.apply();
    }

    public static User loadCredentials(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "credentials",
                Context.MODE_PRIVATE
        );

        User u = new User();

        u.setUsername(sharedPreferences.getString("username", ""));
        u.setPassword(sharedPreferences.getString("password", ""));

        return u;
    }

    public static void saveToken(Context context, String token) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "credentials",
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.apply();
    }

    public static String loadToken(Context context) {

        SharedPreferences sharedPreferences = context.getSharedPreferences(
                "credentials",
                Context.MODE_PRIVATE
        );
        String token = sharedPreferences.getString("token", "");

        return token;
    }



}
