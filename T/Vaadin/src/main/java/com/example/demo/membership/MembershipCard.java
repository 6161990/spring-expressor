package com.example.demo.membership;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import lombok.Value;

@Value
public class MembershipCard extends VerticalLayout {

    public MembershipCard() {
        setClassName("membership-card");
        setWidth("100%");
        setHeight("auto");
    }
}
