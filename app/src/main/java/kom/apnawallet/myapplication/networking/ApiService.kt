package kom.apnawallet.myapplication.networking

import kom.apnawallet.myapplication.model.ProductTotal
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/products")
    suspend fun getProducts(): Response<ProductTotal>
}