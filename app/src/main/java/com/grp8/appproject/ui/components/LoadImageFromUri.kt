import android.media.Image
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import coil.compose.rememberImagePainter



@Composable
fun loadImageFromUri(
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
    val painter = rememberImagePainter(
        data = url,
        builder = {
            placeholderResId?.let { placeholder(it) }
            errorResId?.let { error(it) }
            crossfade(crossfadeDuration)
        }
    )

    return Image(
        painter = painter,
        contentDescription = contentDescription,
        modifier = modifier,
        contentScale = contentScale,
        alignment = Alignment.Center,
        alpha = 1f, // Set alpha to 1f for full opacity
        colorFilter = null, // No color filter
    )
}
