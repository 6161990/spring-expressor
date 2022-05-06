package com.specification.matchSpecs.specs;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Step 1. Map 안에 "foo" 가 들어있는지 확인하라.
 * Step 2. Map 안에 "bar" 조건을 추가하라.
 * Step 3. Map 안에 "foo"가 아니면 false 하게 변경하라.
 * Step 4. MatchCondition 에 static 메소드를 추가하라.
 * Step 5. MatchCondition 에 Map key와 value를 확인하는 메소드를 추가하라.
 * Step 6. START REFACTORING
 * Step 7. 더 많은 조건을 만들어보자.
 *         PersonAgeAndNameCondition
 * Step 8. Reuse expected
 *         하나의 조건을 더 추가하면 PersonAgeAndNameCondition 의 네이밍을 바꿔야한다. = 경직
 *         isSatisfy 가 중복되어 사용되는데 한단계 추상화시킬 필요가 있다.
 *         왜냐, 이런 Condition을 사용하는 Policy 객체는 어떤 컨디션인지 관심없고 MatchCondition 이면된다.
 * Step 9. MapCondition extends MatchCondition : 가상 메서드 isSatisfy
 * Step 10. Interface MatchCondition : 가상메서드 굳이 필요없으니까
 *          MapCondition implements MatchCondition
 * Step 11. PersonAgeAndNameCondition implements MatchCondition
 * Step 12. AndCondition 변경 , left/right 변경 -> height 조건 추가
 * Step 13-1. MapCodition(Not 비즈니스) implements MatchCondition(비즈니스) -> 패키지 변경
 *          그러면 utils이 domain을 의지하게됨 -> 이렇게 하면 x. 두 사이의 관계 재정립 중 java.lang.StackOverflowError 발생.
 *      13-2. 서로의 관계를 끊어낸다 1
 *      13-3. 서로의 관계를 끊어낸다 2 - MapCondition.expected 로 inline
 *      13-4. 서로의 관계를 끊어낸다 3 - AndCondition과 MapCondition은 Condition을 구현한다.
 * Step 14. START REFACTORING
 * Step 15. OrSpecification과 LessThanEquals, GreaterThanEquals 조건을 추가한다.
 *      16-1. LessThanEquals, GreaterThanEquals 조건 구현 완료. 테스트 완료
 * Step 16. Condition static, default 메소드로 and, or, not
 */
class MatchConditionTest {

    @DisplayName("object가 Map이면서 안에 foo가 담겨있는지 확인하는 테스트")
    @Test
    void conditionIsFoo() {
        Object factor = Maps.newHashMap("foo", "ooooo");

        Assertions.assertThat(MapCondition.expected("foo", "xxxxx").isSatisfy(factor)).isFalse();
        Assertions.assertThat(MapCondition.expected("foo", "ooooo").isSatisfy(factor)).isTrue();
    }

}