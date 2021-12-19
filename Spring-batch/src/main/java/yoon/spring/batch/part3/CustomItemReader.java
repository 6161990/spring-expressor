package yoon.spring.batch.part3;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import java.util.ArrayList;
import java.util.List;

// 자바 컬렉션의 List를 reader로 처리하는 커스텀 클래스
public class CustomItemReader<T> implements ItemReader<T> {

    private final List<T> items;

    public CustomItemReader(List<T> items) {
        this.items = new ArrayList<>(items);
    }

    @Override
    public T read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if(!items.isEmpty()){
            return items.remove(0); // 0번째의 item을 반환함과 동시에 제거
        }
        return null; // null을 return 하면 chunk반복의 끝이라는 의미
    }
}
