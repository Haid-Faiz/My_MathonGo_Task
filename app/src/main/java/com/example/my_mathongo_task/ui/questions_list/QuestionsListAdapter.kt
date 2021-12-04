package com.example.my_mathongo_task.ui.questions_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.datasource.remote.models.QuestionItem
import com.example.my_mathongo_task.databinding.ItemQuestionBinding

class QuestionsListAdapter(
    private var onItemClicked: ((questionItem: QuestionItem) -> Unit)
) : ListAdapter<QuestionItem, QuestionsListAdapter.ViewHolder>(DiffUtilCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemQuestionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }

    inner class ViewHolder(
        private val binding: ItemQuestionBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(questionItem: QuestionItem, position: Int) = binding.apply {
            examName.text = questionItem.exams[0]
            questionNo.text = position.plus(1).toString()
            questionView.setDisplayText(questionItem.question.text)
        }
    }

    class DiffUtilCallback : DiffUtil.ItemCallback<QuestionItem>() {

        override fun areItemsTheSame(
            oldItem: QuestionItem,
            newItem: QuestionItem
        ): Boolean = oldItem == newItem

        override fun areContentsTheSame(
            oldItem: QuestionItem,
            newItem: QuestionItem
        ): Boolean = oldItem.equals(newItem)
    }
}
