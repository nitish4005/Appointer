package com.example.android.appointer.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Prasad on 30-Apr-18.
 */

public class SignupModelClass {
    @SerializedName("location")
    private MyLocation location;
    private String name, phone, email, password;

    public SignupModelClass(MyLocation location, String name, String phone, String email, String password) {
        this.location = location;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public MyLocation getLocation() {
        return location;
    }

    public void setLocation(MyLocation location) {
        this.location = location;
    }
}
