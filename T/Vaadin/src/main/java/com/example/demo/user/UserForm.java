package com.example.demo.user;

import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;


public class UserForm extends FormLayout {
    TextField id = new TextField("id");
    TextField name = new TextField("name");
    EmailField email = new EmailField("email");

    public UserForm() {
        addClassName("contact-form");
        createButtonsLayout();
        add(id, name, email);
    }

    private HorizontalLayout createButtonsLayout() {
        return new HorizontalLayout(id, name, email);
    }
}
