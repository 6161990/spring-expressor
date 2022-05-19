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

    public List<User> findByUserId(String userId) {
        if (userId == null || userId.isEmpty()) {
            return searchAdapter.findAll();
        } else {
            return searchAdapter.searchUserByUserId(userId);
        }
    }

}
