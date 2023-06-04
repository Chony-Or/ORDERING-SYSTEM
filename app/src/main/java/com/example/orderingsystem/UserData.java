package com.example.orderingsystem;

public class UserData {


    private static Integer cust_id;
    private static Integer custClass_id;
    private static String cust_number;
    private static String cust_name;
    private static String cust_lastname;
    private static String cust_address;



    private static String cust_brgy;

    public UserData(Integer cust_id, Integer custClass_id, String cust_number, String cust_firstname, String cust_lastname, String cust_houseno, String cust_street, String cust_city, String cust_brgy) {
        this.cust_id = cust_id;
        this.custClass_id = custClass_id;
        this.cust_number = cust_number;
        this.cust_name = cust_firstname + " " + cust_lastname;
        this.cust_address = cust_houseno + " "+ cust_street +" "+ cust_brgy +" "+cust_city;
    }

    public static String getCust_brgy() {
        return cust_brgy;
    }


    public static Integer getCust_id() {
        return cust_id;
    }

    public static void setCust_id(Integer cust_id) {
        UserData.cust_id = cust_id;
    }

    public static Integer getCustClass_id() {
        return custClass_id;
    }


    public static String getCust_number() {
        return cust_number;
    }

    public static void setCust_number(String cust_number) {
        UserData.cust_number = cust_number;
    }

    public static String getCust_name() {
        return cust_name;
    }

    public static void setCust_name(String cust_name) {
        UserData.cust_name = cust_name;
    }

    public static String getCust_lastname() {
        return cust_lastname;
    }

    public static void setCust_lastname(String cust_lastname) {
        UserData.cust_lastname = cust_lastname;
    }

    public static String getCust_address() {
        return cust_address;
    }

    public static void setCust_address(String cust_address) {
        UserData.cust_address = cust_address;
    }
    public static void setCustClass_id(Integer custClass_id) {
        UserData.custClass_id = custClass_id;
    }
    public static void setCust_brgy(String cust_brgy) {
        UserData.cust_brgy = cust_brgy;
    }









}
