package com.example.android.appointer.Service;

/**
 * Created by Prasad on 18-Apr-18.
 */
public interface SharedPreferencesService {

    String get(String key);

    void save(String key, String value);

    void remove(String key);

    public Boolean contains(String key);
}
