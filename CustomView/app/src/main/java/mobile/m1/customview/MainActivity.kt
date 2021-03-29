package mobile.m1.customview

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import mobile.m1.customview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), SensorEventListener {

    private var mSensorManager : SensorManager? = null
    private var mAccelerometer : Sensor? = null

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sColors = listOf(
            ResourcesCompat.getColor(resources, R.color.color_red, null),
            ResourcesCompat.getColor(resources, R.color.color_yellow, null),
            ResourcesCompat.getColor(resources, R.color.color_green, null)
        )

        // get reference of the service
        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        // focus in accelerometer
        mAccelerometer = mSensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let {binding.customView.changePositionWithSensor(event.values[1], event.values[0])}
    }

    override fun onResume() {
        super.onResume()
        mSensorManager?.registerListener(this,mAccelerometer,
                SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onPause() {
        super.onPause()
        mSensorManager?.unregisterListener(this)
    }

    companion object {
        var sColors: List<Int> = listOf()
    }
}