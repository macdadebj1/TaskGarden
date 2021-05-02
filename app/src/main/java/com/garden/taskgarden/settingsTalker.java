package com.garden.taskgarden;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;


/**
 * This file is intended to let you save and retrieve settings data on the device.
 *
 * @author Blake MacDade
 * */
public class settingsTalker {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    Context context;

    private static final String settingsFileName = "settings";
    private static final String debugTag = "settingsTalker";


    public settingsTalker(Context context){
        this.context = context;
        preferences = context.getApplicationContext().getSharedPreferences(settingsFileName,0);

    }
    /**
     * addEntry adds an entry to the settings file.
     * @param key key string to store the data at.
     * @param value value string to store.
     * */
    public void addEntry(String key, String value){
        editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
    /**
     * getEntry returns an entry from the settings file.
     * @param key key string to look up and return data from settings file.
     * @return String value stored with the key.
     * */
    public String getEntry(String key){
        return preferences.getString(key,null);
    }
    /**
     * removeEntry removes an entry from the settings file.
     * @param key string to remove from the settings file.
     * */
    public void removeEntry(String key){
        editor = preferences.edit();
        editor.remove(key);
        editor.apply();
    }
    /**
     * clearAll CLEARS ENTIRE SETTINGS FILE
     * @param iReallyWantToClearEverything just to double check :)
     * */
    public void clearAll(boolean iReallyWantToClearEverything){
        Log.d(debugTag,"Someone is trying to clear the settings file!!!");
        if(iReallyWantToClearEverything){
            editor = preferences.edit();
            editor.clear();
            editor.apply();
        }
    }



}
