package rustam.urazov.shop.view

import android.animation.AnimatorSet
import android.animation.ObjectAnimator.ofInt
import rustam.urazov.shop.R.styleable.*
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.OvershootInterpolator
import androidx.annotation.Keep
import androidx.interpolator.view.animation.FastOutLinearInInterpolator
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationMenuView

class BottomNavigationViewIndicator @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val targetId: Int
    private var target: BottomNavigationMenuView? = null

    private var rect = Rect()
    private val backgroundDrawable: Drawable

    private var index = 0
    private var animator: AnimatorSet? = null

    init {
        if (attrs == null) {
            targetId = NO_ID
            backgroundDrawable = ColorDrawable(Color.TRANSPARENT)
        } else {
            with(context.obtainStyledAttributes(attrs, BottomNavigationViewIndicator)) {
                targetId = getResourceId(BottomNavigationViewIndicator_targetBottomNavigation, NO_ID)
                val clipableId = getResourceId(BottomNavigationViewIndicator_clipableBackground, NO_ID)
                backgroundDrawable = if (clipableId != NO_ID) {
                    getDrawable(context, clipableId) ?: ColorDrawable(Color.TRANSPARENT)
                } else {
                    ColorDrawable(getColor(BottomNavigationViewIndicator_clipableBackground, Color.TRANSPARENT))
                }
                recycle()
            }
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        if (targetId == NO_ID) return attachError("Invalid target id $targetId, did you set the app:targetBottomNavigation attribute?")
        val parentView = parent as? View ?: return attachError("Impossible to find the view using $parent")
        val child = parentView.findViewById<View?>(targetId)
        if (child !is MainBottomNavigationView) return attachError("Invalid view $child, the app:targetBottomNavigation has to be n ListenableBottomNavigationView")

        for (i in 0 until child.childCount) {
            val subView = child.getChildAt(i)
            if (subView is BottomNavigationMenuView) target = subView
        }
        elevation = child.elevation
        child.addOnNavigationItemSelectedListener { updateRectByIndex(it, true) }
        post { updateRectByIndex(index, false) }
    }

    private fun attachError(message: String) {
        Log.e("BottomNavigationViewIndicator", message)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        target = null
    }

    private fun updateRectByIndex(index: Int, animated: Boolean) {
        this.index = index
        target?.apply {
            if (childCount < 1 || index >= childCount) return
            val reference = getChildAt(index)

            val start = reference.left + left
            val end = reference.right + left

            backgroundDrawable.setBounds(left, top, right, bottom)
            val newRect = Rect(start, 0, end, height)
            if (animated) startUpdateRectAnimation(newRect) else updateRect(newRect)
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.clipRect(rect)
        backgroundDrawable.draw(canvas)
    }

    private fun startUpdateRectAnimation(rect: Rect) {
        val toLeft = rect.left < this.rect.left
        animator?.cancel()
        animator = AnimatorSet().also {
            it.playTogether(
                ofInt(this, "rectLeft", this.rect.left, rect.left).apply {
                    interpolator = if (toLeft) OvershootInterpolator() else LinearOutSlowInInterpolator()
                },
                ofInt(this, "rectRight", this.rect.right, rect.right).apply {
                    interpolator = if (toLeft) LinearOutSlowInInterpolator() else OvershootInterpolator()
                },
                ofInt(this, "rectTop", this.rect.top, rect.top),
                ofInt(this, "rectBottom", this.rect.bottom, rect.bottom)
            )
            it.interpolator = FastOutSlowInInterpolator()
            it.duration = resources.getInteger(android.R.integer.config_shortAnimTime).toLong()
            it.start()
        }
    }

    private fun updateRect(rect: Rect) {
        this.rect = rect
        postInvalidate()
    }

    @Keep fun setRectLeft(left: Int) = updateRect(rect.apply { this.left = left })
    @Keep fun setRectRight(right: Int) = updateRect(rect.apply { this.right = right })
    @Keep fun setRectTop(top: Int) = updateRect(rect.apply { this.top = top })
    @Keep fun setRectBottom(bottom: Int) = updateRect(rect.apply { this.bottom = bottom })
}