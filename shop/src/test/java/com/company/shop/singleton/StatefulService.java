package com.company.shop.singleton;

public class StatefulService {

    private int price; //상태를 유지하는 필드

    public int order(String name, int price){
        System.out.println("name = "+ name + "price = "+ price);
        //this.price = price; //문제 발생 포인트
        return price; //가격을 int 로 넘겨버림
    }

    /*public int getPrice(){
        return price;
    }*/
}
