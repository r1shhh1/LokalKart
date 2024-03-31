package kom.apnawallet.myapplication.productList.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kom.apnawallet.myapplication.databinding.ProductItemBinding
import kom.apnawallet.myapplication.productList.domain.model.Product


//logic for the adapter used to populate the ProductList Recycler view
class ProductListAdapter():
    RecyclerView.Adapter<ProductListAdapter.ProductsViewHolder>() {

    private var products: List<Product> = emptyList()

    var onItemClick: ((Product) -> Unit)? = null

    inner class ProductsViewHolder(val binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val binding = ProductItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ProductsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.binding.apply {
            val product = products[position]
            pName.text = "${product.brand} ${product.title}"
            pCost.text = "₹${product.price}"
            pDisc.text = "${product.discountPercentage}%"
            ratingBar.rating = product.rating

            Glide.with(holder.itemView)
                .load(product.thumbnail)
                .into(pImage)

            root.setOnClickListener{
                onItemClick?.invoke(product)
            }
        }
    }

    fun setProducts(products: List<Product>){
        this.products = products
        notifyDataSetChanged()
    }

}