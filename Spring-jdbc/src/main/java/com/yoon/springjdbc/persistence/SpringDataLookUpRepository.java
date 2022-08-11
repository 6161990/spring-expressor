package com.yoon.springjdbc.persistence;

import com.yoon.springjdbc.domain.RRLookUp;
import com.yoon.springjdbc.domain.RRLookUpId;
import org.springframework.data.repository.CrudRepository;

public interface SpringDataLookUpRepository extends CrudRepository<RRLookUpEntity, RRLookUpId>, LookUpRepository {
}
