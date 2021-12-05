package com.example.my_mathongo_task.ui.questions_list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.datasource.remote.models.QuestionItem
import com.example.my_mathongo_task.R
import com.example.my_mathongo_task.databinding.FragmentQuestionsListBinding
import com.example.my_mathongo_task.ui.QuestionsViewModel
import com.example.my_mathongo_task.utils.Resource
import com.example.my_mathongo_task.utils.showSnackBar

class QuestionListFragment : Fragment() {

    private var _binding: FragmentQuestionsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: QuestionsViewModel by activityViewModels()
    private lateinit var adapter: QuestionsListAdapter
    private var currentUnAttemptedList: ArrayList<QuestionItem>? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuestionsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpClickListeners()

        viewModel.questions.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> binding.apply {
                    showSnackBar(it.message!!)
                    shimmerProgress.isGone = true
                    shimmerProgress.stopShimmer()
                    rvQuestionsList.isGone = true
                }
                is Resource.Loading -> binding.apply {
                    shimmerProgress.isGone = false
                    shimmerProgress.startShimmer()
                    rvQuestionsList.isGone = true
                }
                is Resource.Success -> binding.apply {
                    // Stop Shimmer
                    shimmerProgress.isGone = true
                    shimmerProgress.stopShimmer()
                    rvQuestionsList.isGone = false
                    // Update the UI
                    adapter.submitList(it.data)
                    currentUnAttemptedList = it.data as ArrayList
                }
            }
        }
    }

    private fun setUpClickListeners() = binding.apply {
        filterBtn.setOnClickListener {

        }
    }

    private fun setUpRecyclerView() = binding.apply {
        // Setting up All Questions Results RecyclerView
        adapter = QuestionsListAdapter { currentPosition ->
            currentUnAttemptedList?.let { currentList ->
                Log.d("CurrentPosition_Quest", "$currentPosition")
                viewModel.setQuestionList(currentList)
                viewModel.setCurrentQuestionNum(currentPosition)
                findNavController().navigate(R.id.action_questionListFragment_to_quizFragment)
            }
        }
        rvQuestionsList.setHasFixedSize(true)
        rvQuestionsList.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}