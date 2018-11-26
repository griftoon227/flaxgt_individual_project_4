package com.example.grift.flaxgt_individual_project_4.db_firebase_model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MyFirebaseDatabase {
    private DatabaseReference dbRef;

    public static final String REF_PATH = "incorrect_attempts";
    private String username = "";
    private static long attempts;

    public MyFirebaseDatabase(String username){
        this.username = username;

        dbRef = FirebaseDatabase.getInstance().getReference(REF_PATH);

        //adds a value event listener for when the amount of attempts is changed using a snapshot of our data
        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()){
                    if(data.getKey().equals(username))
                    {
                        attempts = (long)data.getValue();
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.e("ERROR", databaseError.getMessage());
            }
        });
    }

    //changes a user's amounts of attempts data
    public void addOrChangeData(long attempt){
        dbRef.child(username).setValue(attempt);
    }

    //returns the current number of attempts for a user
    public long getCurrentNumberOfAttempts(){
        return attempts;
    }
}
