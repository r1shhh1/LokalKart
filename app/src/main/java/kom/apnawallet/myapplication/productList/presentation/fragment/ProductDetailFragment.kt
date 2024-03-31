package kom.apnawallet.myapplication.productList.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import dagger.hilt.android.AndroidEntryPoint
import kom.apnawallet.myapplication.productList.presentation.adapters.ProductSliderAdapter
import kom.apnawallet.myapplication.databinding.FragmentProductDetailBinding
import kom.apnawallet.myapplication.productList.domain.model.Product

@AndroidEntryPoint
class ProductDetailFragment(val product: Product) : Fragment() {

    lateinit var binding: FragmentProductDetailBinding
    private lateinit var productSliderAdapter: ProductSliderAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        product.apply {
            setValues(product)
            setViewPager(product)
        }
    }

    private fun setValues(product: Product) {
        binding.catTv.text = "Category: ${product.category}"
        binding.ViewMoreTv.text = "View more from ${product.brand}"
        binding.nameTv.text = "${product.brand} ${product.title}"
        binding.discount.text = "${product.discountPercentage}% off"
        binding.price.text = "₹${product.price}"
        binding.stock.text = "${product.stock} in stock"
        binding.desc.text = "${product.description}"
        binding.ratingBar.rating = product.rating

    }

    private fun setViewPager(product: Product) {
        val productSliderAdapter = ProductSliderAdapter(requireContext(), product.images)
        binding.viewPager.adapter = productSliderAdapter
    }

}