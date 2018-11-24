package com.example.grift.flaxgt_individual_project_4.db_model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.example.grift.flaxgt_individual_project_4.db_model.UserAccountContract.SQL_CREATE_ENTRIES;
import static com.example.grift.flaxgt_individual_project_4.db_model.UserAccountContract.SQL_DELETE_ENTRIES;

//Helps to create and manage the actual database
public class UserAccountDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "user_account.db";

    protected UserAccountDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //creates the database
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    //upgrade the database version
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    //revert the database version
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}