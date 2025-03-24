package com.banco.cuscatlan.pokemoncuscatlan.ui.screens



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.Composable


import coil.compose.AsyncImage
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.banco.cuscatlan.pokemoncuscatlan.R
import coil.request.ImageRequest
import coil.size.Size
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onTimeout: () -> Unit) {
    LaunchedEffect(true) {
        delay(3000) // Duración del splash
        onTimeout()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(R.raw.loading)
                .decoderFactory(
                    if (android.os.Build.VERSION.SDK_INT >= 28)
                        ImageDecoderDecoder.Factory()
                    else
                        GifDecoder.Factory()
                )
                .size(Size.ORIGINAL)
                .build(),
            contentDescription = "Logo animado",
            modifier = Modifier.size(200.dp)
        )
    }
}