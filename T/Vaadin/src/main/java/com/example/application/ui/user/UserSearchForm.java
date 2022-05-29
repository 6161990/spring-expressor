package com.example.application.ui.user;

import com.example.application.data.service.CrmService;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;

@Tag("user-search-form")
@JsModule("./src/components/user-search-form.ts")
public class UserSearchForm extends LitTemplate {

    @Id("combobox")
    private ComboBox<String> combobox;
    @Id("searchField")
    private TextField searchField;
    @Id("searchButton")
    private Button search;
    @Id("userGrid")
    private UserGrid userGrid;

    private CrmService crmService;

    public UserSearchForm(CrmService crmService) {
        this.crmService = crmService;
        userGrid.setItems(crmService.findAll());

        configureCombobox();
        configureSearchField();
        updateList();
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
        searchField.addValueChangeListener(e -> updateList());

        search.addClickListener(click -> {
            if (combobox.getValue().equals("id")) {
                userGrid.setItems(crmService.findByUserId(searchField.getValue().trim()));
            } else if (combobox.getValue().equals("name")) {
                userGrid.setItems(crmService.findByUserName(searchField.getValue().trim()));
            } else if (combobox.getValue().equals("phone")) {
                userGrid.setItems(crmService.findByUserPhoneNumber(searchField.getValue().trim()));
            }
        });
    }

    public void updateList() {
        String searchFieldValue = searchField.getValue();
        if (searchFieldValue == null) {
            userGrid.setItems(crmService.findAll());
        } else {
            userGrid.setItems(crmService.findByUserId(searchFieldValue));
        }
    }

    public UserGrid getUserGrid() {
        return userGrid;
    }
}
