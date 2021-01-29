package op.mobile.app.dev.age.calculator

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val btnSelectDate: Button = findViewById(R.id.btn_select_date)
        btnSelectDate.setOnClickListener { showDatePicker() }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val currentTimeMillis = System.currentTimeMillis()

        val datePickerDialogListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            val selectedDate = "$day/${month + 1}/$year"

            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val parsedSelectedDate = simpleDateFormat.parse(selectedDate)
            val selectedDateToMinutes = parsedSelectedDate!!.time / 60000
            Log.d("MainActivity", "Selected date to minutes: $selectedDateToMinutes")

            val currentDate =
                simpleDateFormat.parse(simpleDateFormat.format(currentTimeMillis))
            val currentDateToMinutes = currentDate!!.time / 60000
            Log.d("MainActivity", "Current date to minutes: $currentDateToMinutes")

            val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes

            val intent = Intent(this, AgeDataActivity::class.java)
            intent.putExtra("selected_date", getString(R.string.selected_date, selectedDate))
            intent.putExtra(
                "difference_in_minutes",
                getString(R.string.minutes_difference, differenceInMinutes.toString())
            )
            startActivity(intent)
        }

        val datePickerDialog = DatePickerDialog(
            this,
            datePickerDialogListener,
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )

        datePickerDialog.datePicker.maxDate = currentTimeMillis
        datePickerDialog.show()
    }
}