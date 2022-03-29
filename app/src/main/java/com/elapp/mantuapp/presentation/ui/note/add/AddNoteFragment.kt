package com.elapp.mantuapp.presentation.ui.note.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.elapp.mantuapp.R
import com.elapp.mantuapp.data.entity.Note
import com.elapp.mantuapp.databinding.FragmentAddNoteBinding
import com.elapp.mantuapp.presentation.ui.note.NoteViewModel
import com.elapp.mantuapp.utils.showSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNoteFragment : Fragment() {

    private val noteViewModel: NoteViewModel by viewModels()

    private var _fragmentAddNoteBinding: FragmentAddNoteBinding? = null
    private val binding get() = _fragmentAddNoteBinding!!

    private var action: Int? = 1
    private var note: Note? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentAddNoteBinding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return _fragmentAddNoteBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initArguments()
        initUI()
        initAction()
    }

    private fun initUI() {
        binding.toolbar.apply {
            setBackgroundColor(ContextCompat.getColor(context, R.color.dark_blue_200))
            title = isEditOrNew(action)
            setTitleTextColor(ContextCompat.getColor(context, R.color.white))
            navigationIcon = AppCompatResources.getDrawable(context, R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                it.findNavController().popBackStack()
            }
        }

        if (action == 2) {
            val noteTitle = note?.noteTitle ?: ""
            val noteDescription = note?.noteDescription ?: ""
            binding.apply {
                edtNoteTitle.setText(noteTitle)
                edtNoteDescription.setText(noteDescription)
                toolbar.apply {
                    inflateMenu(R.menu.menu_detail_note)
                    setOnMenuItemClickListener {
                        when (it.itemId) {
                            R.id.delete -> {
                                // Delete task data
                                AlertDialog.Builder(context).apply {
                                    setTitle("Hapus Task")
                                    setMessage("Kamu yakin ingin menghapus task ini ?")
                                    setNegativeButton("Batal") { p0, _ ->
                                        p0.dismiss()
                                    }
                                    setPositiveButton("Hapus") { _, _ ->
                                        findNavController().popBackStack()
                                    }
                                }.create().show()
                                true
                            }
                            R.id.pin -> {
                                // Save profile changes
                                true
                            }
                            else -> false
                        }
                    }
                }
            }
        }
    }

    private fun initArguments() {
        note = arguments?.getParcelable("note")
        action = arguments?.getInt("action") ?: 1

        Toast.makeText(context, "$action", Toast.LENGTH_SHORT).show()
    }

    private fun initAction() {
        binding.fabSaveNote.setOnClickListener {
            if (action == 1) {
                val note = Note(
                    noteTitle = binding.edtNoteTitle.text.toString(),
                    noteDescription = binding.edtNoteDescription.text.toString()
                )
                noteViewModel.addNewNote(note)
                it.findNavController().popBackStack()
                view?.showSnackbar(requireView(), "Catatan berhasil ditambahkan")
            } else if (action == 2) {
                val note = Note(
                    noteTitle = binding.edtNoteTitle.text.toString(),
                    noteDescription = binding.edtNoteDescription.text.toString()
                )
                noteViewModel.updateNote(note)
                it.findNavController().popBackStack()
                view?.showSnackbar(requireView(), "Catatan berhasil diperbarui")
            }
        }
    }

    private fun isEditOrNew(action: Int?): String {
        return when (action) {
            1 -> {
                getString(R.string.tambah_note_baru)
            }
            2 -> {
                getString(R.string.edit_note)
            }
            else -> {
                getString(R.string.tambah_note_baru)
            }
        }
    }

    private fun initObserver() {

    }

}