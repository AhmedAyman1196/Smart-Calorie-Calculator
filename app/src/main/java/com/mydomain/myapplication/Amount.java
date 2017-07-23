package com.mydomain.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class Amount extends AppCompatActivity {

    static EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amount);
        editText = (EditText) findViewById(R.id.editText);
    }

    public void onDone(View view) {
        int result = Integer.parseInt(editText.getText().toString()); // calculated by watson
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result", result);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}
