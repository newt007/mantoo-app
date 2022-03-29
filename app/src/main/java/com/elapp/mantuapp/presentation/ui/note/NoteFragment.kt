package com.elapp.mantuapp.presentation.ui.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.elapp.mantuapp.R
import com.elapp.mantuapp.data.entity.Note
import com.elapp.mantuapp.databinding.FragmentNoteBinding
import com.elapp.mantuapp.presentation.ui.note.listener.NoteItemListener
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class NoteFragment : Fragment(), NoteItemListener {

    private val noteViewModel: NoteViewModel by viewModels()

    private var _fragmentNoteBinding: FragmentNoteBinding? = null
    private val binding get() = _fragmentNoteBinding!!

    private lateinit var noteAdapter: NoteAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentNoteBinding = FragmentNoteBinding.inflate(inflater, container, false)
        return _fragmentNoteBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUI()
        initObserver()
        initAction()
    }

    private fun initUI() {
        binding.rvNote.layoutManager = GridLayoutManager(context, 2)
        binding.toolbar.apply {
            setBackgroundColor(ContextCompat.getColor(context, R.color.dark_blue_200))
            title = getString(R.string.daftar_task)
            setTitleTextColor(ContextCompat.getColor(context, R.color.white))
            navigationIcon = AppCompatResources.getDrawable(context, R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                it.findNavController().popBackStack()
            }
        }
    }

    private fun initAction() {
        binding.fabAddNote.setOnClickListener {
            val emptyNote = Note(noteTitle = "", noteDescription = "")
            val action = NoteFragmentDirections.actionNoteFragmentToAddNoteFragment(emptyNote, 1)
            it.findNavController().navigate(action)
        }
        binding.svNote.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                searchNote(newText)
                return false
            }
        })
    }

    private fun searchNote(keyword: String) {
        noteViewModel.searchNote(keyword).observe(viewLifecycleOwner) { notes ->
            if (notes.isEmpty()) {
                val emptyList = Collections.emptyList<Note>()
                noteAdapter = NoteAdapter(emptyList)
                binding.tvEmptyList.visibility = View.VISIBLE
            } else {
                val noteAdapter = NoteAdapter(notes)
                noteAdapter.setItemListener(this)
                binding.tvEmptyList.visibility = View.GONE
                binding.rvNote.adapter = noteAdapter
            }
        }
    }

    private fun initObserver() {
        noteViewModel.getAllNotes().observe(viewLifecycleOwner) { notes ->
            if (notes.isEmpty()) {
                val emptyList = Collections.emptyList<Note>()
                noteAdapter = NoteAdapter(emptyList)
                binding.tvEmptyList.visibility = View.VISIBLE
            } else {
                noteAdapter = NoteAdapter(notes)
                noteAdapter.setItemListener(this)
                binding.tvEmptyList.visibility = View.GONE
                binding.rvNote.adapter = noteAdapter
            }
        }
    }

    override fun onResume() {
        super.onResume()
        initObserver()
    }

    override fun onItemClicked(note: Note) {
        val action = NoteFragmentDirections.actionNoteFragmentToAddNoteFragment(note, 2)
        findNavController().navigate(action)
    }

}