import android.content.Context
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.grp8.appproject.R


@Composable
fun loadImageFromUri(
    context: Context,
    url: String?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
    placeholderResId: Int? = null,
    errorResId: Int? = null,
    fadeIn: FiniteAnimationSpec<Float> = tween(),
    crossfadeDuration: Int = 300,
    size: Dp? = null
) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = url)
            .apply(block = fun ImageRequest.Builder.() {
                placeholderResId?.let { placeholder(it) }
                errorResId?.let { error(it) }
                crossfade(crossfadeDuration)
            }).build()
    )

    return Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        alignment = Alignment.Center,
        alpha = 1f,
        colorFilter = null,
    )
}

@Preview
@Composable
fun ImagePreview() {
    loadImageFromUri(
        context = LocalContext.current,
        url = "https://firebasestorage.googleapis.com/v0/b/appprojectgrp8-9f8c8.appspot.com/o/Screwdriver.jpg?alt=media&token=cf9e37a4-7b3e-4c87-ad99-11ff43cf5b6e",
        contentDescription = "Image preview",
        modifier = Modifier.fillMaxSize(),
        placeholderResId = R.drawable.profilepicture, // Optional: Placeholder resource ID while image is loading
        errorResId = R.drawable.profilepicture // Optional: Error placeholder resource ID if image fails to load
    )
}