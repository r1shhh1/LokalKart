package kom.apnawallet.myapplication.repository

import android.util.Log
import kom.apnawallet.myapplication.ui.TAG
import kom.apnawallet.myapplication.model.Product
import kom.apnawallet.myapplication.networking.ApiService
import retrofit2.HttpException
import java.io.IOException

class ProductRepository(private val apiService: ApiService) : ProductRepositoryInterface {

    override suspend fun fetchProducts(): List<Product>? {
        try {
            val response = apiService.getProducts()
            if (response.isSuccessful && response.body() != null) {
                return response.body()!!.products
            }
        } catch (e: IOException) {
            Log.e(TAG, "IOException Internet Issue!", e)
        } catch (e: HttpException) {
            Log.e(TAG, "HttpException, unexpected response!", e)
        }
        return null
    }
}