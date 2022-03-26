package com.example.dbcheck;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class editproj extends AppCompatActivity {
    private TextView projname;
    private RequestQueue nqueue;
    private RecyclerView contactsRecView;
    private ProgressBar pb;
    private Button delete, editbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editproj);
        pb = findViewById(R.id.pb);
        contactsRecView = findViewById(R.id.contactsRecView);
        int id = Integer.parseInt(getIntent().getStringExtra("studentID"));
        ArrayList<contact> contacts = new ArrayList<contact>();
        contactRecViewEdit adapter = new contactRecViewEdit(this);
        RequestQueue queue = Volley.newRequestQueue(editproj.this);
        String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/students";
        pb.setVisibility(View.VISIBLE);
        JsonArrayRequest getRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject currentProject = response.getJSONObject(i);
                                if(id == currentProject.getInt("studentID")) {
                                    int projectID = currentProject.getInt("projectID");
                                    int studentID = currentProject.getInt("studentID");
                                    String title = currentProject.getString("title");
                                    String description = currentProject.getString("description");
                                    int year = currentProject.getInt("year");
                                    String photo = currentProject.getString("photo");
                                    contacts.add(new contact(studentID, projectID, title, description, year, photo));
                                }
                            }
                        } catch (JSONException e) {

                        }
                        pb.setVisibility(View.GONE);
                        contactsRecView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(editproj.this, "volley error:" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(getRequest);
        adapter.setContacts(contacts);
        contactsRecView.setLayoutManager(new LinearLayoutManager(this));
    }
}