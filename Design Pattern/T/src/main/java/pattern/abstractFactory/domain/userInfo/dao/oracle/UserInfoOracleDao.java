package pattern.abstractFactory.domain.userInfo.dao.oracle;

import pattern.abstractFactory.domain.userInfo.UserInfo;
import pattern.abstractFactory.domain.userInfo.dao.UserInfoDao;

public class UserInfoOracleDao implements UserInfoDao {
    @Override
    public String insertUserInfo(UserInfo userInfo) {
        return "insert into ORACLE DB userId =" + userInfo.getUserId();
    }

    @Override
    public String updateUserInfo(UserInfo userInfo) {
        return "update into ORACLE DB userId =" + userInfo.getUserId();
    }

    @Override
    public String deleteUserInfo(UserInfo userInfo) {
        return "delete into ORACLE DB userId =" + userInfo.getUserId();
    }
}
