package com.yoon.springjdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;
@Data
@AllArgsConstructor
public class RRLookUp {

    private RRLookUpId id;
    private OrderId orderId;
    private List<String> ledgers;
    private ConfirmationValue pcv;
    private LocalDateTime revenueRecognizedAt;




}
