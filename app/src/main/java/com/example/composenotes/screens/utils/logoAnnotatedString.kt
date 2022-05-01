package com.example.composenotes.screens.utils

import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import com.example.composenotes.ui.theme.GoogleBlue
import com.example.composenotes.ui.theme.GoogleGreen
import com.example.composenotes.ui.theme.GoogleRed
import com.example.composenotes.ui.theme.GoogleYellow

val logoAnnotatedString = buildAnnotatedString {
    // this doesn't require localization
    pushStyle(SpanStyle(color = GoogleBlue))
    append("C")
    pushStyle(SpanStyle(color = GoogleRed))
    append("o")
    pushStyle(SpanStyle(color = GoogleYellow))
    append("m")
    pushStyle(SpanStyle(color = GoogleBlue))
    append("p")
    pushStyle(SpanStyle(color = GoogleGreen))
    append("o")
    pushStyle(SpanStyle(color = GoogleRed))
    append("s")
    pushStyle(SpanStyle(color = GoogleYellow))
    append("e")
    append(" ")
    pushStyle(SpanStyle(color = GoogleBlue))
    append("Notes")
}