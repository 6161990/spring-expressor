package com.yoon.boundNotBoundTest.payment.domain;

public class Product {
    private String name;
    private int price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public static Product of(String name, int price) {
        return new Product(name, price);
    }


}