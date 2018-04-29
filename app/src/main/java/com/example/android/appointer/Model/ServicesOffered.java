package com.example.android.appointer.Model;

/**
 * Created by Prasad on 22-Apr-18.
 */

public class ServicesOffered {
    private String service;
    private String description;
    private int[] ratings ;

    public ServicesOffered(String service, String description, int[] ratings) {
        this.service = service;
        this.description = description;
        this.ratings = ratings;
    }
// Getter Methods

    public int getRatings() {
        int rating =0;
        for(int i=0;i<ratings.length;i++){
            rating+=ratings[i];
        }
        return ratings.length == 0 ? 0 : (rating/(ratings.length));

    }


    public String getService() {
        return service;
    }

    public String getDescription() {
        return description;
    }

    // Setter Methods

    public void setService( String service ) {
        this.service = service;
    }

    public void setDescription( String description ) {
        this.description = description;
    }

    public void setRatings(int[] ratings) {
        this.ratings = ratings;
    }
}