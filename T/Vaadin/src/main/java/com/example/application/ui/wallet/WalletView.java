package com.example.application.ui.wallet;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JsModule;
import com.vaadin.flow.component.littemplate.LitTemplate;
import com.vaadin.flow.component.template.Id;


public class WalletView  {

    private TicketCard ticketCard;

    public WalletView() {
        configureClickEvent();
    }

    private void configureClickEvent() {

    }

    public TicketCard getTicketCard() {
        return ticketCard;
    }

}
