package com.example.my_mathongo_task.ui.questions_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.datasource.remote.models.QuestionItem
import com.example.my_mathongo_task.R
import com.example.my_mathongo_task.databinding.FragmentQuestionsListBinding
import com.example.my_mathongo_task.ui.QuestionsViewModel
import com.example.my_mathongo_task.utils.Constants.ATTEMPTED
import com.example.my_mathongo_task.utils.Constants.NOT_ATTEMPTED
import com.example.my_mathongo_task.utils.Resource
import com.example.my_mathongo_task.utils.showSnackBar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QuestionListFragment : Fragment() {

    private var _binding: FragmentQuestionsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: QuestionsViewModel by activityViewModels()
    private lateinit var unAttemptedAdapter: QuestionsListAdapter
    private lateinit var attemptedAdapter: QuestionsListAdapter
    private var currentUnAttemptedList: ArrayList<QuestionItem>? = null
    private var attemptedQuestionList: ArrayList<QuestionItem>? = null
    private var questionsListType: String? = null

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

        viewModel.getQuestionListType().observe(viewLifecycleOwner) {
            questionsListType = it
            when (it) {
                null -> {
                    binding.autoCompleteText.setText(NOT_ATTEMPTED, false)
                    viewModel.getAllQuestions()
                    binding.unattemptedView.root.isGone = false
                    binding.attemptedView.root.isGone = true
                }
                NOT_ATTEMPTED -> {
                    binding.autoCompleteText.setText(NOT_ATTEMPTED, false)
                    viewModel.getAllQuestions()
                    binding.unattemptedView.root.isGone = false
                    binding.attemptedView.root.isGone = true
                }
                ATTEMPTED -> {
                    binding.autoCompleteText.setText(ATTEMPTED, false)
                    binding.unattemptedView.root.isGone = true
                    binding.attemptedView.root.isGone = false
                }
            }
        }

        viewModel.getAttemptedQuestions().observe(viewLifecycleOwner) { attemptedList ->
            this.attemptedQuestionList = attemptedList as ArrayList
            if (attemptedList.isEmpty()) {
                binding.attemptedView.rvAttemptedList.isGone = true
                binding.attemptedView.emptyLayout.root.isGone = false
            } else {
                attemptedAdapter.submitList(attemptedList)
                binding.attemptedView.rvAttemptedList.isGone = false
                binding.attemptedView.emptyLayout.root.isGone = true
            }
        }

        viewModel.allQuestions.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> binding.apply {
                    showSnackBar(it.message!!)
                    unattemptedView.shimmerProgress.isGone = true
                    unattemptedView.shimmerProgress.stopShimmer()
                    unattemptedView.errorLayout.root.isGone = false
                    unattemptedView.rvUnAttemptedList.isGone = true
                }
                is Resource.Loading -> binding.apply {
                    unattemptedView.shimmerProgress.isGone = false
                    unattemptedView.shimmerProgress.startShimmer()
                    unattemptedView.errorLayout.root.isGone = true
                    unattemptedView.rvUnAttemptedList.isGone = true
                }
                is Resource.Success -> binding.apply {
                    // Stop Shimmer
                    unattemptedView.shimmerProgress.isGone = true
                    unattemptedView.shimmerProgress.stopShimmer()
                    unattemptedView.rvUnAttemptedList.isGone = false
                    // Update the UI
                    currentUnAttemptedList = it.data as ArrayList
                    attemptedQuestionList?.let { attemptedList ->
                        currentUnAttemptedList?.removeAll(attemptedList)
                    }
                    unAttemptedAdapter.submitList(currentUnAttemptedList!!)
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        setUpDropDownMenu()
    }

    private fun setUpDropDownMenu() {
        val questionsType = resources.getStringArray(R.array.questions_type)
        val arrayAdapter = ArrayAdapter(requireContext(), R.layout.dropdown_item, questionsType)
        binding.autoCompleteText.setAdapter(arrayAdapter)
    }

    private fun setUpClickListeners() = binding.apply {

        unattemptedView.errorLayout.retryButton.setOnClickListener {
            viewModel.getAllQuestions()
        }

        attemptedView.emptyLayout.clearFilter.setOnClickListener {
            binding.autoCompleteText.setText(NOT_ATTEMPTED, false)
            viewModel.saveQuestionListType(NOT_ATTEMPTED)
            viewModel.getAllQuestions()
            binding.unattemptedView.root.isGone = false
            binding.attemptedView.root.isGone = true
        }

        binding.autoCompleteText.setOnItemClickListener { adapterView, view, position: Int, id: Long ->
            when (position) {
                0 -> {
                    viewModel.saveQuestionListType(NOT_ATTEMPTED)
                    // update ui
                    binding.unattemptedView.root.isGone = false
                    binding.attemptedView.root.isGone = true
                }
                1 -> {
                    viewModel.saveQuestionListType(ATTEMPTED)
                    // update ui
                    binding.unattemptedView.root.isGone = true
                    binding.attemptedView.root.isGone = false
                }
            }
        }
    }

    private fun setUpRecyclerView() = binding.apply {
        // Setting up Un attempted questions RecyclerView
        unattemptedView.rvUnAttemptedList.setHasFixedSize(true)
        unAttemptedAdapter = QuestionsListAdapter { currentPosition ->
            currentUnAttemptedList?.let { currentList ->
                viewModel.setQuestionList(currentList)
                viewModel.setCurrentQuestionNum(currentPosition)
                findNavController().navigate(R.id.action_questionListFragment_to_quizFragment)
            }
        }
        unattemptedView.rvUnAttemptedList.adapter = unAttemptedAdapter
        // Setting up attempted questions RecyclerView
        attemptedView.rvAttemptedList.setHasFixedSize(true)
        attemptedAdapter = QuestionsListAdapter { currentPosition ->
            attemptedQuestionList?.let { currentList ->
                viewModel.setQuestionList(currentList)
                viewModel.setCurrentQuestionNum(currentPosition)
                findNavController().navigate(R.id.action_questionListFragment_to_quizFragment)
            }
        }
        attemptedView.rvAttemptedList.adapter = attemptedAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
