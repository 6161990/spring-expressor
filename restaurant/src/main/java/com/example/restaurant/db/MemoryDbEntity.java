package com.example.restaurant.db;

import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MemoryDbEntity { //모든 데이터베이스를 가진 객체가 상속받을 클래스

    protected Integer index;

}
