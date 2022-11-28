package com.example.orderingsystem;

public class CartData {

    private Integer product_id;
    private Integer customer_id;
    private String product_name;
    private Integer quantity;
    private Double amount;
    public String product_picture;

    public CartData(Integer product_id, Integer customer_id, String product_name, Integer quantity, Double amount, String product_picture) {
        this.product_id = product_id;
        this.customer_id = customer_id;
        this.product_name = product_name;
        this.quantity = quantity;
        this.amount = amount;
        this.product_picture = product_picture;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id) {
        this.customer_id = customer_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getProduct_picture() {
        return product_picture;
    }
    public void setProduct_picture(String product_picture) {
        this.product_picture = product_picture;
    }
}
