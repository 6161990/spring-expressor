package com.example.springcalculator.component;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
//Calculator 의 후보군이 현재 DollarCalculator밖에 없기 때문에 자동 주입(auto wired)되므로 calculator자체를 활용할 수 있다.
@RequiredArgsConstructor
public class Calculator {

    private final ICalculator iCalculator;

    public int sum(int x, int y) {
        this.iCalculator.init();
        return iCalculator.sum(x, y);
    }

    public int minus(int x, int y) {
        this.iCalculator.init();
        return iCalculator.minus(x, y);
    }
}
