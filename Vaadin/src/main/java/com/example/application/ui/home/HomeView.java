package com.example.application.ui.home;

import com.example.application.ui.RootLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@PageTitle("Home")
@Route(value = "home", layout = RootLayout.class)
public class HomeView extends VerticalLayout {
    public HomeView() {
        H1 welcome = new H1("환영합니다.");
        add(welcome);
    }
}
