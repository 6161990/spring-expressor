package com.yoon.springjdbc.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Getter
@AllArgsConstructor
@Table("lookup")
public class RRLookUp {

    @Id
    private RRLookUpId id;
    private OrderId orderId;
    private List<String> ledgers;
    private ConfirmationValue pcv;
    private LocalDateTime revenueRecognizedAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @Version
    private Long version;


    public RRLookUp(RRLookUpId id, OrderId orderId, List<String> ledgers, ConfirmationValue pcv, LocalDateTime revenueRecognizedAt) {
        this.id = id;
        this.orderId = orderId;
        this.ledgers = ledgers;
        this.pcv = pcv;
        this.revenueRecognizedAt = revenueRecognizedAt;
    }
}
