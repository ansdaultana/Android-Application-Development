package com.example.contacts;

import de.hdodenhof.circleimageview.CircleImageView;

public class model {

    private String Fname,Lname,email,phone,addedTime,updatedTime;
    private CircleImageView image;
int id;
public model()
{

}
    // create constructor
    public model(int id,String Fname, String Lname,  String email, String phone,CircleImageView IMAGE) {
        this.id=id;
        this.Fname = Fname;
        this.Lname = Lname;
this.image=IMAGE;
        this.phone = phone;
        this.email = email;



    }

    public model(int id,String Fname, String Lname,   String email,String phone) {
this.id=id;
        this.Fname = Fname;
        this.Lname = Lname;

        this.phone = phone;
        this.email = email;



    }

    public model(String Fname, String Lname,  String email ,String phone)
    {
        this.id=id;
        this.Fname = Fname;
        this.Lname = Lname;

        this.phone = phone;
        this.email = email;



    }

    // create getter and setter method

public void setId(int id)
{
    this.id=id;
}
public int getId()
{
    return  id;
}

    public String getFName() {
        return Fname;
    }

    public void setFName(String fname) {
        this.Fname = fname;
    }
    public String getLName() {
        return Lname;
    }

    public void setLName(String lname) {
        this.Lname = lname;
    }


    public CircleImageView getImage() {
        return image;
    }

    public void setImage(CircleImageView image) {
        this.image = image;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(String addedTime) {
        this.addedTime = addedTime;
    }

    public String getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(String updatedTime) {
        this.updatedTime = updatedTime;
    }
}