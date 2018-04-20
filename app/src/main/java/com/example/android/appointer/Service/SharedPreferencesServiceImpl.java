package com.example.android.appointer.Service;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.android.appointer.MainActivity;

/**
 * Created by Prasad on 18-Apr-18.
 */

public class SharedPreferencesServiceImpl implements SharedPreferencesService{

    @Override
    public String get(String key){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.context);
        String value = sharedPref.getString(key, "NOT_FOUND");
        return value;
    }

    @Override
    public void save(String key, String value){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(key, value);
        editor.commit();
    }

    @Override
    public void remove(String key){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.context);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(key);
        editor.commit();
    }

    @Override
    public Boolean contains(String key){
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(MainActivity.context);;
        return sharedPref.contains(key);
    }

}
