package com.yoon.springjdbc.persistence.config;

import org.postgresql.util.PGobject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;

import java.sql.SQLException;

@WritingConverter
public class LedgersValueToPgObjectConverter implements Converter<LedgersWrapperForPersist, PGobject> {

    @Override
    public PGobject convert(LedgersWrapperForPersist source) {
        String serialize = Serializer.getInstance().serialize(source);
        PGobject pGobject = new PGobject();
        pGobject.setType("json");
        try {
            pGobject.setValue(serialize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pGobject;
    }

}
