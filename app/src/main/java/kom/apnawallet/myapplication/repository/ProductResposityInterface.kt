package kom.apnawallet.myapplication.repository

import kom.apnawallet.myapplication.model.Product

interface ProductRepositoryInterface {
    suspend fun fetchProducts(): List<Product>?
}