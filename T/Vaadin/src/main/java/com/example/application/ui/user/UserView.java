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
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import static com.vaadin.flow.component.button.ButtonVariant.LUMO_TERTIARY;

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

    public UserView() {
        addClassNames("users-view");
        setSizeFull();
        configureGrid();
        configureUserList();

        add(getSearchBar(), grid, getContent());
    }

    private void configureGrid() {
        grid.addClassNames("contact-grid");
        grid.setSizeFull();
        grid.setColumns("userId", "userName", "email", "phoneNumber" ,"signedAt");
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


        searchButton.addClickListener(click -> {
            String value = filterText.getValue();
            if(select.getValue().equals("userId")) {
                System.out.println(value);
                grid.setItems(service.findByUserId(value)); // TODO filterText.getValue() 로 가능하도록
            } else if(select.getValue().equals("userName")) {
                grid.setItems(service.findByUserName(filterText.getValue().trim()));
            }
        });

        HorizontalLayout layout = new HorizontalLayout(select, filterText, searchButton);
        layout.setAlignItems(Alignment.BASELINE);

        return layout;
    }


    private Component getContent() {
        HorizontalLayout content = new HorizontalLayout(userCard, userList);
        content.setFlexGrow(1, userCard);
        content.setFlexGrow(2, userList);
        content.addClassNames("contents");
        content.setSizeFull();
        return content;
    }

    private VirtualList<User> configureUserList () { // TODO 아무것도 없을 때 전체 유저 리스트
        if(filterText.getValue() != null){
            grid.setItems(service.findByUserId(null));
        }
        return userList;
    }

}
