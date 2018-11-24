package com.example.grift.flaxgt_individual_project_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.grift.flaxgt_individual_project_4.db_model.MyDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    //binds the needed views to the activity
    @BindView(R.id.username_field_entry) EditText username_etv;
    @BindView(R.id.password_field_entry) EditText password_etv;
    @BindView(R.id.toggle_account_checkbox) CheckBox toggle_acct_cb;

    //declare a database variable for later usage
    private MyDatabase db;

    //creates an enumeration for account types parent and child
    private enum Account_Types {
        PARENT,
        CHILD
    }

    //initializes a current account type so there is a beginning point for toggling
    private Account_Types current_account_type = Account_Types.CHILD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //binds butter knife api to this activity
        ButterKnife.bind(this);

        //instantiates database within this context
        db = new MyDatabase(this);

        //sets the current account type to child for starting off purposes
        toggle_acct_cb.setText(String.format("%s %s", getString(R.string.toggle_account_cb_text), current_account_type));
    }

    //confirms user login based on account type
    @OnClick(R.id.login_btn)
    protected void confirm_login(View view){
        //if its a parent account
        if(current_account_type.equals(Account_Types.PARENT))
        {
            //check db for parent acct
            if(db.validateParentAccount(username_etv.getText().toString(), password_etv.getText().toString())) {
                //start new statistics for child activity
                Intent intent = new Intent(LoginActivity.this, ChildStatsActivity.class);
                intent.putExtra("childUsername", db.getChildUsernameByParentAccount(username_etv.getText().toString()));
                startActivity(intent);
            }
            else
                //Toast that information is incorrect or nonexistent
                Toast.makeText(getApplicationContext(),"Username or password does not exist or is incorrect.",
                        Toast.LENGTH_SHORT).show();
        }
        else
        {
            //check db for existence of child acct
            if(db.validateChildAccount(username_etv.getText().toString(), password_etv.getText().toString())) {
                //start new game activity
                Intent intent = new Intent(LoginActivity.this, LevelsScreenActivity.class);
                intent.putExtra("childUsername", username_etv.getText().toString());
                startActivity(intent);
            }
            else
                //Toast that information is incorrect or nonexistent
                Toast.makeText(getApplicationContext(),"Username or password does not exist or is incorrect.",
                        Toast.LENGTH_SHORT).show();
        }
    }

    //toggle the account type for parent and child
    @OnClick(R.id.toggle_account_checkbox)
    protected void toggle_account_type(View view){
        //if it is checked
        if(toggle_acct_cb.isChecked())
            //set account type to parent
            current_account_type = Account_Types.PARENT;
        else
            //set account type to child
            current_account_type = Account_Types.CHILD;

        toggle_acct_cb.setText(String.format("%s %s", getString(R.string.toggle_account_cb_text), current_account_type));
    }

    //allow the user to return to the intro screen
    @OnClick(R.id.go_back_button)
    protected void go_back(View view){
        startActivity(new Intent(LoginActivity.this, IntroScreenActivity.class));
    }
}
