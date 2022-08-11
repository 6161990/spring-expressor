package com.yoon.springjdbc.persistence.config;

import com.yoon.springjdbc.domain.*;
import com.yoon.springjdbc.persistence.RevenueRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class jdbcPersist {

    @Autowired
    RevenueRepositoryImpl revenueRepositoryImpl;

    @Test
    void name() {
        List<String> ledgers = new ArrayList<>();
        String dd = "";
        ledgers.add(dd);
        ConfirmationValue pcv = new ConfirmationValue(ConfirmationType.MEETING, "key");

        revenueRepositoryImpl.save(new RRLookUp(RRLookUpId.of(12L), OrderId.of("if"), ledgers, pcv, LocalDateTime.now()));
    }

    @Test
    void lookUpFindByOrderId() {
        List<RRLookUp> lookUps = revenueRepositoryImpl.findAllByOrderId(OrderId.of("orderId"));
    }

    @Test
    void findByRRk() {
        List<RRLookUp> lookUps = revenueRepositoryImpl.findById(Key.of("A"));
    }
}
