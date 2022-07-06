package pattern.abstractFactory.factory;

import pattern.abstractFactory.domain.product.dao.ProductDao;
import pattern.abstractFactory.domain.userInfo.dao.UserInfoDao;

public interface DaoFactory {
    UserInfoDao createUserInfo();
    ProductDao createProduct();
}
