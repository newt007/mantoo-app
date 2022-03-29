package com.elapp.mantuapp.presentation.ui.note

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elapp.mantuapp.data.entity.Note
import com.elapp.mantuapp.databinding.NoteItemGridBinding
import com.elapp.mantuapp.presentation.ui.note.listener.NoteItemListener

class NoteAdapter(private val noteList: List<Note>) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private lateinit var noteItemListener: NoteItemListener

    fun setItemListener(listener: NoteItemListener) {
        this.noteItemListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteAdapter.NoteViewHolder {
        val binding = NoteItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteViewHolder, position: Int) {
        noteList[position].let { note ->
            holder.bind(note)
            holder.itemView.setOnClickListener {
                noteItemListener.onItemClicked(note)
            }
        }
    }

    override fun getItemCount(): Int = noteList.size

    inner class NoteViewHolder(private val binding: NoteItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(note: Note) {
                binding.apply {
                    tvNoteTitle.text = note.noteTitle
                    tvNoteDescription.text = note.noteDescription
                }
            }
    }

}