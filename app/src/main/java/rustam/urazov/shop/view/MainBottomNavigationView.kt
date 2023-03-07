package rustam.urazov.shop.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.MenuItem
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.doOnPreDraw
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import rustam.urazov.shop.R
import kotlin.math.abs

class MainBottomNavigationView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : BottomNavigationView(context, attrs, defStyleAttr), OnItemSelectedListener {

    private val onNavigationItemSelectedListeners = mutableListOf<OnItemSelectedListener>()

    init {
        super.setOnItemSelectedListener(this)
    }

    override fun setOnItemSelectedListener(listener: OnItemSelectedListener?) {
        super.setOnItemSelectedListener(listener)
        if (listener != null) addOnNavigationItemSelectedListener(listener)
    }

    fun addOnNavigationItemSelectedListener(listener: OnItemSelectedListener) {
        onNavigationItemSelectedListeners.add(listener)
    }

    fun addOnNavigationItemSelectedListener(listener: (Int) -> Unit) {
        addOnNavigationItemSelectedListener(OnItemSelectedListener {
            for (i in 0 until menu.size()) if (menu.getItem(i) == it) listener(i)
            false
        })
    }

    override fun onNavigationItemSelected(item: MenuItem) = onNavigationItemSelectedListeners
        .map { it.onNavigationItemSelected(item) }
        .fold(false) { acc, it -> acc || it }

}