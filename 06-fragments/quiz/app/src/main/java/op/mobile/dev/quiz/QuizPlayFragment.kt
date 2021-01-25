package op.mobile.dev.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import op.mobile.dev.quiz.databinding.FragmentQuizPlayBinding

class QuizPlayFragment : Fragment() {

    private val questions: MutableList<Question> = mutableListOf(
        Question(
            text = "What is the capital of Australia?",
            answers = listOf("Canberra", "Adelaide", "Brisbane", "Sydney")
        ),
        Question(
            text = "What is the capital of Brazil?",
            answers = listOf("Brasília", "Porto Alegre", "Rio de Janeiro", "São Paulo")
        ),
        Question(
            text = "What is the capital of New Zealand?",
            answers = listOf("Wellington", "Auckland", "Christchurch", "Dunedin")
        )
    )
    private var questionIdx = 0

    lateinit var currQuestion: Question
    lateinit var answers: MutableList<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentQuizPlayBinding>(
            inflater, R.layout.fragment_quiz_play, container, false
        )

        binding.quiz = this

        randomiseQuestions()

        binding.btnSubmitAnswer.setOnClickListener { view: View ->
            val checkedAnswerRdoBtnId = binding.rdoGroupAnswers.checkedRadioButtonId

            if (checkedAnswerRdoBtnId != -1) {
                var answerIdx = 0

                when (checkedAnswerRdoBtnId) {
                    R.id.rdo_btn_answer_two -> answerIdx = 1
                    R.id.rdo_btn_answer_three -> answerIdx = 2
                    R.id.rdo_btn_answer_four -> answerIdx = 3
                }

                if (answers[answerIdx] == currQuestion.answers[0]) {
                    questionIdx++
                    if (questionIdx < questions.size) {
                        currQuestion = questions[questionIdx]
                        setQuestion()
                        binding.rdoGroupAnswers.clearCheck()
                        binding.invalidateAll()
                    } else {
                        view.findNavController()
                            .navigate(R.id.action_quiz_play_to_quiz_win)
                    }
                } else {
                    Toast.makeText(
                        activity,
                        "The answer was ${currQuestion.answers[0]}",
                        Toast.LENGTH_SHORT
                    ).show()
                    view.findNavController()
                        .navigate(R.id.action_quiz_play_to_quiz_lose)
                }
            } else {
                Toast.makeText(
                    activity,
                    "Please select an answer",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        return binding.root
    }

    private fun setQuestion() {
        currQuestion = questions[questionIdx]
        answers = currQuestion.answers.toMutableList()
        answers.shuffle()
    }

    private fun randomiseQuestions() {
        questions.shuffle()
        questionIdx = 0
        setQuestion()
    }
}