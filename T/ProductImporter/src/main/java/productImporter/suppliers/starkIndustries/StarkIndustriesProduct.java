package productImporter.suppliers.starkIndustries;

import lombok.Getter;

@Getter
public class StarkIndustriesProduct {
    private final String code;
    private final String name;
    private final int listPrice;
    private final int discountAmount;

    public StarkIndustriesProduct(String code, String name, int listPrice, int discountAmount) {
        this.code = code;
        this.name = name;
        this.listPrice = listPrice;
        this.discountAmount = discountAmount;
    }


}
