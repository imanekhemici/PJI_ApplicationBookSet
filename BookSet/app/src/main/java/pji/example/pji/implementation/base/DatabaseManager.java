package pji.example.pji.implementation.base;

import android.content.Context;

/**
 * Created by imane khemici on 28/03/15.
 */
public class DatabaseManager {

    static private DatabaseManager instance;

    private DatabaseBookSet bookSet;


    static public void init(Context context) {
        if(null==instance) {
            instance = new DatabaseManager(context);
        }
    }

    static public DatabaseManager getInstance() {
        return instance;
    }


    private DatabaseManager(Context context) {
        bookSet = new DatabaseBookSet(context);
    }

    public DatabaseBookSet getHelper() {
        return bookSet;
    }



}
