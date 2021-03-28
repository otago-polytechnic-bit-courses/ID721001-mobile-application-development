package op.mobile.app.dev.bottom.navigation.ui.dashboard

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {
    private val timer: CountDownTimer

    private val _count = MutableLiveData<Int>()
    val count: LiveData<Int> get() = _count

    private val _countdownTime = MutableLiveData<Long>()
    private val countdownTime: LiveData<Long> get() = _countdownTime

    val countdownTimeString = Transformations.map(countdownTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    init {
        _count.value = 0

        timer = object : CountDownTimer(ONE_MINUTE, ONE_SECOND) {
            override fun onTick(millisUntilFinished: Long) {
                _countdownTime.value = millisUntilFinished / ONE_SECOND
            }

            override fun onFinish() {
                _countdownTime.value = END
                reset()
            }
        }
        timer.start()
    }

    fun plusOne() {
        _count.value = _count.value?.plus(1)
    }

    fun reset() {
        _count.value = 0
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    companion object {
        private const val END = 0L
        private const val ONE_SECOND = 1000L
        private const val ONE_MINUTE = 60000L
    }
}