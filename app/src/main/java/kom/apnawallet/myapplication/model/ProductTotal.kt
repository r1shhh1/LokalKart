package kom.apnawallet.myapplication.model

import java.io.Serializable

data class ProductTotal(
    val products: List<Product>,
    val total: Int,
    val skip: Int,
    val limit: Int,
)

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Int,
    val discountPercentage: Float,
    val rating: Float,
    val stock: Int,
    val brand:String,
    val category: String,
    val thumbnail: String,
    val images: List<String>,
): Serializable