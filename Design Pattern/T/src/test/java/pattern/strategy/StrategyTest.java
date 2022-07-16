package pattern.strategy;

import pattern.strategy.forObjectOriented.*;

import java.io.IOException;


/** Context : 어떤 ConcreteStrategy 가 수행될 것인지 따라 정책을 선택한다*/
// Strategy 클래스와 Context 클래스는 선택한 알고리즘이 동작하도록 협력
class StrategyTest {

    public static void main(String[] args) throws IOException {
        System.out.println("전화 상담 할당 방식을 선택하세요.");
        System.out.println("R : 한명씩 차례로 할당");
        System.out.println("L : 쉬고 있거나 대기가 가장 적은 상담원에게 할당");
        System.out.println("P : 우선순위가 높은 고객 먼저 할당");
        System.out.println("A : Agent 에게 전화를 넘김");

        int ch = System.in.read();

        Scheduler scheduler = null;

        if(ch == 'r' || ch == 'R'){
            scheduler = new RoundRobin();
        }else if(ch == 'l' || ch == 'L'){
            scheduler = new LeastJob();
        }else if(ch == 'p' || ch == 'P'){
            scheduler = new PriorityAllocation();
        }else if(ch == 'a' || ch == 'A'){
            scheduler = new AgentGetCall();
        }else{
            throw new IllegalArgumentException("NOT SUPPORT");
        }

        System.out.println(scheduler.getNextCall());
        System.out.println(scheduler.sendCallToAgent());
    }
}
