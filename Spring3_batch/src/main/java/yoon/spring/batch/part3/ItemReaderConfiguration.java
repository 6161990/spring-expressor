package yoon.spring.batch.part3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Configuration
public class ItemReaderConfiguration {
// 인텔리제이의 template 기능, 같은 configuration을 코드를 여러 곳에서 사용하기 위함

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    public ItemReaderConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, DataSource dataSource) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.dataSource = dataSource;
    }

    @Bean
    public Job itemReaderJob() throws Exception {
        return this.jobBuilderFactory.get("itemReaderJob")
                .incrementer(new RunIdIncrementer())
                .start(this.customItemReaderStep())
                .next(this.csvFileStep())
                .next(this.jdbcStep())
                .build();
    }

    @Bean
    public Step customItemReaderStep() {
        return this.stepBuilderFactory.get("customItemReaderStep")
                .<Person, Person>chunk(10)
                .reader(new CustomItemReader<>(getItems()))
                .writer(itemWriter())
                .build();
    }

    @Bean
    public Step csvFileStep() throws Exception {
        return  stepBuilderFactory.get("csvFileStep")
                .<Person, Person>chunk(10)
                .reader(this.csvFileItemReader())
                .writer(itemWriter())
                .build();
    }

    //jdbcCursorItemReader를 실행할 수 있는 스텝 만들기
    @Bean
    public Step jdbcStep() throws Exception {
        return stepBuilderFactory.get("jdbcStep")
                .<Person, Person>chunk(10)
                .reader(this.jdbcCursorItemReader())
                .writer(itemWriter())
                .build();
    }

     private JdbcCursorItemReader<Person> jdbcCursorItemReader() throws Exception {
        JdbcCursorItemReader<Person> itemReader = new JdbcCursorItemReaderBuilder<Person>()
                .name("jdbcCursorItemReader")
                .dataSource(dataSource)
                .sql("select id, name, age, address from person")
                .rowMapper((rs, rowNum) -> new Person(
                        rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4))) // sql()에서 쿼리를 통해 조회된 결과를 person객체에 매핑 : rowMapper
                .build();
        itemReader.afterPropertiesSet();
        return itemReader;
    }

    private FlatFileItemReader<Person> csvFileItemReader() throws Exception {
        //item을 한 줄씩 읽을 수 있는 설정 : LineMapper 객체
        DefaultLineMapper<Person> lineMapper = new DefaultLineMapper<>();
        //csv 파일을 person 객체로 mapping하기 위해서 person 필드명을 설정하는 Tokenizer객체가 필요
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("id", "name", "age", "address"); //person의 필드명 설정
        lineMapper.setLineTokenizer(tokenizer);

        lineMapper.setFieldSetMapper(fieldSet -> {
            //csv 파일에서 읽어온 값들을 person 객체로 매핑해주는 것.
            int id = fieldSet.readInt("id");
            String name = fieldSet.readString("name");
            String age = fieldSet.readString("age");
            String address = fieldSet.readString("address");

            return new Person(id, name, age, address);
        });

        FlatFileItemReader<Person> itemReader = new FlatFileItemReaderBuilder<Person>()
                .name("csvFileItemReader")
                .encoding("UTF-8")
                .resource(new ClassPathResource("test.csv"))
                .linesToSkip(1)
                .lineMapper(lineMapper)
                .build();

        itemReader.afterPropertiesSet(); //itemReader에서 필요한 필수 설정값이 정상정으로 설정되었는지 검사.

        return itemReader;
    }
    private ItemWriter<Person> itemWriter() {
        return  items -> log.info(items.stream()
                .map(Person::getName)
                .collect(Collectors.joining(",")));
    }

    private List<Person> getItems() {
        List<Person> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            items.add(new Person(i+1, "test name " + i, "test age", "test address"));
        }

        return items;
    }


}

/**
 * JDBC로 DB를 조회할 수 있는 2가지 방법
 * Cursor 기반 조회
 * 자바 ResultSet 이 이 Cursor를 구현한 클래스.
 * -> 쿼리를 실행하기 위해 커넥션이 연결된 상태에서 커서를 한칸씩이동하면서 데이터를 하나씩 뽑아오는 것.
 * ItemStreamInterface는 open() : 커넥션 연결, update() : 커서를 이동하면서 데이터 뽑음, close() : 커넥션 끊기, update메소드 원하는 데이터가 모두 조회될 때까지 반복하여 실행(null까지)
 * 배치 처리가 완료될 때까지 DB Connection이 연결
 * DB Connection 빈도가 낮아 성능이 좋은 반면, 긴 Connection 유지 시간 필요
 * 하나의 Connection에서 처리되기 때문에, Thread safe하지 않음
 * 모든 결과를 메모리에 할당하기 때문에, 더 많은 메모리를 사용
 *
 * Paging 기반 조회
 * 페이징 단위(limit, offset)로 DB Connection을 연결
 * DB Connection빈도가 높아 비교적 성능이 낮은 반면, 짧은 Connection 유지 시간 필요
 * -> 1000개의 데이터를 가져오기 위해 10번 끊어서 가져오는데 그때마다 새 커넥션
 * 매번 Connection을 하기 때문에 Thread Safe
 * 페이징 단위의 결과만 메모리에 할당하기 때문에, 비교적 더 적은 메모리를 사용
 *
 *
 *
 * ItemWriter
 * 마지막으로 배치 처리 대상 데이터를 어떻게 처리할 지 결정
 * Step에서 ItemWriter는 필수
 * 예를 들면 ItemReader에서 읽은 데이터를 DB에 저장, API로 서버에 요청, 파일에 데이터를 write
 * 항상 write가 아님 데이터를 최종 마무리 하는 것이 ItemWriter
 *
 * ItemProcessor
 * ItemReader에서 읽은 데이터를 가공 또는 Filtering
 * Step의 ItemProcessor는 optional
 * input을 output으로 변환하거나
 * ItemWriter의 실행 여부를 판단할 수 있도록 filtering역할 (-> itemWriter로 보낼지 말지 결정하는)을 한다.
 * ItemWriter는 not null만 처리한다.
 *  itemReader에서 null을 내보내면 chunk 처리가 끝났다는 의미
 *  itemProcessor에서 null을 내보내면 해당 item을 writer로 보내지 않겠다는 의미
 *
 */
