package com.perullheim.homework.presentation.equipment.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.perullheim.homework.domain.model.Equipment

@Composable
fun EquipmentItem(
    modifier: Modifier = Modifier,
    equipment: Equipment
) {
    Column {
        ListItem(
            headlineContent = { Text(equipment.name) },
            leadingContent = { EquipmentChildrenAnnotation(count = equipment.parentAmount) },
            modifier = modifier
                .fillMaxWidth()
        )
        HorizontalDivider(
            color = Color.LightGray,
            modifier = Modifier
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
private fun EquipmentChildrenAnnotation(
    modifier: Modifier = Modifier,
    count: Int
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        repeat(count.coerceIn(0, 4)) {
            Ball(
                color = Color.Red,
                size = 8.dp,
                modifier = Modifier
                    .padding(horizontal = 1.dp)
            )
        }
    }
}

@Composable
private fun Ball(
    modifier: Modifier = Modifier,
    color: Color,
    size: Dp
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color)
    )

}

@Preview
@Composable
private fun EquipmentItemPreview() {
    val emptyEquipment = Equipment(
        id = "",
        name = "Heavy Excavator",
        nameDe = "Heavy Excavator",
        createdAt = "",
        parentAmount = 0
    )

    EquipmentItem(equipment = emptyEquipment)
}

@Preview
@Composable
private fun EquipmentChildrenAnnotationPreview() {
    EquipmentChildrenAnnotation(count = 4)
}

@Preview
@Composable
private fun BallPreview() {
    Ball(
        color = Color.Red,
        size = 8.dp
    )
}