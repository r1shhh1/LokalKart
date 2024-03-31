package kom.apnawallet.myapplication.productList.data.remote.response

data class ProductListDto(
    val products: List<ProductDto>,
    val total: Int,
    val skip: Int,
    val limit: Int,
)

