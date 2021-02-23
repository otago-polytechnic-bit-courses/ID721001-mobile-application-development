package op.mobile.app.dev.age.calculator

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var tVSelectedDate: TextView
    private lateinit var tVMinutesDifference: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSelectDate: Button = findViewById(R.id.btn_select_date)
        btnSelectDate.setOnClickListener {}

        tVSelectedDate = findViewById(R.id.tv_selected_date)
        tVMinutesDifference = findViewById(R.id.tv_minutes_difference)
    }

    private fun onDatePicker() {
        val calendar = Calendar.getInstance()
        val currentTimeMillis = System.currentTimeMillis()

        val datePickerDialogListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            val selectedDate = "$day/${month + 1}/$year"
            tVSelectedDate.text = getString(R.string.selected_date, selectedDate)

            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val parsedSelectedDate = simpleDateFormat.parse(selectedDate)
            val selectedDateToMinutes = parsedSelectedDate!!.time / 60000

            val currentDate =
                simpleDateFormat.parse(simpleDateFormat.format(currentTimeMillis))
            val currentDateToMinutes = currentDate!!.time / 60000

            val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes
            tVMinutesDifference.text =
                getString(R.string.minutes_difference, differenceInMinutes.toString())
        }

        val datePickerDialog = DatePickerDialog(
            this@MainActivity,
            datePickerDialogListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.datePicker.maxDate = currentTimeMillis
        datePickerDialog.show()
    }
}