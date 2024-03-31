package kom.apnawallet.myapplication.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kom.apnawallet.myapplication.productList.data.repository.ProductRepository
import kom.apnawallet.myapplication.productList.domain.repository.ProductRepositoryInterface
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindProductRepositoryInterface(
        productRepository: ProductRepository
    ): ProductRepositoryInterface
}