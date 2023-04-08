package com.example.intouch;
import android.content.Context;


public class DatabaseSingleton {
    private static DatabaseSingleton instance = null;
    private static DatabaseHelper databaseHelper = null;

    private DatabaseSingleton(Context context) {
        databaseHelper = new DatabaseHelper(context);
    }

    public static synchronized DatabaseSingleton getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseSingleton(context);
        }
        return instance;
    }

    public DatabaseHelper getDatabaseHelper() {
        return databaseHelper;
    }
}
