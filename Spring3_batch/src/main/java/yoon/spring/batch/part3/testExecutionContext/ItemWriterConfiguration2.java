package yoon.spring.batch.part3.testExecutionContext;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import yoon.spring.batch.part3.CustomItemReader;
import yoon.spring.batch.part3.Person;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@Configuration
public class ItemWriterConfiguration2 {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final DataSource dataSource;

    public ItemWriterConfiguration2(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, DataSource dataSource) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.dataSource = dataSource;
    }

    @Bean
    public Job itemWriterJob() throws Exception {
        return this.jobBuilderFactory.get("itemWriterJob1")
                .incrementer(new RunIdIncrementer())
                .start(this.step1())
                .listener(jobExecutionListener())
                .listener(stepExecutionListener())
                .build();
    }

    @Bean
    public Step step1() {
         return stepBuilderFactory.get("jdbcBatchItemWriterStep")
                 .<Person, Person>chunk(10)
                 .reader(itemReader())
                 .writer(itemWriter())
                .build();
    }

    @Bean
    public JobExecutionListener jobExecutionListener() {
        return new JobExecutionListener() {
            @Override
            public void beforeJob(JobExecution jobExecution) {
                log.info(">>>>> Person Batch Job Start");
            }

            @Override
            public void afterJob(JobExecution jobExecution) {
                log.info(">>>>>  Person Batch  Job Finished");
                int readCount = jobExecution.getStepExecutions().stream()
                        .mapToInt(StepExecution::getReadCount)
                        .sum();
                ExecutionContext context = jobExecution.getExecutionContext();
                context.put("personPhoneNumber", 2020203777);

                log.info(">>>>> executed Job readCount = {}" , readCount);
                int writeCount = jobExecution.getStepExecutions().stream()
                        .mapToInt(StepExecution::getWriteCount)
                        .sum();
                log.info(">>>>> executed Job writeCount = {}", writeCount);
            }
        };
    }

    @Bean
    public StepExecutionListener stepExecutionListener() {
        return new StepExecutionListener() {
            @Override
            public void beforeStep(StepExecution stepExecution) {
                JobExecution jobExecution = stepExecution.getJobExecution();
                ExecutionContext executionContext = jobExecution.getExecutionContext();
                PersonNumber personPhoneNumber = (PersonNumber) executionContext.get("personPhoneNumber");

                ExecutionContext stepExecutionContext = stepExecution.getExecutionContext();
                stepExecutionContext.put("personPhoneNumber", personPhoneNumber);

                String stepName = stepExecution.getStepName();
                int readCount = stepExecution.getReadCount();
                log.info(">>>>> Person Batch Step Start", stepName);
                log.info(">>>>> executed readCount = {}", readCount);
            }

            @Override
            public ExitStatus afterStep(StepExecution stepExecution) {
                String stepName = stepExecution.getStepName();
                int writeCount = stepExecution.getWriteCount();
                Date endTime = stepExecution.getEndTime();
                log.info(">>>>> Person Batch Step End", stepName);
                log.info(">>>>> executed writeCount = {}", writeCount);
                log.info(">>>>> executed endTime = {}", endTime);
                return ExitStatus.COMPLETED;
            }
        };
    }

    private ItemWriter<Person> itemWriter() {
         JdbcBatchItemWriter<Person> itemWriter = new JdbcBatchItemWriterBuilder<Person>()
                .namedParametersJdbcTemplate(new NamedParameterJdbcTemplate(dataSource))
                .sql("insert into person(name,age,address) values(:name, :age, :address)")
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>()) // person 클래스를 파라미터로 자동으로 셍성할 수 있는 객체
                .beanMapped()
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
