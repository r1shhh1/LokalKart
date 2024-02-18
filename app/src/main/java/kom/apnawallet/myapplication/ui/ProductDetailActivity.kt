package kom.apnawallet.myapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import androidx.viewpager.widget.ViewPager
import kom.apnawallet.myapplication.util.IndicatorPageTransformer
import kom.apnawallet.myapplication.R
import kom.apnawallet.myapplication.adapters.ProductSliderAdapter
import kom.apnawallet.myapplication.databinding.ActivityProductDetailBinding
import kom.apnawallet.myapplication.model.Product

class ProductDetailActivity : AppCompatActivity() {

    private lateinit var binding: kom.apnawallet.myapplication.databinding.ActivityProductDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        //parsing data between activities
        val selectedProduct = intent.getSerializableExtra("PRODUCT_DETAILS") as Product


        //setting up image sliding view
        val images = selectedProduct.images
        val prodAdapter = ProductSliderAdapter(this, images)
        binding.viewPager.adapter = prodAdapter



        //setting up indicator transformer
        binding.viewPager.setPageTransformer(true, IndicatorPageTransformer())
        addIndicators(images.size)


        // Add a listener to update the indicators when the page changes
        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

            override fun onPageSelected(position: Int) {
                updateIndicators(position)
            }
            override fun onPageScrollStateChanged(state: Int) {}
        })



        //setting up ui
        binding.catTv.text = "Category: ${selectedProduct.category}"
        binding.ViewMoreTv.text = "View more from ${selectedProduct.brand}"
        binding.nameTv.text = "${selectedProduct.brand} ${selectedProduct.title}"
        binding.discount.text = "${selectedProduct.discountPercentage}% off"
        binding.price.text = "₹${selectedProduct.price}"
        binding.stock.text = "${selectedProduct.stock} in stock"
        binding.desc.text = "${selectedProduct.description}"
        binding.ratingBar.rating = selectedProduct.rating

        //initiates indicator while launch
        updateIndicators(0)
    }

    //updating the indicators
    private fun updateIndicators(position: Int) {
        for (i in 0 until binding.indicatorLayout.childCount) {
            val indicator = binding.indicatorLayout.getChildAt(i)
            indicator.setBackgroundResource(if (i == position) R.drawable.indicator_dot_selected else R.drawable.indicator_dot)
        }
    }

    //adding indicators
    private fun addIndicators(size: Int) {
        for (i in 0 until size) {
            val indicator = View(this)
            val layoutParams = LinearLayout.LayoutParams(20, 20) // Adjust size as needed
            layoutParams.setMargins(8, 0, 8, 0) // Adjust margins as needed
            indicator.layoutParams = layoutParams
            indicator.setBackgroundResource(R.drawable.indicator_dot)
            binding.indicatorLayout.addView(indicator)
        }
    }
}

