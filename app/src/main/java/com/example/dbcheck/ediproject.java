package com.example.dbcheck;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;

public class ediproject extends AppCompatActivity {
    public Button editprojbtn;
    private EditText title, year, description, sID;
    private int projectID;
    NotificationManagerCompat notificationmanager;
    Notification notification;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ediproject);
        title = findViewById(R.id.titleedit);
        year = findViewById(R.id.yearedit);
        description = findViewById(R.id.descriptionedit);
        editprojbtn = findViewById(R.id.editprojbtn);
        sID = findViewById(R.id.studentid);
        projectID = Integer.parseInt((String) getIntent().getStringExtra("projectID"));

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel newnot = new NotificationChannel("newchan", "First Channel", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manage = getSystemService(NotificationManager.class);
            manage.createNotificationChannel(newnot);
        }

        NotificationCompat.Builder build1 = new NotificationCompat.Builder(ediproject.this,"newchan")
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle("Project Updated")
                .setContentText("Your new Project has been updated successfully!");

        notification = build1.build();
        notificationmanager = NotificationManagerCompat.from(ediproject.this);

        editprojbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(ediproject.this);
                String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/students/" + String.valueOf(projectID);

                HashMap<String, Object> params = new HashMap<String, Object>();
                params.put("Title", title.getText().toString());
                params.put("Year", Integer.parseInt(year.getText().toString()));
                params.put("Description", description.getText().toString());

                JsonObjectRequest put = new JsonObjectRequest(Request.Method.PUT,url, new JSONObject(params),
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    Toast.makeText(ediproject.this, "Project Updated.", Toast.LENGTH_SHORT).show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ediproject.this, "Project Updated", Toast.LENGTH_SHORT).show();
                        notificationmanager.notify(1, notification);
                        Intent intent = new Intent(ediproject.this,editproj.class);
                        startActivity(intent);
                    }
                });
                queue.add(put);
            }
        });
    }
}