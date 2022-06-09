package com.example.application.data.views;

import lombok.Data;

import java.util.List;

@Data
public class Wallet {

    private List<Ticket> ticketHistory;
}