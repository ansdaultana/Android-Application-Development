package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;


import com.example.contacts.adaptor.RecycleviewAdaptor;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
private String phoneNum;
    private boolean firstTime=true;
    private FloatingActionButton FButton;
    private RecyclerView contactRv;
    private ListView lv;
    private RecycleviewAdaptor adaptor;
    private ArrayList<model>contactArrayList;
    private ArrayAdapter<String>arrayAdapter;
    private SearchView searchView;
private List<model> allContacts;

    private My_db_handler dbHelper;


    @Override
    protected void onResume() {
        My_db_handler db = new My_db_handler(MainActivity.this);

      /*  Intent intent=getIntent();
        phoneNum=intent.getStringExtra("phone") ;
        if(firstTime==true) {

        }*/
        allContacts = db.getAllContacts();

contactArrayList.clear();

        contactArrayList = new ArrayList<>();


        for(model contact: allContacts){



            contactArrayList.add(contact);

        }

        adaptor =new RecycleviewAdaptor(MainActivity.this,contactArrayList);

        contactRv.setAdapter(adaptor);

        super.onResume();
    }
private void FilterText(List<model> contactlist,String ToSearch)
{
    List<model> filterContacts= new ArrayList<>() ;
    for(model contact: contactlist) {


        if (contact.getFName().toLowerCase().contains(ToSearch.toLowerCase()) ||contact.getLName().toLowerCase().contains(ToSearch.toLowerCase())) {
            filterContacts.add(contact);
        }
    }
if (filterContacts.isEmpty())
{
    Toast.makeText(this,"No Contact Found",Toast.LENGTH_SHORT).show();
}
else
{
    adaptor =new RecycleviewAdaptor(MainActivity.this,filterContacts);
    contactRv.setAdapter(adaptor);

}
    }

@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactRv=(RecyclerView)findViewById(R.id.Recycle) ;
        contactRv.setHasFixedSize(true);
        contactRv.setLayoutManager(new LinearLayoutManager(this));
FButton=(FloatingActionButton)findViewById(R.id.floatingAdd) ;



        My_db_handler db = new My_db_handler(MainActivity.this);


 allContacts=db.getAllContacts();



        contactArrayList = new ArrayList<>();


        for(model contact: allContacts){



contactArrayList.add(contact);

        }

     adaptor =new RecycleviewAdaptor(MainActivity.this,contactArrayList);
        contactRv.setAdapter(adaptor);

        searchView=(SearchView)findViewById(R.id.search_bar);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                FilterText(allContacts,s);
                return true;
            }
        });

        FButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,newcontact.class);
               // intent.putExtra("contactlist",contactArrayList);

                startActivity(intent);
            }
        });
    }

}




