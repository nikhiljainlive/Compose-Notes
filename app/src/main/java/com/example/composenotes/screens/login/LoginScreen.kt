package com.example.composenotes.screens.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.composenotes.R
import com.example.composenotes.utils.HexToJetpackColor

@Composable
fun LoginScreen(
    onRegisterClicked: () -> Unit,
    onLoginSuccessful: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(16.dp)
                .align(alignment = Center)
                .offset(y = (-50).dp)
        ) {
            val imageCardSize = 100.dp
            Card(
                modifier = Modifier
                    .size(imageCardSize)
                    .zIndex(1f)
                    .align(CenterHorizontally)
                    .offset(y = imageCardSize / 2),
                elevation = 4.dp,
                shape = RoundedCornerShape(16.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_user),
                    contentDescription = "image for login",
                    modifier = Modifier.background(
                        HexToJetpackColor.getColor("2B8BDF")
                    )
                )
            }
            Card(
                modifier = Modifier
                    .wrapContentSize(), elevation = 4.dp
            ) {
                Column(
                    horizontalAlignment = CenterHorizontally,
                    modifier = Modifier
                        .padding(32.dp)
                ) {
                    Spacer(modifier = Modifier.height(imageCardSize / 2))
                    Text(
                        text = stringResource(R.string.welcome_back),
                        style = TextStyle(fontFamily = FontFamily.SansSerif, fontSize = 18.sp)
                    )
                    Text(
                        text = stringResource(R.string.login_to_jetpack_compose),
                        style = TextStyle(fontFamily = FontFamily.SansSerif, fontSize = 18.sp)
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = email,
                        onValueChange = { email = it },
                        label = {
                            Text(text = stringResource(R.string.email))
                        },
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = password,
                        onValueChange = { password = it },
                        label = {
                            Text(text = stringResource(R.string.password))
                        },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation()
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(onClick = {
                        // TODO :: Log in
                        onLoginSuccessful()
                    }) {
                        Text(text = stringResource(R.string.login))
                    }
                    Spacer(modifier = Modifier.height(24.dp))
                    Row {
                        Text(
                            modifier = Modifier.align(CenterVertically),
                            text = stringResource(R.string.new_to_notes)
                        )
                        TextButton(
                            modifier = Modifier.align(CenterVertically),
                            onClick = { onRegisterClicked() }) {
                            Text(text = stringResource(R.string.register_here))
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview
@Composable
fun LoginScreen_Previews() {
    LoginScreen({}, {})
}