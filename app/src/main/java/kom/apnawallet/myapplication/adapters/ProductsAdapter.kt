package kom.apnawallet.myapplication.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kom.apnawallet.myapplication.databinding.ProductItemBinding
import kom.apnawallet.myapplication.model.Product

class ProductsAdapter(
    private val products: List<Product>,
    private val onItemClick: (Product) -> Unit
):
    RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {

    inner class ProductsViewHolder(val binding: ProductItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(ProductItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
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
                onItemClick(product)
            }
        }
    }

}