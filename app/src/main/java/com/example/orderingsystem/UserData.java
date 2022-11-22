package com.example.orderingsystem;

public class UserData {
    private static Integer customer_id;
    private static String customer_name;
    private static String customer_address;
    private static String customer_contactNo;
    private Integer customerClass_id;

    public UserData(Integer customer_id, String customer_name, String customer_address, String customer_contactNo, Integer customerClass_id) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_address = customer_address;
        this.customer_contactNo = customer_contactNo;
        this.customerClass_id = customerClass_id;
    }

    public static Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public static String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public static String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public static String getCustomer_contactNo() {
        return customer_contactNo;
    }

    public void setCustomer_contactNo(String customer_contactNo) {
        this.customer_contactNo = customer_contactNo;
    }

    public Integer getCustomerClass_id() {
        return customerClass_id;
    }

    public void setCustomerClass_id(Integer customerClass_id) {
        this.customerClass_id = customerClass_id;
    }
}
