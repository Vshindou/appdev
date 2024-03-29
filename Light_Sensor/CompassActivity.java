package com.example.sensors;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CompassActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor magnetometer;
    private TextView textViewCompassData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        // Lock the screen orientation to portrait
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        // Initialize views
        textViewCompassData = findViewById(R.id.textViewCompassData);

        // Initialize sensor manager and magnetometer
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        // Check if the device has a magnetometer
        if (magnetometer == null) {
            // Handle the case where the device does not have a magnetometer
            textViewCompassData.setText("Magnetometer not available");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Register the magnetometer sensor listener
        if (magnetometer != null) {
            sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Unregister the magnetometer sensor listener to save battery
        if (magnetometer != null) {
            sensorManager.unregisterListener(this);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Check if the sensor type is the magnetometer
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float[] magneticValues = event.values;

            // Display the raw magnetometer data in the TextView
            String data = String.format("X: %.2f\nY: %.2f\nZ: %.2f",
                    magneticValues[0], magneticValues[1], magneticValues[2]);

            textViewCompassData.setText(data);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing for now
    }
}
