package pattern.abstractFactory.factory;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import pattern.abstractFactory.domain.product.dao.ProductDao;
import pattern.abstractFactory.domain.product.dao.mysql.ProductMySqlDao;
import pattern.abstractFactory.domain.userInfo.dao.UserInfoDao;
import pattern.abstractFactory.domain.userInfo.dao.mysql.UserInfoMySqlDao;

@Component
@Profile("mysql")
public class MySqlDaoFactory implements DaoFactory {
    @Override
    public UserInfoDao createUserInfo() {
        return new UserInfoMySqlDao();
    }

    @Override
    public ProductDao createProduct() {
        return new ProductMySqlDao();
    }

    @Override
    public String toString() {
        return "MySqlDaoFactory";
    }
}
