package productimporter;

public interface ProductInventory { // ProductSynchronizer 입장에서 간접 출력을 담당한다.
    void upsertProduct(Product product);
}
