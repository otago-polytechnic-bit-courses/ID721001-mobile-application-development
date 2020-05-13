package com.example.graysonorr.a111sensor2

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {
    private lateinit var mSensorManager: SensorManager
    private lateinit var mSensor: Sensor
    private lateinit var mClownFaceImgView: ImageView
    private lateinit var mCordTxtView: TextView
    private lateinit var mSensorEvnt: OnSensorEventHandler
    private var mXSpeed: Float = 0.toFloat()
    private var mYSpeed: Float = 0.toFloat()
    private var mCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        mClownFaceImgView = findViewById(R.id.img_view_clown_face)
        mCordTxtView = findViewById(R.id.txt_view_cords)
        mXSpeed = 0f
        mYSpeed = 0f
        mSensorEvnt = OnSensorEventHandler()
        mCount = 0
    }

    override fun onResume() {
        super.onResume()
        mSensorManager.registerListener(mSensorEvnt, mSensor, 1000)
    }

    override fun onStop() {
        super.onStop()
        mSensorManager.unregisterListener(mSensorEvnt)
    }

    inner class OnSensorEventHandler : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            mXSpeed = event.values[0]
            mYSpeed = event.values[1]
            acceleration(mXSpeed, mYSpeed)
            moveClownFace(event.values[0], event.values[1])
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        }
    }

    fun moveClownFace(xSpeed: Float, ySpeed: Float) {
        val xAxis = mClownFaceImgView.x - xSpeed
        val yAxis = mClownFaceImgView.y + ySpeed
        val xPos = Math.round(xAxis)
        val yPos = Math.round(yAxis)
        mClownFaceImgView.x = xPos.toFloat()
        mClownFaceImgView.y = yPos.toFloat()
    }

    fun acceleration(x: Float, y: Float) {
        val decFormat = DecimalFormat("#.##")
        mCordTxtView.text = "X: " + decFormat.format(x.toDouble()) + " Y: " + decFormat.format(y.toDouble())
    }
}

