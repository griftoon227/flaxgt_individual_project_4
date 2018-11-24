package com.example.grift.flaxgt_individual_project_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class IntroScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);
        //bind this activity to the butter knife api
        ButterKnife.bind(this);

    }

    @OnClick({R.id.registration_btn, R.id.login_btn})
    //moves to the next screen depending on which button type is clicked
    protected void move_to_next_page(View view)
    {
        Button btn = (Button)view;

        if(btn.getId() == ((findViewById(R.id.registration_btn)).getId()))
            startActivity(new Intent(IntroScreenActivity.this, RegistrationActivity.class));
        else
            startActivity(new Intent(IntroScreenActivity.this, LoginActivity.class));
    }

}

