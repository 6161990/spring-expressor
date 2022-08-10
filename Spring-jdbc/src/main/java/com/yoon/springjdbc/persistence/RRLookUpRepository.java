package com.yoon.springjdbc.persistence;

import com.yoon.springjdbc.domain.OrderId;
import com.yoon.springjdbc.domain.RRLookUp;
import com.yoon.springjdbc.domain.Key;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RRLookUpRepository {

    private final DataSource dateSource;

    public void save(RRLookUp RRLookUp) {
        // TODO : converter
    }

    public List<RRLookUp> findBy(OrderId orderId) {
        return null;
    }

    public List<RRLookUp> findBy(Key rrk) {
        return null;
    }


}
