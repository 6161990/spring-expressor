package com.yoon.springjdbc.persistence;


import com.yoon.springjdbc.domain.Key;
import com.yoon.springjdbc.domain.OrderId;
import com.yoon.springjdbc.domain.RRLookUp;
import com.yoon.springjdbc.domain.RRLookUpId;
import com.yoon.springjdbc.persistence.config.Serializer;
import lombok.RequiredArgsConstructor;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class RevenueRepositoryImpl implements LookUpRepository {

    private final SpringDataLookUpRepository repository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(RRLookUp revenueRecognitionLookUp) {
        RRLookUpEntity entity =
                RRLookUpEntity.create(revenueRecognitionLookUp.getId(), revenueRecognitionLookUp.getOrderId().getId(), "key", revenueRecognitionLookUp.getRevenueRecognizedAt());
        repository.save(entity);
    }

    @Override
    public List<RRLookUp> findAllByOrderId(OrderId orderId) {
        String sql ="select look_up_data from lookup where lookup.order_id=?";

        return this.jdbcTemplate.query(sql, actorRowMapper, orderId.getId());
    }

    @Override
    public List<RRLookUp> findById(Key rrk) {
        String sql ="select look_up_data from lookup where lookup.revenue_recognized_key=?";

        return this.jdbcTemplate.query(sql, actorRowMapper, rrk.getId());
    }


    private RowMapper<RRLookUp> actorRowMapper
            = (resultSet, rowNum) -> convert((PGobject) resultSet.getObject(1));

    public RRLookUp convert(PGobject source) {
        return Serializer.getInstance().deserialize(source.getValue(), RRLookUp.class);
    }





}
