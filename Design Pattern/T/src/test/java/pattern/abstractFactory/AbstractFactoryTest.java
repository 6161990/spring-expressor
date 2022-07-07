package pattern.abstractFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pattern.abstractFactory.domain.product.Product;
import pattern.abstractFactory.domain.userInfo.UserInfo;
import pattern.abstractFactory.factory.DaoFactory;

class AbstractFactoryTest {

    @Test
    void name() throws Exception {
        LoadDaoFactory sut = new LoadDaoFactory();

        DaoFactory daoFactory = sut.determineConcreteDaoFactory();

        Product examProduct = new Product("1", "product");
        UserInfo examUserInfo = new UserInfo("1", "1234", "rose");

        String productOutput = daoFactory.createProduct().insertProduct(examProduct);
        String userInfoOutput = daoFactory.createUserInfo().insertUserInfo(examUserInfo);

        Assertions.assertEquals(userInfoOutput ,"insert into MYSQL DB userId =" + examUserInfo.getUserId());
        Assertions.assertEquals(productOutput , "insert into MYSQL DB productId =" + examProduct.getProductId());
    }

}
