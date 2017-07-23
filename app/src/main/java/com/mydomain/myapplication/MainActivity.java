package com.mydomain.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static Button cameraButton;
    static Button chooseButton;
    static TextView calories;
    static int totalCalories;

    static final int CAMERA = 1, CHOOSE = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle data = getIntent().getExtras();

        cameraButton = (Button) findViewById(R.id.camera_button);
        chooseButton = (Button) findViewById(R.id.choose_button);
        calories = (TextView) findViewById(R.id.calories);
        calories.setText("0");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (CHOOSE): {
                if (resultCode == Activity.RESULT_OK) {
                    int result = data.getIntExtra("result", 0);
                    totalCalories += result;
                    calories.setText(totalCalories + "");
                }
                break;
            }
            case (CAMERA): {
                int result = data.getIntExtra("result", 0);
                totalCalories += result;
                calories.setText(totalCalories + "");
                break;
            }
        }
    }

    public void cameraOnclick(View view) {
        Intent i = new Intent(this, CameraShot.class);
        startActivityForResult(i, CAMERA);
    }

    public void chooseOnClick(View view) {
        Intent i = new Intent(this, choose.class);
        startActivityForResult(i, CHOOSE);
    }


}
