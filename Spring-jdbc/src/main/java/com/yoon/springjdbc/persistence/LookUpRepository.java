package com.yoon.springjdbc.persistence;

import com.yoon.springjdbc.domain.Key;
import com.yoon.springjdbc.domain.OrderId;
import com.yoon.springjdbc.domain.RRLookUp;

import java.util.List;

public interface LookUpRepository {
    void save(RRLookUp revenueRecognitionLookUp);
    void update(RRLookUp rrLookUp);
    List<RRLookUp> findAllByOrderId(OrderId orderId);
    List<RRLookUp> findById(Key rrk);

}
