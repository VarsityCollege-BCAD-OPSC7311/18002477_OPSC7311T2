package com.cedric.goalfitapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class UserProfile extends AppCompatActivity {

    EditText pullFirstName,pullLastName,pullAge,pullWeight,pullHeight,pullWeightTarget,pullStepTarget;
    private FirebaseAuth mAuth;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Profiles");
    List<String> profileList;
    ArrayAdapter adapter;
    Profile profile;

    ListView lv_profile;
    Button btn_CreateProfile,btn_EditUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        pullFirstName = findViewById(R.id.txtUserFname);
        pullLastName = findViewById(R.id.txtUserLName);
        pullAge = findViewById(R.id.txtUserAge);
        pullWeight = findViewById(R.id.txtWeight);
        pullHeight = findViewById(R.id.txtUserHight);
        pullWeightTarget = findViewById(R.id.txtWeightTarget);
        pullStepTarget = findViewById(R.id.txtStepsTarget);

        lv_profile = findViewById(R.id.lvProfile);
        btn_CreateProfile = findViewById(R.id.btnCreateUser);
        btn_EditUser = findViewById(R.id.btnEditUser);

        mAuth = FirebaseAuth.getInstance();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                profile = new Profile();
                profileList = new ArrayList<String>();
                for(DataSnapshot firebaseProfiles : dataSnapshot.getChildren())
                {
                    profile = firebaseProfiles.getValue(Profile.class);
                    profileList.add(profile.toString());
                }
                adapter = new ArrayAdapter(UserProfile.this,android.R.layout.simple_list_item_1,profileList);
                lv_profile.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(UserProfile.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        btn_EditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatabaseReference myRef = database.getReference(mAuth.getCurrentUser().getUid());
                myRef.child("Profiles").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        for(DataSnapshot profileValues : dataSnapshot.getChildren())
                        {
                            profile = profileValues.getValue(Profile.class);
                        }
                        pullFirstName.setText(profile.getFirstName());
                        pullLastName.setText(profile.getLastName());
                        pullAge.setText(profile.getAge());
                        pullWeight.setText(profile.getWeight());
                        pullHeight.setText(profile.getHeight());
                        pullWeightTarget.setText(profile.getWeightTarget());
                        pullStepTarget.setText(profile.getStepsTarget());

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                        Toast.makeText(UserProfile.this,databaseError.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
                startActivity(new Intent(getApplicationContext(), EditProfile.class));
            }
        });

        btn_CreateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), EditProfile.class));
            }
        });
    }
}