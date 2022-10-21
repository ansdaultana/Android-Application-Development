package com.example.contacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contacts.adaptor.RecycleviewAdaptor;

import java.util.ArrayList;
import java.util.List;

public class My_db_handler extends SQLiteOpenHelper {


    public My_db_handler(Context context) {
        super(context, parameters.DB_NAME, null, parameters.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create = "CREATE TABLE " + parameters.TABLE_NAME + "("
                + parameters.KEY_ID + " INTEGER PRIMARY KEY," + parameters.KEY_FNAME
                + " TEXT, " + parameters.KEY_LNAME
                + " TEXT, "+ parameters.KEY_EMAIL
                + " TEXT, "+ parameters.KEY_PHONE + " TEXT" + ")";

        db.execSQL(create);

    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addContact(model contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(parameters.KEY_FNAME, contact.getFName());

        values.put(parameters.KEY_LNAME, contact.getLName());

        values.put(parameters.KEY_EMAIL, contact.getEmail());
        values.put(parameters.KEY_PHONE, contact.getPhone());



        db.insert(parameters.TABLE_NAME, null, values);

        db.close();


    }

    public List<model> getAllContacts(){
        List<model> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();


        String select = "SELECT * FROM " + parameters.TABLE_NAME;
        Cursor cursor = db.rawQuery(select, null);



        if(cursor.moveToFirst()){
            do{
                model contact = new model();
                Log.d("loading",cursor.getString(0));
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setFName(cursor.getString(1));

                contact.setLName(cursor.getString(2));

                contact.setEmail(cursor.getString(3));
                contact.setPhone(cursor.getString(4));

                contactList.add(contact);
            }while(cursor.moveToNext());
        }
        return contactList;
    }

    public int updateContact(String idofModel,model contact){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(parameters.KEY_FNAME, contact.getFName());

        values.put(parameters.KEY_LNAME, contact.getLName());


        values.put(parameters.KEY_EMAIL, contact.getEmail());
        values.put(parameters.KEY_PHONE, contact.getPhone());
        Log.d( "updateContact:",contact.getFName());
        Log.d( "updateContact:",contact.getLName());
        Log.d( "updateContact:",contact.getEmail());
        Log.d( "updateContact:",String.valueOf(contact.getId()));

        return db.update(parameters.TABLE_NAME, values, parameters.KEY_ID + "=?",
                new String[]{idofModel});

    }

    public void deleteContactById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(parameters.TABLE_NAME, parameters.KEY_ID +"=?", new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteContact(model contact){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(parameters.TABLE_NAME, parameters.KEY_ID +"=?", new String[]{String.valueOf(contact.getId())});
        db.close();
    }

    public int getCount(){
        String query = "SELECT  * FROM " + parameters.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor.getCount();

    }
}
