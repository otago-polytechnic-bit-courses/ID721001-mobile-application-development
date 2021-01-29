package op.mobile.app.dev.sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class MainActivity : AppCompatActivity(), SensorEventListener {

    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometerSensorTxtView: TextView
    private lateinit var accelerometerSensor: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        val sensorList = sensorManager.getSensorList(Sensor.TYPE_ALL)

        val sensorText = StringBuilder()
        for (currentSensor in sensorList.take(10)) {
            sensorText.append(currentSensor.name).append("\n")
        }

        val sensorTextView: TextView = findViewById(R.id.txt_view_available_sensors)
        sensorTextView.text = sensorText

        accelerometerSensorTxtView = findViewById(R.id.txt_view_accelerometer_sensor)

        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onStart() {
        super.onStart()
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onStop() {
        super.onStop()
        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(sensorEvent: SensorEvent) {
        val x = sensorEvent.values[0]
        val y = sensorEvent.values[1]
        val z = sensorEvent.values[2]
        accelerometerSensorTxtView.text = getString(R.string.accelerometer_sensor_value, x, y, z)
    }

    override fun onAccuracyChanged(sensor: Sensor, i: Int) {}
}