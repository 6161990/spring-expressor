package pattern.singleton.RTPSingleton;

public class SystemPermission {
    private SystemUser requestor;
    private SystemProfile profile;
    private Permission state;

    public SystemPermission(SystemUser requestor, SystemProfile profile){
        this.requestor = requestor;
        this.profile = profile;
        state = PermissionRequested.state();
    }

    public void willBeHandledBy(SystemAdmin admin) {
    }

    public void setState(PermissionClaimed permissionClaimed) {
        this.state = permissionClaimed.isWhat();
    }
}
