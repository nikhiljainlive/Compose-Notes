package com.example.composenotes.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composenotes.R
import com.example.composenotes.screens.home.archive.ArchiveScreen
import com.example.composenotes.screens.home.deleted.DeletedScreen
import com.example.composenotes.screens.home.notes.NotesScreen
import com.example.composenotes.screens.home.reminders.RemindersScreen
import com.example.composenotes.screens.utils.ScreenConstants
import com.example.composenotes.screens.utils.logoAnnotatedString
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(
    scaffoldState: ScaffoldState = rememberScaffoldState(
        drawerState = rememberDrawerState(
            initialValue = DrawerValue.Closed
        )
    )
) {
    val navHostController = rememberNavController()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.home))
                },
                navigationIcon = {
                    IconButton(onClick = {
                        coroutineScope.launch {
                            scaffoldState.drawerState.open()
                        }
                    }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = "Menu")
                    }
                }
            )
        },
        drawerContent = {
            DrawerContent(
                scaffoldState = scaffoldState,
                navHostController = navHostController
            )
        }
    ) {
        DrawerNavigation(navHostController)
    }
}

@Composable
private fun DrawerContent(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navHostController: NavHostController = rememberNavController()
) {
    val currentBackStackEntry by navHostController.currentBackStackEntryAsState()
    DrawerHeader()
    LazyColumn(
        content = {
            items(DrawerItem.values()) { item ->
                DrawerItemView(
                    scaffoldState = scaffoldState,
                    navHostController = navHostController,
                    item = item,
                    isSelected = currentBackStackEntry?.destination?.route == item.route
                )
            }
        })
}

@Composable
private fun DrawerHeader() {
    Box(modifier = Modifier.padding(16.dp)) {
        Text(
            text = logoAnnotatedString, style = TextStyle(fontSize = 22.sp),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
private fun DrawerItemView(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navHostController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    item: DrawerItem,
    isSelected: Boolean = false
) {
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .clip(
                RoundedCornerShape(
                    topEndPercent = 50,
                    bottomEndPercent = 50,
                )
            )
            .background(color = if (isSelected) Color.LightGray else Color.Transparent)
            .clickable(
                onClick = {
                    navHostController.navigate(item.route)
                    coroutineScope.launch {
                        scaffoldState.drawerState.close()
                    }
                }, interactionSource = MutableInteractionSource(),
                indication = rememberRipple(bounded = true)
            )
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            )
            .fillMaxWidth()
    ) {
        Icon(
            painter = painterResource(id = item.icon),
            contentDescription = stringResource(id = item.text)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = stringResource(id = item.text))
    }
}

@Composable
private fun DrawerNavigation(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = ScreenConstants.NOTES) {
        composable(ScreenConstants.NOTES) { NotesScreen() }
        composable(ScreenConstants.REMINDERS) { RemindersScreen() }
        composable(ScreenConstants.ARCHIVE) { ArchiveScreen() }
        composable(ScreenConstants.DELETED) { DeletedScreen() }
    }
}

// Previews

@Preview
@Composable
fun HomeScreen_Previews() {
    HomeScreen()
}

@Preview
@Composable
fun DrawerContent_Previews() {
    Column {
        DrawerContent()
    }
}

@Preview
@Composable
fun DrawerItemView_Previews() {
    Column {
        DrawerItemView(item = DrawerItem.Notes, isSelected = true)
        DrawerItemView(item = DrawerItem.Reminder, isSelected = false)
    }
}