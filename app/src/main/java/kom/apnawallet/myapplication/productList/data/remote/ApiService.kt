package kom.apnawallet.myapplication.productList.data.remote

import kom.apnawallet.myapplication.productList.data.remote.response.ProductListDto
import retrofit2.http.GET

interface ApiService {
    @GET("/products")
    suspend fun getProducts(
    ): ProductListDto

    companion object {
        val BASE_URL = "https://dummyjson.com"
    }
}