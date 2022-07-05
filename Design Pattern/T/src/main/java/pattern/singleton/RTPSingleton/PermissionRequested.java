package pattern.singleton.RTPSingleton;

public class PermissionRequested extends Permission {
    public static final String NAME = "REQUESTED";
    private static Permission state = new PermissionRequested();

    public String name(){
        return NAME;
    }

    public static Permission state(){
        return state;
    }

    public void claimedBy(SystemAdmin admin, SystemPermission permission){
        permission.willBeHandledBy(admin);
        permission.setState(new PermissionClaimed());
    }

}
/**
 * 스테이트 클래스가 6개있는데, 클라이언트에서 각 클래스의 인스턴스를 생성.
 * 그 중 하나인 PermissionRequested.
 * */
