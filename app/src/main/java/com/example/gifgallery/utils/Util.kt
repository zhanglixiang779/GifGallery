package com.example.gifgallery.utils

import android.content.Context
import android.content.res.Resources
import android.os.Build
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.example.gifgallery.R
import com.example.gifgallery.data.local.DbGif

val Int.DP: Int
    get() = (this / Resources.getSystem().displayMetrics.density).toInt()

val Int.PX: Int
    get() = (this * Resources.getSystem().displayMetrics.density).toInt()

val Context.screenWidth: Int
    get() {
        val metrics: DisplayMetrics = resources.displayMetrics
        return metrics.widthPixels
    }

@Composable
fun GifImage(
    url: String?,
    modifier: Modifier,
    contentScale: ContentScale = ContentScale.Crop,
    onSuccess: () -> Unit = {}
) {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            if (Build.VERSION.SDK_INT >= 28) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }
        .respectCacheHeaders(false)
        .build()

    AsyncImage(
        model = url,
        contentDescription = null,
        modifier = modifier,
        contentScale = contentScale,
        imageLoader = imageLoader,
        onSuccess = { onSuccess() },
    )
}

@Composable
fun ErrorToast(message: String?) {
    Toast.makeText(
        LocalContext.current,
        "${stringResource(R.string.error_message)}: $message",
        Toast.LENGTH_SHORT
    ).show()
}

@Composable
fun Gifs(
    gifs: List<DbGif>,
    navToDetail: (id: String?) -> Unit
) {
    LazyVerticalGrid(
        contentPadding = PaddingValues(vertical = 4.dp),
        columns = GridCells.Fixed(3)
    ) {
        items(
            items = gifs,
            key = { it.gifId.orEmpty() }
        ) { gif ->
            GifItem(gif, navToDetail)
        }
    }
}

@Composable
private fun GifItem(
    gif: DbGif,
    navToDetail: (id: String?) -> Unit
) {
    val context = LocalContext.current
    val height = Dp((context.screenWidth.DP.toFloat()))
    Column(
        modifier = Modifier.clickable {
            navToDetail(gif.gifId)
        }
    ) {
        GifImage(url = gif.url, modifier = Modifier
            .fillMaxWidth()
            .height(height / 3))
        Text(text = gif.title.orEmpty())
    }
}
