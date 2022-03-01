package productimporter.suppliers.wayneenterprises;

import java.math.BigDecimal;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import productimporter.Pricing;
import productimporter.Product;
import productimporter.ProductImporter;

public final class WayneEnterprisesProductImporter implements ProductImporter {

    private final WayneEnterprisesProductSource dataSource;

    public WayneEnterprisesProductImporter(WayneEnterprisesProductSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Iterable<Product> fetchProducts() {
        return StreamSupport.stream(dataSource.fetchProducts().spliterator(), false)
                .map(x -> translate(x)).collect(Collectors.toList());
    }

    private Product translate(WayneEnterprisesProduct x) {
        return new Product("WAYNE", x.getId(), x.getTitle(), new Pricing(new BigDecimal(x.getListPrice()), new BigDecimal(x.getListPrice() - x.getSellingPrice())));
    }

}
