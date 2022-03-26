package com.example.dbcheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class signin extends AppCompatActivity {
    public Button signinbtn;
    private EditText sID, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        sID = findViewById(R.id.studentid);

        signinbtn = (Button) findViewById(R.id.signinbtn);
        signinbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText password = (EditText) findViewById(R.id.password);
                if (sID.length() == 0) {
                    sID.setError("Student ID Required");
                } else if (password.length() == 0) {
                    password.setError("Password is Required");
                }
                else {
                    if (password.getText().toString().equals("admin")) {
                        String ID = sID.getText().toString();
                        Intent intent = new Intent(signin.this, welcome.class);
                        intent.putExtra("studentID",ID);
                        startActivity(intent);
                        Toast.makeText(signin.this, "SUCCESS", Toast.LENGTH_SHORT).show();
                    } else{
                        Toast.makeText(signin.this, "FAILED", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}