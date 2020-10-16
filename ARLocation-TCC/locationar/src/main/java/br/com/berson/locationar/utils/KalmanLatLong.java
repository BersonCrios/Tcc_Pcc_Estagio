package br.com.berson.locationar.utils;

public class KalmanLatLong {
    private final float MinAccuracy = 1;

    private float quantidade_metros_por_segundo;
    private long TimeStamp_milliseconds;
    private double lat;
    private double lng;
    private float variance; // P matrix. Negative means object uninitialised.

    public int consecutiveRejectCount;

    public KalmanLatLong(float quantidade_metros_por_segundo) {
        this.quantidade_metros_por_segundo = quantidade_metros_por_segundo;
        variance = -1;
        consecutiveRejectCount = 0;
    }

    public long get_TimeStamp() {
        return TimeStamp_milliseconds;
    }

    public double get_lat() {
        return lat;
    }

    public double get_lng() {
        return lng;
    }

    public float get_accuracy() {
        return (float) Math.sqrt(variance);
    }

    public void SetState(double lat, double lng, float accuracy,
                         long TimeStamp_milliseconds) {
        this.lat = lat;
        this.lng = lng;
        variance = accuracy * accuracy;
        this.TimeStamp_milliseconds = TimeStamp_milliseconds;
    }

    public void Process(double lat_measurement, double lng_measurement,
                        float accuracy, long TimeStamp_milliseconds, float quantidade_metros_por_segundo) {
        this.quantidade_metros_por_segundo = quantidade_metros_por_segundo;

        if (accuracy < MinAccuracy)
            accuracy = MinAccuracy;
        if (variance < 0) {
            // if variance < 0, object is unitialised, so initialise with
            // current values
            this.TimeStamp_milliseconds = TimeStamp_milliseconds;
            lat = lat_measurement;
            lng = lng_measurement;
            variance = accuracy * accuracy;
        } else {
            // else apply Kalman filter methodology

            long TimeInc_milliseconds = TimeStamp_milliseconds
                    - this.TimeStamp_milliseconds;
            if (TimeInc_milliseconds > 0) {
                // time has moved on, so the uncertainty in the current position
                // increases
                variance += TimeInc_milliseconds * quantidade_metros_por_segundo
                        * quantidade_metros_por_segundo / 1000;
                this.TimeStamp_milliseconds = TimeStamp_milliseconds;

            }

            // Kalman gain matrix K = Covarariance * Inverse(Covariance +
            // MeasurementVariance)
            // NB: because K is dimensionless, it doesn't matter that variance
            // has different units to lat and lng
            float K = variance / (variance + accuracy * accuracy);
            // apply K
            lat += K * (lat_measurement - lat);
            lng += K * (lng_measurement - lng);
            // new Covarariance matrix is (IdentityMatrix - K) * Covarariance
            variance = (1 - K) * variance;
        }
    }

    public int getConsecutiveRejectCount() {
        return consecutiveRejectCount;
    }

    public void setConsecutiveRejectCount(int consecutiveRejectCount) {
        this.consecutiveRejectCount = consecutiveRejectCount;
    }


}