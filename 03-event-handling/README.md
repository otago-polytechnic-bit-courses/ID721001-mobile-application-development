# **Event Handling**

## Input Events


### MainActivity.kt
```kotlin
class MainActivity : AppCompatActivity() {
    ...
}
```

```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var txtViewSelectedDate: TextView
    private lateinit var txtViewMinutesDifference: TextView
    
    ...
}
```

```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var txtViewSelectedDate: TextView
    private lateinit var txtViewMinutesDifference: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val btnSelectDate: Button = findViewById(R.id.btn_select_date)

        txtViewSelectedDate = findViewById(R.id.txt_view_selected_date)
        txtViewMinutesDifference = findViewById(R.id.txt_view_minutes_difference)
    }
    
    ...
}
```

```kotlin
class MainActivity : AppCompatActivity() {
    private lateinit var txtViewSelectedDate: TextView
    private lateinit var txtViewMinutesDifference: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val btnSelectDate: Button = findViewById(R.id.btn_select_date)

        txtViewSelectedDate = findViewById(R.id.txt_view_selected_date)
        txtViewMinutesDifference = findViewById(R.id.txt_view_minutes_difference)
    }
    
    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val currentTimeMillis = System.currentTimeMillis()

        val datePickerDialogListener = DatePickerDialog.OnDateSetListener { _, year, month, day ->
            val selectedDate = "$day/${month + 1}/$year"
            txtViewSelectedDate.text = "Selected Date: $selecteDate"

            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val parsedSelectedDate = simpleDateFormat.parse(selectedDate)
            val selectedDateToMinutes = parsedSelectedDate!!.time / 60000
            Log.d("MainActivity", "Selected date to minutes: $selectedDateToMinutes")

            val currentDate =
                simpleDateFormat.parse(simpleDateFormat.format(currentTimeMillis))
            val currentDateToMinutes = currentDate!!.time / 60000
            Log.d("MainActivity", "Current date to minutes: $currentDateToMinutes")

            val differenceInMinutes = currentDateToMinutes - selectedDateToMinutes
            txtViewMinutesDifference.text = "You are $differenceInMinutes minutes old"
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
```

## Activity ✏️

## Practical
The practical for this topic is available [here]().
