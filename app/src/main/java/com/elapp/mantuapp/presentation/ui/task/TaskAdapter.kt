package com.elapp.mantuapp.presentation.ui.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.elapp.mantuapp.data.entity.Task
import com.elapp.mantuapp.databinding.TaskItemRowBinding

class TaskAdapter(private val taskList: List<Task>): RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapter.TaskViewHolder {
        val binding = TaskItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskAdapter.TaskViewHolder, position: Int) {
        taskList[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = taskList.size

    inner class TaskViewHolder(private val binding: TaskItemRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.apply {
                txTaskTitle.text = task.taskTitle
                txTaskCategory.text = task.taskCategory.toString()
            }
        }
    }

}