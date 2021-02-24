package op.mobile.app.dev.age.calculator

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

const val EXTRA_SELECTED_DATE = "op.mobile.app.dev.age.calculator.SELECTED_DATE"
const val EXTRA_MINUTES_DIFFERENCE = "op.mobile.app.dev.age.calculator.MINUTES_DIFFERENCE"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSelectDate: Button = findViewById(R.id.btn_select_date)
        btnSelectDate.setOnClickListener {
            onDatePicker()
        }
    }

    private fun onDatePicker() {
        val calendar = Calendar.getInstance()
        val currentTimeMillis = System.currentTimeMillis()

        val datePickerDialogListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            val selectedDate = "$day/${month + 1}/$year"

            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val parsedSelectedDate = simpleDateFormat.parse(selectedDate)
            val selectedDateToMinutes = parsedSelectedDate!!.time / 60000

            val currentDate =
                simpleDateFormat.parse(simpleDateFormat.format(currentTimeMillis))
            val currentDateToMinutes = currentDate!!.time / 60000

            val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes

            val intent = Intent(this, SecondActivity::class.java).apply {
                putExtra(EXTRA_SELECTED_DATE, getString(R.string.selected_date, selectedDate))
                putExtra(
                    EXTRA_MINUTES_DIFFERENCE,
                    getString(R.string.minutes_difference, differenceInMinutes.toString())
                )
            }
            startActivity(intent)
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
