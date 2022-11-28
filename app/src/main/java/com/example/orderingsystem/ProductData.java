package com.example.orderingsystem;

public class ProductData {
    private Integer product_id;
    private String product_name;
    private Double product_price;
    private Integer product_stock;
    private String product_code;
    private String product_picture;


    public ProductData(Integer product_id, String product_name, Double product_price, Integer product_stock, String product_code, String product_picture) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_stock = product_stock;
        this.product_code = product_code;
        this.product_picture = product_picture;
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

    public Double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(Double product_price) {
        this.product_price = product_price;
    }

    public Integer getProduct_stock() {
        return product_stock;
    }

    public void setProduct_stock(Integer product_stock) {
        this.product_stock = product_stock;
    }

    public String getProduct_code() {
        return product_code;
    }

    public void setProduct_code(String product_code) {
        this.product_code = product_code;
    }

    public String getProduct_picture() {
        return product_picture;
    }

    public void setProduct_picture(String product_picture) {
        this.product_picture = product_picture;
    }
}
