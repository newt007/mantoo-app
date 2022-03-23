package com.elapp.mantuapp.presentation.ui.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elapp.mantuapp.data.entity.Task
import com.elapp.mantuapp.databinding.SortedTaskItemRowBinding

class SortedTaskAdapter(private val taskList: List<Task>): RecyclerView.Adapter<SortedTaskAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SortedTaskAdapter.TaskViewHolder {
        val binding = SortedTaskItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SortedTaskAdapter.TaskViewHolder, position: Int) {
        taskList[position].let { task ->
            holder.bind(task)
        }
    }

    override fun getItemCount(): Int = taskList.size

    inner class TaskViewHolder(private val binding: SortedTaskItemRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.apply {
                txTaskTitle.text = task.taskTitle
                txTaskDesc.text = task.taskDescription
                txTaskTime.text = task.taskTime
            }
        }
    }

}