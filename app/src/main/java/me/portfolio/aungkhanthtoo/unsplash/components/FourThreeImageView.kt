package me.portfolio.aungkhanthtoo.unsplash.components

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView

class FourThreeImageView : android.support.v7.widget.AppCompatImageView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val fourThreeHeight = MeasureSpec.getSize(widthMeasureSpec) * 4 / 3
        val fourThreeHeightSpec =
                MeasureSpec.makeMeasureSpec(fourThreeHeight, MeasureSpec.EXACTLY)
        super.onMeasure(widthMeasureSpec, fourThreeHeightSpec)
    }
}