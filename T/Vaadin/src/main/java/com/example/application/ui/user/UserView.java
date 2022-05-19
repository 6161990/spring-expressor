package com.example.application.ui.user;

import com.example.application.data.service.CrmService;
import com.example.application.data.service.FakeSearchAdapter;
import com.example.application.data.views.Membership;
import com.example.application.data.views.User;
import com.example.application.ui.RootLayout;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.virtuallist.VirtualList;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Users")
@Route(value = "user", layout = RootLayout.class)
public class UserView extends VerticalLayout {
    FakeSearchAdapter adapter = new FakeSearchAdapter();
    CrmService service = new CrmService(adapter);

    Select<String> select = new Select<>();

    TextField filterText = new TextField(); // f9c11039-50f8-4f7a-8759-574eb2ea1933
    Button searchButton = new Button("search");

    Grid<User> grid = new Grid<>(User.class);
    UserCard userCard = new UserCard();
    VirtualList<User> userList = new VirtualList<>();
    VirtualList<Membership> membershipList = new VirtualList<>();



    public UserView() {
        addClassNames("users-view");
        setSizeFull();
        configureGrid();
        configureUserList();
        configureMembershipList();

        add(getSearchBar(), grid, getContent());
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
//        grid.setHeight("auto");
        grid.setColumns("id", "name", "phoneNumber", "joinedAt");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private Component getSearchBar() {
        addClassName("list-header");
        select.setLabel("search by");
        select.setItems("userId", "userName", "phoneNumber");
        select.setValue("userId");


        filterText.setPlaceholder("filter by...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        searchButton.addClickListener(click -> {
            if(select.getValue().equals("userName")) {
                System.out.println(filterText.getValue());
            } else if(select.getValue().equals("userId")) {
                grid.setItems(service.findByUserId(filterText.getValue().trim()));
//                userList.setItems(service.findByUserId(filterText.getValue().trim().toString()));
            }
        });

        HorizontalLayout layout = new HorizontalLayout(select, filterText, searchButton);
        layout.setAlignItems(Alignment.BASELINE);

        return layout;
    }


    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(userCard, userList, membershipList);
        content.setFlexGrow(1, userCard);
        content.setFlexGrow(2, userList);
        content.setFlexGrow(2, membershipList);
        content.addClassNames("contents");
        content.setSizeFull();
        return content;
    }

    private VirtualList<User> configureUserList () {
        userList.setItems();
        return userList;
    }

    private void configureMembershipList () {
        membershipList.setItems();
    }

    private void updateList() {
        grid.setItems(service.findByUserId(filterText.getValue()));
//        userList.setItems();
    }
}
