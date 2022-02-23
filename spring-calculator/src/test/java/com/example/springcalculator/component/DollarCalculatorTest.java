package com.example.springcalculator.component;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.yaml.snakeyaml.error.Mark;

import javax.tools.DiagnosticCollector;

@SpringBootTest //모든 bean 통합적 테스트
@Import({MarketApi.class , DiagnosticCollector.class})//이 부분만 테스트
public class DollarCalculatorTest {

    @MockBean // 왜 mockBean? 스프링에서는 mock으로 관리, markrtApi를 빈 모킹 처리를 하기 위해
    private MarketApi marketApi;

    @Autowired
    private DollarCalculator dollarCalculator;

    @Test
    public void dollarCalculatorTest(){

        Mockito.when(marketApi.connect()).thenReturn(3000);
        dollarCalculator.init();

        int sum = dollarCalculator.sum(10,10);
        int minus = dollarCalculator.minus(10,10);

        Assertions.assertEquals(60000, sum);
        Assertions.assertEquals(0,minus);
    }
}
