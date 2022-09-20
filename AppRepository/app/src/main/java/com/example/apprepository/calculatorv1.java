package com.example.apprepository;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class calculatorv1 extends AppCompatActivity {
    private EditText editBil1, editBil2;
    private TextView textHasil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculatorv1);

        getSupportActionBar().setTitle("Calculator");

        editBil1=(EditText)findViewById(R.id.editText0);
        editBil2=(EditText)findViewById(R.id.editText1);
        textHasil=(TextView)findViewById(R.id.textView2);

        Button buttonAdd = (Button) findViewById(R.id.btn1);
        buttonAdd.setOnClickListener(tambah);

        Button buttonMin = (Button) findViewById(R.id.btn2);
        buttonMin.setOnClickListener(kurang);

        Button buttonMul = (Button) findViewById(R.id.btn3);
        buttonMul.setOnClickListener(kali);

        Button buttonDiv = (Button) findViewById(R.id.btn4);
        buttonDiv.setOnClickListener(bagi);

        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.whiteb));
    }

    View.OnClickListener tambah =  new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            float bil1, bil2, hasil;

            bil1=Float.parseFloat(editBil1.getText().toString());
            bil2=Float.parseFloat(editBil2.getText().toString());
            hasil=bil1+bil2;

            textHasil.setText(bil1+" + "+bil2+" = "+hasil);
        }
    };

    View.OnClickListener kurang =  new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            float bil1, bil2, hasil;

            bil1=Float.parseFloat(editBil1.getText().toString());
            bil2=Float.parseFloat(editBil2.getText().toString());
            hasil=bil1-bil2;

            textHasil.setText(bil1+" - "+bil2+" = "+hasil);
        }
    };

    View.OnClickListener kali =  new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            float bil1, bil2, hasil;

            bil1=Float.parseFloat(editBil1.getText().toString());
            bil2=Float.parseFloat(editBil2.getText().toString());
            hasil=bil1*bil2;

            textHasil.setText(bil1+" x "+bil2+" = "+hasil);
        }
    };

    View.OnClickListener bagi =  new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            float bil1, bil2, hasil;

            bil1=Float.parseFloat(editBil1.getText().toString());
            bil2=Float.parseFloat(editBil2.getText().toString());
            hasil=bil1/bil2;

            textHasil.setText(bil1+" / "+bil2+" = "+hasil);
        }
    };
}