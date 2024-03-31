package kom.apnawallet.myapplication.productList.data.remote.response

class ProductDto(
    val id: Int?,
    val title: String?,
    val description: String?,
    val price: Int?,
    val discountPercentage: Float?,
    val rating: Float?,
    val stock: Int?,
    val brand:String?,
    val category: String?,
    val thumbnail: String?,
    val images: List<String>?,
)

