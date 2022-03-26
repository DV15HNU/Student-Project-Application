package com.example.dbcheck;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;

public class contactRecViewEdit extends RecyclerView.Adapter<contactRecViewEdit.ViewHolder> {

    private ArrayList<contact> contacts = new ArrayList<>();

    private Context context;
    NotificationManagerCompat notificationmanager;
    Notification notification;




    public contactRecViewEdit(Context context) {
        this.context = context;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parents, int viewType) {
        View view = LayoutInflater.from(parents.getContext()).inflate(R.layout.eporj, parents, false);
        ViewHolder holder = new ViewHolder(view);



        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.txtName1.setText(contacts.get(position).getTitle());
        holder.txtYear1.setText(String.valueOf(contacts.get(position).getYear()));
        holder.desc1.setText(contacts.get(position).getDescription());


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue queue = Volley.newRequestQueue(context);
                String url = "http://web.socem.plymouth.ac.uk/COMP2000/api/students/" + String.valueOf(contacts.get(position).getProjectsID());
                JsonArrayRequest dl = new JsonArrayRequest(Request.Method.DELETE, url,null,
                        new Response.Listener<JSONArray>()
                        {
                            @Override
                            public void onResponse(JSONArray response) {
                                // response
                                notifyDataSetChanged();
                                Toast.makeText(context,response.toString(), Toast.LENGTH_SHORT).show();
                            }
                        },
                        new Response.ErrorListener()
                        {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                notifyDataSetChanged();
                                Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(context,welcome.class);
                                context.startActivity(intent);
                            }
                        }
                );
                queue.add(dl);
//                Intent intent = new Intent(context,welcome.class);
//                context.startActivity(intent);
            }
        });


        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ediproject.class);
                intent.putExtra("projectID",String.valueOf(contacts.get(position).getProjectsID()));
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public void setContacts(ArrayList<contact> contacts) {
        this.contacts = contacts;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName1, txtYear1, desc1;
        private CardView parents;
        private Button delete, editbtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName1 = itemView.findViewById(R.id.txtname1);
            parents = itemView.findViewById(R.id.parents);
            txtYear1 = itemView.findViewById(R.id.txtemail1);
            desc1 = itemView.findViewById(R.id.desc1);
            delete = itemView.findViewById(R.id.delete);
            editbtn = itemView.findViewById(R.id.editbtn);
        }


    }


}
