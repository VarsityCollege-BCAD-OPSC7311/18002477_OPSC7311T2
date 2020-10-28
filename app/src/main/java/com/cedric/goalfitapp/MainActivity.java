package com.cedric.goalfitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Method used to display a toast message
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }


     //Shows a message of the Card view clicked.
    public void showStatsMessage(View view) {
        displayToast(getString(R.string.Statistics_Construct));
    }
    public void showGoalsMessage(View view) {
        displayToast(getString(R.string.Goals_Construct));
    }
    // Method used to open the food information activity
    public void foodActivity(View view){
        Intent intent = new Intent(this, UserMeals.class);
        startActivity(intent);
    }
    public void scheduleActivity(View view){
        Intent intent = new Intent(this, Schedule.class);
        startActivity(intent);
    }
    public void exerciseActivity(View view){
        Intent intent = new Intent(this, Exercises.class);
        startActivity(intent);
    }
    public void EditProfileActivity(View view){
        Intent intent = new Intent(this, EditProfile.class);
        startActivity(intent);
    }
    public void ProfileActivity(View view){
        Intent intent = new Intent(this, UserProfile.class);
        startActivity(intent);
    }
}