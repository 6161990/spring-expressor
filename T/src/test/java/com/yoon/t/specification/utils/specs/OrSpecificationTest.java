package com.yoon.t.specification.utils.specs;

import org.assertj.core.util.Maps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.BDDAssertions.fail;

public class OrSpecificationTest {

    @DisplayName("이름이 yoon 이 아니고 나이가 200살이거나 215 이하이며, 키가 160이하인 사람")
    @Test
    void OrSpecificationTest() {
        Map<String, Object> factor = Maps.newHashMap("name", "foo");
        factor.put("age", 214);
        factor.put("height", 159);


    }

    @Test
    void todoTest(){
        fail("테스트 필요");
        // age >= 200 || age <= 214

        Condition condition = new OrCondition(
                new LessThanEquals("age",200),
                new GreaterThanEquals("age",214)
        );
    }


}
