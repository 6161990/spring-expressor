package pattern.singleton.forObjectOriented;

public class ConnectionPool {

    /** 유일한 정적변수 */
    private static ConnectionPool instance = new ConnectionPool();

    /** 외부에서 생성하지 못하도록 private default construct */
    private ConnectionPool() {}

    /** 클래스를 생성하지 않아도 호출할 수 있는 메서드 : static */
    public static ConnectionPool getInstance() {
        if(instance == null){
            return instance = new ConnectionPool();
        }
        return instance;
    }

    /**
     * 일반적으로는 이렇게 사용하지만,
     * 배열로 instance 를 여러개 만들어 놓고 반환하는 방식의 싱글톤 패턴도 있다.
     * private ArrayList<Something> somethings
     * private add(Something something){ ... }
     * private get(){ return somethings.get(i) }
     * */
}
