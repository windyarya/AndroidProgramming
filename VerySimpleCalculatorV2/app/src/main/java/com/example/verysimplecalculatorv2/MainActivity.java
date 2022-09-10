package com.example.verysimplecalculatorv2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        Button buttonAdd = (Button) findViewById(R.id.btn1);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                float bil1, bil2, hasil;

                bil1=Float.parseFloat(editBil1.getText().toString());
                bil2=Float.parseFloat(editBil2.getText().toString());
                hasil=bil1+bil2;

                textHasil.setText(bil1+" + "+bil2+" = "+hasil);
            }
        });

        Button buttonMin = (Button) findViewById(R.id.btn2);
        buttonMin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                float bil1, bil2, hasil;

                bil1=Float.parseFloat(editBil1.getText().toString());
                bil2=Float.parseFloat(editBil2.getText().toString());
                hasil=bil1-bil2;

                if (hasil % 1 != 0) {
                    hasil = Math.round(hasil);
                }

                textHasil.setText(bil1+" - "+bil2+" = "+hasil);
            }
        });

        Button buttonMul = (Button) findViewById(R.id.btn3);
        buttonMul.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                float bil1, bil2, hasil;

                bil1=Float.parseFloat(editBil1.getText().toString());
                bil2=Float.parseFloat(editBil2.getText().toString());
                hasil=bil1*bil2;

                textHasil.setText(bil1+" * "+bil2+" = "+hasil);
            }
        });

        Button buttonDiv = (Button) findViewById(R.id.btn4);
        buttonDiv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                float bil1, bil2, hasil;

                bil1=Float.parseFloat(editBil1.getText().toString());
                bil2=Float.parseFloat(editBil2.getText().toString());
                hasil=bil1/bil2;

                textHasil.setText(bil1+" / "+bil2+" = "+hasil);
            }
        });
    }
}