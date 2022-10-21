package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contacts.adaptor.RecycleviewAdaptor;
import com.example.contacts.adaptor.displayContact;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class editContact extends AppCompatActivity {

    private RecyclerView contactRv;
    private ListView lv;
    private RecycleviewAdaptor adaptor;
    private ArrayList<model> contactArrayList;
    public Context context;

    private ArrayAdapter<String> arrayAdapter;

    private My_db_handler dbHelper;
    private FloatingActionButton updatebtn;
    public  int i;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_layout,menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        My_db_handler db = new My_db_handler(editContact.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);
        Intent intent=getIntent();

        String id=intent.getStringExtra("id");

        String fname=intent.getStringExtra("Lname");
        String lname=intent.getStringExtra("Fname");
        String email=intent.getStringExtra("email");
        String phone=intent.getStringExtra("phone");
        EditText fnameD=findViewById(R.id.DisplayFName);

        EditText lnameD=findViewById(R.id.DisplayLName);

        EditText emailD=findViewById(R.id.Displayemail);
        EditText phoneD=findViewById(R.id.Displayphone);

        fnameD.setText(fname);
        emailD.setText(email);
        phoneD.setText(phone);
        lnameD.setText(lname);
        Log.d( "position",id);
        i=Integer.parseInt(id);

        updatebtn=(FloatingActionButton) findViewById(R.id.updatebutton);

        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                model contact = new model(lnameD.getText().toString(),fnameD.getText().toString(),emailD.getText().toString(),phoneD.getText().toString());

                Log.d("clicked",fnameD.getText().toString());
          //      db.updateContact(String.valueOf(i),contact);
                Toast.makeText(getApplicationContext(), "Updated", Toast.LENGTH_SHORT).show();

                //Init();
            }
        });

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        My_db_handler db = new My_db_handler(editContact.this);
        switch (item.getItemId())
        {
            case R.id.delete:
                Log.d("delete",String.valueOf(i));


                db.deleteContactById(i);
                // Init();
                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                finish();

        }

        return true;
    }



    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}