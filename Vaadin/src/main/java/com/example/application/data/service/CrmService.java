package com.example.application.data.service;

import com.example.application.data.views.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmService {
    public final FakeSearchAdapter searchAdapter;

    public CrmService(FakeSearchAdapter searchAdapter) {
        this.searchAdapter = searchAdapter;
    }

    public List<User> findAll() {
        return searchAdapter.findAll();
    }

    public List<User> findByUserId(String userId) {
        if (userId == null || userId.isEmpty()) {
            return searchAdapter.findAll();
        } else {
            return searchAdapter.searchUserByUserId(userId);
        }
    }

    public List<User> findByUserName(String userName) {
        if (userName == null || userName.isEmpty()) {
            return searchAdapter.findAll();
        } else {
            return searchAdapter.searchUserByUserName(userName);
        }
    }

    public List<User> findByUserPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return searchAdapter.findAll();
        } else {
            return searchAdapter.searchUserByPhoneNumber(phoneNumber);
        }
    }
}
