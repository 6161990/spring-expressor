using Inventory.Queries;
using System.Collections.Immutable;
using System.Threading;
using System.Threading.Tasks;

namespace Inventory.QueryModel
{
    public sealed class GetAllProductQueryProcessor //데이터 조회기능 담당. 도메인 모델의 Product를 view 모델인 productView 전환하는 책임을 하고있음.
    {
        private readonly IProductReader _reader;

        public GetAllProductQueryProcessor(IProductReader reader) => _reader = reader;

        public async Task<ImmutableArray<ProductView>> ProcessQuery(
            GetAllProducts query,
            CancellationToken cancellationToken = default)
        {
            return ImmutableArray.CreateRange(
                items: await _reader.GetAllProducts(cancellationToken),
                selector: Transform);
        }

        private ProductView Transform(Product source) =>
            new ProductView(
                source.Id,
                source.SupplierName,
                source.ProductCode,
                source.ProductName,
                source.Pricing.ListPrice,
                source.Pricing.Discount,
                SellingPrice: source.Pricing.ListPrice - source.Pricing.Discount);
    }
}
