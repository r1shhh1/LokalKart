package kom.apnawallet.myapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kom.apnawallet.myapplication.model.Product
import kom.apnawallet.myapplication.repository.ProductRepositoryInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Timer
import java.util.TimerTask

class MainViewModel(private val productRepository: ProductRepositoryInterface) : ViewModel() {

    val _products = MutableLiveData<List<Product>?>()
    val products: MutableLiveData<List<Product>?> get() = _products

    val _lastRefreshTime = MutableLiveData<Long>()
    val lastRefreshTime: LiveData<Long> get() = _lastRefreshTime

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        // Initialize the ViewModel
        fetchProducts()
    }


    fun fetchProducts() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.Main) {
            try {
                val productList = productRepository.fetchProducts()
                _products.value = productList
                _lastRefreshTime.value = System.currentTimeMillis()
            } catch (e: Exception) {
                Log.d("TAG", "fetchProducts:  Error in fetching products")
            } finally {
                _isLoading.value = false
            }
        }
    }

}