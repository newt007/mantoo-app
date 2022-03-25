package com.elapp.mantuapp.presentation.ui.task

import android.app.DatePickerDialog
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
import androidx.recyclerview.widget.LinearLayoutManager
import com.elapp.mantuapp.R
import com.elapp.mantuapp.databinding.FragmentTaskBinding
import com.elapp.mantuapp.utils.dateFormatToGetDay
import com.elapp.mantuapp.utils.dayFormatter
import com.elapp.mantuapp.utils.formattedDate
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*

@AndroidEntryPoint
class TaskFragment : Fragment() {

    private val taskViewModel: TaskViewModel by viewModels()

    private var _fragmentTaskBinding: FragmentTaskBinding? = null
    private val binding get() = _fragmentTaskBinding!!

    private var currentDate: String = ""
    private var currentDay: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _fragmentTaskBinding = FragmentTaskBinding.inflate(inflater, container, false)
        return _fragmentTaskBinding?.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val date = Calendar.getInstance()
        currentDate = formattedDate(date.time)
        currentDay = dayFormatter(date.time)
        getTaskListByDate(currentDate)
        setupToolbar()

        binding.txDateDay.text = dateFormatToGetDay(date.time)

        binding.txDateDay.setOnClickListener {
            val datePicker = DatePickerDialog(requireContext())
            datePicker.setOnDateSetListener { _, year, month, day ->

                val test = date.set(year, month, day)
                Toast.makeText(context, test.toString(), Toast.LENGTH_SHORT).show()
//                binding.txDateDay.text = dateFormatToGetDay(mDate)
            }
            datePicker.show()
        }
    }

    private fun setupToolbar() {
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

    private fun getTaskListByDate(date: String) {
        taskViewModel.getTaskByDate(date).observe(viewLifecycleOwner) { taskList ->
            if (taskList.isEmpty()) {
                Toast.makeText(context, "Tidak ada task hari ini", Toast.LENGTH_SHORT).show()
                binding.txTaskSubtitle.text = resources.getString(R.string.task_subtitile, "0")
            } else {
                val adapter = SortedTaskAdapter(taskList)
                binding.apply {
                    rvTask.adapter = adapter
                    rvTask.layoutManager = LinearLayoutManager(context)
                    txTaskSubtitle.text = resources.getString(R.string.task_subtitile, taskList.size.toString())
                }
            }
        }
    }

}