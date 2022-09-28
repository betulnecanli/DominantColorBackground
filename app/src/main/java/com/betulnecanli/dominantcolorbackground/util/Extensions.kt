package com.betulnecanli.dominantcolorbackground.util

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.View

fun View.linearGradientBackground(dominantColor : Int): GradientDrawable{

    return GradientDrawable().apply {
        colors = intArrayOf(
            dominantColor,
            Color.parseColor("#2E2929"),
            Color.parseColor("#171616")
        )
        gradientType = GradientDrawable.LINEAR_GRADIENT
        orientation = GradientDrawable.Orientation.TOP_BOTTOM


    }


}