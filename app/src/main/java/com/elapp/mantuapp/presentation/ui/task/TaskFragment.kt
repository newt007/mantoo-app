package com.elapp.mantuapp.presentation.ui.task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.elapp.mantuapp.databinding.FragmentTaskBinding
import com.elapp.mantuapp.utils.formattedDate
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class TaskFragment : Fragment() {

    private val taskViewModel: TaskViewModel by viewModels()

    private var _fragmentTaskBinding: FragmentTaskBinding? = null
    private val binding get() = _fragmentTaskBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentTaskBinding = FragmentTaskBinding.inflate(inflater, container, false)
        return _fragmentTaskBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val currentDate = formattedDate(Calendar.getInstance().time)
        getTaskListByDate(currentDate)
    }

    private fun getTaskListByDate(date: String) {
        taskViewModel.getTaskByDate(date).observe(viewLifecycleOwner) { taskList ->
            if (taskList.isEmpty()) {
                Toast.makeText(context, "Tidak ada task hari ini", Toast.LENGTH_SHORT).show()
            } else {
                val adapter = SortedTaskAdapter(taskList)
                binding.rvTask.adapter = adapter
                binding.rvTask.layoutManager = LinearLayoutManager(context)
            }
        }
    }

}