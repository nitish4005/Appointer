package com.example.android.appointer.Model;

import java.util.ArrayList;

/**
 * Created by Prasad on 22-Apr-18.
 */

public class ServiceProvidersModel {
    private String devMessage;
    private String message;
    private String color;
    private ArrayList<ServiceProvidersDetails> data = new ArrayList<>();

    public ServiceProvidersModel(String devMessage, String message, String color, ArrayList<ServiceProvidersDetails> data) {
        this.devMessage = devMessage;
        this.message = message;
        this.color = color;
        this.data = data;
    }

// Getter Methods

    public String getDevMessage() {
        return devMessage;
    }

    public String getMessage() {
        return message;
    }

    public String getColor() {
        return color;
    }

    public ArrayList<ServiceProvidersDetails> getData() {
        return data;
    }

// Setter Methods

    public void setDevMessage( String devMessage ) {
        this.devMessage = devMessage;
    }

    public void setMessage( String message ) {
        this.message = message;
    }

    public void setColor( String color ) {
        this.color = color;
    }

    public void setData(ArrayList<ServiceProvidersDetails> data) {
        this.data = data;
    }
}