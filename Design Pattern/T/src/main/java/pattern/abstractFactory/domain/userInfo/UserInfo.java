package pattern.abstractFactory.domain.userInfo;

import lombok.Value;

@Value
public class UserInfo {
    String userId;
    String password;
    String userName;

    public UserInfo(String userId, String password, String userName) {
        this.userId = userId;
        this.password = password;
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }
}
