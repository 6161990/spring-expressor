package com.example.demo.user;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class UserCard extends VerticalLayout {

    private UserForm userForm = new UserForm();

    public UserCard() {
        setWidth("100%");
        userForm.setWidth("25em");

        add(userForm);
    }
}
