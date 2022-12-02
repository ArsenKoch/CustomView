package com.example.customview

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

class BottomButtonsView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    constructor(context: Context,attrs: AttributeSet?,defStyleAttr: Int) : this(context,attrs,defStyleAttr, defStyleRes = 0)
    constructor(context: Context,attrs: AttributeSet?) : this(context,attrs,0)
    constructor(context: Context) : this(context,null)
}