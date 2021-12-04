package com.example.my_mathongo_task.ui.questions_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.my_mathongo_task.databinding.FragmentQuestionsListBinding
import com.example.my_mathongo_task.utils.Resource
import com.example.my_mathongo_task.utils.showSnackBar

class QuestionListFragment : Fragment() {

    private var _binding: FragmentQuestionsListBinding? = null
    private val binding get() = _binding!!
    private val viewModel: QuestionsViewModel by viewModels()


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

        viewModel.trendingQuestions.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Error -> showSnackBar(it.message!!)
                is Resource.Loading -> binding.apply {
//                    // Stop Shimmer
//                    shimmerProgress.isGone = false
//                    shimmerProgress.stopShimmer()
                }
                is Resource.Success -> binding.apply {
                    // Stop Shimmer
                }
            }
        }
    }

    private fun setUpClickListeners() = binding.apply {
    }

    private fun setUpRecyclerView() = binding.apply {
        // Setting up All Questions Results RecyclerView
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}