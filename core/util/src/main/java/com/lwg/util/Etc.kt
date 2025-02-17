package com.lwg.util

import java.util.Locale

object Etc {

    fun convertDoubleToString(value: Double): String {
        return String.format(Locale.KOREA, "%.1f", value)
    }
}