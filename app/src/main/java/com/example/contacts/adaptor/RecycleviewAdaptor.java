package com.example.contacts.adaptor;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contacts.MainActivity;
import com.example.contacts.R;
import com.example.contacts.call;
import com.example.contacts.editContact;
import com.example.contacts.model;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecycleviewAdaptor extends RecyclerView.Adapter<RecycleviewAdaptor.ViewHolder> {

    private static final int REQUEST_CALL = 1;
    private Context context;
    private List<model> contactlist;
    private ImageButton call;

    private ImageButton sms;

    public String phoneNum;



    public RecycleviewAdaptor(Context context, List<model> contactlist) {
        this.context = context;
        this.contactlist = contactlist;
    }

    @NonNull
    @Override
    public RecycleviewAdaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleviewAdaptor.ViewHolder holder, int position) {
        model Contact=contactlist.get(position);

        holder.Fname.setText(Contact.getFName());
holder.email.setText(Contact.getEmail());



        holder.Lname.setText(Contact.getLName());


        holder.phone.setText(Contact.getPhone());

        
holder.idnum.setText( Integer.toString(Contact.getId()));

    }

    @Override
    public int getItemCount() {
        return contactlist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView Fname;

        public TextView Lname;

        public TextView phone;

        public TextView email;
        public TextView idnum;
        public CircleImageView image;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            
            Fname=itemView.findViewById(R.id.lname);

            Lname =itemView.findViewById(R.id.fname);
email=itemView.findViewById(R.id.email);
            image=itemView.findViewById(R.id.imageContact);
            phone=itemView.findViewById(R.id.textNumber);
            idnum=itemView.findViewById(R.id.idof);
            image.setOnClickListener(this);
            itemView.findViewById(R.id.edit).setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {

                    int idnow = (int) getAdapterPosition();

                    model Contact=contactlist.get(idnow);

                    Intent intent =new Intent(context,editContact.class);
                    intent.putExtra("Fname",Fname.getText().toString());
                    intent.putExtra("Lname",Lname.getText().toString());
                    intent.putExtra("email",email.getText().toString());
                    intent.putExtra("phone",phone.getText().toString());
                    intent.putExtra("id",idnum.getText().toString());


                    context.startActivity(intent);

                }
            });

        }



        @Override
        public void onClick(View view) {



            int position=this.getAdapterPosition();

            model Contact=contactlist.get(position);
            String fname=Contact.getFName();

            String lname=Contact.getLName();
            String email=Contact.getEmail();
            String phone=Contact.getPhone();
            String id= String.valueOf(Contact.getId());


            Intent intent=new Intent(context, displayContact.class);
            intent.putExtra("Fname",fname);

            intent.putExtra("Lname",lname);
            intent.putExtra("email",email);
            intent.putExtra("phone",phone);

            intent.putExtra("id",id);
            context.startActivity(intent);

        }
    }









}
