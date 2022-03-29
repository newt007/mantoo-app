package com.elapp.mantuapp.presentation.ui.note.listener

import com.elapp.mantuapp.data.entity.Note

interface NoteItemListener {

    fun onItemClicked(note: Note)

}