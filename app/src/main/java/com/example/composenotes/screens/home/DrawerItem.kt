package com.example.composenotes.screens.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.composenotes.R
import com.example.composenotes.screens.utils.ScreenConstants

sealed class DrawerItem(
    @StringRes val text: Int, @DrawableRes val icon: Int, val route: String
) {
    object Notes : DrawerItem(
        text = R.string.notes,
        icon = R.drawable.ic_light_bulb,
        route = ScreenConstants.NOTES
    )

    object Reminder : DrawerItem(
        text = R.string.reminders,
        icon = R.drawable.ic_reminders,
        route = ScreenConstants.REMINDERS
    )

    object Archive : DrawerItem(
        text = R.string.archive,
        icon = R.drawable.ic_archive,
        route = ScreenConstants.ARCHIVE
    )

    object Deleted : DrawerItem(
        text = R.string.deleted,
        icon = R.drawable.ic_deleted,
        route = ScreenConstants.DELETED
    )

    companion object {
        fun values() = listOf(Notes, Reminder, Archive, Deleted)
    }
}