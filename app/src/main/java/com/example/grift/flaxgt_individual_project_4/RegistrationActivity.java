package com.example.grift.flaxgt_individual_project_4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.example.grift.flaxgt_individual_project_4.db_model.MyDatabase;

public class RegistrationActivity extends AppCompatActivity {
    //bind all of the needed views
    @BindView(R.id.first_name_field_entry) EditText parent_first_name_etv;
    @BindView(R.id.last_name_field_entry) EditText parent_last_name_etv;
    @BindView(R.id.email_field_entry) EditText parent_email_etv;
    @BindView(R.id.username_field_entry) EditText parent_username_etv;
    @BindView(R.id.password_field_entry) EditText parent_password_etv;
    @BindView(R.id.confirm_password_field_entry) EditText parent_confirm_password_etv;
    @BindView(R.id.child_first_name_field_entry) EditText child_first_name_etv;
    @BindView(R.id.child_username_field_entry) EditText child_username_etv;
    @BindView(R.id.child_password_field_entry) EditText child_password_etv;
    @BindView(R.id.child_confirm_password_field_entry) EditText child_confirm_password_etv;

    //declare an instance of the my database class for database access
    MyDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //bind this activity to butter knife api
        ButterKnife.bind(this);
        //instantiate a database using the current activity
        db = new MyDatabase(this);
    }

    //allows the user to submit their registration information, go through checking of data procedures, and then return to intro
    @OnClick(R.id.submit_btn)
    protected void confirm_information(View view) {
        //ensures no fields are empty
        if(parent_first_name_etv.getText().toString().isEmpty() ||
                parent_last_name_etv.getText().toString().isEmpty() ||
                parent_email_etv.getText().toString().isEmpty() ||
                parent_username_etv.getText().toString().isEmpty() ||
                parent_password_etv.getText().toString().isEmpty() ||
                parent_confirm_password_etv.getText().toString().isEmpty() ||
                child_first_name_etv.getText().toString().isEmpty() ||
                child_username_etv.getText().toString().isEmpty() ||
                child_password_etv.getText().toString().isEmpty() ||
                child_confirm_password_etv.getText().toString().isEmpty())
        {
            //toasts if the any empty fields exist to ensure the user fills them before submitting
            Toast.makeText(RegistrationActivity.this, "Please make sure all required fields are filled.",
                    Toast.LENGTH_SHORT).show();
        }
        //validates the information
        else if((!(parent_first_name_etv.getText().toString().length() >= 3 && parent_first_name_etv.getText().toString().length() <= 30)) ||
                (!(parent_last_name_etv.getText().toString().length() >= 3 && parent_last_name_etv.getText().toString().length() <=30)) ||
                (!(parent_email_etv.getText().toString().endsWith("@example.com"))) ||
                (!(parent_password_etv.getText().toString().equals(parent_confirm_password_etv.getText().toString()))) ||
                (!(child_first_name_etv.getText().toString().length() >= 3 && child_first_name_etv.getText().toString().length() <=30)) ||
                (!(child_password_etv.getText().toString().equals(child_confirm_password_etv.getText().toString()))))
        {
            //toasts to ensure the user knows that not all fields were entered correctly and they should fix that
            Toast.makeText(RegistrationActivity.this, "Please ensure all fields were entered properly.", Toast.LENGTH_SHORT).show();
        }
        //if all seems good, now we can create a new database record, a user log file, and then return
        else
        {
            //adds the fields to the database
            db.addRecord(new String[]{parent_first_name_etv.getText().toString(),
                    parent_last_name_etv.getText().toString(), parent_email_etv.getText().toString(),
                    parent_username_etv.getText().toString(), parent_password_etv.getText().toString(),
                    child_first_name_etv.getText().toString(), child_username_etv.getText().toString(),
                    child_password_etv.getText().toString()});

            //creates a shared preference log file and then allows editing
            SharedPreferences sharedPreferences = getSharedPreferences(child_username_etv.getText().toString()+"_log_file", MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            //enters in false for all fields to represent that all levels start off incomplete
            editor.putBoolean("easy_1", false);
            editor.putBoolean("easy_2", false);
            editor.putBoolean("easy_3", false);
            editor.putBoolean("medium_1", false);
            editor.putBoolean("medium_2", false);
            editor.putBoolean("medium_3", false);
            editor.putBoolean("hard_1", false);
            editor.putBoolean("hard_2", false);
            editor.putBoolean("hard_3", false);
            editor.putBoolean("brutal_1", false);
            editor.putBoolean("brutal_2", false);
            editor.putBoolean("brutal_3", false);

            //applies the changes to the shared preferences file
            editor.apply();

            //goes back to the intro screen
            startActivity(new Intent(RegistrationActivity.this, IntroScreenActivity.class));
        }
    }
}

