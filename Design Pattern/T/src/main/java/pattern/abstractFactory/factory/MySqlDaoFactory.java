package pattern.abstractFactory.factory;

import pattern.abstractFactory.domain.product.dao.ProductDao;
import pattern.abstractFactory.domain.product.dao.mysql.ProductMySqlDao;
import pattern.abstractFactory.domain.userInfo.dao.UserInfoDao;
import pattern.abstractFactory.domain.userInfo.dao.mysql.UserInfoMySqlDao;

public class MySqlDaoFactory implements DaoFactory{
    @Override
    public UserInfoDao createUserInfo() {
        return new UserInfoMySqlDao();
    }

    @Override
    public ProductDao createProduct() {
        return new ProductMySqlDao();
    }
}
