package com.elapp.mantuapp.data.source

import com.elapp.mantuapp.data.entity.Note
import com.elapp.mantuapp.data.local.dao.NoteDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteDataSource @Inject constructor(private val noteDao: NoteDao) {

    suspend fun addNewNote(note: Note) {
        val newNote = Note(
            noteTitle = note.noteTitle,
            noteDescription = note.noteDescription,
        )
        noteDao.insertNewNote(newNote)
    }

    suspend fun getAllNotes(): List<Note> = noteDao.getAllNotes()

    suspend fun deleteNote(noteId: Int) = noteDao.deleteNote(noteId)

    suspend fun searchNote(keyword: String) = noteDao.searchNote(keyword)

    suspend fun updateNote(note: Note) {
        val newNote = Note(
            noteTitle = note.noteTitle,
            noteDescription = note.noteDescription,
        )
        noteDao.updateNote(newNote)
    }

}