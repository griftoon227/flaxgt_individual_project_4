package com.example.grift.flaxgt_individual_project_4;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.grift.flaxgt_individual_project_4.adapters.LinkedHashMapAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedHashMap;

import static com.example.grift.flaxgt_individual_project_4.db_firebase_model.MyFirebaseDatabase.*;

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
    @BindView(R.id.listview) ListView listview;

    //child username from the intent
    String childUsername = "";

    //FireBase database reference and to listen to changes to the database
    DatabaseReference mRef;
    ChildEventListener mChildEventListener;

    //Lists of users and their # of attempts
    LinkedHashMap<String, Long> incorrect_attempts_per_user;

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

        //Instantiate database variables and create the leader board list view
        mRef = FirebaseDatabase.getInstance().getReference(REF_PATH);
        Query query = mRef.orderByValue().limitToFirst(5);

        incorrect_attempts_per_user = new LinkedHashMap<>();

        //create a value event listener for our query to update the list view data and keep it persistent
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                incorrect_attempts_per_user.clear();
                for(DataSnapshot data : dataSnapshot.getChildren()){
                    incorrect_attempts_per_user.put(data.getKey(), (long)data.getValue());
                }
                LinkedHashMapAdapter linkedHashMapAdapter = new LinkedHashMapAdapter(listview.getContext(), incorrect_attempts_per_user);
                listview.setAdapter(linkedHashMapAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("ERROR", databaseError.getMessage());
            }
        };

        //add the value event listener to our previously defined query
        query.addValueEventListener(valueEventListener);
    }

    //logout of the child stats activity
    @OnClick(R.id.logout_btn)
    protected void logout(View v){
        startActivity(new Intent(ChildStatsActivity.this, LoginActivity.class));
    }
}
