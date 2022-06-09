package com.example.application.ui;

import com.example.application.ui.home.HomeView;
import com.example.application.ui.user.UserView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.router.RouterLink;

public class RootLayout extends AppLayout {
    public static class MenuItemInfo extends ListItem {
        private final Class<? extends Component> view;
        public MenuItemInfo(String menuTitle, String iconClass, Class<? extends Component> view) {
            this.view = view;
            RouterLink link = new RouterLink();
            link.addClassNames("menu-item-link");
            link.setRoute(view);
            Span text = new Span(menuTitle);
            text.addClassNames("menu-item-text");
            link.add(new LineAwesomeIcon(iconClass), text);
            add(link);
        }
        public Class<?> getView() {
            return view;
        }
        /**
         * Simple wrapper to create icons using LineAwesome iconset. See
         * https://icons8.com/line-awesome
         */
        @NpmPackage(value = "line-awesome", version = "1.3.0")
        public static class LineAwesomeIcon extends Span {
            public LineAwesomeIcon(String lineawesomeClassnames) {
                addClassNames("menu-item-icon");
                if (!lineawesomeClassnames.isEmpty()) {
                    addClassNames(lineawesomeClassnames);
                }
            }
        }
    }
    private H1 viewTitle;
    public RootLayout() {
        setPrimarySection(Section.DRAWER);
        addToNavbar(true, createHeaderContent());
        addToDrawer(createDrawerContent());
    }
    private Component createDrawerContent() {
        H2 appName = new H2("NEW ADMIN");
        appName.addClassNames("app-name");
        com.vaadin.flow.component.html.Section section = new com.vaadin.flow.component.html.Section(appName,
                createNavigation(), createFooter());
        section.addClassNames("drawer-section");
        return section;
    }
    private Component createFooter() {
        Footer layout = new Footer();
        layout.addClassNames("footer");
        return layout;
    }
    private Component createNavigation() {
        Nav nav = new Nav();
        nav.addClassNames("menu-item-container");
        nav.getElement().setAttribute("aria-labelledby", "views");
        UnorderedList list = new UnorderedList();
        list.addClassNames("navigation-list");
        nav.add(list);
        for (MenuItemInfo menuItem : createMenuItems()) {
            list.add(menuItem);
        }
        return nav;
    }

    private MenuItemInfo[] createMenuItems() {
        return new MenuItemInfo[]{ //
                new MenuItemInfo("home", "la la-globe", HomeView.class),
                new MenuItemInfo("user", "la la-user", UserView.class),
        };
    }
    private Component createHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
        toggle.addClassName("layout-toggle");
        toggle.addThemeVariants(ButtonVariant.LUMO_CONTRAST);
        toggle.getElement().setAttribute("aria-label", "Toggle Menu");
        viewTitle = new H1();
        viewTitle.addClassName("view-title");
        Header header = new Header(toggle, viewTitle);
        header.addClassNames("header");
        return header;
    }
}