package com.example.grift.flaxgt_individual_project_4.db_model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import static com.example.grift.flaxgt_individual_project_4.db_model.UserAccountContract.UserAccountEntry.*;
import static com.example.grift.flaxgt_individual_project_4.db_model.UserAccountContract.TABLE_NAME;

//A helper class to utilize the SQLite database
public class MyDatabase {
    private UserAccountDbHelper mDbHelper;

    public MyDatabase(Context context){
        mDbHelper=new UserAccountDbHelper(context);
    }

    //add a record to the database
    public void addRecord(String[] addValues){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PARENT_FIRST_NAME, addValues[0]);
        values.put(COL_LAST_NAME, addValues[1]);
        values.put(COL_EMAIL, addValues[2]);
        values.put(COL_PARENT_USERNAME, addValues[3]);
        values.put(COL_PARENT_PASSWORD, addValues[4]);
        values.put(COL_CHILD_FIRST_NAME, addValues[5]);
        values.put(COL_CHILD_USERNAME, addValues[6]);
        values.put(COL_CHILD_PASSWORD, addValues[7]);
        long result = db.insert(TABLE_NAME, null, values);
    }

    //validate the parent account's existence based on username and password entered
    public boolean validateParentAccount(String username, String password){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_PARENT_USERNAME, COL_PARENT_PASSWORD}, null, null, null, null,
                null);

        while(cursor.moveToNext())
        {
            if(cursor.getString(cursor.getColumnIndexOrThrow(UserAccountContract.UserAccountEntry.COL_PARENT_USERNAME))
                    .equals(username) &&
                    cursor.getString(cursor.getColumnIndexOrThrow(UserAccountContract.UserAccountEntry.COL_PARENT_PASSWORD))
                            .equals(password)) {
                return true;
            }
        }

        cursor.close();

        return false;
    }

    //validate the child account's existence based on username and password entered
    public boolean validateChildAccount(String username, String password){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_CHILD_USERNAME, COL_CHILD_PASSWORD}, null, null, null, null,
                null);

        while(cursor.moveToNext())
        {
            if(cursor.getString(cursor.getColumnIndexOrThrow(UserAccountContract.UserAccountEntry.COL_CHILD_USERNAME))
                    .equals(username) &&
                    cursor.getString(cursor.getColumnIndexOrThrow(UserAccountContract.UserAccountEntry.COL_CHILD_PASSWORD))
                            .equals(password)) {
                return true;
            }
        }

        cursor.close();

        return false;
    }

    //returns a username filtered by the type of account the username is linked to and it re
    public String getChildUsernameByParentAccount(String parentUsername){
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[]{COL_PARENT_USERNAME, COL_CHILD_USERNAME}, null, null ,null, null,
                null);

        String childUsername = "";
        while(cursor.moveToNext()) {
            if (cursor.getString(cursor.getColumnIndexOrThrow(UserAccountContract.UserAccountEntry.COL_PARENT_USERNAME))
                    .equals(parentUsername)) {
                childUsername = cursor.getString(cursor.getColumnIndexOrThrow(UserAccountContract.UserAccountEntry.COL_CHILD_USERNAME));
                break;
            }
        }

        cursor.close();

        return childUsername;
    }
}
