package com.example.contacts;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class sms extends AppCompatActivity {
    public EditText sms;
    public ImageButton send;
public String phonenum;
public TextView num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        sms=(EditText) findViewById(R.id.MessageInput);
        send=(ImageButton) findViewById(R.id.sendbutton);
        Intent intent = getIntent();
        phonenum=intent.getStringExtra("phone");
num=(TextView)findViewById(R.id.DisplayMobilePhoneNumber);

num.setText(phonenum.toString());


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
if (ContextCompat.checkSelfPermission(sms.this, Manifest.permission.SEND_SMS)== PackageManager.PERMISSION_GRANTED)
{
    sendsms();
}else
{
    ActivityCompat.requestPermissions(sms.this,new String[]{Manifest.permission.SEND_SMS},100);
}
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    if(requestCode==100&&grantResults.length>0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED)
    {
        sendsms();
    }
    else
    {

        Toast.makeText(this,"Permission Denied!",Toast.LENGTH_SHORT).show();

    }
    }

    public void sendsms()
    {
        String message=sms.getText().toString().trim();
        String numer=phonenum.toString().trim();

        if(!message.isEmpty() &&!numer.isEmpty() )
        {
            SmsManager SMS=SmsManager.getDefault();
            SMS.sendTextMessage(numer,null,message,null,null);

            Toast.makeText(this,"Message Sent!",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"Enter Message!",Toast.LENGTH_SHORT).show();

        }
    }
}