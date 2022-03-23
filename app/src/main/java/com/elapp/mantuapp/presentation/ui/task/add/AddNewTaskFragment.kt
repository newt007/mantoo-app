package com.elapp.mantuapp.presentation.ui.task.add

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.elapp.mantuapp.R
import com.elapp.mantuapp.data.entity.Task
import com.elapp.mantuapp.databinding.FragmentAddNewTaskBinding
import com.elapp.mantuapp.presentation.ui.category.SpinnerCategoryAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AddNewTaskFragment : Fragment() {

    private val addTaskViewModel: AddNewTaskViewModel by viewModels()

    private var _fragmentAddNewTaskBinding: FragmentAddNewTaskBinding? = null
    private val binding get() = _fragmentAddNewTaskBinding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentAddNewTaskBinding = FragmentAddNewTaskBinding.inflate(inflater, container, false)
        return _fragmentAddNewTaskBinding?.root
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.apply {
            setBackgroundColor(ContextCompat.getColor(context, R.color.dark_blue_200))
            title = getString(R.string.add_new_task)
            setTitleTextColor(ContextCompat.getColor(context, R.color.white))
            navigationIcon = AppCompatResources.getDrawable(context, R.drawable.ic_arrow_back)
            setNavigationOnClickListener {
                it.findNavController().popBackStack()
            }
        }
        getCategory()

        binding.edtTaskDate.setOnClickListener {
            val datePicker = DatePickerDialog(requireContext())
            datePicker.setOnDateSetListener { _, year, month, day ->
                binding.edtTaskDate.setText("$year/$month/$day")
                binding.edtTaskDate.clearFocus()
            }
            datePicker.show()
        }
        binding.edtTaskTime.setOnClickListener {
            val timePicker = TimePickerDialog(
                requireContext(), { _, hours, minute ->
                    binding.edtTaskTime.setText("$hours:$minute")
                },
                12, 10, true
            )
            timePicker.show()
        }
        binding.btnAddTask.setOnClickListener {
            val task = Task(
                taskTitle = binding.edtTaskName.text.toString(),
                taskCategory = binding.spinnerCategory.id,
                taskTime = binding.edtTaskTime.text.toString(),
                taskDate = binding.edtTaskDate.text.toString(),
                taskDescription = binding.edtTaskDesc.text.toString()
            )
            addNewTask(task)
        }
    }

    private fun getCategory() {
        addTaskViewModel.getAllCategory().observe(viewLifecycleOwner) { category ->
            val adapter = SpinnerCategoryAdapter(requireContext(), category)
            adapter.setInflate(layoutInflater)
            binding.spinnerCategory.adapter = adapter
        }
    }

    private fun addNewTask(newTask: Task) {
        addTaskViewModel.addNewTask(newTask)
        findNavController().popBackStack()
        Toast.makeText(context, "Task baru berhasil ditambahkan", Toast.LENGTH_SHORT).show()
    }

}