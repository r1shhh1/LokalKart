package kom.apnawallet.myapplication.productList.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import kom.apnawallet.myapplication.R
import kom.apnawallet.myapplication.databinding.FragmentProductListBinding
import kom.apnawallet.myapplication.productList.domain.model.Product
import kom.apnawallet.myapplication.productList.presentation.adapters.ProductListAdapter
import kom.apnawallet.myapplication.productList.presentation.ProductListViewModel
import kom.apnawallet.myapplication.productList.presentation.activity.MainActivity

@AndroidEntryPoint
class ProductListFragment: Fragment() {

    private var _binding: FragmentProductListBinding? = null
    private val binding
        get() = _binding!!

    val productListViewModel: ProductListViewModel by viewModels()

    private lateinit var productListAdapter: ProductListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProductListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        productListAdapter = ProductListAdapter()

        val recyclerView: RecyclerView = view.findViewById(R.id.rv)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productListAdapter
        }

        attachObserver()

        productListAdapter.onItemClick = { product: Product ->
            (requireActivity() as MainActivity).navigateToProductDetail(product)
        }
    }

    private fun attachObserver() {

        lifecycleScope.launchWhenStarted {
            productListViewModel.productListState.collect {
                productListAdapter.setProducts(it.productList)
            }
        }

    }
}