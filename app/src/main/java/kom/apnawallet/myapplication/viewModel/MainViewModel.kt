package kom.apnawallet.myapplication.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kom.apnawallet.myapplication.model.Product
import kom.apnawallet.myapplication.repository.ProductRepositoryInterface
import kom.apnawallet.myapplication.ui.isFirstFetch
import kom.apnawallet.myapplication.util.AppUtil.formatTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(private val productRepository: ProductRepositoryInterface) : ViewModel() {

    private val _products = MutableLiveData<List<Product>?>()
    val products: MutableLiveData<List<Product>?> get() = _products

    private val _lastRefreshTime = MutableLiveData<Long>()
    val lastRefreshTime: LiveData<Long> get() = _lastRefreshTime

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _lastRefreshTimeText = MutableLiveData<String>()
    val lastRefreshTimeText: LiveData<String>
        get() = _lastRefreshTimeText

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
                updateLastRefreshTimeText()
                isFirstFetch = false
            } catch (e: Exception) {
                Log.d("TAG", "fetchProducts:  Error in fetching products")
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun updateLastRefreshTimeText() {
        if(!isFirstFetch){
            val formattedTime = formatTime(_lastRefreshTime.value ?: 0L)
            _lastRefreshTimeText.value = "Last refreshed: $formattedTime"
        }
    }
}