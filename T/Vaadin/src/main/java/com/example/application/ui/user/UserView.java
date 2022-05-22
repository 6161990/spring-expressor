package com.example.application.ui.user;

import com.example.application.data.service.CrmService;
import com.example.application.data.service.FakeSearchAdapter;
import com.example.application.data.views.Membership;
import com.example.application.data.views.User;
import com.example.application.ui.RootLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.List;
import java.util.Optional;

import static com.vaadin.flow.component.button.ButtonVariant.LUMO_TERTIARY;

@PageTitle("Users")
@Route(value = "user", layout = RootLayout.class)
public class UserView extends VerticalLayout {
    FakeSearchAdapter adapter = new FakeSearchAdapter();
    CrmService service = new CrmService(adapter);

    Select<String> select = new Select<>();

    TextField filterText = new TextField();
    Button searchButton = new Button("search");
    private Binder<User> binder = new Binder<>();
    UserForm userForm = new UserForm();

    Grid<User> grid = new Grid<>(User.class);

    public UserView() {
        addClassNames("users-view");
        setSizeFull();
        configureGrid();

        grid.setItems(service.findByUserId(filterText.getValue().trim()));
        add(getSearchBar(), grid, getContent());
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("userId", "userName", "email", "phoneNumber" ,"signedAt");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.addSelectionListener(selectionEvent -> {
            Optional<User> optionalUser = grid.getSelectedItems().stream().findAny();

            if (optionalUser.isPresent()) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!"+optionalUser.stream().findFirst().get());
                userForm.setUser(optionalUser.stream().findFirst().get());
                System.out.println("@@@@@@@@@@@@@@@@@@"+userForm.deletedAt.getValue());
                System.out.println("@@@@@@@@@@@@@@@@@1111@"+userForm.userName.getValue());

                binder.readBean(optionalUser.stream().findFirst().get());

            }
        });
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
                grid.setItems(service.findByUserId(filterText.getValue().trim()));
            } else if(select.getValue().equals("userName")) {
                grid.setItems(service.findByUserName(filterText.getValue().trim()));
            } else if(select.getValue().equals("phoneNumber")) {
                grid.setItems(service.findByUserPhoneNumber(filterText.getValue().trim()));
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
        grid.setItems(service.findByUserId(filterText.getValue()));
    }


}
