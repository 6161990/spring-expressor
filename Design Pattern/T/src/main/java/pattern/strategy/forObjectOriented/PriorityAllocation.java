package pattern.strategy.forObjectOriented;

/** ConcreteStrategy : Strategy 에 선언된 여러 기능들을 구현 */
public class PriorityAllocation implements Scheduler{
    @Override
    public String getNextCall() {
        return "고객 등급이 높은 고객의 전화를 먼저 가져옵니다.";
    }

    @Override
    public String sendCallToAgent() {
        return "업무 skill 값이 높은 상담원에게 우선적으로 배분합니다.";
    }
}
