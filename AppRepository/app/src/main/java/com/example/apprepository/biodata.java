package com.example.apprepository;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toolbar;

public class biodata extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);
        getSupportActionBar().setTitle("Biodata");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.whiteb));
    }
}