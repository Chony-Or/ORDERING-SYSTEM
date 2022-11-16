package com.example.orderingsystem;

public class ProductData {
    private String productName;
    private String productPrice;
    private Integer productImage;

    public ProductData(String productName, String productPrice, Integer productImage) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductImage() {
        return productImage;
    }

    public void setProductmage(Integer ProductImage) {
        this.productImage = productImage;
    }
}
