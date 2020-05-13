package com.example.graysonorr.a111sensors

import android.content.Context
import android.hardware.Sensor
import android.hardware.Sensor.*
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

import java.text.DecimalFormat
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var mSensorLstView: ListView
    private lateinit var mProxTxtView: TextView
    private lateinit var mXTxtView: TextView
    private lateinit var mYTxtView: TextView
    private lateinit var mZTxtView: TextView
    private lateinit var mSensorManager: SensorManager
    private lateinit var mProximity: Sensor
    private lateinit var mAccelerometer: Sensor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSensorLstView = findViewById(R.id.lst_view_sensors)
        mProxTxtView = findViewById(R.id.txt_view_prox)
        mXTxtView = findViewById(R.id.txt_view_x)
        mYTxtView = findViewById(R.id.txt_view_y)
        mZTxtView = findViewById(R.id.txt_view_z)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        bindSensorAdapter(phoneSensors())
        val proximityEventListener = ProximityEventListener()
        val accelerometerEventListener = AccelerometerEventListener()

        if (checkSensorAvailability(TYPE_PROXIMITY)) {
            mProximity = mSensorManager.getDefaultSensor(TYPE_PROXIMITY)
            mSensorManager.registerListener(proximityEventListener, mProximity, SensorManager.SENSOR_DELAY_NORMAL)
        }

        if (checkSensorAvailability(TYPE_ACCELEROMETER)) {
            mAccelerometer = mSensorManager.getDefaultSensor(TYPE_ACCELEROMETER)
            mSensorManager.registerListener(accelerometerEventListener, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    private fun phoneSensors(): ArrayList<String> {
        val deviceSensors = mSensorManager.getSensorList(TYPE_ALL)
        val sensorList = ArrayList<String>()
        for (sensor in deviceSensors)
            sensorList.add(sensor.name)
        return sensorList
    }

    private fun bindSensorAdapter(sensorList: ArrayList<String>) {
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, sensorList)
        mSensorLstView.adapter = adapter
    }

    private fun checkSensorAvailability(sensor: Int): Boolean {
        when (sensor) {
            TYPE_PROXIMITY -> return mSensorManager.getDefaultSensor(TYPE_PROXIMITY) != null
            TYPE_ACCELEROMETER -> return mSensorManager.getDefaultSensor(TYPE_ACCELEROMETER) != null
            else -> return false
        }
    }

    inner class ProximityEventListener : SensorEventListener {
        override fun onSensorChanged(sensorEvent: SensorEvent) {
            val distance = sensorEvent.values[0].toDouble()
            val selfProximity: String
            selfProximity = if (distance < resources.getInteger(R.integer.proximity))
                getString(R.string.you_are_near)
            else getString(R.string.you_are_far)
            mProxTxtView.text = selfProximity
        }

        override fun onAccuracyChanged(sensor: Sensor, i: Int) {

        }
    }

    inner class AccelerometerEventListener : SensorEventListener {
        override fun onSensorChanged(sensorEvent: SensorEvent) {
            val x = sensorEvent.values[0]
            val y = sensorEvent.values[1]
            val z = sensorEvent.values[2]

            val decFormat = DecimalFormat(getString(R.string.two_decimal_points)) // Round to 2 decimal point
            mYTxtView.text = getString(R.string.y_cord) + decFormat.format(y.toDouble())
            mZTxtView.text = getString(R.string.z_cord) + decFormat.format(z.toDouble())
            mXTxtView.text = getString(R.string.x_cord) + decFormat.format(x.toDouble())
        }

        override fun onAccuracyChanged(sensor: Sensor, i: Int) {

        }
    }
}



