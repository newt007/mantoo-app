package com.elapp.mantuapp.presentation.ui.task.detail

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.elapp.mantuapp.R
import com.elapp.mantuapp.data.entity.Task
import com.elapp.mantuapp.databinding.FragmentDetailTaskBinding
import com.elapp.mantuapp.presentation.ui.task.TaskViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailTaskFragment : Fragment() {

    private val taskViewModel: TaskViewModel by viewModels()

    private var _fragmentDetailTask: FragmentDetailTaskBinding? = null
    private val binding get() = _fragmentDetailTask!!

    var task: Task? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentDetailTask = FragmentDetailTaskBinding.inflate(inflater, container, false)
        return _fragmentDetailTask?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getArgumentsNavigation()
        setupToolbar()
        setupUI()
    }

    private fun getArgumentsNavigation() {
        task = arguments?.getParcelable("task")
    }

    private fun setupToolbar() {
        binding.toolbar.apply {
            setBackgroundColor(ContextCompat.getColor(context, R.color.dark_blue_200))
            title = task?.taskTitle
            setTitleTextColor(ContextCompat.getColor(context, R.color.white))
            navigationIcon = AppCompatResources.getDrawable(context, R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                it.findNavController().popBackStack()
            }
            inflateMenu(R.menu.menu_detail_task)
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
                                task?.idTask?.let { id -> deleteTask(id) }
                                findNavController().popBackStack()
                            }
                        }.create().show()
                        true
                    }
                    R.id.edit -> {
                        // Save profile changes
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun setupUI() {
        binding.apply {
            tvTaskTitle.text = task?.taskTitle
            tvTaskDesc.text = task?.taskDescription
            tvTaskDate.text = task?.taskDate
            tvTaskTime.text = task?.taskTime
        }
    }

    private fun deleteTask(taskId: Int) {
        taskViewModel.deleteTask(taskId)
    }
}