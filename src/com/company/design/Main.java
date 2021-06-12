package com.company.design;

public class Main {

    public static void main(String[] args) {
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";


        //Encoder라는 애는 IEncoder(Base64Encoder , UrlEncoder)를 외부로부터 주입 받음
        //test를 할 때 Encoder를 건들필요 없이 내가 원하는 부분만 (주입 (new Base64Encoder()); )
        Encoder encoder = new Encoder(new Base64Encoder());
        //Encoder encoder = new Encoder(new UrlEncoder());
        String result = encoder.encode(url);
        System.out.println(result);
    }
}
