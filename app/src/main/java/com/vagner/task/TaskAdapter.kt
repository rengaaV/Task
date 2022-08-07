package com.vagner.task

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.vagner.task.databinding.ResItemTaskBinding

class TaskAdapter(
    private val onClick: (Task) -> Unit,
    private val onClickDelete: (Task) -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    private var tasks = mutableListOf<Task>()

    inner class TaskViewHolder(
        private val binding: ResItemTaskBinding
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(
            task: Task,
            onClickDelete: (Task) -> Unit,
            onClick: (Task) -> Unit
        ) {
            binding.tvTitleTask.text = task.title

            binding.imgBtnDeleteTask.setOnClickListener {
                onClickDelete(task)
            }

            binding.clTask.setOnClickListener {
                onClick(task)
            }

            if (task.done) {
                binding.tvTitleTask.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.white)
                )

                binding.imgBtnDeleteTask.setImageResource(R.drawable.delete_white)

                binding.clTask.setBackgroundColor(
                    ContextCompat.getColor(itemView.context, R.color.medium_sea_green)
                )
            } else {
                binding.tvTitleTask.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.black)
                )

                binding.imgBtnDeleteTask.setImageResource(R.drawable.delete_black)

                binding.clTask.setBackgroundColor(
                    ContextCompat.getColor(itemView.context, R.color.bright_gray)
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder(
            ResItemTaskBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position], onClickDelete, onClick)
    }

    override fun getItemCount(): Int = tasks.size

    fun addTask(task: Task) {
        tasks.add(task)
        notifyItemInserted(tasks.size - 1)
    }

    fun deleteTask(task: Task) {
        val deletePosition = tasks.indexOf(task)
        tasks.remove(task)
        notifyItemRemoved(deletePosition)
    }

    fun updateTask(task: Task) {
        val updatePosition = tasks.indexOf(task)
        tasks[updatePosition] = task
        notifyItemChanged(updatePosition)
    }

    fun isEmpty() = tasks.isEmpty()
}