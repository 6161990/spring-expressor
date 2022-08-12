package com.yoon.springjdbc.persistence;

import com.yoon.springjdbc.domain.*;
import com.yoon.springjdbc.persistence.SpringDataLookUpRepository;
import com.yoon.springjdbc.persistence.config.JdbcConfig;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase;
import io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseType;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.zonky.test.db.AutoConfigureEmbeddedDatabase.DatabaseType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.annotation.DirtiesContext.ClassMode.*;

@Transactional
@DirtiesContext(classMode = BEFORE_EACH_TEST_METHOD)
@AutoConfigureEmbeddedDatabase(type = POSTGRES)
@SpringJUnitConfig(value = {JdbcConfig.class} )
@Sql(scripts = "classpath:") // TODO classpath
class SpringDataLookUpRepositoryTest {

    @Autowired
    SpringDataLookUpRepository repository;
    RRLookUp lookUp;

    @BeforeEach
    void setUp() {
        Ledger dd = new Ledger(0, 100, LocalDateTime.now());
        lookUp =
                new RRLookUp(
                        new RRLookUpId(12L),
                        OrderId.of("orderId"),
                        Arrays.asList(dd),
                        new ConfirmationValue(ConfirmationType.MEETING, "key")
                        ,LocalDateTime.now());

        assertThat(repository.findById(lookUp.getId())).isNull();
    }
}
