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

        editor.putLong("id", credentials.getId());
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
        u.setId(sharedPreferences.getLong("id", -1));
        u.setUsername(sharedPreferences.getString("username", ""));
        u.setPassword(sharedPreferences.getString("password", ""));

        return u;
    }


}
