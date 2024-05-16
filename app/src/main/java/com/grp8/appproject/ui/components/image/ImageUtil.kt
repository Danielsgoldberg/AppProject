package com.grp8.appproject.ui.components.image

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

@Composable
fun bitmapFromResource(resId: Int): Bitmap? {
    val context = LocalContext.current
    val bitmapDrawable = ContextCompat.getDrawable(context, resId)
    bitmapDrawable?.let {
        return BitmapFactory.decodeResource(context.resources, resId)
    }
    return null
}