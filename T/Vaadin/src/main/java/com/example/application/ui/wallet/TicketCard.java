package com.example.application.ui.wallet;


import com.example.application.data.views.Ticket;
import com.example.application.ui.BadgeIcon;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;

public class TicketCard  {

    private TextField serviceName;

    private Span membershipId;

    private Span reason;

    private Span usedAtSize;

    private Span createdAt;

    private Span expiredAt;

    private Span terminatedAt;

    private Span membership;

    private BadgeIcon state;

    private TextField period;

    private Binder<Ticket> binder;

    //    private CollaborationBinder<Ticket> collaborationBinder;

    public TicketCard() {
        binder = new Binder<>(Ticket.class);
    }

    public void setTicket(Ticket ticket) {
        setBindUser(ticket);
//        setVisible(true);
    }

    private void setBindUser(Ticket ticket) {
        binder.setBean(ticket);
//        Binder.Binding<Ticket, String> returningBinding =
//                binder.forField(serviceName.getText())
//                        .bind(Ticket::getService, Ticket::setService);
//        binder.forField(serviceName).bind(); TODO :!!!!!!!!!!!!here
        binder.bindInstanceFields(this);
    }
}
