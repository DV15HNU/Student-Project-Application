package com.example.dbcheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class welcome extends AppCompatActivity {
    public Button logout, newproject, myprojects, editproject;
    private String ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ID = getIntent().getStringExtra("studentID");
        logout = (Button) findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(welcome.this, MainActivity.class);
                startActivity(intent);
            }
        });

        newproject= (Button) findViewById(R.id.newproject);
        newproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(welcome.this,newproj.class);
                intent.putExtra("studentID",ID);
                startActivity(intent);
            }
        });

        editproject = (Button) findViewById(R.id.editproject);
        editproject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(welcome.this, editproj.class);
                intent.putExtra("studentID",ID);
                startActivity(intent);
            }
        });

        myprojects = (Button) findViewById(R.id.myprojects);
        myprojects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(welcome.this, myproj.class);
                intent.putExtra("studentID", ID);
                startActivity(intent);
            }
        });
    }
}