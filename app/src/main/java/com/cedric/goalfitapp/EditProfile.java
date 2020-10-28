package com.cedric.goalfitapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class EditProfile extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private FirebaseAuth mAuth;
    Profile profile;
    EditText firstName,lastName,Age,weight,height,weightTarget,stepTarget;
    Button btn_save;

    private Switch Sw_Imperialsystem;
    private TextView tv_height, tv_weight, tv_targetWeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        firstName = findViewById(R.id.txtUserFname);
        lastName = findViewById(R.id.txtUserLName);
        Age = findViewById(R.id.txtUserAge);
        weight = findViewById(R.id.txtWeight);
        height = findViewById(R.id.txtUserHight);
        weightTarget = findViewById(R.id.txtWeightTarget);
        stepTarget = findViewById(R.id.txtStepsTarget);

        profile = new Profile();

        btn_save = findViewById(R.id.btnSave);
        // Current logged on user
        mAuth = FirebaseAuth.getInstance();

        Sw_Imperialsystem = (Switch) findViewById(R.id.swImperial);
        tv_height = findViewById(R.id.tvHeight);
        tv_weight = findViewById(R.id.tvWeight);
        tv_targetWeight = findViewById(R.id.tvWeightTarget);
        ImperialSystem();

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fName = firstName.getText().toString().trim();
                String lName = lastName.getText().toString().trim();
                int age = Integer.parseInt(Age.getText().toString().trim());
                int weightValue = Integer.parseInt(weight.getText().toString().trim());
                int heightValue = Integer.parseInt(height.getText().toString().trim());
                int weightTargetValue = Integer.parseInt(weightTarget.getText().toString().trim());
                int stepTargetValue = Integer.parseInt(stepTarget.getText().toString().trim());

                DatabaseReference myRef = database.getReference(mAuth.getCurrentUser().getUid());
                Profile profile = new Profile(fName,lName,age,weightValue,heightValue,weightTargetValue,stepTargetValue);
                myRef.child("Profiles").push().setValue(profile);
                Toast.makeText(EditProfile.this, "Profile successfully created", Toast.LENGTH_SHORT).show();

            }
        });
    }

    // This method changes the settings from metric to the imperial system
    protected void ImperialSystem(){
        Sw_Imperialsystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Sw_Imperialsystem.isChecked() == true){
                    tv_height.setText("Inch");
                    tv_weight.setText("lbs");
                    tv_targetWeight.setText("lbs");
                }
                else{
                    tv_height.setText("cm");
                    tv_weight.setText("Kg");
                    tv_targetWeight.setText("Kg");
                }
            }
        });
    }
}