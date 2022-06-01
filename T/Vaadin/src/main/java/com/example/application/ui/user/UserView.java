package com.example.application.ui.user;

import com.example.application.data.service.CrmService;
import com.example.application.data.service.FakeSearchAdapter;
import com.example.application.data.views.User;
import com.example.application.ui.RootLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import javax.annotation.security.RolesAllowed;

import java.util.Optional;

@PageTitle("Users")
@Route(value = "user", layout = RootLayout.class)
public class UserView extends VerticalLayout {
    FakeSearchAdapter adapter = new FakeSearchAdapter();
    CrmService service = new CrmService(adapter);

    Select<String> select = new Select<>();
    TextField filterText = new TextField();
    Button searchButton = new Button("search");

    private UserForm userForm = new UserForm();
    private UserGrid userGrid = new UserGrid(userForm);

    public UserView() {
        addClassNames("users-view");
        setSizeFull();
        configureGrid();
        configureGridEvent();

        userGrid.setItems(service.findByUserId(filterText.getValue().trim()));
        add(getSearchBar(), userGrid, getContent());
    }

    private void configureGridEvent() {

        userGrid.grid.addSelectionListener(selectionEvent -> {
            Optional<User> optionalUser = userGrid.grid.getSelectedItems().stream().findAny();

            if (optionalUser.isPresent()) {
                userForm.setForm(optionalUser.stream().findFirst().get());
            }
        });
    }


    private void configureGrid() {
        userGrid.addClassNames("contact-grid");
        userGrid.setSizeFull();
    }

    private Component getSearchBar() {
        addClassName("list-header");
        select.setLabel("search by");
        select.setItems("userId", "userName", "phoneNumber");
        select.setValue("userId");

        filterText.setPlaceholder("filter by...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e-> updateList());

        searchButton.addClickListener(click -> {
            if(select.getValue().equals("userId")) {
                userGrid.setItems(service.findByUserId(filterText.getValue().trim()));
            } else if(select.getValue().equals("userName")) {
                userGrid.setItems(service.findByUserName(filterText.getValue().trim()));
            } else if(select.getValue().equals("phoneNumber")) {
                userGrid.setItems(service.findByUserPhoneNumber(filterText.getValue().trim()));
            }
        });

        HorizontalLayout layout = new HorizontalLayout(select, filterText, searchButton);
        layout.setAlignItems(Alignment.BASELINE);

        return layout;
    }


    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(userForm);
        content.setFlexGrow(1, userForm);
        content.addClassNames("contents");
        content.setSizeFull();
        return content;
    }


    private void updateList() {
        userGrid.setItems(service.findByUserId(filterText.getValue()));
    }

    public UserForm getUserForm(){
        return userForm;
    }


}
