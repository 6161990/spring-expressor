package pattern.abstractFactory.domain.userInfo.dao.mysql;

import pattern.abstractFactory.domain.userInfo.UserInfo;
import pattern.abstractFactory.domain.userInfo.dao.UserInfoDao;

public class UserInfoMySqlDao implements UserInfoDao {
    @Override
    public String insertUserInfo(UserInfo userInfo) {
        return "insert into MYSQL DB userId =" + userInfo.getUserId();
    }

    @Override
    public String updateUserInfo(UserInfo userInfo) {
        return "update into MYSQL DB userId =" + userInfo.getUserId();
    }

    @Override
    public String deleteUserInfo(UserInfo userInfo) {
        return "delete into MYSQL DB userId =" + userInfo.getUserId();
    }
}
