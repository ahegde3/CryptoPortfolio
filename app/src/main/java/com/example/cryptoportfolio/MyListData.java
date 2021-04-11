package com.example.cryptoportfolio;

public class MyListData{
    private String token;
    private int price;
    public MyListData(String token, int price) {
        this.token = token;
        this.price = price;
    }
    public String getToken() {
        return token;
    }

    public int getPrice() {
        return price;
    }

}