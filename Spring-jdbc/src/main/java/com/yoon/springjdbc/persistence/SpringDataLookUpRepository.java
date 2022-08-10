package com.yoon.springjdbc.persistence;

import com.yoon.springjdbc.domain.RRLookUp;
import org.springframework.data.repository.CrudRepository;

public interface SpringDataLookUpRepository extends CrudRepository<RRLookUp, Long>, LookUpRepository {
}
