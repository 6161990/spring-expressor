package com.example.application.ui.user;

import com.example.application.data.service.CrmService;
import com.example.application.data.service.UserSearchService;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserSearchForm {

    private ComboBox<String> combobox;

    private TextField searchField;

    private Button search;

    private UserGrid userGrid;

    @Autowired
    private CrmService crmService;

    @Autowired
    private UserSearchService apiService;

    public UserSearchForm() {
        configureCombobox();
        configureSearchField();
    }

    private void configureCombobox() {
        combobox.setAllowCustomValue(true);
        combobox.setPlaceholder("select filter");
        combobox.setItems("id", "name", "phone");
        combobox.setValue("id");
    }

    private void configureSearchField() {
        searchField.setPlaceholder("filter by...");
        searchField.setClearButtonVisible(true);
        searchField.setValueChangeMode(ValueChangeMode.LAZY);
        searchField.addValueChangeListener(e -> searchUser());
        search.addClickListener(click -> searchUser());
    }

    private void searchUser() {
        String searchValue = searchField.getValue().trim();
        if (combobox.getValue().equals("id")) {
            userGrid.setItems(List.of(apiService.getUserById(searchValue)));
        } else if (combobox.getValue().equals("name")) {
            userGrid.setItems(apiService.getUserByName(searchValue));
        } else if (combobox.getValue().equals("phone")) {
            userGrid.setItems(apiService.getUserByPhoneNumber(searchValue));
        }
    }

    public UserGrid getUserGrid() {
        return userGrid;
    }
}
