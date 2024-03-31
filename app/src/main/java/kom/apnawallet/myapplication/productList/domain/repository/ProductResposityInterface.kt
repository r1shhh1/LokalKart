package kom.apnawallet.myapplication.productList.domain.repository

import kom.apnawallet.myapplication.productList.domain.model.Product
import kom.apnawallet.myapplication.util.Resources
import kotlinx.coroutines.flow.Flow

interface ProductRepositoryInterface {
    suspend fun fetchProducts(): Flow<Resources<List<Product>>>
}