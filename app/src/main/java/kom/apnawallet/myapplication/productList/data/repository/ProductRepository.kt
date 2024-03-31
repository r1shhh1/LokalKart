package kom.apnawallet.myapplication.productList.data.repository

import kom.apnawallet.myapplication.productList.data.mappers.toProduct
import kom.apnawallet.myapplication.productList.data.remote.ApiService
import kom.apnawallet.myapplication.productList.domain.model.Product
import kom.apnawallet.myapplication.productList.domain.repository.ProductRepositoryInterface
import kom.apnawallet.myapplication.util.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


class ProductRepository @Inject constructor(
    private val apiService: ApiService
) : ProductRepositoryInterface {


    override suspend fun fetchProducts(
    ): Flow<Resources<List<Product>>> {
        return flow {
            emit(Resources.Loading(true))

            val productListFromApi = try {
                apiService.getProducts()
            }catch (e: IOException){
                e.printStackTrace()
                emit(Resources.Error(message = "Error loading movies"))
                return@flow
            }catch (e: retrofit2.HttpException){
                e.printStackTrace()
                emit(Resources.Error(message = "Error loading movies"))
                return@flow
            }catch (e: Exception){
                e.printStackTrace()
                emit(Resources.Error(message = "Error loading movies"))
                return@flow
            }

            val productList = productListFromApi.products.let {
                it.map { productDto ->
                    productDto.toProduct()
                }
            }

            emit(Resources.Success(
                productList
            ))

            emit(Resources.Loading(false))
        }
    }


//    override suspend fun fetchProducts(): List<Product>? {
//        try {
//            val response = apiService.getProducts()
//            if (response.isSuccessful && response.body() != null) {
//                return response.body()!!.products
//            }
//        } catch (e: IOException) {
//            //TODO
//        } catch (e: HttpException) {
//            //TODO
//        }
//        return null
//    }
}