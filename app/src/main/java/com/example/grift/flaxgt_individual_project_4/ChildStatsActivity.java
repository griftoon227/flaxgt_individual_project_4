package com.example.grift.flaxgt_individual_project_4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChildStatsActivity extends AppCompatActivity {
    //associate the views to the activity
    @BindView(R.id.child_stats_title) TextView title;
    @BindView(R.id.EZ_LVL_1_complete) TextView ez_lvl_1_complete;
    @BindView(R.id.EZ_LVL_2_complete) TextView ez_lvl_2_complete;
    @BindView(R.id.EZ_LVL_3_complete) TextView ez_lvl_3_complete;
    @BindView(R.id.MED_LVL_1_complete) TextView med_lvl_1_complete;
    @BindView(R.id.MED_LVL_2_complete) TextView med_lvl_2_complete;
    @BindView(R.id.MED_LVL_3_complete) TextView med_lvl_3_complete;
    @BindView(R.id.HRD_LVL_1_complete) TextView hrd_lvl_1_complete;
    @BindView(R.id.HRD_LVL_2_complete) TextView hrd_lvl_2_complete;
    @BindView(R.id.HRD_LVL_3_complete) TextView hrd_lvl_3_complete;
    @BindView(R.id.BRUT_LVL_1_complete) TextView brut_lvl_1_complete;
    @BindView(R.id.BRUT_LVL_2_complete) TextView brut_lvl_2_complete;
    @BindView(R.id.BRUT_LVL_3_complete) TextView brut_lvl_3_complete;

    //child username from the intent
    String childUsername = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_stats);
        //bind the views using the butter knife api
        ButterKnife.bind(this);

        //grab the intent from the login activity, put them in a bundle, and grab the child username, and set it
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if(!(extras == null || extras.isEmpty()))
            childUsername = extras.getString("childUsername");

        //get the values from the child's log file and set their text depending on what their value of completion
        SharedPreferences sharedPreferences = getSharedPreferences(childUsername+"_log_file", MODE_PRIVATE);
        if(sharedPreferences.getBoolean("easy_1", false))
            ez_lvl_1_complete.setText(getString(R.string.child_stats_complete_text));
        else
            ez_lvl_1_complete.setText(getString(R.string.child_stats_incomplete_text));

        if(sharedPreferences.getBoolean("easy_2", false))
            ez_lvl_2_complete.setText(getString(R.string.child_stats_complete_text));
        else
            ez_lvl_2_complete.setText(getString(R.string.child_stats_incomplete_text));

        if(sharedPreferences.getBoolean("easy_3", false))
            ez_lvl_3_complete.setText(getString(R.string.child_stats_complete_text));
        else
            ez_lvl_3_complete.setText(getString(R.string.child_stats_incomplete_text));

        if(sharedPreferences.getBoolean("medium_1", false))
            med_lvl_1_complete.setText(getString(R.string.child_stats_complete_text));
        else
            med_lvl_1_complete.setText(getString(R.string.child_stats_incomplete_text));

        if(sharedPreferences.getBoolean("medium_2", false))
            med_lvl_2_complete.setText(getString(R.string.child_stats_complete_text));
        else
            med_lvl_2_complete.setText(getString(R.string.child_stats_incomplete_text));

        if(sharedPreferences.getBoolean("medium_3", false))
            med_lvl_3_complete.setText(getString(R.string.child_stats_complete_text));
        else
            med_lvl_3_complete.setText(getString(R.string.child_stats_incomplete_text));

        if(sharedPreferences.getBoolean("hard_1", false))
            hrd_lvl_1_complete.setText(getString(R.string.child_stats_complete_text));
        else
            hrd_lvl_1_complete.setText(getString(R.string.child_stats_incomplete_text));

        if(sharedPreferences.getBoolean("hard_2", false))
            hrd_lvl_2_complete.setText(getString(R.string.child_stats_complete_text));
        else
            hrd_lvl_2_complete.setText(getString(R.string.child_stats_incomplete_text));

        if(sharedPreferences.getBoolean("hard_3", false))
            hrd_lvl_3_complete.setText(getString(R.string.child_stats_complete_text));
        else
            hrd_lvl_3_complete.setText(getString(R.string.child_stats_incomplete_text));

        if(sharedPreferences.getBoolean("brutal_1", false))
            brut_lvl_1_complete.setText(getString(R.string.child_stats_complete_text));
        else
            brut_lvl_1_complete.setText(getString(R.string.child_stats_incomplete_text));

        if(sharedPreferences.getBoolean("brutal_2", false))
            brut_lvl_2_complete.setText(getString(R.string.child_stats_complete_text));
        else
            brut_lvl_2_complete.setText(getString(R.string.child_stats_incomplete_text));

        if(sharedPreferences.getBoolean("brutal_3", false))
            brut_lvl_3_complete.setText(getString(R.string.child_stats_complete_text));
        else
            brut_lvl_3_complete.setText(getString(R.string.child_stats_incomplete_text));
    }

    //logout of the child stats activity
    @OnClick(R.id.logout_btn)
    protected void logout(View v){
        startActivity(new Intent(ChildStatsActivity.this, LoginActivity.class));
    }
}
