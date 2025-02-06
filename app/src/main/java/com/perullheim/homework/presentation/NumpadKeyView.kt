package com.perullheim.homework.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.perullheim.homework.databinding.LayoutNumpadKeyBinding
import com.perullheim.homework.domain.model.NumpadKey

class NumpadKeyView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private val binding = LayoutNumpadKeyBinding.inflate(LayoutInflater.from(context), this, true)

    fun setIcon(type: NumpadKey) {
        with(binding) {
            if (!type.isDrawable) {
                tvNumber.text = type.value.toString()
            } else {
                tvNumber.visibility = View.GONE
                imgDrawable.visibility = View.VISIBLE

                imgDrawable.setImageResource(type.value)
            }
        }
    }

    fun setClickListener(block: View.() -> Unit) {
        this.setOnClickListener(block)
    }
}