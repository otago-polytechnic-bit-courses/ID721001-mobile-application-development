package op.mobile.app.dev.graysono.travelling.ui.quiz

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import op.mobile.app.dev.graysono.travelling.model.Country
import op.mobile.app.dev.graysono.travelling.model.Quiz

class QuizViewModel(_country: Country) : ViewModel() {
    var country: Country = _country

    private val _questionIdx = MutableLiveData<Int>()
    val questionIdx: LiveData<Int> get() = _questionIdx

    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int> get() = _score

    private val _quiz = MutableLiveData<Quiz>()
    val quiz: LiveData<Quiz> get() = _quiz

    private val _answers = MutableLiveData<MutableList<String>>()
    val answers: LiveData<MutableList<String>> get() = _answers

    private val _isFinished = MutableLiveData<Boolean>()
    val isFinished: LiveData<Boolean> get() = _isFinished

    private val _time = MutableLiveData<Long>()
    private val time: LiveData<Long> get() = _time

    lateinit var countDownTimer: CountDownTimer

    val timeString = Transformations.map(time) {
        DateUtils.formatElapsedTime(it)
    }

    init {
        _questionIdx.value = 0
        _score.value = 0
    }

    fun addQuestionIdx() {
        _questionIdx.value = _questionIdx.value?.plus(1)
    }

    fun addScore() {
        _score.value = _score.value?.plus(1)
    }

    fun setQuestion() {
        _quiz.value = country.quiz[_questionIdx.value!!]
        _answers.value = quiz.value?.answers?.toMutableList()
        _answers.value?.shuffle()
    }

    fun startTimer() {
        countDownTimer = object : CountDownTimer(THREE_MINUTES, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _time.value = millisUntilFinished / ONE_SECOND
            }

            override fun onFinish() {
                _time.value = END
                isFinished()
            }
        }
        countDownTimer.start()
    }

    fun isFinished() {
        _isFinished.value = true
    }

    override fun onCleared() {
        super.onCleared()
        countDownTimer.cancel()
    }

    companion object {
        private const val END = 0L
        private const val ONE_SECOND = 1000L
        private const val THREE_MINUTES = 180000L
    }
}
