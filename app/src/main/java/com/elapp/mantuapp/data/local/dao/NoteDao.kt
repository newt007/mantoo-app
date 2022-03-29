package com.elapp.mantuapp.data.local.dao

import androidx.room.*
import com.elapp.mantuapp.data.entity.Note

@Dao
interface NoteDao {

    @Insert
    suspend fun insertNewNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("SELECT * FROM tbl_note")
    suspend fun getAllNotes(): List<Note>

    @Query("DELETE FROM tbl_note WHERE note_id=:noteId")
    suspend fun deleteNote(noteId: Int)

    @Query("SELECT * FROM tbl_note WHERE note_title LIKE '%' || :keyword || '%'")
    suspend fun searchNote(keyword: String): List<Note>

}