### ProductImporter 구조

---

[SomethingProductImporter]   
**implements ProductImporter** 
* productSource
* translator
* **@Override**    
  Iterable\<Product\> fetchProducts()

<br/>

[SomethingProductSource : **interface**]    
* Iterable<StarkIndustriesProduct\> getAllProducts()   
* Iterable<WayneEnterprisesProduct\> fetchProducts()

<br/>

[SomethingProductTranslator]  
* translate()
* getPricing()

<br/>

[ListPriceFilter]   
**implements ProductValidator**
* **@Override**    
  isValid(Product product)

<br/>

[Product]
* supplierName
* productCode
* productName
* pricing
```
StarkIndustriesProduct {
    private final String code;
    private final String name;
    private final int listPrice;
    private final int discountAmount;
}

WayneEnterprisesProduct {
    private final String id;
    private final String title;
    private final int listPrice;
    private final int sellingPrice;
}
```

<br/>

[Pricing]
* listPrice
* discount

[ProductSynchronizer]
* importer
* validator
* inventory
* run() 
  * Step4. ProductSynchronizer 가 ProductInventory 에 상품을 잘 저장한다.
  * Step5. 올바르지 않은 상품은 저장하지 않는다.