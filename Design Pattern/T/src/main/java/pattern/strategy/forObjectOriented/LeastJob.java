package pattern.strategy.forObjectOriented;

/** ConcreteStrategy : Strategy 에 선언된 여러 기능들을 구현 */
public class LeastJob implements Scheduler{
    @Override
    public String getNextCall() {
        return "상담 전화를 순서대로 대기열에서 가져옵니다.";
    }

    @Override
    public String sendCallToAgent() {
        return "현재 상담업무가 없거나 상담대기가 가장 작은 상담원에게 할당합니다.";
    }
}
