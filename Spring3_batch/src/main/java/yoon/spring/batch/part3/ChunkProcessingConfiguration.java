package yoon.spring.batch.part3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@Slf4j
public class ChunkProcessingConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public ChunkProcessingConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job chunkProcessingJob() {
        return jobBuilderFactory.get("chunkProcessingJob")
                .incrementer(new RunIdIncrementer())
                .start(this.taskBaseStep())
                .next(this.chunkBaseStep(null))
                .build();
    }

    @Bean
    @JobScope
    public Step chunkBaseStep(@Value("#{jobParameters[chunkSize]}") String chunkSize) {  // 고정된 정크의 크기를 파라미터로 받아 유연하게 하는 방법 1
        return stepBuilderFactory.get("chunkBaseStep")
                .<String, String> chunk(chunkSize.isEmpty() ? 10 : Integer.parseInt(chunkSize))
                .reader(itemReader())
                .processor(itemProcessor())
                .writer(itemWriter())
                .build();
    }

    private ItemWriter<String> itemWriter() {
        return items ->  log.info("chunk item size : {}", items.size());
      //  return items -> items.forEach(log::info);
    }

    private ItemProcessor<String, String> itemProcessor() { // null로 return 되면 writer로 리턴되지못함
        return item -> item + ", Spring Batch";
    }

    private ItemReader<String> itemReader() {
        return new ListItemReader<>(getItems());
    }

    @Bean
    public Step taskBaseStep() {
        return stepBuilderFactory.get("taskBaseStep")
                .tasklet(this.taskletLikeChunk(null))
                .build();
    }

    /** 기본 tasklet */
    private Tasklet tasklet() {
        return (contribution, chunkContext) -> {
            List<String> items = getItems();
            log.info("task item size : {}", items.size());

            return RepeatStatus.FINISHED;
        };
    }

    /** chunk처럼 이용하는  tasklet */
    @Bean
    @StepScope
    public Tasklet taskletLikeChunk(@Value("#{jobParameters[chunkSize]}") String value) {
        List<String> items = getItems();

        return (contribution, chunkContext) -> {
            StepExecution stepExecution = contribution.getStepExecution();
/*            JobParameters jobParameters = stepExecution.getJobParameters();// 고정된 정크의 크기를 파라미터로 받아 유연하게 하는 방법 1

            String value = jobParameters.getString("chunkSize", "10"); // 파라미터로(Program Arguments) 넘어오는 정크 사이즈가 없다면 10*/
            int chunkSize = value.isEmpty() ? 10 : Integer.parseInt(value);

            int fromIndex = stepExecution.getReadCount();
            int toIndex = fromIndex + chunkSize;

            if(fromIndex >= items.size()){
                return RepeatStatus.FINISHED;
            }

            List<String> subList = items.subList(fromIndex, toIndex);

            log.info("task item size : {}", subList.size());

            stepExecution.setReadCount(toIndex);

            return RepeatStatus.CONTINUABLE;
        };
    }

    private List<String> getItems() {
        List<String> items = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            items.add(i + "Hello");
        }

        return items;
    }


}


/**
 *  배치를 처리할 수 있는 방법 크게 2가지
 *  Tasklet을 사용한 Task 기반 처리
 *      배치 처리 과정이 비교적 쉬운 경우 쉽게 사용
 *      대량 처리를 하는 경우 더 복잡
 *      하나의 큰 덩어리를 여러 덩어리로 나누어 처리하기 부적합
 *   Chunk를 사용한 chunk(덩어리) 기반 처리
 *      ItemReader, ItemProcessor, ItemWriter의 관계 이해 필요
 *      대량 처리를 하는 경우 Tasklet 보다 비교적 쉽게 구현
 *          이를 Tasklet으로 처리하면 10,000개를 한번에 처리하거나, 수동으로 1,000개씩 분할
 *   정크 기반의 step 실행 종료 시점은 itemReader에서 null을 리턴할 때 까지(읽을 데이터가 없을 때까지)
 *   itemReader와 itemProcessor는 item을 하나씩 처리 itemWriter는 List로 처리
 *   <String, String> 1번째 타입 itemReader에서 읽고 반환되는 input 타입 ,
 *   그 input을 processsor에서 받아서 처리한 후, output 타입으로 반환하는 것이 두번째 인자
 *   <Input, Output>
 *
 *
 *   배치를 실행에 필요한 값을 parameter 를 통해 외부에서 주입
 *   JobParameters는 외부에서 주입된 parameter를 관리하는 객체
 *   방법 1 ) String parameter = jobParameters.getString(key, defaultValue);
 *   방법 2 ) @Value("#{jobParameters[key]}") -> 이 경우 메소드의 시그니처가 변경되었기 때문에 호출 부분에서도 변경해줘야하는데
 *   인자를 null로 해도 됨. 그래도 정크사이즈 파라미터 정상작동 why? @JobScope !!
 *
 *
 * @Scope 는 어떤 시점에 bean을 생성/소멸 시킬 지 bean 의 lifecycle을 설정
 * @JobScope는 job 실행 시점에 생성/소멸
 *  Step에 선언
 * @StepScope는 step 실행 시점에 생성/소멸, bean이어야함.
 *  Tasklet, Chunk에 선언
 * Spring의 @Scope와 같은 것
 * @Scope("job") == @JobScope
 * @Scope("Step") == @StepScope
 * Job과 Step 라이프 사이클에 의해 생성되기 때문에 Thread safe하게 작동
 * @Value("#{jobParameters[key]}")를 사용하기 위해 @JobScope와 @StepScope는 필수
 *  -> 잡 파라미터에 접근 하는 것이 스텝스코프 라이프 사이클에 의해 동작되므로.
 * */
