package pattern.abstractFactory.domain.userInfo.dao;

import pattern.abstractFactory.domain.userInfo.UserInfo;

public interface UserInfoDao {
    String insertUserInfo(UserInfo userInfo);
    String updateUserInfo(UserInfo userInfo);
    String deleteUserInfo(UserInfo userInfo);
}
