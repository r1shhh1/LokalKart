package kom.apnawallet.myapplication.productList.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import dagger.hilt.android.AndroidEntryPoint
import kom.apnawallet.myapplication.R
import kom.apnawallet.myapplication.databinding.ActivityMainBinding
import kom.apnawallet.myapplication.productList.domain.model.Product
import kom.apnawallet.myapplication.productList.presentation.fragment.ProductDetailFragment
import kom.apnawallet.myapplication.productList.presentation.fragment.ProductListFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val fragment = ProductListFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.navHostFragment, fragment)
            .commit()

    }

    fun navigateToProductDetail(product: Product) {
        supportFragmentManager.commit {
            replace(R.id.navHostFragment, ProductDetailFragment(product))
            addToBackStack(null)
        }
    }

}


