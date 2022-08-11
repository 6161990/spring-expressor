package com.yoon.springjdbc.persistence;

import com.yoon.springjdbc.domain.OrderId;
import com.yoon.springjdbc.domain.RRLookUp;
import com.yoon.springjdbc.domain.Key;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RRLookUpRepository {

    private final SpringDataLookUpRepository repository;

    public void save(RRLookUp RRLookUp) {
        repository.save(RRLookUp);
    }

    public List<RRLookUp> findBy(OrderId orderId) {
        return repository.findAllByOrderId(orderId);
    }

    public List<RRLookUp> findBy(Key rrk) {
        return repository.findById(rrk);
    }


}
