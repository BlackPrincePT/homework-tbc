package com.perullheim.homework.model

import androidx.annotation.ColorRes
import com.perullheim.homework.R

enum class OrderStatus(@ColorRes val colorResId: Int) {
    PENDING(R.color.pending),
    DELIVERED(R.color.delivered),
    CANCELED(R.color.canceled);
}