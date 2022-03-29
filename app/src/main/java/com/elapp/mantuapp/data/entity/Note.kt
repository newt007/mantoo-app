package com.elapp.mantuapp.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "tbl_note")
@Parcelize
data class Note(
    @ColumnInfo(name = "note_id")
    @PrimaryKey(autoGenerate = true)
    val noteId : Int = 0,
    @ColumnInfo(name = "note_title")
    val noteTitle: String,
    @ColumnInfo(name = "note_description")
    val noteDescription: String,
    @ColumnInfo(name = "note_pinned")
    val notePinned: Boolean = false
): Parcelable
