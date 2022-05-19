package com.example.application.ui.user;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;


public class UserForm extends FormLayout {
    TextField id = new TextField("id");
    TextField name = new TextField("name");
    TextField phoneNumber = new TextField("phoneNumber");
    TextField joinedAt = new TextField("joinedAt");
    TextField isInactive = new TextField("isInactive");
    TextField isBlock = new TextField("isBlock");
    TextField deletedAt = new TextField("deletedAt");

    public UserForm() {
        addClassName("contact-form");
        add(createButtonsLayout());
    }

    private VerticalLayout createButtonsLayout() {
        return new VerticalLayout(id, name, phoneNumber, joinedAt,isInactive, isBlock, deletedAt);
    }
}
