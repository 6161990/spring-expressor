package pattern.abstractFactory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pattern.abstractFactory.domain.product.Product;
import pattern.abstractFactory.domain.userInfo.UserInfo;
import pattern.abstractFactory.factory.DaoFactory;

//@SpringBootTest(classes = AbstractFactoryConfigurations.class) 아래와 같은 것
@SpringJUnitConfig(classes = AbstractFactoryConfigurations.class)
@ActiveProfiles("mysql")
class AbstractFactoryTest {

    @Autowired
    LoadDaoFactory sut;
    Product examProduct;
    UserInfo examUserInfo;

    @BeforeEach
    void setUp(){
        examProduct = new Product("1", "product");
        examUserInfo = new UserInfo("1", "1234", "rose");
    }

    @Test
    void 의존성주입_역할_책임분리를_통해_DaoFactory_결정합니다() throws Exception {
        DaoFactory daoFactory = sut.determineConcreteDaoFactory();

        String concreteDaoFactory = daoFactory.toString();

        Assertions.assertEquals(concreteDaoFactory, "MySqlDaoFactory");
    }

    @Test
    void 클라이언트가_지정한_DaoFactory를_동적으로_사용합니다() throws Exception {
        DaoFactory daoFactory = sut.determineConcreteDaoFactory_dynamic();

        String concreteDaoFactory = daoFactory.toString();

        Assertions.assertEquals(concreteDaoFactory, "MySqlDaoFactory");
    }

    @Test
    void 클라이언트가_지정한_DaoFactory를_사용합니다() throws Exception {
        DaoFactory daoFactory = sut.determineConcreteDaoFactory_static();

        String productOutput = daoFactory.createProduct().insertProduct(examProduct);
        String userInfoOutput = daoFactory.createUserInfo().insertUserInfo(examUserInfo);

        Assertions.assertEquals(userInfoOutput ,"insert into MYSQL DB userId =" + examUserInfo.getUserId());
        Assertions.assertEquals(productOutput , "insert into MYSQL DB productId =" + examProduct.getProductId());
    }

}
