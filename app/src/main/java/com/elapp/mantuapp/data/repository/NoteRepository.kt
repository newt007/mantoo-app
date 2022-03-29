package com.elapp.mantuapp.data.repository

import com.elapp.mantuapp.data.entity.Note
import com.elapp.mantuapp.data.source.NoteDataSource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteRepository @Inject constructor(private val noteDataSource: NoteDataSource) {

    suspend fun addNewNote(note: Note) = noteDataSource.addNewNote(note)

    suspend fun getAllNotes(): List<Note> = noteDataSource.getAllNotes()

    suspend fun deleteNote(noteId: Int) = noteDataSource.deleteNote(noteId)

    suspend fun searchNote(keyword: String) = noteDataSource.searchNote(keyword)

    suspend fun updateNote(note: Note) = noteDataSource.updateNote(note)

}