package com.perullheim.homework.view.form

import android.view.ViewGroup
import android.view.ViewGroup.MarginLayoutParams
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.core.view.allViews
import androidx.core.view.setPadding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.perullheim.homework.R
import com.perullheim.homework.helper.currentData
import com.perullheim.homework.model.Field

class FormAdapter :
    ListAdapter<ArrayList<Field>, FormAdapter.FieldGroupViewHolder>(FormDiffUtil()) {

    private interface FormDataListener {
        fun fetchData(): Map<Int, String>
        fun clearData()
    }

    private val listeners: MutableList<FormDataListener> = mutableListOf()

    fun onRegister() : Map<Int, String>? {
        val result = mutableMapOf<Int, String>()

        listeners.forEach { result.putAll(it.fetchData())  }

        if (result.values.any { it.isEmpty() })
            return null

        listeners.forEach { it.clearData() }

        return result
    }

    /* ############################################################# */
    /*                 ADAPTER FUNCTIONS STARTS HERE                 */
    /* ############################################################# */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FieldGroupViewHolder {
        return LinearLayout(parent.context).apply {
            orientation = LinearLayout.VERTICAL
            background =
                AppCompatResources.getDrawable(parent.context, R.drawable.white_rounded_background)
            layoutParams = MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            ).also { it.bottomMargin = 128 }
            setPadding(32)
        }.let { FieldGroupViewHolder(it) }.also { listeners.add(it) }
    }

    override fun onBindViewHolder(holder: FieldGroupViewHolder, position: Int) {
        holder.onBind()
    }

    private class FormDiffUtil : DiffUtil.ItemCallback<ArrayList<Field>>() {
        override fun areItemsTheSame(
            oldItem: ArrayList<Field>,
            newItem: ArrayList<Field>
        ): Boolean {
            return oldItem.size == newItem.size
        }

        override fun areContentsTheSame(
            oldItem: ArrayList<Field>,
            newItem: ArrayList<Field>
        ): Boolean {
            return oldItem == newItem
        }
    }

    /* ############################################################# */
    /*                    VIEW HOLDER STARTS HERE                    */
    /* ############################################################# */

    inner class FieldGroupViewHolder(private val layout: LinearLayout) :
        RecyclerView.ViewHolder(layout), FormDataListener {

        fun onBind() {
            layout.removeAllViews()

            getItem(adapterPosition).forEach { field ->
                when (field.type) {
                    Field.Type.INPUT -> {
                        val editText =
                            AppCompatEditText(layout.context).let { Field.getDefault(it, field) }
                        layout.addView(editText)
                    }

                    Field.Type.CHOOSER -> {
                        if (field.chooserType == null)
                            return

                        val spinner =
                            AppCompatSpinner(layout.context).let { Field.getDefault(it, field) }
                        layout.addView(spinner)
                    }
                }
            }
        }

        override fun fetchData(): Map<Int, String> {
            val result = mutableMapOf<Int, String>()

            layout.allViews.forEach {
                when (it) {
                    is AppCompatEditText -> result += it.currentData
                    is AppCompatSpinner -> result += it.currentData
                }
            }

            return result
        }

        override fun clearData() {
            layout.allViews.forEach {
                if (it is AppCompatEditText)
                    it.text = null
            }
        }
    }
}

