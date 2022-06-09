package com.example.application.ui.login;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;

@Route("")
@PageTitle("Login")
@RouteAlias(value = "login")
public class LoginView extends VerticalLayout implements BeforeEnterObserver {


    private final LoginForm login = new LoginForm();

    public LoginView() {
        addClassNames("login-view");
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        login.setAction("home");

        add(new H1("NEW CRM WELCOME"), login);
    }

    @Override
    public void beforeEnter(BeforeEnterEvent beforeEnterEvent) {
        if(beforeEnterEvent.getLocation().getQueryParameters().getParameters().containsKey("error")) {
            login.setError(true);
        }
    }
}
