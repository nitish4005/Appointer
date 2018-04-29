package com.example.android.appointer.Model;

import java.util.ArrayList;

/**
 * Created by Prasad on 22-Apr-18.
 */

public class ServiceProvidersDetails {
    private String _id;
    private String name;
    private String email;
    private float phone;
    private ArrayList<ServicesOffered> services = new ArrayList<>();

    public ServiceProvidersDetails(String _id, String name, String email, float phone, ArrayList<ServicesOffered> services) {
        this._id = _id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.services = services;
    }

// Getter Methods

    public ArrayList<ServicesOffered> getServices() {
        return services;
    }


    public String get_id() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public float getPhone() {
        return phone;
    }

    // Setter Methods

    public void set_id( String _id ) {
        this._id = _id;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public void setEmail( String email ) {
        this.email = email;
    }

    public void setPhone( float phone ) {
        this.phone = phone;
    }


    public void setServices(ArrayList<ServicesOffered> services) {
        this.services = services;
    }
}