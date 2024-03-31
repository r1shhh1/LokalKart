package kom.apnawallet.myapplication.productList.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kom.apnawallet.myapplication.productList.data.repository.ProductRepository
import kom.apnawallet.myapplication.util.Resources
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productRepository: ProductRepository
): ViewModel() {
    private var _productListState = MutableStateFlow(ProductListState())
    var productListState = _productListState.asStateFlow()

    init {
        getProductList()
    }

    private fun getProductList() {
        viewModelScope.launch {
            _productListState.update {
                it.copy(
                    isLoading = true
                )
            }

            productRepository.fetchProducts().collectLatest { result ->
                when(result) {
                    is Resources.Loading -> {
                        _productListState.update {
                            it.copy(isLoading = true)
                        }
                    }
                    is Resources.Success -> {
                        result.data?.let {productList ->
                            _productListState.update {
                                it.copy(
                                    productList = productList
                                )
                            }
                        }
                    }
                    is Resources.Error -> {
                        _productListState.update {
                            it.copy(isLoading = false)
                        }
                    }
                }
            }
        }
    }
}