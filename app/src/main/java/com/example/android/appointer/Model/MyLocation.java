package com.example.android.appointer.Model;

import java.util.ArrayList;

/**
 * Created by Prasad on 08-May-18.
 */

public class MyLocation {
    private String type;
    ArrayList<Long> coordinates = new ArrayList<Long>();

    public MyLocation(String type, ArrayList<Long> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public ArrayList<Long> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Long> coordinates) {
        this.coordinates = coordinates;
    }

    public String getType() {
        return type;
    }


    public void setType( String type ) {
        this.type = type;
    }
}

