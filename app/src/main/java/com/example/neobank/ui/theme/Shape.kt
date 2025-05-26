package com.example.neobank.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

val AppShapes = Shapes(
    extraSmall = RoundedCornerShape(4.dp),
    small = RoundedCornerShape(8.dp),
    medium = RoundedCornerShape(12.dp), // Default for many components like cards
    large = RoundedCornerShape(16.dp),
    extraLarge = RoundedCornerShape(28.dp) // For larger elements like bottom sheets
)