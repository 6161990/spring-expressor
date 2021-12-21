package yoon.spring.batch.part3;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import yoon.spring.batch.TestConfiguration;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBatchTest //Step 의 JobScope이 제대로 동작하려면 !
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {SavePersonConfiguration.class, TestConfiguration.class})
public class SavePersonConfigurationTest {

    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Autowired
    private PersonRepository personRepository;

    @After
    public void tearDown() throws Exception {  // 각 test method가 모두 실행된 후에 모든 repository가 지워지게 되고 그럼 서로 데이터를 공유할 일이 없으니까 test 통과
        personRepository.deleteAll();
    }

    @Test
    public void test_step(){
        JobExecution savePersonStep = jobLauncherTestUtils.launchStep("savePersonStep");

        assertThat(savePersonStep.getStepExecutions().stream()
                .mapToInt(StepExecution::getWriteCount)
                .sum())
                .isEqualTo(personRepository.count()) //실제로 DB에 저장이 되었는지 검사
                .isEqualTo(3);
    }

    @Test
    public void test_allow_duplicate() throws Exception {
        //given
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("allow_duplicate", "false")
                .toJobParameters();

        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        //then
        assertThat(jobExecution.getStepExecutions().stream()
                .mapToInt(StepExecution::getWriteCount)
                .sum())
                .isEqualTo(personRepository.count()) //실제로 DB에 저장이 되었는지 검사
                .isEqualTo(3);
    }

    @Test
    public void test_not_allow_duplicate() throws Exception {
        //given
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("allow_duplicate", "true")
                .toJobParameters();

        //when
        JobExecution jobExecution = jobLauncherTestUtils.launchJob(jobParameters);

        //then
        assertThat(jobExecution.getStepExecutions().stream()
                .mapToInt(StepExecution::getWriteCount)
                .sum())
                .isEqualTo(personRepository.count())
                .isEqualTo(100);
    }

/**
 * JobLauncherTestUtils : JobLauncher 를 이용해 테스트를 가능하게끔 만든 클래스
 */


}
