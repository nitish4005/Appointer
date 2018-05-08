package com.example.android.appointer.Model;

/**
 * Created by Prasad on 09-May-18.
 */

public class LoginModelClassIForPhoneNumber {
    private String phoneNumber;
    private String password;

    public LoginModelClassIForPhoneNumber(String phoneNumber, String password) {
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
