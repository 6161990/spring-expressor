
### ProductImporterTest 구조

---
[SomethingProductImporter_specs]     
* [Step1. WayneEnterprises 사가 가지고 있는 상품 목록만큼 우리 커머스 서비스가 규정하고 있는 상품 포맷을 통과한 개수가 나와야한다.    
  ***WayneEnterprisesProductSourceStub 을 만들어야한다.***
* [Step2. WayneEnterprises 사의 Supplier 의 이름을 Importer 에서 WAYNE 이라고 설정한다]
* [Step3. WayneEnterprises 사의 properties 들을 잘 투영한다.]
* [Step7. StarkIndustries 사가 넘겨준 상품 수 만큼 커머스 서비스에 등록된다]
* [Step8. StarkIndustries 사가 넘겨준 상품을 우리 커머스에 맞게 잘 변환한다]

<br/>

[ProductSynchronizer_specs]   
* [Step4. ProductSynchronizer 가 ProductInventory 에 상품을 잘 저장한다.]
* [Step5. 올바르지 않은 상품은 저장하지 않는다.]
* [Step6. Spy 를 사용하던 ProductSynchronizer 를 Mock 을 사용해 TestCase 작성]

[ProductInventorySpy]   
**implements ProductInventory**
* **@Override**    
  upsertProduct(Product product)

<br/>

[WayneEnterprisesProductSourceStub]    
**implements WayneEnterprisesProductSource**
* @Override
  Iterable<WayneEnterprisesProduct> fetchProducts()

<br/>

[CompositeArgumentResolver]     
**implements DomainArgumentResolver** 
* **@Override**     
  Optional<Object> tryResolve(Class<?> parameterType)

<br/>
   
[DomainArgumentResolver]   
* DomainArgumentResolver
* CompositeArgumentResolver(ProductArgumentResolver, StarkIndustriesProductArgumentResolver, WayneEnterprisesProductArgumentResolver)

<br/>

[ProductArgumentResolver]    
**implements DomainArgumentResolver**
* generate()
* **@Override**
  Optional<Object> tryResolve(Class<?> parameterType)

<br/>

[DomainArgumentsProvider implements ArgumentsProvider, AnnotationConsumer<DomainArgumentsSource>]     
* **@Override**    
  accept(DomainArgumentsSource domainArgumentsSource) 
* **@Override**    
  Stream<? extends Arguments> provideArguments(ExtensionContext context)

<br/>

[SomethingProductArgumentResolver]        
**implements DomainArgumentResolver**
* generate()
* **@Override**    
  Optional<Object> tryResolve(Class<?> parameterType)

<br/>

[@DomainArgumentsSource]
* @ArgumentsSource(DomainArgumentsProvider.class)

[For Test ArgumentsSource]
Step1. DomainArgumentsSource
Step2. DomainArgumentsProvider
Step3. DomainArgumentResolver
Step4. CompositeArgumentResolver
Step5. ProductArgumentResolver
Step6. StarkIndustriesProductArgumentResolver
Step7. WayneEnterprisesProductArgumentResolver

[For ProductSynchronizer]
Step8-1. ListPriceFilter_specs
Step8-2. ListPriceFilter_specs
Step9-1. ProductSynchronizer_specs
* [Step9-1-1. ProductSynchronizer 가 ProductInventory 에 상품을 잘 저장한다.]
* [Step9-1-2. 올바르지 않은 상품은 저장하지 않는다.]
* [Step9-1-3. Spy 를 사용하던 ProductSynchronizer 를 Mock 을 사용해 TestCase 작성]
Step9-2. ProductSynchronizer
Step10. ProductImporter
Step11. ProductValidator
Step12-1. ProductInventory
Step12-2. ProductInventorySpy

[For ProductImporter]
Step13-1. ProductImporter
Step13-2. ProductImporter_specs
Step14. ProductSource
Step15. ProductTranslator
* [Step15-2-1. WayneEnterprises 사가 가지고 있는 상품 목록만큼 우리 커머스 서비스가 규정하고 있는 상품 포맷을 통과한 개수가 나와야한다.]
  * Step15-2-2. WayneEnterprisesProductSourceStub 을 만들어야한다.
* [Step15-2-3. WayneEnterprises 사의 Supplier 의 이름을 Importer 에서 WAYNE 이라고 설정한다]
* [Step15-2-4. WayneEnterprises 사의 properties 들을 잘 투영한다.]
* [Step15-2-5. StarkIndustries 사가 넘겨준 상품 수 만큼 커머스 서비스에 등록된다]
* [Step15. StarkIndustries 사가 넘겨준 상품을 우리 커머스에 맞게 잘 변환한다]


[Product]
Step16. Product
Step17. Pricing