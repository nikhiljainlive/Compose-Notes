package com.example.composenotes.utils

import androidx.compose.ui.graphics.Color

object HexToJetpackColor {
    /**
    * convert hex color to Compose color
    * */
    fun getColor(colorString: String): Color {
        return Color(android.graphics.Color.parseColor("#$colorString"))
    }
}