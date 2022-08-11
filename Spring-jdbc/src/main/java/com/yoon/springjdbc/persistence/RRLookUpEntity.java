package com.yoon.springjdbc.persistence;

import com.yoon.springjdbc.domain.RRLookUpId;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Slf4j
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE, onConstructor_ = @PersistenceConstructor)
@Table("lookup")
public class RRLookUpEntity {

    @Id
    private RRLookUpId id;

    @Column("order_id")
    private String orderId;

    private String revenueRecognizedKey;

    private LocalDateTime revenueRecognizedAt;

    @CreatedDate
    private LocalDateTime createdAt;

    @Version
    private Long version;

    public RRLookUpEntity(RRLookUpId id,String orderId, String revenueRecognizedKey, LocalDateTime revenueRecognizedAt) {
        this.id = id;
        this.orderId = orderId;
        this.revenueRecognizedKey = revenueRecognizedKey;
        this.revenueRecognizedAt = revenueRecognizedAt;
    }

    public static RRLookUpEntity create(RRLookUpId id, String orderId, String key, LocalDateTime revenueRecognizedAt) {
        return new RRLookUpEntity(id, orderId, key, revenueRecognizedAt);
    }
}
