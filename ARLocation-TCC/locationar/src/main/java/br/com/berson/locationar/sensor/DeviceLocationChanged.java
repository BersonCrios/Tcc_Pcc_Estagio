package br.com.berson.locationar.sensor;

import android.location.Location;

public interface DeviceLocationChanged {
    void onChange(Location location);
}
