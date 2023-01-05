package com.example.orderingsystem;

public class ReceiptData {

    private Integer product_id;
    private String product_name;
    private Integer order_number;
    private Integer quantity;
    private Double amount;

    public ReceiptData(Integer product_id, String product_name, Integer order_number, Integer quantity, Double amount) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.order_number = order_number;
        this.quantity = quantity;
        this.amount = amount;
    }

    public Integer getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Integer product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Integer getOrder_number() {
        return order_number;
    }

    public void setOrder_number(Integer order_number) {
        this.order_number = order_number;
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



    }

