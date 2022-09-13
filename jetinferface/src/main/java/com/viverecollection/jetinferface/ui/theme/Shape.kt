package com.viverecollection.jetinferface.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.viverecollection.jetinferface.ui.component.twentyDp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)
val ScrimColor = Color.Black.copy(alpha = 0.32f)
val BottomSheetShape = RoundedCornerShape(topStart = twentyDp, topEnd = twentyDp)
