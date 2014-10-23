package com.kenai.function.sensor;

import java.util.List;

import com.kenai.function.message.XLog;


import android.content.Context;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public abstract class XSensorListener {
	private final SensorEventListener listener = new SensorEventListener() {

		public void onSensorChanged(SensorEvent event) {
			doInformation(event);
		}

		public void onAccuracyChanged(android.hardware.Sensor sensor,
				int accuracy) {
			XLog.xLog("sensor onAccuracyChanged");
		}

	};
	private SensorManager mSensorManager;
	private final int id_sensor;
	private Context context;
	private boolean isBind;

	// //////////////////////////////////////////////////////////////

	public abstract void doInformation(SensorEvent event);

	// ///////////////////////////////////////////////////////////////
	public XSensorListener(int id_sensor, Context context) {
		XLog.xLog("sensor create");
		this.context = context;
		this.id_sensor = id_sensor;
	}

	/**
	 * 已经含有解绑保护
	 */
	public void munbindSensor() {
		if (isBind) {
			XLog.xLog("sensor unbind");
			mSensorManager.unregisterListener(listener);
			isBind = false;
		}

	}

	/**
	 * 含有绑定保护
	 */
	public void mbindSensor() {

		mSensorManager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);
		List<android.hardware.Sensor> sensors1 = mSensorManager
				.getSensorList(id_sensor);

		if (sensors1.size() > 0) {
			android.hardware.Sensor sensor = sensors1.get(0);

			munbindSensor();
			isBind = mSensorManager.registerListener(listener, sensor,
					SensorManager.SENSOR_DELAY_NORMAL);
			XLog.xLog("sensor bind");

		}

	}

	public void mbindSensor_super() {

		mSensorManager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);
		List<android.hardware.Sensor> sensors1 = mSensorManager
				.getSensorList(id_sensor);

		if (sensors1.size() > 0) {
			android.hardware.Sensor sensor = sensors1.get(0);
			munbindSensor();
			isBind = mSensorManager.registerListener(listener, sensor,
					SensorManager.SENSOR_DELAY_UI);
			XLog.xLog("sensor bind");
		}

	}

	public void clear() {
		XLog.xLog("sensor clear()");
	}
	public boolean get_isbind(){
		return isBind;
	}

	public static boolean hasSensor(Context context, int id_sensor) {
		SensorManager mSensorManager = (SensorManager) context
				.getSystemService(Context.SENSOR_SERVICE);
		List<android.hardware.Sensor> sensors1 = mSensorManager
				.getSensorList(id_sensor);
        return sensors1.size() > 0;
	}
}
