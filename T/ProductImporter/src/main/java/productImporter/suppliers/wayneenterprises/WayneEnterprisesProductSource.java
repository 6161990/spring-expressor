package productImporter.suppliers.wayneenterprises;

public interface WayneEnterprisesProductSource {
    Iterable<WayneEnterprisesProduct> fetchProducts();
}
