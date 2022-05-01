package com.example.composenotes.screens.home.notes

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composenotes.R

@Composable
fun NotesScreen(
    pinnedNotes: List<Note> = emptyList(),
    notes: List<Note> = emptyList(),
    onPinnedNoteClick: (Int, Note) -> Unit = { _, _ -> },
    onNoteClick: (Int, Note) -> Unit = { _, _ -> }
) {
    if (pinnedNotes.isNotEmpty() or notes.isNotEmpty()) {
        LazyColumn(
            modifier = Modifier.padding(horizontal = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (pinnedNotes.isNotEmpty()) {
                item {
                    Text(modifier = Modifier.padding(8.dp), text = stringResource(R.string.pinned))
                }
                itemsIndexed(pinnedNotes) { index, item ->
                    NoteItemView(item, onItemClick = {
                        onPinnedNoteClick(index, item)
                    })
                }
                item {
                    Text(modifier = Modifier.padding(8.dp), text = stringResource(R.string.others))
                }
            }
            if (notes.isNotEmpty()) {
                itemsIndexed(notes) { index, item ->
                    NoteItemView(item, onItemClick = {
                        onNoteClick(index, item)
                    })
                }
                item { Spacer(modifier = Modifier.height(8.dp)) }
            }
        }
        return
    }
    // TODO:: change implementation
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Notes")
    }
}

@Composable
fun NoteItemView(note: Note, onItemClick: () -> Unit) {
    val shape = MaterialTheme.shapes.medium
    Card(
        modifier = Modifier
            .shadow(elevation = 1.dp, shape = shape)
            .clip(shape = shape)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true)
            ) { onItemClick() },
        shape = shape
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(text = note.title, style = TextStyle(fontWeight = FontWeight.Bold))
            Text(text = note.description)
        }
    }
}

// Previews

@Preview
@Composable
fun NotesScreen_Previews() {
    NotesScreen(pinnedNotes = items, notes = items)
}

@Preview
@Composable
fun NoteItemView_Previews() {
    val context = LocalContext.current
    NoteItemView(
        note = Note(title = "Hello", description = "How are you?"),
        onItemClick = {
            Toast.makeText(context, "Clicked!", Toast.LENGTH_SHORT).show()
        }
    )
}

data class Note(
    var title: String,
    var description: String
)

private val items = listOf(
    Note("Hello", "How are you?"),
    Note("Hello", "How are you?"),
    Note("Hello", "How are you?"),
    Note("Hello", "How are you?"),
    Note("Hello", "How are you?"),
    Note("Hello", "How are you?"),
    Note("Hello", "How are you?"),
    Note("Hello", "How are you?"),
    Note("Hello", "How are you?"),
    Note("Hello", "How are you?")
)