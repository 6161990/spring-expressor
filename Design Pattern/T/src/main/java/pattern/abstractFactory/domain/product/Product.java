package pattern.abstractFactory.domain.product;

import lombok.Value;

@Value
public class Product {
    String ProductId;
    String ProductName;

    public static Product getDefaultProduct(){
        return new Product("1", "product");
    }


}
