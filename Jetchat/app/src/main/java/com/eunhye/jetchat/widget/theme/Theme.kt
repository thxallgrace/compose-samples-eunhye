package com.eunhye.jetchat.widget.theme

import androidx.glance.material3.ColorProviders
import com.eunhye.jetchat.theme.JetchatDarkColorScheme
import com.eunhye.jetchat.theme.JetchatLightColorScheme

object JetchatGlanceColorScheme {
    val colors = ColorProviders(
        light = JetchatLightColorScheme,
        dark = JetchatDarkColorScheme,
    )
}
