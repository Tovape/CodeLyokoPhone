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

    // Filtered
    public String getNameFiltered(String filter) {
        if (name.matches(".*" + filter + ".*")) {
            return name;
        } else {
            return "";
        }
    }

    public String getPhoneFiltered(String filter) {
        if (name.matches(".*" + filter + ".*")) {
            return phone;
        } else {
            return "";
        }
    }

    // Tostring
    @Override
    public String toString() {
        return name + "SEPARATORTVP" + phone;
    }

}
