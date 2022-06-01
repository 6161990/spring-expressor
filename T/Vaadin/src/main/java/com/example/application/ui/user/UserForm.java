package com.example.application.ui.user;

import com.example.application.data.views.User;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;


public class UserForm extends FormLayout {
    TextField userId = new TextField("userId");
    TextField userName = new TextField("userName");
    TextField email = new TextField("email");
    TextField phoneNumber = new TextField("phoneNumber");
    TextField signedAt = new TextField("signedAt");
    TextField isInactive = new TextField("isInactive");
    TextField isBlock = new TextField("isBlock");
    TextField deletedAt = new TextField("deletedAt");
    private Binder<User> binder;

    public UserForm() {
        addClassName("contact-form");
        binder = new Binder<>(User.class);
        add(createButtonsLayout());
    }

    public void setForm(User user) {
        setBindUser(user);

        setVisible(true);
    }

    private void setBindUser(User user) {
        binder.setBean(user);
        binder.bindInstanceFields(this);
    }

    private VerticalLayout createButtonsLayout() {
        return new VerticalLayout(userId, userName, email, phoneNumber, signedAt,isInactive, isBlock, deletedAt);
    }


}
