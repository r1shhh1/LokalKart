package kom.apnawallet.myapplication.productList.data.mappers

import kom.apnawallet.myapplication.productList.data.remote.response.ProductDto
import kom.apnawallet.myapplication.productList.domain.model.Product

fun ProductDto.toProduct() : Product {
    return Product(
        id = id ?: 0 ,
        title = title ?: "",
        thumbnail = thumbnail ?: "",
        category = category ?: "",
        description = description ?: "",
        price = price ?: 0,
        discountPercentage = discountPercentage ?: 0.0F,
        brand = brand ?: "",
        stock = stock ?: 0,

        images = try {
            images?.toList() ?: listOf("", "", "")
        } catch (e: Exception) {
            listOf("", "", "")
        },

        rating = rating ?: 0.0F
    )
}