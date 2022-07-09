package pattern.abstractFactory.factory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pattern.abstractFactory.domain.product.dao.ProductDao;
import pattern.abstractFactory.domain.product.dao.oracle.ProductOracleDao;
import pattern.abstractFactory.domain.userInfo.dao.UserInfoDao;
import pattern.abstractFactory.domain.userInfo.dao.oracle.UserInfoOracleDao;

@Component
@Profile("oracle")
public class OracleDaoFactory implements DaoFactory {
    @Override
    public UserInfoDao createUserInfo() {
        return new UserInfoOracleDao();
    }

    @Override
    public ProductDao createProduct() {
        return new ProductOracleDao();
    }

    @Override
    public String toString() {
        return "OracleDaoFactory";
    }
}
