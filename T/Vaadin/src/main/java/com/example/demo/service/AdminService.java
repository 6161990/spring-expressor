package com.example.demo.service;

import com.example.demo.dto.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    public final FakeSearchAdapter searchAdapter;

    public AdminService(FakeSearchAdapter searchAdapter) {
        this.searchAdapter = searchAdapter;
    }

    public List<User> findByUserId(String userId) {
        if (userId == null || userId.isEmpty()) {
            return searchAdapter.findAll();
        } else {
            return searchAdapter.searchUserByUserId(userId);
        }
    }

}
