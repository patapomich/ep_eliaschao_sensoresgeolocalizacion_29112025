package com.example.ep_eliaschao_sensoresgeolocalizacion_29112025;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView EJEX, EJEY, EJEZ, estadoAce;
    SensorManager gestorSensores;
    Sensor sensorAcelerometro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EJEX = findViewById(R.id.EJEX);
        EJEY = findViewById(R.id.EJEY);
        EJEZ = findViewById(R.id.EJEZ);

        gestorSensores = (SensorManager) getSystemService(SENSOR_SERVICE);

        sensorAcelerometro = gestorSensores.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        if (sensorAcelerometro != null) {
            gestorSensores.registerListener(this, sensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        } else {
            Toast.makeText(this, "No hay sensor de luz", Toast.LENGTH_LONG).show();
            estadoAce.setText("Sensor no disponible");
        }

    }



    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            EJEX.setText(String.format("%.2f µT", x));
            EJEY.setText(String.format("%.2f µT", y));
            EJEZ.setText(String.format("%.2f µT", z));

        }


    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (sensorAcelerometro != null) {
            gestorSensores.registerListener(this, sensorAcelerometro, SensorManager.SENSOR_DELAY_NORMAL);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        gestorSensores.unregisterListener(this);
    }


}