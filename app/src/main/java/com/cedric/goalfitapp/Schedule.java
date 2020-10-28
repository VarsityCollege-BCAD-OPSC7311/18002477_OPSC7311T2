package com.cedric.goalfitapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Schedule extends AppCompatActivity {

    EditText title;
    EditText location;
    EditText description;
    Button addSchedule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        title = findViewById(R.id.txtTitle);
        location = findViewById(R.id.txtLocation);
        description = findViewById(R.id.txtDescription);
        addSchedule = findViewById(R.id.btnAddSchedule);

        addSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!title.getText().toString().isEmpty() && !location.getText().toString().isEmpty()
                    && !description.getText().toString().isEmpty() ){

                    Intent intent = new Intent(Intent.ACTION_INSERT);
                    intent.setData(CalendarContract.Events.CONTENT_URI);
                    intent.putExtra(CalendarContract.Events.TITLE, title.getText().toString());
                    intent.putExtra(CalendarContract.Events.DESCRIPTION, description.getText().toString());
                    intent.putExtra(CalendarContract.Events.EVENT_LOCATION, location.getText().toString());
                    intent.putExtra(CalendarContract.Events.ALL_DAY, true);
                    intent.putExtra(Intent.EXTRA_EMAIL, "ntumbacedy12@gmail.com");
                    startActivity(intent);
//                    if(intent.resolveActivity(getPackageManager()) != null){
//                        startActivity(intent);
//                    }else{
//                        Toast.makeText(Schedule.this,"There is no app that can support this action",Toast.LENGTH_SHORT).show();
//                    }

                }else{
                    Toast.makeText(Schedule.this,"Please fill all the fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}