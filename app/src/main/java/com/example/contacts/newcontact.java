package com.example.contacts;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.util.ArrayList;
import java.util.List;
import java.util.jar.Manifest;

import de.hdodenhof.circleimageview.CircleImageView;

public class newcontact extends AppCompatActivity {



    private CircleImageView imageIn;


    private FloatingActionButton fabIn;
    private boolean pick;
    private EditText firstnameIn, lastnameIn, numberIn, emailIn;
    String firstname, lastname, number, email;
    ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcontact);
//Intent intent=getIntent();

        imageIn = findViewById(R.id.image);
        firstnameIn = findViewById(R.id.FirstName);


        lastnameIn = findViewById(R.id.LastName);
        numberIn = findViewById(R.id.Number);
        emailIn = findViewById(R.id.Email);
        fabIn = (FloatingActionButton) findViewById(R.id.savebutton);
        imageIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d( "onClick", "opening dialog to choose new photo");
            }
        });

if (pick==true)
{
    //   if(!CameraPermissionCheck())
    {
      //  RequestCameraPermission();
    }
//    else
    {
  //      pickImage();
    }
}
else
{
 //   if(!StoragePermissionCheck())
    {
      //  RequestStoragePermission();
    }
   // else
    {
    //    pickImage();
    }

}
        fabIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                savedata();
            }

        });

    }


    private void savedata() {
        firstname = firstnameIn.getText().toString();

        lastname = lastnameIn.getText().toString();
        email = emailIn.getText().toString();
        number = numberIn.getText().toString();


        if (!firstname.isEmpty() || !lastname.isEmpty() || !number.isEmpty() || !email.isEmpty()) {

            My_db_handler db= new My_db_handler(newcontact.this);


            model temp=new model(lastname,firstname,email,number);
            db.addContact(temp);


            Toast.makeText(getApplicationContext(), "added", Toast.LENGTH_SHORT).show();



        } else {

         }


    }

private boolean CameraPermissionCheck()
{
    //boolean res1= ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
  //  boolean res2= ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE) == PackageManager.PERMISSION_GRANTED;

return true;
}
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}