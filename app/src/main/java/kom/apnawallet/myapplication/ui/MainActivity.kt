package kom.apnawallet.myapplication.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.facebook.shimmer.ShimmerFrameLayout
import kom.apnawallet.myapplication.R
import kom.apnawallet.myapplication.adapters.ProductsAdapter
import kom.apnawallet.myapplication.databinding.ActivityMainBinding
import kom.apnawallet.myapplication.model.Product
import kom.apnawallet.myapplication.networking.ApiService
import kom.apnawallet.myapplication.networking.RetroInstance
import kom.apnawallet.myapplication.repository.ProductRepository
import kom.apnawallet.myapplication.util.AppUtil.formatTime
import kom.apnawallet.myapplication.viewModel.MainViewModel
import kom.apnawallet.myapplication.viewModel.MainViewModelFactory
import retrofit2.HttpException
import java.io.IOException
import java.util.Timer
import java.util.TimerTask

const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    lateinit var shimmerFrameLayout: ShimmerFrameLayout

    lateinit var dataLayout: LinearLayout

    lateinit var productsAdapter: ProductsAdapter

    lateinit var retroInstance: RetroInstance

    lateinit var productRepository: ProductRepository

    private lateinit var mainActivityViewModel: MainViewModel

    var lastRef: Long = 0L

    private var refreshTimer: Timer? = null

    private var refreshTimer2: Timer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.timer.isVisible = false
        shimmerFrameLayout = findViewById(R.id.shimmerView)
        dataLayout = findViewById(R.id.data_layout);

        // Initialize the ProductRepository with the ApiService instance
        retroInstance = RetroInstance
        val apiService = retroInstance.getInstance().create(ApiService::class.java)
        productRepository = ProductRepository(apiService)

        //initialize viewModel
        mainActivityViewModel = ViewModelProvider(this, MainViewModelFactory(productRepository)).get(MainViewModel::class.java)

        setUpObservers()

        fetchProducts()

        binding.swipeRefreshLayout.setOnRefreshListener {
            onSwipeRefresh()
        }

        //Timer to update lastRefresh
        refreshTimer2 = Timer()
        refreshTimer2?.schedule(object: TimerTask() {
            override fun run() {
                runOnUiThread{
                    updateLastRefreshTimeText()
                }
            }
        }, 1000, 1000)

        //Timer to schedule refresh
        refreshTimer = Timer()
        refreshTimer?.schedule(object: TimerTask() {
            override fun run() {
                runOnUiThread{
                    fetchProducts()
                }
            }
        }, 3 * 60 * 1000L, 3 * 60 * 1000L)
    }

    //logic for setting up the rv
    fun setUpRecycleView(products: List<Product>) = binding.rvProducts.apply {
        val onItemClick: (Product) -> Unit = { selectedProduct ->
            val intent = Intent(this@MainActivity , ProductDetailActivity::class.java)
            intent.putExtra("PRODUCT_DETAILS", selectedProduct)
            startActivity(intent)
        }


        productsAdapter = ProductsAdapter(products, onItemClick)
        adapter = productsAdapter
        layoutManager = LinearLayoutManager(this@MainActivity)

    }

    private fun setUpObservers() {
        mainActivityViewModel.products.observe(this) { products ->
            products?.let {
                setUpRecycleView(it)
            }
        }

        mainActivityViewModel.lastRefreshTime.observe(this) { time ->
            time?.let {
                lastRef = it
                updateLastRefreshTimeText()
            }
        }

        mainActivityViewModel.isLoading.observe(this) { isLoading ->
            isLoading?.let {
                handleLoadingState(it)
            }
        }
    }

    private fun handleLoadingState(isLoading: Boolean) {
        if (isLoading) {
            shimmerFrameLayout.isVisible = true
            shimmerFrameLayout.startShimmer()
            dataLayout.isVisible = false
        } else {
            shimmerFrameLayout.stopShimmer()
            shimmerFrameLayout.isVisible = false
            dataLayout.isVisible = true
        }
    }

    private fun onSwipeRefresh(){
        fetchProducts()
        binding.swipeRefreshLayout.isRefreshing = false
    }

    //logic for fetching the products list and setting up the rv
    private fun fetchProducts() {
        mainActivityViewModel.fetchProducts()
    }

    fun updateLastRefreshTimeText() {
        val formattedTime = formatTime(lastRef) // Define a formatting function
        binding.timer.text = "Last refreshed: $formattedTime"
    }

}


