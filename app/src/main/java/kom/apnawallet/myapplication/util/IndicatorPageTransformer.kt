package kom.apnawallet.myapplication.util

import android.view.View
import androidx.viewpager.widget.ViewPager

class IndicatorPageTransformer: ViewPager.PageTransformer{

    override fun transformPage(page: View, position: Float) {
        page.alpha = 1 - Math.abs(position)
        page.scaleY = 0.85f + (1 - Math.abs(position)) * 0.15f
    }
}