package com.example.application.data.service;

import com.example.application.data.views.User;

import java.util.List;

public interface SearchAdapter {
    List<User> findAll();

    List<User> searchUserByUserId(String userId);
    List<User> searchUserByPhoneNumber(String phoneNumber);
    List<User> searchUserByUserName(String userName);
}
