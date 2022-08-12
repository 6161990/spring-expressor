package com.yoon.springjdbc.persistence;

import com.yoon.springjdbc.domain.*;
import com.yoon.springjdbc.persistence.config.LedgersValueToPgObjectConverter;
import com.yoon.springjdbc.persistence.config.LedgersWrapperForPersist;
import com.yoon.springjdbc.persistence.config.Serializer;
import lombok.RequiredArgsConstructor;
import org.postgresql.util.PGobject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

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
    public void update(RRLookUp lookUp) {
        LedgersValueToPgObjectConverter converter = new LedgersValueToPgObjectConverter();

        // FIXME : 바뀌지않을 정보들도 같이 update하고 있다. OrderId가 바뀔 일이 있을까?  revenue_recognized_key도 다른 메소드에서 따로 단일 update 해주는 건 어떨까
        String sql =
                "update lookup set order_id=?, ledgers=?, total_amount=?, revenue_recognized_key=?, revenue_recognized_at=? ,version=+1 where lookup.id=?";

        jdbcTemplate.update(sql, lookUp.getOrderId().getId(), converter.convert((LedgersWrapperForPersist) lookUp.getLedgers()), lookUp.getTotalAmount(), lookUp.getRecognizedKey().getId() ,lookUp.getRevenueRecognizedAt(), lookUp.getId());
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


    private RowMapper<RRLookUp> actorRowMapper2
            = (resultSet, rowNum) -> new RRLookUp(RRLookUpId.of(resultSet.getLong(1)),
            OrderId.of(resultSet.getString(2)),
            convertLedgers((PGobject) resultSet.getObject(3)).getLedgers(),
            ConfirmationValue.of(ConfirmationType.valueOf(String.valueOf(resultSet.getObject(4))), Key.of(resultSet.getString(5))),
            resultSet.getTimestamp(6) == null ? null :resultSet.getTimestamp(6).toLocalDateTime());


    public RRLookUp convert(PGobject source) {
        return Serializer.getInstance().deserialize(source.getValue(), RRLookUp.class);
    }

    public LedgersWrapperForPersist convertLedgers(PGobject source) {
        return Serializer.getInstance().deserialize(source.getValue(), LedgersWrapperForPersist.class);
    }




}
