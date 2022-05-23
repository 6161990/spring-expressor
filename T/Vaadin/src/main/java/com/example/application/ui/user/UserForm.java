package com.example.application.ui.user;

import com.example.application.data.views.User;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
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
    private Binder<User> binder = new Binder<>();

    public UserForm() {
        addClassName("contact-form");
        binder.forField(userId).asRequired().bind(User::getUserId, User::setUserId);
        binder.forField(userName).bind(User::getUserName, User::setUserName);
        binder.forField(email).bind(User::getEmail, User::setEmail);
        binder.forField(phoneNumber).bind(User::getPhoneNumber, User::setPhoneNumber);
        binder.forField(signedAt).bind(User::getSignedAt, User::setSignedAt);
        binder.forField(isInactive).bind(User::getIsInactive, User::setIsInactive);
        binder.forField(isBlock).bind(User::getIsBlock, User::setIsBlock);
        binder.forField(deletedAt).bind(User::getDeletedAt, User::setDeletedAt);
        add(createButtonsLayout());
    }

    public void setUser(User user) {
        binder.setBean(user);
    }

    private VerticalLayout createButtonsLayout() {
        return new VerticalLayout(userId, userName, phoneNumber, signedAt,isInactive, isBlock, deletedAt);
    }
}
