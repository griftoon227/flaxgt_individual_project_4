package com.example.grift.flaxgt_individual_project_4.db_model;

import android.provider.BaseColumns;

//creates a contract for how the database will be structured
class UserAccountContract {
    private UserAccountContract() {}

    protected static class UserAccountEntry implements BaseColumns {
        protected static final String COL_PARENT_FIRST_NAME = "parent_first_name";
        protected static final String COL_LAST_NAME = "last_name";
        protected static final String COL_EMAIL = "email";
        protected static final String COL_PARENT_USERNAME = "parent_username";
        protected static final String COL_PARENT_PASSWORD = "parent_password";
        protected static final String COL_CHILD_FIRST_NAME = "child_first_name";
        protected static final String COL_CHILD_USERNAME = "child_username";
        protected static final String COL_CHILD_PASSWORD = "child_password";
    }


    protected static final String TABLE_NAME = "user_account_table";


    protected static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    UserAccountEntry.COL_PARENT_FIRST_NAME + " TEXT," +
                    UserAccountEntry.COL_LAST_NAME + " TEXT," +
                    UserAccountEntry.COL_EMAIL + " TEXT," +
                    UserAccountEntry.COL_PARENT_USERNAME + " TEXT," +
                    UserAccountEntry.COL_PARENT_PASSWORD + " TEXT," +
                    UserAccountEntry.COL_CHILD_FIRST_NAME + " TEXT," +
                    UserAccountEntry.COL_CHILD_USERNAME + " TEXT," +
                    UserAccountEntry.COL_CHILD_PASSWORD + " TEXT )"
            ;

    protected static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

}
