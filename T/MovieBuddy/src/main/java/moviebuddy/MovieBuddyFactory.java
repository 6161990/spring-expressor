package moviebuddy;

import moviebuddy.domain.CsvMovieReader;
import moviebuddy.domain.MovieFinder;
import org.springframework.context.annotation.Configuration;

public class MovieBuddyFactory {

    /**
     * 리팩토링 후, MovieFinder 는 추상화된 MovieReader 에만 의존해서
     * 런타임에 생성자를 통해 CsvMovieReader 객체를 전달받아 동작한다.
     * 때문에 다형성을 적극적으로 이용할 수 있고 재사용성도 높아진다.
     * */
    public MovieFinder movieFinder(){
        return new MovieFinder(new CsvMovieReader());
    }
}
/**
 * 📍전략패턴 : 개방 폐쇄 원칙에 가장 잘 들어맞는 패턴.
 * 전략 패턴은 자신의 기능 맥락에서 필요에 따라 변경이 필요한 알고리즘을 추상화를 통해
 * 통째로 외부로 분리시키고 이를 구현한 구체적인 알고리즘 클래스를 필요에 따라 바꿔 사용할 수 있게 하는 디자인 패턴.
 *
 * - Context :
 * `MovieFinder` 는 전략 패턴의 컨텍스트에 해당하며,
 * 컨텍스트는 자신의 기능을 수행하는 데 필요한 기능 중에서,
 * 변경 가능한, 즉 메타데이터 읽기 알고리즘을 `MovieReader` 라는 인터페이스로 추상화하고
 * 이를 구현한 클래스를 바꿔가며 사용할 수 있게 분리한다.
 *
 * - Strategy :
 * 컨텍스트가 필요한 알고리즘을 추상화하고 분리한다.
 * `MovieReader` 가 알고리즘에 해당.
 * */


/**
 * 📍템플릿 메소드 패턴 : 어떤 작업을 처리하는 일부분을 서브 클래스로 캡슐화해서 전체 구조는 바꾸지 않으면서도 특정 행위를 수행하는 전략만 바꾸는 패턴.
 * 상속을 통해 메타데이터 읽기 전략을 확장하는 코드 작성 때 이용한 패턴.
 * MovieFinder 에서 영화 검색을 처리하는 주요 로직을 작성하고 영화 목록을 얻는 MovieReader loadMovies 메소드만 추상화시켰다.
 * 이후 상속을 통해 서브클래스에서 영화 목록을 얻는 행위를 구체적으로 작성했다.
 * */