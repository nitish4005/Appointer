package com.example.android.appointer.Model;

/**
 * Created by Prasad on 09-May-18.
 */

public class LoginModelClassForEmail {
    private String email;
    private String password;

    public LoginModelClassForEmail(String email, String password) {
        this.email = email;
        this.password = password;
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
}
