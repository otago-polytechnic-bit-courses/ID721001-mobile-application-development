package op.mobile.app.dev.graysono.travelling.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import op.mobile.app.dev.graysono.travelling.R
import op.mobile.app.dev.graysono.travelling.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {
    private lateinit var viewModel: QuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding: FragmentQuizBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_quiz,
            container,
            false
        )

        /**
         * Get the Country object from the bundle. It is passed from the
         * HomeFragment to the QuizFragment when the user clicks on
         * a RecyclerView item
         */
        val viewModelFactory =
            /**
             * QuizViewModelFactory allows you to retrieve the Country object passed
             * from HomeFragment
             */
            QuizViewModelFactory(
                QuizFragmentArgs.fromBundle(requireArguments()).country
            )

        viewModel = ViewModelProvider(
            this,
            viewModelFactory
        ).get(QuizViewModel::class.java)

        /**
         * Observe when the quiz is over
         */
        viewModel.isFinished.observe(
            viewLifecycleOwner,
            Observer {
                if (it) isFinished()
            }
        )

        /**
         * Set the quiz questions and start the timer
         */
        viewModel.setQuestion()
        viewModel.startTimer()

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            quizViewModel = viewModel

            btnSubmit.setOnClickListener {
                val checkedRadioBtnId = binding.radioBtnGroupAnswers.checkedRadioButtonId
                /**
                 * The first answer is the correct answer
                 */
                val correctAnswer = viewModel.quiz.value?.answers?.get(0)

                /**
                 * If a RadioButton is checked
                 */
                if (checkedRadioBtnId != -1) {
                    var answerIdx = 0

                    /**
                      For example:

                      "answers": [
                        "Secret ballots", Correct answer
                        "Write-in nominations", Incorrect answer
                        "Recounting of votes", Incorrect answer
                        "All of the above" Incorrect answer
                      ],
                     */

                    /**
                     * When a RadioButton is checked, get the answer's index.
                     * It will be important in getting the index's value
                     */
                    when (checkedRadioBtnId) {
                        R.id.radio_btn_answer_two -> answerIdx = 1
                        R.id.radio_btn_answer_three -> answerIdx = 2
                        R.id.radio_btn_answer_four -> answerIdx = 3
                    }

                    /**
                     * If the answer is correct
                     */
                    if (viewModel.answers.value?.get(answerIdx)
                        == correctAnswer
                    ) {
                        /**
                         * Self-directed learning:
                         * - Add to the score
                         * - Display a message for a correct answer
                         */
                    } else {
                        /**
                         * Self-directed learning:
                         *
                         * Display a message for an incorrect answer
                         */
                    }

                    viewModel.addQuestionIdx()

                    /**
                     * If the current question's index is less than the number of
                     * quiz question (five)
                     */
                    if (viewModel.questionIdx.value!! < viewModel.country.quiz.size) {
                        viewModel.setQuestion()
                        /**
                         * Clear the checked RadioButton from the previous question
                         */
                        binding.radioBtnGroupAnswers.clearCheck()
                        binding.invalidateAll()
                    } else {
                        isFinished() // Quiz is finished
                    }
                } else {
                    /**
                     * Self-directed learning:
                     *
                     * Display a message if a RadioButton is not clicked
                     */
                }
            }

            return root
        }
    }

    /**
     * When the quiz is finished, navigate to the
     * QuizResultsFragment with the final score value.
     * This is declared in mobile_navigation.xml using
     * the argument tag
     */
    private fun isFinished() {
        val action = QuizFragmentDirections
            .actionQuizFragmentToQuizResultsFragment(
                viewModel.score.value!!
            )
        findNavController().navigate(action)
    }
}
