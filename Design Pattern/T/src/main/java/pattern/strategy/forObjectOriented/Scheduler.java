package pattern.strategy.forObjectOriented;

/** Strategy : 정책이 수행해야 하는 기능들을 인터페이스로 선언 */
public interface Scheduler {
    String getNextCall();
    String sendCallToAgent();
}
