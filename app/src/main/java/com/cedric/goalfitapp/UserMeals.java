package com.cedric.goalfitapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserMeals extends AppCompatActivity {

    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private FirebaseAuth mAuth;
    Meal meal;
    public static final int CAMERA_ACTION_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    private Button btnTakePhoto,btn_Submit;
    private ImageView imgUserMeal;
    private EditText calories,sugarCount,fatCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_meals);

        imgUserMeal = findViewById(R.id.imgMeal);
        btnTakePhoto = findViewById(R.id.btnMealPhoto);

        calories = findViewById(R.id.txtCalories);
        sugarCount = findViewById(R.id.txtSugar);
        fatCount = findViewById(R.id.txtFat);
        btn_Submit = findViewById(R.id.btnSubmitMeal);

        meal = new Meal();
        // Current logged on user
        mAuth = FirebaseAuth.getInstance();
        btn_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(calories.getText().toString().isEmpty() || sugarCount.getText().toString().isEmpty() || fatCount.getText().toString().isEmpty())
                {
                    Toast.makeText(UserMeals.this, "One or many fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    int caloriesValue = Integer.parseInt(calories.getText().toString().trim());
                    int sugarsValue = Integer.parseInt(sugarCount.getText().toString().trim());
                    int fatValue = Integer.parseInt(fatCount.getText().toString().trim());

                    DatabaseReference myRef = database.getReference(mAuth.getCurrentUser().getUid());
                    Meal meal = new Meal(caloriesValue,sugarsValue,fatValue);
                    myRef.child("Today's Meal").push().setValue(meal);
                    Toast.makeText(UserMeals.this, "Meal successfully Added", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnTakePhoto.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                askCameraPermissions();
            }
        });
    }
    // Method used to request for camera permission
    private void askCameraPermissions() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA},CAMERA_ACTION_CODE);
        }else{
            openCamera();
        }
    }
    // Method used to return the request for camera permission
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == CAMERA_ACTION_CODE){
            if (grantResults.length < 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                openCamera();
            }else{
                Toast.makeText(this, "Camera permission is required to use camera", Toast.LENGTH_SHORT).show();
            }

        }
    }
    // Method used to open a camera with the use of intent
    private void openCamera() {
        Intent camera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE);
    }
    // Method used to display the captured photo onto an image view
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            Bitmap image = (Bitmap) data.getExtras().get("data");
            imgUserMeal.setImageBitmap(image);
        }
    }
}