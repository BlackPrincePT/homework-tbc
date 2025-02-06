package com.perullheim.homework.domain.model

import com.perullheim.homework.R

enum class NumpadKey(val value: Int) {
    ONE(value = 1),
    TWO(value = 2),
    THREE(value = 3),
    FOUR(value = 4),
    FIVE(value = 5),
    SIX(value = 6),
    SEVEN(value = 7),
    EIGHT(value = 8),
    NINE(value = 9),
    TOUCH_ID(value = R.drawable.ic_touch_id),
    ZERO(value = 0),
    BACKSPACE(value = R.drawable.ic_backspace);

    val isDrawable: Boolean
        get() = this == TOUCH_ID || this == BACKSPACE
}