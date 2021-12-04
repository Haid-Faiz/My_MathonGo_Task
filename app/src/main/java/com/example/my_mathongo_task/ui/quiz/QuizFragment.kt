package com.example.my_mathongo_task.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.my_mathongo_task.databinding.FragmentQuizBinding
import com.example.my_mathongo_task.utils.Resource
import com.example.my_mathongo_task.utils.showSnackBar
import com.example.my_mathongo_task.ui.questions_list.QuestionsViewModel

class QuizFragment : Fragment() {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private val viewModel: QuestionsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentQuizBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}