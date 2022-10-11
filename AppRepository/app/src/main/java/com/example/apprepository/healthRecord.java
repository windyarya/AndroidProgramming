package com.example.apprepository;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class healthRecord extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private EditText judul, desc, tinggi, berat;
    private Button submitD, tgl,  cekD;
    private SQLiteDatabase ehr;
    private SQLiteOpenHelper Opendb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_record);
        getSupportActionBar().setTitle("Health Record");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initDatePicker();

        judul = (EditText) findViewById(R.id.judulrk);
        tgl = (Button) findViewById(R.id.tglrk);
        desc = (EditText) findViewById(R.id.descrk);
        tinggi = (EditText) findViewById(R.id.heightrk);
        berat = (EditText) findViewById(R.id.weightrk);
        submitD = (Button) findViewById(R.id.submitRecord);
        cekD = (Button) findViewById(R.id.cekRecord);
        dateButton = findViewById(R.id.tglrk);
        dateButton.setText(getTodaysDate());

        submitD.setOnClickListener(opData);
        cekD.setOnClickListener(opData);
        dateButton.setOnClickListener(opDatePicker);

        Opendb = new SQLiteOpenHelper(this, "ehrec.sql", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase ehrec) {

            }

            @Override
            public void onUpgrade(SQLiteDatabase ehrec, int oldVersion, int newVersion) {

            }
        };

        ehr = Opendb.getWritableDatabase();
        ehr.execSQL("create table if not exists rec(judul TEXT, tanggal TEXT, tinggi_badan TEXT, berat_badan TEXT, deskripsi TEXT);");
    }
    @Override
    protected void onStop() {
        super.onStop();
        ehr.close();
        Opendb.close();
    }

    View.OnClickListener opDatePicker = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openDatePicker();
        }
    };

    View.OnClickListener opData = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.submitRecord:submit();break;
                case R.id.cekRecord:cek();break;
            }
        }
    };

    private void submit() {
        ContentValues newData = new ContentValues();

        newData.put("judul", judul.getText().toString());
        newData.put("tanggal", tgl.getText().toString());
        newData.put("deskripsi", desc.getText().toString());
        newData.put("tinggi_badan", tinggi.getText().toString());
        newData.put("berat_badan", berat.getText().toString());
        ehr.insert("rec", null, newData);
        Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_LONG).show();
    }

    private void cek() {
        Cursor cur = ehr.rawQuery("select * from rec where tanggal='" + tgl.getText().toString() + "'", null);

        if (cur.getCount() > 0) {
            Toast.makeText(this, "Record ditemukan sejumlah " + cur.getCount(), Toast.LENGTH_LONG).show();
            cur.moveToFirst();
        } else {
            Toast.makeText(this, "Data tidak ditemukan", Toast.LENGTH_LONG).show();
        }
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return convDateString(day, month, year);
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int day, int month, int year) {
                month = month + 1;
                String date = convDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
    }

    private String convDateString(int day, int month, int year) {
        String strDate = String.valueOf(day);
        String strMonth = String.valueOf(month);
        String strYear = String.valueOf(year);
        return strDate + "-" + strMonth + "-" + strYear;
    }

    public void openDatePicker() {
        datePickerDialog.show();
    }
}