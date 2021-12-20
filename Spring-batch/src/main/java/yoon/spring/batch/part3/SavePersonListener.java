package yoon.spring.batch.part3;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterJob;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeJob;
import org.springframework.batch.core.annotation.BeforeStep;

@Slf4j
public class SavePersonListener {

    public static class SavePersonStepExecutionListener {
        @BeforeStep
        public void beforeStep(StepExecution stepExecution) {
            log.info("beforeStep");
        }

        @AfterStep
        public ExitStatus afterStep(StepExecution stepExecution) {
            log.info("afterStep : {}", stepExecution.getWriteCount());
            if (stepExecution.getWriteCount() == 0 ){
                return ExitStatus.FAILED;
            }
            return stepExecution.getExitStatus(); // 스프링 배치는 내부적으로 스텝이 실패하거나 종료되면 내부적으로 상태는 stepExecution에 저장함
        }
    }

    public static class SavePersonJobExecutionListener implements JobExecutionListener {

        @Override
        public void beforeJob(JobExecution jobExecution) {
            log.info("beforeJob");
        }

        @Override
        public void afterJob(JobExecution jobExecution) {
            int sum = jobExecution.getStepExecutions().stream()
                    .mapToInt(StepExecution::getWriteCount)
                    .sum();
            log.info("after Job : {}", sum);
        }
    }

    public static class SavePersonAnnotationJobExecutionListener {
        @BeforeJob
        public void beforeJob(JobExecution jobExecution) {
            log.info("annotationBeforeJob");
        }

        @AfterJob
        public void afterJob(JobExecution jobExecution) {
            int sum = jobExecution.getStepExecutions().stream()
                    .mapToInt(StepExecution::getWriteCount)
                    .sum();
            log.info("annotationAfter Job : {}", sum);
        }
    }

    /**
     * Listener
     * 스프링 배치에서 전 처리, 후 처리를 하는 다양한 종류의 리스너가 있음.
     * interface 구현
     * @Annotation 정의
     * Job 실행 전과 후에 실행할 수 있는 JobExecutionListener
     * Step 실행 전과 후에 실행할 수 있는 StepExecutionListener
     *
     * Job의 실행 전과 후에 실행될 수 있도록 Job에도 설정이 필요함.
     * savePersonJob의
     *      .listener(new SavePersonListener.SavePersonJobExecutionListener())
     *      .listener(new SavePersonListener.SavePersonAnnotationJobExecution())
     */
}
