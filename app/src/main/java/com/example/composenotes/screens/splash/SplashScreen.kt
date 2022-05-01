package com.example.composenotes.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composenotes.R
import com.example.composenotes.screens.utils.logoAnnotatedString
import com.example.composenotes.ui.theme.GoogleBlue
import com.example.composenotes.ui.theme.GoogleGreen
import com.example.composenotes.ui.theme.GoogleRed
import com.example.composenotes.ui.theme.GoogleYellow
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onSplashScreenEnded: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        LaunchedEffect(key1 = true, block = {
            delay(2000)
            onSplashScreenEnded()
        })
        Text(
            text = logoAnnotatedString, style = TextStyle(fontSize = 36.sp),
            modifier = Modifier.align(Alignment.Center)
        )
        Text(
            text = stringResource(R.string.powered_by_jetpack_compose),
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Preview(showBackground = true)
@Preview
@Composable
fun SplashScreen_Previews() {
    SplashScreen(onSplashScreenEnded = {})
}