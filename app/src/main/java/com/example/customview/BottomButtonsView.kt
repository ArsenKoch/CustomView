package com.example.customview

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.customview.databinding.PartButtonsBinding

enum class BottomButtonActions {
    POSITIVE, NEGATIVE
}

typealias OnBottomButtonsActionListener = (BottomButtonActions) -> Unit

class BottomButtonsView(
    context: Context,
    attrs: AttributeSet?,
    defStyleAttr: Int,
    defStyleRes: Int
) : ConstraintLayout(context, attrs, defStyleAttr, defStyleRes) {

    private var binding: PartButtonsBinding

    private var isProgressMode: Boolean = false
        set(value) {
            field = value
            if (value) {
                binding.buttonPositive.visibility = INVISIBLE
                binding.buttonNegative.visibility = INVISIBLE
                binding.progressBar.visibility = VISIBLE
            } else {
                binding.buttonPositive.visibility = VISIBLE
                binding.buttonNegative.visibility = VISIBLE
                binding.progressBar.visibility = GONE
            }
        }

    private var listener: OnBottomButtonsActionListener? = null

    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.part_buttons, this, true)
        binding = PartButtonsBinding.bind(this)
        initialize(attrs, defStyleAttr, defStyleRes)
        initListener()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : this(
        context, attrs, defStyleAttr, defStyleRes = R.attr.bottomButtonStyle
    )

    constructor(context: Context, attrs: AttributeSet?) : this(
        context,
        attrs,
        R.style.MyBottomButtonStyle
    )

    constructor(context: Context) : this(context, null)

    private fun initialize(attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) {
        if (attrs == null) return
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.BottomButtonsView,
            defStyleAttr,
            defStyleRes
        )

        val positiveButtonText =
            typedArray.getString(R.styleable.BottomButtonsView_bottomPositiveButtonText)
        setPositiveButtonText(positiveButtonText)

        val negativeButtonText =
            typedArray.getString(R.styleable.BottomButtonsView_bottomNegativeButtonText)
        setNegativeButtonText(negativeButtonText)

        val positiveButtonColor = typedArray.getColor(
            R.styleable.BottomButtonsView_bottomPositiveButtonColor,
            Color.WHITE
        )
        binding.buttonPositive.backgroundTintList = ColorStateList.valueOf(positiveButtonColor)

        val negativeButtonColor = typedArray.getColor(
            R.styleable.BottomButtonsView_bottomNegativeButtonColor,
            Color.BLACK
        )
        binding.buttonNegative.backgroundTintList = ColorStateList.valueOf(negativeButtonColor)

        isProgressMode =
            typedArray.getBoolean(R.styleable.BottomButtonsView_bottomProgressMode, false)


        typedArray.recycle()
    }

    private fun initListener() {
        binding.buttonPositive.setOnClickListener {
            this.listener?.invoke(BottomButtonActions.POSITIVE)
        }
        binding.buttonNegative.setOnClickListener {
            this.listener?.invoke(BottomButtonActions.NEGATIVE)
        }
    }

    fun setListener(listener: OnBottomButtonsActionListener?) {
        this.listener = listener
    }

    fun setPositiveButtonText(text: String?) {
        binding.buttonPositive.text = text ?: "Ok"
    }

    fun setNegativeButtonText(text: String?) {
        binding.buttonNegative.text = text ?: "Cancel"
    }

    override fun onSaveInstanceState(): Parcelable {
        val superState = super.onSaveInstanceState()!!
        val savedState = SavedState(superState)
        savedState.positiveButtonText = binding.buttonPositive.text.toString()
        savedState.negativeButtonText = binding.buttonNegative.text.toString()
        return savedState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        val savedState = state as SavedState
        super.onRestoreInstanceState(savedState.superState)
        binding.buttonPositive.text = savedState.positiveButtonText
        binding.buttonNegative.text = savedState.negativeButtonText
    }

    class SavedState : BaseSavedState {

        var positiveButtonText: String? = null
        var negativeButtonText: String? = null

        constructor(superState: Parcelable) : super(superState)
        constructor(parcel: Parcel) : super(parcel) {
            positiveButtonText = parcel.readString()
            negativeButtonText = parcel.readString()
        }

        override fun writeToParcel(out: Parcel, flags: Int) {
            super.writeToParcel(out, flags)
            out.writeString(positiveButtonText)
            out.writeString(negativeButtonText)
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<SavedState> = object : Parcelable.Creator<SavedState> {
                override fun createFromParcel(p0: Parcel): SavedState {
                    return SavedState(p0)
                }

                override fun newArray(p0: Int): Array<SavedState?> {
                    return Array(p0) { null }
                }
            }
        }
    }
}