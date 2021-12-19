package yoon.spring.batch.part1;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration // 스프링의 설정 클래스를 인식
public class HelloConfiguration {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    public HelloConfiguration(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job helloJob() {
        return jobBuilderFactory.get("helloJob")
                .incrementer(new RunIdIncrementer())
                // 이 클래스가 항상 새로운 잡 인스턴스 만들도록함. id를 incrementer하게 자동생성하도록.
                // 만약 이 클래스를 파라미터로 두지 않고 실행한다면, 매번 실행할 때마다 동일한 JobInstance로 실행한다는 뜻.
                // 이 설정을 추가하면 항상 다른 JobInstance가 실행됨.
                .start(helloStep())
                .build();
    }

    @Bean
    public Step helloStep() {
        return stepBuilderFactory.get("helloStep")
                .tasklet((contribution, chunkContext) -> {
                    log.info("hello spring batch");
                    return RepeatStatus.FINISHED;
                }).build();
    }
}
    /**
   JobBuilderFactory 이미 스프링 빈으로 등록된 객체.
    잡의 실행단위를 구분할 수 있는 incrementer와 잡의 이름 그리고 step을 설정
   RunIdIncrementer
    잡이 실행될 때 마다 파라미터 id를 자동 생성해줌.
   잡 이름('helloJob')
    스프링 배치를 실행할 수 있는 키
   .start()
    잡 실행시 최소로 실행될 메소드 이름
   Step
    잡의 실행 단위, 하나의 잡은 한개 이상의 스텝을 가질 수 있음.
   tasklet 비교적 간단한 작업 단위 처리 : chuck와 같은 스텝의 실행 기반 (하나의 작업 기반으로 실행)
   chuck 대량 묶음 단위 처리 (하나의 큰 덩어리를 n개씩 나눠서 실행)
   chuck 기반 step은 *(Item은 배치 처리 대상 객체를 의미)
    ItemReader, 배치 처리 대상 객체를 읽어 다음 둘 에게 전달
    Optional-ItemProcessor, input 객체를 output 객체로 filtering 또는 processing 해 ItemWriter에게 전달.
    ItemWriter, 배치 처리 대상 객체를 처리

   helloJob 외에 다른 job이 있는 상태에서 이 애플리케이션을 실행시키려면 어떤 잡이 실행될까
   -> 모든 잡 ->  개별 잡을 실행하려면 설정 하면됨.
   Program arguments : --spring.batch.job.names=helloJob

   빈을 생성만 했을 뿐인데, 배치가 실행됨. 스프링 배치는 job type의 빈이 생성되면 JobLauncher 객체에 의해 잡을 실행.
   잡은 스텝을 실행.
   JobRepository = DB, Memory에 스프링 배치가 실행할 수 있도록 메타데이터를 관리할 수 있도록 하는 객체

   */


    /**
     * JobInstance의 생성 기준은 JobParameters 중복 여부에 따라 생성
     * 다른 parameter로 Job이 실행되면, JobInstance가 생성
     * 같은 parameter로 Job이 실행되면, 이미 생성된 JobInstance가 실행
     * JobExecution은 항상 새롭게 생성
     *
     * ex ) 처음 Job 실행 시 date parameter가 1월 1일로 실행됐다면, 1번 JobInstance 생성
     *      다음 Job 실행 시 date parameter가 1월 2일로 실행됐다면, 2번 JobInstance 생성
     *      다음 Job 실행 시 date parameter가 1월 2일로 실행됐다면, 2번 JobInstance 재실행
     *          이때 Job이 재실행 대상이 아닌 경우 에러가 발생
     * Job을 항상 새로운 JobInstance가 실행될 수 있도록 RunIdIncrementer 제공
     *  RunIdIncrementer는 항상 다른 run.id를 Parameter로 설정
     * */

