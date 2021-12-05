package com.example.my_mathongo_task.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.datasource.remote.models.Option
import com.example.datasource.remote.models.QuestionItem
import com.example.datasource.remote.models.Solution
import com.example.my_mathongo_task.R
import com.example.my_mathongo_task.databinding.FragmentQuizBinding
import com.example.my_mathongo_task.ui.QuestionsViewModel
import com.google.android.material.card.MaterialCardView
import dagger.hilt.android.AndroidEntryPoint
import katex.hourglass.`in`.mathlib.MathView


@AndroidEntryPoint
class QuizFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentQuizBinding? = null
    private val binding get() = _binding!!
    private val viewModel: QuestionsViewModel by activityViewModels()
    private lateinit var unAttemptedList: ArrayList<QuestionItem>
    private var currentQuestPosition: Int = 0
    private var selectedCardId: Int? = null
    private var selectedRoundTextId: Int? = null
    private var selectedOption: Option? = null
    private var hatrickQuestionCount: Int = 0
    private lateinit var popingAnim: Animation


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
        unAttemptedList = viewModel.getQuestionList() as ArrayList
        currentQuestPosition = viewModel.getCurrentQuestionNum()
        updateUI(unAttemptedList[currentQuestPosition])
        popingAnim = AnimationUtils.loadAnimation(requireContext(), R.anim.poping_anim)

        setUpClickListeners()
    }

    override fun onClick(view: View?) {
        binding.apply {
            when (view?.id) {
                R.id.option_a -> {
                    selectOption(cardView = optionA, textView = aText)
                    selectedCardId = R.id.option_a
                    selectedRoundTextId = R.id.a_text
                    selectedOption = unAttemptedList[currentQuestPosition].options[0]
                }
                R.id.option_b -> {
                    selectOption(cardView = optionB, textView = bText)
                    selectedCardId = R.id.option_b
                    selectedRoundTextId = R.id.b_text
                    selectedOption = unAttemptedList[currentQuestPosition].options[1]
                }
                R.id.option_c -> {
                    selectOption(cardView = optionC, textView = cText)
                    selectedCardId = R.id.option_c
                    selectedRoundTextId = R.id.c_text
                    selectedOption = unAttemptedList[currentQuestPosition].options[2]
                }
                R.id.option_d -> {
                    selectOption(cardView = optionD, textView = dText)
                    selectedCardId = R.id.option_d
                    selectedRoundTextId = R.id.d_text
                    selectedOption = unAttemptedList[currentQuestPosition].options[3]
                }
            }
        }
    }

    private fun selectOption(cardView: MaterialCardView, textView: TextView) = binding.apply {
        // Enable check answer button
        checkAnsBtn.isEnabled = true
        // Now checking whether this option is selected or not
        if (cardView.isChecked) {
            // Uncheck it & disable check answer button
            checkAnsBtn.isEnabled = false
            cardView.isChecked = false
            cardView.strokeColor = ContextCompat.getColor(requireContext(), R.color.dark_grey)
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_dark))
            textView.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.unselected_option_round_bg)
        } else {
            // Check it & unCheck others
            resetOptions()
            cardView.strokeColor = ContextCompat.getColor(requireContext(), R.color.blue)
            textView.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.selected_option_round_bg)
            textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
            cardView.isChecked = true
        }
    }

    private fun resetOptions() = binding.apply {
        // Reseting option A
        optionA.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
        optionA.strokeColor = ContextCompat.getColor(requireContext(), R.color.dark_grey)
        optionA.isChecked = false
        aText.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.unselected_option_round_bg)
        aText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_dark))
        // Reseting option B
        optionB.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
        optionB.strokeColor = ContextCompat.getColor(requireContext(), R.color.dark_grey)
        optionB.isChecked = false
        bText.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.unselected_option_round_bg)
        bText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_dark))
        // Reseting option C
        optionC.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
        optionC.strokeColor = ContextCompat.getColor(requireContext(), R.color.dark_grey)
        optionC.isChecked = false
        cText.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.unselected_option_round_bg)
        cText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_dark))
        // Reseting option D
        optionD.setCardBackgroundColor(ContextCompat.getColor(requireContext(), R.color.white))
        optionD.strokeColor = ContextCompat.getColor(requireContext(), R.color.dark_grey)
        optionD.isChecked = false
        dText.background =
            ContextCompat.getDrawable(requireContext(), R.drawable.unselected_option_round_bg)
        dText.setTextColor(ContextCompat.getColor(requireContext(), R.color.black_dark))
    }

    private fun updateUI(questionItem: QuestionItem) = binding.apply {

        questionNum.text = "Q${currentQuestPosition.plus(1)} (${questionItem.type})"
        questionText.setDisplayText(questionItem.question.text)
        // If question also contains image then show it
        if (!questionItem.question.image.isNullOrEmpty()) {
            // load the image also
            questionImage.isGone = false
            questionImage.load(questionItem.question.image)
        } else {
            questionImage.isGone = true
        }
        examName.text = questionItem.exams[0]

        // Load option A
        loadOption(
            option = questionItem.options[0],
            optionMathView = optionAMathView,
            optionImage = optionAImage
        )
        // load option B
        loadOption(
            option = questionItem.options[1],
            optionMathView = optionBMathView,
            optionImage = optionBImage
        )
        // load option C
        loadOption(
            option = questionItem.options[2],
            optionMathView = optionCMathView,
            optionImage = optionCImage
        )
        // load option D
        loadOption(
            option = questionItem.options[3],
            optionMathView = optionDMathView,
            optionImage = optionDImage
        )
        // handle next & previous buttons states
        nextQuestionBtn.isEnabled = unAttemptedList.size.minus(1) != currentQuestPosition
        previousQuestionBtn.isEnabled = currentQuestPosition != 0
        if (currentQuestPosition != 0) {
            previousQuestionBtn.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_back
                )
            )
        } else {
            previousQuestionBtn.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_disable_back
                )
            )
        }
    }

    private fun loadOption(option: Option, optionMathView: MathView, optionImage: ImageView) {
        optionMathView.setDisplayText(option.text)
        // If it also contains image then show it
        if (!option.image.isNullOrEmpty()) {
            // load the image also
            optionImage.isGone = false
            optionImage.load(option.image)
        } else {
            optionImage.isGone = true
        }
    }

    private fun loadSolution(solution: Solution) = binding.apply {
        solutionLl.isGone = false
        solutionMathView.setDisplayText(solution.text)
        // If it also contains image then show it
        if (!solution.image.isNullOrEmpty()) {
            // load the image also
            solutionImage.isGone = false
            solutionImage.load(solution.image)
        } else {
            solutionImage.isGone = true
        }
    }

    private fun setUpClickListeners() = binding.apply {

        backBtn.setOnClickListener {
            findNavController().popBackStack()
        }

        streakView.viewSolutionBtn.setOnClickListener {
            streakView.root.isGone = true
            solutionLl.parent.requestChildFocus(solutionLl, solutionLl)
        }

        streakView.nextQuestBtn.setOnClickListener {
            hatrickQuestionCount = 0
            streakView.root.isGone = true
            midNextBtn.isGone = true
            checkAnsBtn.isGone = false
            checkAnsBtn.isEnabled = false
            currentQuestPosition = ++currentQuestPosition
            updateUI(unAttemptedList[currentQuestPosition])
            resetOptions()
            selectedCardId = null
            selectedRoundTextId = null
            selectedOption = null
            solutionLl.isGone = true
            // Make options clickable
            optionA.isClickable = true
            optionB.isClickable = true
            optionC.isClickable = true
            optionD.isClickable = true
            optionA.isEnabled = true
            optionB.isEnabled = true
            optionC.isEnabled = true
            optionD.isEnabled = true

            aText.isClickable = true
            bText.isClickable = true
            cText.isClickable = true
            dText.isClickable = true
            // Disable options click
            optionAMathView.isClickable = true
            optionBMathView.isClickable = true
            optionCMathView.isClickable = true
            optionDMathView.isClickable = true
            // Remove answer status poster
            correctPosterText.isGone = true
            wrongPosterText.isGone = true
        }

        optionA.setOnClickListener(this@QuizFragment)
        optionB.setOnClickListener(this@QuizFragment)
        optionC.setOnClickListener(this@QuizFragment)
        optionD.setOnClickListener(this@QuizFragment)

        nextQuestionBtn.setOnClickListener {
            midNextBtn.isGone = true
            checkAnsBtn.isGone = false
            checkAnsBtn.isEnabled = false
            currentQuestPosition = ++currentQuestPosition
            updateUI(unAttemptedList[currentQuestPosition])
            resetOptions()
            selectedCardId = null
            selectedRoundTextId = null
            selectedOption = null
            solutionLl.isGone = true
            // Make options clickable
            optionA.isClickable = true
            optionB.isClickable = true
            optionC.isClickable = true
            optionD.isClickable = true
            optionA.isEnabled = true
            optionB.isEnabled = true
            optionC.isEnabled = true
            optionD.isEnabled = true

            aText.isClickable = true
            bText.isClickable = true
            cText.isClickable = true
            dText.isClickable = true
            // Disable options click
            optionAMathView.isClickable = true
            optionBMathView.isClickable = true
            optionCMathView.isClickable = true
            optionDMathView.isClickable = true
            // Remove answer status poster
            correctPosterText.isGone = true
            wrongPosterText.isGone = true
        }

        previousQuestionBtn.setOnClickListener {
            midNextBtn.isGone = true
            checkAnsBtn.isGone = false
            checkAnsBtn.isEnabled = false
            currentQuestPosition = --currentQuestPosition
            updateUI(unAttemptedList[currentQuestPosition])
            resetOptions()
            selectedCardId = null
            selectedRoundTextId = null
            selectedOption = null
            solutionLl.isGone = true
            // Make options clickable
            optionA.isClickable = true
            optionB.isClickable = true
            optionC.isClickable = true
            optionD.isClickable = true
            optionA.isEnabled = true
            optionB.isEnabled = true
            optionC.isEnabled = true
            optionD.isEnabled = true

            aText.isClickable = true
            bText.isClickable = true
            cText.isClickable = true
            dText.isClickable = true
            // Disable options click
            optionAMathView.isClickable = true
            optionBMathView.isClickable = true
            optionCMathView.isClickable = true
            optionDMathView.isClickable = true
            // Remove answer status poster
            correctPosterText.isGone = true
            wrongPosterText.isGone = true
        }

        midNextBtn.setOnClickListener {
            midNextBtn.isGone = true
            checkAnsBtn.isGone = false
            checkAnsBtn.isEnabled = false
            currentQuestPosition = ++currentQuestPosition
            updateUI(unAttemptedList[currentQuestPosition])
            resetOptions()
            selectedCardId = null
            selectedRoundTextId = null
            selectedOption = null
            solutionLl.isGone = true
            // Make options clickable
            optionA.isClickable = true
            optionB.isClickable = true
            optionC.isClickable = true
            optionD.isClickable = true
            optionA.isEnabled = true
            optionB.isEnabled = true
            optionC.isEnabled = true
            optionD.isEnabled = true

            aText.isClickable = true
            bText.isClickable = true
            cText.isClickable = true
            dText.isClickable = true
            // Disable options click
            optionAMathView.isClickable = true
            optionBMathView.isClickable = true
            optionCMathView.isClickable = true
            optionDMathView.isClickable = true
            // Remove answer status poster
            correctPosterText.isGone = true
            wrongPosterText.isGone = true
        }

        checkAnsBtn.setOnClickListener {

            if (selectedOption != null && selectedCardId != null && selectedRoundTextId != null) {
                // load solution
                loadSolution(unAttemptedList[currentQuestPosition].solution)

                val card = root.findViewById<MaterialCardView>(selectedCardId!!)
                val textView = root.findViewById<TextView>(selectedRoundTextId!!)

                if (selectedOption!!.isCorrect) {
                    hatrickQuestionCount++
                    // Change the selected option color to green
                    card.strokeColor =
                        ContextCompat.getColor(requireContext(), R.color.green)
                    textView.background =
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.correct_option_round_bg
                        )
                    textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

                    if (hatrickQuestionCount == 3) {
                        streakView.root.isGone = false
                        streakView.root.startAnimation(popingAnim)
                    } else {
                        // Show correct answer poster
                        correctPosterText.isGone = false
                        correctPosterText.startAnimation(popingAnim)
                        correctPosterText.postDelayed({ correctPosterText.isGone = true }, 3000)
                    }

                } else {
                    hatrickQuestionCount = 0
                    // Change the selected option color to red
                    card.strokeColor =
                        ContextCompat.getColor(requireContext(), R.color.red)
                    textView.background =
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.wrong_option_round_bg
                        )
                    textView.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))

                    // Now highlight the correct one
                    // Get the correct option
                    val filteredOptions: List<Option> =
                        unAttemptedList[currentQuestPosition].options.filter {
                            it.isCorrect
                        }
                    // get the index of correct option
                    val correctOptionIndex =
                        unAttemptedList[currentQuestPosition].options.indexOf(filteredOptions[0])

                    val (correctCard, correctText) = when (correctOptionIndex) {
                        0 -> Pair(
                            root.findViewById<MaterialCardView>(R.id.option_a),
                            root.findViewById<TextView>(R.id.a_text)
                        )
                        1 -> Pair(
                            root.findViewById<MaterialCardView>(R.id.option_b),
                            root.findViewById<TextView>(R.id.b_text)
                        )
                        2 -> Pair(
                            root.findViewById<MaterialCardView>(R.id.option_c),
                            root.findViewById<TextView>(R.id.c_text)
                        )
                        3 -> Pair(
                            root.findViewById(R.id.option_d),
                            root.findViewById(R.id.d_text)
                        )
                        else -> Pair(
                            root.findViewById(R.id.option_a),
                            root.findViewById(R.id.a_text)
                        )
                    }

                    correctCard.strokeColor =
                        ContextCompat.getColor(requireContext(), R.color.green)
                    correctText.background =
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.correct_option_round_bg
                        )
                    correctText.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.white
                        )
                    )
                    // Show the wrong ans poster
                    wrongPosterText.isGone = false
                    wrongPosterText.startAnimation(popingAnim)
                    wrongPosterText.postDelayed({ wrongPosterText.isGone = true }, 3000)
                }

                // insert this question in database
                viewModel.insertQuestion(unAttemptedList[currentQuestPosition])
                // Show Mid Next Button
                checkAnsBtn.isGone = true
                midNextBtn.isGone = false
                midNextBtn.isEnabled = true
            }

            // Disable options click
            optionA.isClickable = false
            optionB.isClickable = false
            optionC.isClickable = false
            optionD.isClickable = false
            optionA.isEnabled = false
            optionB.isEnabled = false
            optionC.isEnabled = false
            optionD.isEnabled = false
            // Disable options click
            aText.isClickable = false
            bText.isClickable = false
            cText.isClickable = false
            dText.isClickable = false
            // Disable options click
            optionAMathView.isClickable = false
            optionBMathView.isClickable = false
            optionCMathView.isClickable = false
            optionDMathView.isClickable = false
        }
    }

    private fun verifyAnswer() = binding.apply {

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
