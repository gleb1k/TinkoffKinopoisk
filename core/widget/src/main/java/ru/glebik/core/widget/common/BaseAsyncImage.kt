package ru.glebik.core.widget.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import kotlinx.coroutines.Dispatchers
import ru.glebik.core.widget.R

@Composable
fun BaseAsyncImage(
    modifier: Modifier = Modifier.fillMaxWidth(),
    data: String?,
    contentDescription: String = "Image",
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data)
            .crossfade(true)
            .dispatcher(Dispatchers.IO)
            .build(),
        placeholder = painterResource(id = R.drawable.placeholder),
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier,
        error = painterResource(id = R.drawable.placeholder)
    )
}