package productImporter.suppliers.wayneenterprises;

public interface WayneEnterprisesProductSource {
    /** WayneEnterprisesProduct의 제품 시퀀스를 반환*/
    Iterable<WayneEnterprisesProduct> fetchProducts();
}
