package com.elapp.mantuapp.presentation.ui.note

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elapp.mantuapp.data.entity.Note
import com.elapp.mantuapp.data.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(private val noteRepository: NoteRepository): ViewModel() {

    fun addNewNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.addNewNote(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.updateNote(note)
        }
    }

    fun getAllNotes(): LiveData<List<Note>> {
        val noteList = MutableLiveData<List<Note>>()
        viewModelScope.launch(Dispatchers.IO) {
            noteList.postValue(noteRepository.getAllNotes())
        }
        return noteList
    }

    fun searchNote(keyword: String): LiveData<List<Note>> {
        val noteList = MutableLiveData<List<Note>>()
        viewModelScope.launch(Dispatchers.IO) {
            noteList.postValue(noteRepository.searchNote(keyword))
        }
        return noteList
    }

    fun deleteNote(noteId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            noteRepository.deleteNote(noteId)
        }
    }

}