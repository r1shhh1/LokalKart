package kom.apnawallet.myapplication.productList.presentation

import kom.apnawallet.myapplication.productList.domain.model.Product

data class ProductListState(
    val isLoading: Boolean = false,
    val productList: List<Product> = emptyList()
)