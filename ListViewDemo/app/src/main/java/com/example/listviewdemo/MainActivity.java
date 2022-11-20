package com.example.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    private ListView lv;
    private ArrayAdapter<String> kontak;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.listView);
        String[] nama = new String[] {"Agus", "Budi", "Cica", "Dani", "Eka", "Fani", "Gina", "Hadi", "Indah", "Joko", "Kana", "Lina", "Maya", "Nanik", "Opi", "Putra", "Qantas", "Rama"};
//        ArrayList<String> listNama = new ArrayList<>();
//        listNama.addAll(Arrays.asList(nama));
        kontak = new ArrayAdapter<String>(this, R.layout.simplerow, R.id.textView, nama);
        lv.setAdapter(kontak);
    }
}