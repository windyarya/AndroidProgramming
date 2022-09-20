package com.example.apprepository;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Rewind");
        Button bCalc = (Button) findViewById(R.id.calc);
        Button bBio = (Button) findViewById(R.id.bio);
        Button bIG = (Button) findViewById(R.id.ig);
        Button bLinkedin = (Button) findViewById(R.id.linkedin);
        Button bGithub = (Button) findViewById(R.id.github);
        bCalc.setOnClickListener(opCalc);
        bBio.setOnClickListener(opBio);
        bIG.setOnClickListener(opIG);
        bLinkedin.setOnClickListener(opLinkedIn);
        bGithub.setOnClickListener(opGithub);

        getWindow().getDecorView().setBackgroundColor(getResources().getColor(R.color.whiteb));
    }

    View.OnClickListener opCalc = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openCalc();
        }
    };

    View.OnClickListener opBio = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            openBio();
        }
    };

    View.OnClickListener opIG = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            linkIG();
        }
    };

    View.OnClickListener opLinkedIn = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            linkLinkedIn();
        }
    };

    View.OnClickListener opGithub = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            linkGithub();
        }
    };

    void openCalc() {
        Intent intentCalc = new Intent(getBaseContext(), calculatorv1.class);
        startActivityForResult(intentCalc, 0);
    }

    void openBio() {
        Intent intentBio = new Intent(getBaseContext(), biodata.class);
        startActivityForResult(intentBio, 0);
    }

    void linkIG() {
        String url = "https://www.instagram.com/1windyoung/";
        Intent ig = new Intent(Intent.ACTION_VIEW);
        ig.setData(Uri.parse(url));
        startActivity(ig);
    }

    void linkLinkedIn() {
        String url = "https://www.linkedin.com/in/windyarya/";
        Intent li = new Intent(Intent.ACTION_VIEW);
        li.setData(Uri.parse(url));
        startActivity(li);
    }

    void linkGithub() {
        String url = "https://github.com/windyarya";
        Intent li = new Intent(Intent.ACTION_VIEW);
        li.setData(Uri.parse(url));
        startActivity(li);
    }
}