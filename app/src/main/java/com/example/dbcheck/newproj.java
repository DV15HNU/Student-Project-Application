package com.example.dbcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class newproj extends AppCompatActivity {
    public Button image, createprojbtn;
    public int SELECT_IMAGE_CODE;
    private EditText titlepro, yyear, ddescription, fname, sname, sID;
    private TextView welcome;
    NotificationManagerCompat notificationmanager;
    Notification notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newproj);
        image = findViewById(R.id.image);
        titlepro = findViewById(R.id.titleproj);
        yyear = findViewById(R.id.year);
        ddescription = findViewById(R.id.description);
        createprojbtn = findViewById(R.id.createprojbtn);
        fname = findViewById(R.id.fname);
        sname = findViewById(R.id.sname);
        sID = findViewById(R.id.studentid);

        image.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent();
                 intent.setType("image/*");
                 intent.setAction(Intent.ACTION_GET_CONTENT);
                 startActivityForResult(Intent.createChooser(intent, "Title"), SELECT_IMAGE_CODE);
             }
        });


        String var = getIntent().getStringExtra("studentID");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel newnot = new NotificationChannel("newchan", "First Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manage = getSystemService(NotificationManager.class);
            manage.createNotificationChannel(newnot);
        }

        NotificationCompat.Builder build1 = new NotificationCompat.Builder(newproj.this,"newchan")
                .setSmallIcon(android.R.drawable.stat_sys_upload_done)
                .setContentTitle("Project Created")
                .setContentText("Your new Project has been created successfully!");
        notification = build1.build();
        notificationmanager = NotificationManagerCompat.from(newproj.this);

        RequestQueue queue = Volley.newRequestQueue(this);
        String URL = "http://web.socem.plymouth.ac.uk/COMP2000/api/students";
        createprojbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("StudentID", Integer.parseInt(var));
                params.put("Title", titlepro.getText().toString());
                params.put("Description", ddescription.getText().toString());
                params.put("Year", Integer.parseInt(yyear.getText().toString()));
                params.put("First_Name", fname.getText().toString());
                params.put("Second_Name", sname.getText().toString());

                JsonObjectRequest request_json = new JsonObjectRequest(URL, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    Toast.makeText(newproj.this,"Project added.",Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.e("Error: ", error.getMessage());
                    }
                });
                queue.add(request_json);
                notificationmanager.notify(1, notification);
                Intent intent = new Intent(newproj.this,welcome.class);
                startActivity(intent);

            }
        });
    }
}
