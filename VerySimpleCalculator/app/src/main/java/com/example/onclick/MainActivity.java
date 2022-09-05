package com.example.onclick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editBil1, editBil2;
    private TextView textHasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editBil1=(EditText)findViewById(R.id.editText1);
        editBil2=(EditText)findViewById(R.id.editText0);
        textHasil=(TextView)findViewById(R.id.textView2);
    }

    public void tambah(View v) {
        float bil1, bil2, hasil;

        bil1=Float.parseFloat(editBil1.getText().toString());
        bil2=Float.parseFloat(editBil2.getText().toString());
        hasil=bil1+bil2;

        textHasil.setText(bil1+" + "+bil2+" = "+hasil);
    }

    public void kurang(View v) {
        float bil1, bil2, hasil;

        bil1=Float.parseFloat(editBil1.getText().toString());
        bil2=Float.parseFloat(editBil2.getText().toString());
        hasil=bil1-bil2;

        textHasil.setText(bil1+" - "+bil2+" = "+hasil);
    }

    public void kali(View v) {
        float bil1, bil2, hasil;

        bil1=Float.parseFloat(editBil1.getText().toString());
        bil2=Float.parseFloat(editBil2.getText().toString());
        hasil=bil1*bil2;

        textHasil.setText(bil1+" * "+bil2+" = "+hasil);
    }

    public void bagi(View v) {
        float bil1, bil2, hasil;

        bil1=Float.parseFloat(editBil1.getText().toString());
        bil2=Float.parseFloat(editBil2.getText().toString());
        hasil=bil1/bil2;

        textHasil.setText(bil1+" / "+bil2+" = "+hasil);
    }
}