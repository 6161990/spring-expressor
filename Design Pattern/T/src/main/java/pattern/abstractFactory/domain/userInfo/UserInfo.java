package pattern.abstractFactory.domain.userInfo;

import lombok.Value;

@Value
public class UserInfo {
    String userId;
    String password;
    String userName;

    public static UserInfo getDefaultUserInfo(){
        return new UserInfo("1", "1234", "rose");
    }

}
