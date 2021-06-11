package com.example.hello.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PostRequestDto {

    private String account;
    private String email;
    private String address;
    private String password;

    //자바는 카멜케이스 , json은 스네이크 케이스 phone_number
    //so, 따로 지정해주어야함
    //매칭 방법 1) @JsonProperty ; 다른 케이스나 애매한 경우에도 변수에 특정한 값 또는 이름을 이렇게 매칭시켜줄 수 있음
    @JsonProperty("phone_number")
    private String phoneNumber;

    //이 경우 외에도, json 자체를 스네이크케이스로 전부 다 바꾸는 방법과 스프링 어플리케이션을 이용하는 방법 등이 있음.

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return "PostRequestDto{" +
                "account='" + account + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
