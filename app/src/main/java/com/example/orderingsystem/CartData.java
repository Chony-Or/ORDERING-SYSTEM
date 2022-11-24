package com.example.orderingsystem;

public class CartData {
    private static String cartProductName;
    private static Double cartProductPrice;
    private String cartProductImage;

//    public CartData(String cartProductName, String cartProductPrice, int cartProductImage) {
//        this.cartProductName = cartProductName;
//        this.cartProductImage = cartProductImage;
//        this.cartProductPrice = cartProductPrice;
//
//    }

    public CartData(Integer product_id, String cartProductName, Double cartProductPrice, Integer product_stock, String cartProductImage, String product_picture) {
        this.cartProductName = cartProductName;
        this.cartProductImage = cartProductImage;
        this.cartProductPrice = cartProductPrice;
    }


    public static String getCartProductName() {
        return cartProductName;
    }

    public static CharSequence getCartProductPrice() {
        return null;

    }

    public void setCartProductName(String cartProductName) {
        this.cartProductName = cartProductName;
    }


    public void setCartProductPrice(Double cartProductPrice) {
        this.cartProductPrice = cartProductPrice;
    }


    public static int getCartProductImage() {

        return 0;
    }

    public void setCartProductImage(String cartProductImage) {
        this.cartProductImage = cartProductImage;
    }


}
