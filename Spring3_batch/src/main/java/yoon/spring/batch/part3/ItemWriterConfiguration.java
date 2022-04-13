package yoon.spring.batch.part3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Configuration
public class ItemWriterConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    public ItemWriterConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, DataSource dataSource) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.dataSource = dataSource;
    }

    @Bean
    public Job itemWriterJob() throws Exception {
        return this.jobBuilderFactory.get("itemWriterJob")
                .incrementer(new RunIdIncrementer())
                .start(this.csvItemWriterStep())
                .next(this.jdbcBatchItemWriterStep())
                .build();
    }

    @Bean
    public Step csvItemWriterStep() throws Exception {
        return this.stepBuilderFactory.get("csvItemWriterStep")
                .<Person, Person>chunk(10)
                .reader(itemReader())
                .writer(this.csvFileItemWriter())
                .build();
    }

    @Bean
    public Step jdbcBatchItemWriterStep() {
        return stepBuilderFactory.get("jdbcBatchItemWriterStep")
                .<Person, Person>chunk(10)
                .reader(itemReader())
                .writer(jdbcBatchItemWriter())
                .build();
    }

    private ItemWriter<Person> jdbcBatchItemWriter() {
/*        return new ItemWriter<Person>() {
            @Override
            public void write(List<? extends Person> items) throws Exception {
*//*                NamedParameterJdbcTemplate namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
                namedParameterJdbcTemplate.batchUpdate("insert into person(name,age,address) values(:name, :age, :address)",
                        SqlParameterSourceUtils.createBatch(items));*//*
                new JdbcBatchItemWriterBuilder<Person>()
                        .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                        .namedParametersJdbcTemplate(new NamedParameterJdbcTemplate(dataSource))
                        .sql("INSERT INTO CUSTOMER (first_name, middle_initial, last_name,address, city, state, zip, email) "
                                + "VALUES(:firstName, :middleInitial, :lastName, :address, :city, :state, :zip, :email)")
                        .beanMapped()
                        .build();
            }
        };*/
         JdbcBatchItemWriter<Person> itemWriter = new JdbcBatchItemWriterBuilder<Person>()
                .namedParametersJdbcTemplate(new NamedParameterJdbcTemplate(dataSource))
                .sql("insert into person(name,age,address) values(:name, :age, :address)")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>()) // person 클래스를 파라미터로 자동으로 셍성할 수 있는 객체
                .beanMapped()
                .build();

        itemWriter.afterPropertiesSet();

        return itemWriter;
    }

    private ItemWriter<Person> csvFileItemWriter() throws Exception {
        BeanWrapperFieldExtractor<Person> fieldExtractor = new BeanWrapperFieldExtractor<>(); //csv파일에 있는 데이터를 추출하기 위한 객체
        fieldExtractor.setNames(new String[] {"id", "name", "age", "address"});//person객체 기준으로 필드 생성
        DelimitedLineAggregator<Person> lineAggregator = new DelimitedLineAggregator<>();//각 필드 이름을 하나의 라인에 작성하기 위해 구분값 설정
        lineAggregator.setDelimiter(",");
        lineAggregator.setFieldExtractor(fieldExtractor);

        FlatFileItemWriter<Person> itemWriter = new FlatFileItemWriterBuilder<Person>()
                .name("csvFileItemWriter")
                .encoding("UTF-8")
                .resource(new FileSystemResource("output/test-output.csv"))
                .lineAggregator(lineAggregator)
                .headerCallback(writer -> writer.write("id,이름,나이,거주지"))
                .footerCallback(writer -> writer.write("-------------------\n"))
                .append(true) // 덮어쓰기가 아니라 재생성되게끔.
                .build();
        itemWriter.afterPropertiesSet();

        return itemWriter;
    }

    private ItemReader<Person> itemReader() {
        return new CustomItemReader<>(getItems());
    }

    private List<Person> getItems() {
        List<Person> items = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            items.add(new Person(i+1, "test name" + i, "test age", "test address"));
        }
        return items;
    }
}


/**
 * JdbcBatchItemWriter는 jdbc를 사용해 DB에 write
 * JdbcBatchItemWriter는 bulk insert/update/delete 처리
 *     insert into person (name,age,address) values(1,2,3),(4,5,6),(7,8,9);
 * 단건 처리가 아니기 때문에 비교적 높은 성능
 * 예제 참고
 */
