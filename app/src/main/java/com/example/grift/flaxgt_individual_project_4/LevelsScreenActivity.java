package com.example.grift.flaxgt_individual_project_4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.grift.flaxgt_individual_project_4.LevelType.*;

public class LevelsScreenActivity extends AppCompatActivity {
    //holds the child username received from the login activity
    String childUsername = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_levels_screen);
        //binds this view to the butter knife api
        ButterKnife.bind(this);

        //receives the intent sent to this activity
        Intent intent = getIntent();
        //put the extras received from the intent into a bundle object
        Bundle extras = intent.getExtras();

        //if the bundle object has extras, set the child username to the extra sent from the login activity
        if(!(extras == null || extras.isEmpty()))
            childUsername = extras.getString("childUsername");

        //check the levels completion and open up levels depending on what is complete and what is not
        SharedPreferences userLogFile = getSharedPreferences(childUsername+"_log_file", MODE_PRIVATE);
        if(userLogFile.getBoolean("easy_1", false))
            findViewById(R.id.level_2_easy_btn).setVisibility(View.VISIBLE);
        if(userLogFile.getBoolean("easy_2", false))
            findViewById(R.id.level_3_easy_btn).setVisibility(View.VISIBLE);
        if(userLogFile.getBoolean("medium_1", false))
            findViewById(R.id.level_2_medium_btn).setVisibility(View.VISIBLE);
        if(userLogFile.getBoolean("medium_2", false))
            findViewById(R.id.level_3_medium_btn).setVisibility(View.VISIBLE);
        if(userLogFile.getBoolean("hard_1", false))
            findViewById(R.id.level_2_hard_btn).setVisibility(View.VISIBLE);
        if(userLogFile.getBoolean("hard_2", false))
            findViewById(R.id.level_3_hard_btn).setVisibility(View.VISIBLE);
        if(userLogFile.getBoolean("brutal_1", false))
            findViewById(R.id.level_2_brutal_btn).setVisibility(View.VISIBLE);
        if(userLogFile.getBoolean("brutal_2", false))
            findViewById(R.id.level_3_brutal_btn).setVisibility(View.VISIBLE);
    }

    //bind all of the image view "buttons" to the activity
    @OnClick({R.id.level_1_easy_btn, R.id.level_2_easy_btn, R.id.level_3_easy_btn, R.id.level_1_medium_btn,
            R.id.level_2_medium_btn, R.id.level_3_medium_btn, R.id.level_1_hard_btn, R.id.level_2_hard_btn,
            R.id.level_3_hard_btn, R.id.level_1_brutal_btn, R.id.level_2_brutal_btn, R.id.level_3_brutal_btn})
    //move to the game screen and send over the level type to set the game screen
    protected void move_to_game_screen(View view){
        int id = ((ImageView)view).getId();
        LevelType levelType;
        switch(id) {
            case R.id.level_1_easy_btn:
                levelType = easy_1;
                break;
            case R.id.level_2_easy_btn:
                levelType = easy_2;
                break;
            case R.id.level_3_easy_btn:
                levelType = easy_3;
                break;
            case R.id.level_1_medium_btn:
                levelType = medium_1;
                break;
            case R.id.level_2_medium_btn:
                levelType = medium_2;
                break;
            case R.id.level_3_medium_btn:
                levelType = medium_3;
                break;
            case R.id.level_1_hard_btn:
                levelType = hard_1;
                break;
            case R.id.level_2_hard_btn:
                levelType = hard_2;
                break;
            case R.id.level_3_hard_btn:
                levelType = hard_3;
                break;
            case R.id.level_1_brutal_btn:
                levelType = brutal_1;
                break;
            case R.id.level_2_brutal_btn:
                levelType = brutal_2;
                break;
            case R.id.level_3_brutal_btn:
                levelType = brutal_3;
                break;
            default:
                levelType = DEFAULT;
        }

        //ensure that an actual level type was set so that an actual game scene is created
        //also send over the child username for log file usage
        if(!levelType.equals(DEFAULT))
        {
            Intent intent = new Intent(LevelsScreenActivity.this, GameActivity.class);
            intent.putExtra("levelType", levelType);
            intent.putExtra("childUsername", childUsername);
            startActivity(intent);
        }
    }

    @OnClick(R.id.logout_btn)
    protected void logout(View v){
        startActivity(new Intent(LevelsScreenActivity.this, LoginActivity.class));
    }
}
