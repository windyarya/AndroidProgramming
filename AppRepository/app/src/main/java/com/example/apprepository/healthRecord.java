package com.example.apprepository;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class healthRecord extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private Button dateButton, dateButton2;
    private EditText nama, desc, tinggi, berat, judul;
    private String[] jenisklm = {"Perempuan", "Laki-Laki"};
    private AutoCompleteTextView jeniskl;
    private ArrayAdapter<String> adItems;
    private Button submitD, cekD;
    private SQLiteDatabase ehr;
    private SQLiteOpenHelper Opendb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_record);
        getSupportActionBar().setTitle("Health Record");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initDatePicker();

        nama = (EditText) findViewById(R.id.namark);
        desc = (EditText) findViewById(R.id.descrk);
        tinggi = (EditText) findViewById(R.id.heightrk);
        berat = (EditText) findViewById(R.id.weightrk);
        jeniskl = findViewById(R.id.jeniskrk);
        judul = (EditText) findViewById(R.id.judulrk);
        submitD = (Button) findViewById(R.id.submitRecord);
        cekD = (Button) findViewById(R.id.cekRecord);
        dateButton = findViewById(R.id.tgllhrrk);
        dateButton2 = findViewById(R.id.tglrk);
        dateButton.setText(getTodaysDate());
        dateButton2.setText(getTodaysDate());

        adItems = new ArrayAdapter<String>(this, R.layout.dropdown_item, jenisklm);

        jeniskl.setAdapter(adItems);
        jeniskl.setOnItemClickListener(opJK);
        submitD.setOnClickListener(opData);
        cekD.setOnClickListener(opData);
        dateButton.setOnClickListener(opDatePicker);
        dateButton2.setOnClickListener(opDatePicker);

        Opendb = new SQLiteOpenHelper(this, "ehrecord.sql", null, 1) {
            @Override
            public void onCreate(SQLiteDatabase ehrecord) {

            }

            @Override
            public void onUpgrade(SQLiteDatabase ehrecord, int oldVersion, int newVersion) {

            }
        };

        ehr = Opendb.getWritableDatabase();
        ehr.execSQL("create table if not exists record(nama TEXT, tanggal_lahir TEXT, jenis_kelamin TEXT, tinggi_badan REAL, berat_badan REAL, judul_record TEXT, tanggal_record TEXT, deskripsi TEXT);");
    }
    @Override
    protected void onStop() {
        super.onStop();
        ehr.close();
        Opendb.close();
    }

    AdapterView.OnItemClickListener opJK = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
            String item = parent.getItemAtPosition(pos).toString();
//            Toast.makeText(getApplicationContext(), "Jenis Kelamin: " + jenisklm, Toast.LENGTH_SHORT).show();
        }
    };

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
                case R.id.submitRecord:submitData();break;
                case R.id.cekRecord:cek();break;
            }
        }
    };

    private void submitData() {
        AlertDialog.Builder confirm = new AlertDialog.Builder(this);
        confirm.setTitle("Konfirmasi Record");
        confirm.setMessage("Apakah anda yakin terhadap record yang anda masukan?");
        confirm.setCancelable(false);
        confirm.setPositiveButton("Iya", alertBtn);
        confirm.setNegativeButton("Tidak", alertBtn);

        AlertDialog alertDialog =  confirm.create();
        alertDialog.show();
    }

    DialogInterface.OnClickListener alertBtn = new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int which) {
            switch (which) {
                case -1: submit();break;
                case -2: break;
            }
        }
    };

    private void submit() {
        ContentValues newData = new ContentValues();

        newData.put("nama", nama.getText().toString());
        newData.put("tanggal_lahir", dateButton.getText().toString());
        newData.put("jenis_kelamin", jeniskl.getText().toString());
        newData.put("tinggi_badan", tinggi.getText().toString());
        newData.put("berat_badan", berat.getText().toString());
        newData.put("judul_record", judul.getText().toString());
        newData.put("tanggal_record", dateButton2.getText().toString());
        newData.put("deskripsi", desc.getText().toString());
        ehr.insert("record", null, newData);
        Toast.makeText(this, "Data Tersimpan", Toast.LENGTH_LONG).show();
    }

    private void cek() {
        Cursor cur = ehr.rawQuery("select * from record where nama='" + nama.getText().toString() + "'", null);

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