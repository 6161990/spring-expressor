package com.example.application.ui.user;

import com.example.application.data.views.User;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;

import java.util.List;
import java.util.Optional;

public class UserGrid extends Div {

    Grid<User> grid = new Grid<>(User.class);
    UserForm userForm = new UserForm();

    public UserGrid() {
        grid.setColumns("userId", "userName", "email", "phoneNumber", "signedAt");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));

        grid.addSelectionListener(selectionEvent -> {
            Optional<User> optionalUser = grid.getSelectedItems().stream().findAny();

            if (optionalUser.isPresent()) {
                System.out.println("!!!!!!!!!!!!!!!!!!!!"+optionalUser.stream().findFirst().get());
                setForm(selectionEvent.getFirstSelectedItem().get());
                userForm.setUser(optionalUser.stream().findFirst().get());
                System.out.println("@@@@@@@@@@@@@@@@@1111@"+userForm.userName.getValue());
            }
        });

        add(grid);
    }

    public void setItems(List<User> users) {
        grid.setItems(users);
    }

    private void setForm(User user) {
        if(user == null){
            closeUserForm();
        } else {
            userForm.setUser(user);
            userForm.setVisible(true);
        }
    }

    private void closeUserForm() {
        userForm.setUser(null);
        userForm.setVisible(false);
    }

    public Grid<User> getUserGrid() {
        return grid;
    }
}