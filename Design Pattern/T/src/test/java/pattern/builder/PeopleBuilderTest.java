
package pattern.builder;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pattern.builder.people.People;
import pattern.builder.people.PeopleBuilder;

public class PeopleBuilderTest {

    People asiaStudent;
    PeopleBuilder defaultAsiaStudent;

    @BeforeEach
    void setUp() {
        defaultAsiaStudent = People.builder()
                .withRace("황인")
                .withIsAdult(false);

        System.out.println("setUp AsiaStudent"  + defaultAsiaStudent.buildForTest());
        /**
         * 테스트를 위해 빌더패턴으로 people 을 생성합니다.
         * */
    }

    @Test
    void 인종이_아시아인인_builder에_구체적_정보를_덧붙여_생성합니다() {
        asiaStudent = defaultAsiaStudent
                .withAddress("강남")
                .withAge(19)
                .withSex("여")
                .buildForTest();

        Assertions.assertEquals(asiaStudent.getSex(), "여");
        Assertions.assertEquals(asiaStudent.getAge(), 19);
    }
}
