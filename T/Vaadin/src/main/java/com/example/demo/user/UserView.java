package com.example.demo.user;
import com.example.demo.RootLayout;
import com.example.demo.dto.Membership;
import com.example.demo.dto.User;
import com.example.demo.service.FakeSearchAdapter;
import com.example.demo.service.AdminService;
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
    AdminService service = new AdminService(adapter);
    Button searchButton = new Button("search");

    Select<String> select = new Select<>();

    TextField filterText = new TextField(); // dsg1b242d-323dba

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
        grid.setHeight("auto");
        grid.setColumns("id", "name", "email");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
    }

    private Component getSearchBar() {
        addClassName("list-header");
        select.setLabel("search by");
        select.setItems("membershipId", "userId");
        select.setValue("membershipId");


        filterText.setPlaceholder("filter by...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList()); // TODO : !!!!!!!!!!!!!

        searchButton.addClickListener(click -> {
            if(select.getValue().equals("membershipId")){
                System.out.println(filterText.getValue());
            } else if(select.getValue().equals("userId")){
                System.out.println(filterText.getValue());
                grid.setItems(service.findByUserId(filterText.getValue()));
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
        content.setFlexGrow(7, userList);
        content.setFlexGrow(7, membershipList);
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
        userList.setItems();
    }
}
