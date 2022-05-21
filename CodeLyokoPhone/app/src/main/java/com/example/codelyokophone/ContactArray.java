package com.example.codelyokophone;

public class ContactArray {

    private String name;
    private String phone;

    // Constructor
    public ContactArray(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    // Gets
    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    // Tostring
    @Override
    public String toString() {
        return "Name: " + name + " | Phone: " + phone;
    }

}
