package com.example.application.ui.user;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class UserCard extends VerticalLayout {

    private UserForm userForm = new UserForm();

    public UserCard() {
        setWidth("100%");
        add(userForm);
    }
}
