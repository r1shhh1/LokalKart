package kom.apnawallet.myapplication.adapters

import android.content.Context
import android.media.Image
import android.text.Layout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import kom.apnawallet.myapplication.R

class ProductSliderAdapter(private val context: Context, private val images: List<String>): PagerAdapter() {
    override fun getCount(): Int {
        return images.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemView = inflater.inflate(R.layout.slide_image, container, false)

        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        Glide.with(context)
            .load(images[position])
            .into(imageView)

        Log.d("ProductSliderAdapter", "Image loaded at position: $position - ${images[position]}")

        container.addView(itemView)

        return itemView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)

        Log.d("ProductSliderAdapter", "destroyItem called for position: $position")
    }
}