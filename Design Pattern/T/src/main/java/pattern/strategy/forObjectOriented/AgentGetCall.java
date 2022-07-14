package pattern.strategy.forObjectOriented;

/** ConcreteStrategy : Strategy 에 선언된 여러 기능들을 구현 */
public class AgentGetCall implements Scheduler{
    @Override
    public String getNextCall() {
        return "agent 에서 고객의 전화를 가져옵니다.";
    }

    @Override
    public String sendCallToAgent() {
        return "고객의 전화를 넘겨줍니다.";
    }

}
