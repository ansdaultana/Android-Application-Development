package com.example.contacts.adaptor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.contacts.MainActivity;
import com.example.contacts.My_db_handler;
import com.example.contacts.R;
import com.example.contacts.editContact;
import com.example.contacts.model;
import com.example.contacts.newcontact;
import com.example.contacts.sms;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class displayContact extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;

    private static final int REQUEST_SMS = 1;
    private TextView FullName;
    private TextView dPhone;
    public Context context;
    private ImageButton call;

    private ImageButton sms;

public String PhoneNum;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_display_contact);
        Intent intent = getIntent();


        String fname = intent.getStringExtra("Lname");
        String lname = intent.getStringExtra("Fname");

        PhoneNum = intent.getStringExtra("phone");
        FullName=(TextView)findViewById(R.id.DisplayFullName) ;
        dPhone=(TextView)findViewById(R.id.DisplayphoneNum);
        sms=(ImageButton)findViewById(R.id.sms);
        call = (ImageButton) findViewById(R.id.call);
        Log.d("Fname",fname);
        FullName.setText(fname+" "+lname);
        dPhone.setText(PhoneNum);
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeSms();
            }
        });
call.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        makePhoneCall();
    }
});



    }
    public  void makeSms() {

        Intent intent=new Intent(displayContact.this, com.example.contacts.sms.class);


        intent.putExtra("phone",PhoneNum);
        startActivity(intent);


//        if (PhoneNum==null)
//        {
//            return;
//        }
//
//        if (PhoneNum.trim().length() > 0) {
//
//            if (ContextCompat.checkSelfPermission(this,
//                    Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(this,
//                        new String[]{Manifest.permission.SEND_SMS}, REQUEST_SMS);
//            } else {
//                String dial = "tel:" +PhoneNum;
//                startActivity(new Intent(Intent.ACTION_SENDTO, Uri.parse(dial)));
//            }
//
//        } else {
//            Toast.makeText(this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
//        }
    }
    public  void makePhoneCall() {
        if (PhoneNum==null)
        {
            return;
        }

        if (PhoneNum.trim().length() > 0) {

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" +PhoneNum;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }

        } else {
            Toast.makeText(this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }
    @SuppressLint("MissingSuperCall")

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
              //  makeSms();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}