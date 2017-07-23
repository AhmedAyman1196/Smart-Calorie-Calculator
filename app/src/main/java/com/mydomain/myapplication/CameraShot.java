package com.mydomain.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class CameraShot extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 3 , AMOUNT = 1 ;
    static ImageView Iview; // stores the taken image
    static Bitmap photo;
    static TextView foodName  , foodCalText;
    static int foodCalorie ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_shot);
        Iview = (ImageView) findViewById(R.id.Iview);
        foodName = (TextView) findViewById(R.id.foodName) ;
        foodCalText = (TextView) findViewById(R.id.calories) ;
        foodCalorie = 0 ;

        // turn off button if user doesnt have a camera
        Button shoot = (Button) findViewById(R.id.shoot);
        if (!hasCamera()) {
            shoot.setEnabled(false);
            shoot.setError("You don't have a camera");
        }

    }

    // check for the camera
    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }

    // launch the camera
    public void launchCamera(View view) {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);
    }

    // return the image taken
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            photo = (Bitmap) extras.get("data");
            Iview.setImageBitmap(photo);
            getCalorie();
        }else if(requestCode == AMOUNT && resultCode == RESULT_OK){
            int result = data.getIntExtra("result", 0);
            Intent resultIntent = new Intent();
            resultIntent.putExtra("result", result * foodCalorie);
            setResult(Activity.RESULT_OK, resultIntent);
            finish();
        }
    }

    // return calorie value
    public void done(View view) {
        Intent i = new Intent(this, Amount.class);
        startActivityForResult(i, AMOUNT);
    }

    // watson here
    public void getCalorie() {
        // use Iview or photo
        // check what is the type of file that watson will take
        foodName.setText("apples");
        foodCalorie = 25 ; // per gram
        foodCalText.setText(foodCalorie+"");
    }

}
