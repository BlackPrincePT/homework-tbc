package com.perullheim.homework.model

import android.text.InputType
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import com.perullheim.homework.R
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Field(
    @SerialName("field_id")
    val id: Int,
    val hint: String,
    @SerialName("field_type")
    val fieldType: String,
    val keyboard: String? = null,
    val required: Boolean,
    @SerialName("is_active")
    val isActive: Boolean,
    val icon: String
) {
    val type: Type
        get() = Type.entries.firstOrNull { it.name.lowercase() == fieldType } ?: Type.INPUT

    val inputType: Int
        get() = when (keyboard) {
            "number" -> InputType.TYPE_CLASS_NUMBER
            else -> InputType.TYPE_CLASS_TEXT
        }

    val chooserType: ChooserType?
        get() {
            if (type != Type.CHOOSER)
                return null

            return when (hint.lowercase()) {
                ChooserType.GENDER.name.lowercase() -> ChooserType.GENDER
                ChooserType.BIRTHDAY.name.lowercase() -> ChooserType.BIRTHDAY
                else -> null
            }
        }

    companion object {
        fun getDefault(editText: AppCompatEditText, field: Field): AppCompatEditText {
            return editText.apply {
                id = field.id
                hint = field.hint
                inputType = field.inputType
                isEnabled = field.isActive
                background = AppCompatResources.getDrawable(context, R.color.white)
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    128
                )
                setPadding(16, 0, 16, 0)
            }
        }

        fun getDefault(spinner: AppCompatSpinner, field: Field): AppCompatSpinner {
            val arrayAdapter = ArrayAdapter(
                spinner.context,
                android.R.layout.simple_spinner_item,
                field.chooserType?.getOptions() ?: emptyList()
            ).also { it.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) }

            return spinner.apply {
                id = field.id
                adapter = arrayAdapter
                isEnabled = field.isActive
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    128
                )
            }
        }
    }

    /* ############################################################## */
    /*                    ENUM CLASSES STARTS HERE                    */
    /* ############################################################## */

    // ========= Field-Specific ========= \\

    enum class Type {
        INPUT,
        CHOOSER
    }

    enum class ChooserType {
        GENDER,
        BIRTHDAY;

        fun getOptions(): List<String> {
            return when (this) {
                GENDER -> Gender.entries.map { it.name.lowercase() }
                BIRTHDAY -> Month.entries.map { it.name.lowercase() }
            }
        }
    }

    // ========= General ========= \\

    enum class Gender {
        MALE,
        FEMALE
    }

    enum class Month {
        JANUARY,
        FEBRUARY,
        MARCH,
        APRIL,
        MAY,
        JUNE,
        JULY,
        AUGUST,
        SEPTEMBER,
        OCTOBER,
        NOVEMBER,
        DECEMBER
    }
}
