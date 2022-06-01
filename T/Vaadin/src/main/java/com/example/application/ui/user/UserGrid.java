package com.example.application.ui.user;

import com.example.application.data.views.User;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;

import java.util.List;
import java.util.Optional;

public class UserGrid extends Div {

    Grid<User> grid = new Grid<>(User.class);

    public UserGrid(UserForm userForm) {
        grid.setColumns("userId", "userName", "email", "phoneNumber", "signedAt");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.addSelectionListener(selectionEvent -> {
            Optional<User> optionalUser = grid.getSelectedItems().stream().findAny();

            if (optionalUser.isPresent()) {
                userForm.setForm(optionalUser.stream().findFirst().get());
            }
        });

        add(grid);
    }

    public void setItems(List<User> users) {
        grid.setItems(users);
    }

}