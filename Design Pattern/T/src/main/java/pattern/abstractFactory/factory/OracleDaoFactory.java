package pattern.abstractFactory.factory;

import pattern.abstractFactory.domain.product.dao.ProductDao;
import pattern.abstractFactory.domain.product.dao.oracle.ProductOracleDao;
import pattern.abstractFactory.domain.userInfo.dao.UserInfoDao;
import pattern.abstractFactory.domain.userInfo.dao.oracle.UserInfoOracleDao;

public class OracleDaoFactory implements DaoFactory {
    @Override
    public UserInfoDao createUserInfo() {
        return new UserInfoOracleDao();
    }

    @Override
    public ProductDao createProduct() {
        return new ProductOracleDao();
    }
}
