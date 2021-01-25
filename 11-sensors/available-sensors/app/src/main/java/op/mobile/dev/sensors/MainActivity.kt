package op.mobile.dev.available.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        val sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)

        val sensorText = StringBuilder()
        for (currentSensor in sensorList.take(10)) {
            sensorText.append(currentSensor.name).append("\n")
        }

        val sensorTextView: TextView = findViewById(R.id.txt_view_available_sensors)
        sensorTextView.text = sensorText
    }
}