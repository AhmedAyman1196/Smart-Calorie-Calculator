package com.mydomain.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class choose extends AppCompatActivity {

    static final int AMOUNT = 1;
    static int choosenCalories;
    static int calories[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
        choosenCalories = 0;

        // here load from database

        String food[] = {"apple", "hamburger", "pizza", "tuna", "candy", "potato", "fool"};
        calories = new int[]{10, 20, 30, 40, 50, 60, 70};

        ListAdapter adapter = new ArrayAdapter<>
                (this, android.R.layout.simple_list_item_1, food);

        ListView listView = (ListView) findViewById(R.id.listView);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override                          //  parent               , postion , id
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        String clicked = String.valueOf(adapterView.getItemAtPosition(i));
                        Toast.makeText(choose.this, calories[i]+" Calories", Toast.LENGTH_LONG).show();
                        // calculation
                        choosenCalories = getCalories(i);
                        getAmount();
                    }
                }
        );
    }

    public void getAmount() {
        Intent i = new Intent(this, Amount.class);
        startActivityForResult(i, AMOUNT);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case (AMOUNT): {
                if (resultCode == Activity.RESULT_OK) {
                    // passing result(amount) * choosenCalories
                    int result = data.getIntExtra("result", 0);
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("result", result * choosenCalories);
                    setResult(Activity.RESULT_OK, resultIntent);
                    finish();
                }
                break;
            }
        }
    }


    public int getCalories(int pos) {
        return calories[pos];
    }


}
