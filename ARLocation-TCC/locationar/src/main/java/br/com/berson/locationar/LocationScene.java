package br.com.berson.locationar;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.util.Log;

import com.google.ar.sceneform.ArSceneView;
import com.google.ar.core.Anchor;
import com.google.ar.core.Frame;
import com.google.ar.core.Pose;
import com.google.ar.core.Session;

import java.util.ArrayList;

import br.com.berson.locationar.rendering.LocationNode;
import br.com.berson.locationar.sensor.DeviceLocation;
import br.com.berson.locationar.sensor.DeviceLocationChanged;
import br.com.berson.locationar.sensor.DeviceOrientation;
import br.com.berson.locationar.utils.LocationUtils;

public class LocationScene {

    public ArSceneView mArSceneView;
    public DeviceLocation deviceLocation;
    public DeviceOrientation deviceOrientation;
    public Context mContext;
    public Activity mActivity;
    public ArrayList<LocationMarker> mLocationMarkers = new ArrayList<>();

    private int anchorRefreshInterval = 1000 * 5; // 5 seconds

    private int distanceLimit = 20;
    private boolean offsetOverlapping = false;

    private int bearingAdjustment = 0;
    private String TAG = "LocationScene";
    private boolean anchorsNeedRefresh = true;
    private boolean minimalRefreshing = false;
    private boolean refreshAnchorsAsLocationChanges = false;
    private Handler mHandler = new Handler();

    Runnable anchorRefreshTask = new Runnable() {
        @Override
        public void run() {
            anchorsNeedRefresh = true;
            mHandler.postDelayed(anchorRefreshTask, anchorRefreshInterval);
        }
    };

    private boolean debugEnabled = false;
    private Session mSession;
    private DeviceLocationChanged locationChangedEvent;

    public LocationScene(Context mContext, Activity mActivity, ArSceneView mArSceneView) {
        Log.i(TAG, "Cena iniciada.");
        this.mContext = mContext;
        this.mActivity = mActivity;
        this.mSession = mArSceneView.getSession();
        this.mArSceneView = mArSceneView;

        startCalculationTask();

        deviceLocation = new DeviceLocation(this);
        deviceOrientation = new DeviceOrientation(this);
        deviceOrientation.resume();
    }

    public boolean isDebugEnabled() {
        return debugEnabled;
    }

    public void setDebugEnabled(boolean debugEnabled) {
        this.debugEnabled = debugEnabled;
    }

    public boolean minimalRefreshing() {
        return minimalRefreshing;
    }

    public void setMinimalRefreshing(boolean minimalRefreshing) {
        this.minimalRefreshing = minimalRefreshing;
    }

    public boolean refreshAnchorsAsLocationChanges() {
        return refreshAnchorsAsLocationChanges;
    }

    public void setRefreshAnchorsAsLocationChanges(boolean refreshAnchorsAsLocationChanges) {
        if (refreshAnchorsAsLocationChanges) {
            stopCalculationTask();
        } else {
            startCalculationTask();
        }
        refreshAnchors();
        this.refreshAnchorsAsLocationChanges = refreshAnchorsAsLocationChanges;
    }

    public DeviceLocationChanged getLocationChangedEvent() {
        return locationChangedEvent;
    }


    public void setLocationChangedEvent(DeviceLocationChanged locationChangedEvent) {
        this.locationChangedEvent = locationChangedEvent;
    }

    public int getAnchorRefreshInterval() {
        return anchorRefreshInterval;
    }

    public void setAnchorRefreshInterval(int anchorRefreshInterval) {
        this.anchorRefreshInterval = anchorRefreshInterval;
        stopCalculationTask();
        startCalculationTask();
    }

    public void clearMarkers() {
        for (LocationMarker lm : mLocationMarkers) {
            if (lm.anchorNode != null) {
                lm.anchorNode.getAnchor().detach();
                lm.anchorNode.setEnabled(false);
                lm.anchorNode = null;
            }

        }
        mLocationMarkers = new ArrayList<>();
    }


    public int getDistanceLimit() {
        return distanceLimit;
    }


    public void setDistanceLimit(int distanceLimit) {
        this.distanceLimit = distanceLimit;
    }

    public boolean shouldOffsetOverlapping() {
        return offsetOverlapping;
    }

    public void setOffsetOverlapping(boolean offsetOverlapping) {
        this.offsetOverlapping = offsetOverlapping;
    }

    public void processFrame(Frame frame) {
        refreshAnchorsIfRequired(frame);
    }

    public void refreshAnchors() {
        anchorsNeedRefresh = true;
    }

    private void refreshAnchorsIfRequired(Frame frame) {
        if (anchorsNeedRefresh) {
            Log.i(TAG, "Atualizando ancoras...");
            anchorsNeedRefresh = false;

            if (deviceLocation == null || deviceLocation.currentBestLocation == null) {
                Log.i(TAG, "Localização ainda não estabelecida");
                return;
            }


            //É AQUI QUE PEGO AS MARCAS
            for (int i = 0; i < mLocationMarkers.size(); i++) {
                Log.e("LocationScene LATITUDE SETADA", mLocationMarkers.get(i).latitude+"");
                Log.e("LocationScene LONGITUDE SETADA", mLocationMarkers.get(i).longitude+"");
                try {

                    int markerDistance = (int) Math.round(
                            LocationUtils.distance(
                                    mLocationMarkers.get(i).latitude,
                                    deviceLocation.currentBestLocation.getLatitude(),
                                    mLocationMarkers.get(i).longitude,
                                    deviceLocation.currentBestLocation.getLongitude(),
                                    0,
                                    0)
                    );

                    if (markerDistance > mLocationMarkers.get(i).getOnlyRenderWhenWithin()) {

                        Log.i(TAG, "Not rendering. Marker distance: " + markerDistance + " Max render distance: " + mLocationMarkers.get(i).getOnlyRenderWhenWithin());
                        continue;
                    }

                    float markerBearing = deviceOrientation.currentDegree + (float) LocationUtils.bearing(
                            deviceLocation.currentBestLocation.getLatitude(),
                            deviceLocation.currentBestLocation.getLongitude(),
                            mLocationMarkers.get(i).latitude,
                            mLocationMarkers.get(i).longitude);


                    Log.e("LocationScene LATITUDE CORRENTE", deviceLocation.currentBestLocation.getLatitude()+"");
                    Log.e("LocationScene LONGITUDE CORRENTE", deviceLocation.currentBestLocation.getLongitude()+"");


                    Log.e("Latitude igual ?", String.valueOf(mLocationMarkers.get(i).latitude == deviceLocation.currentBestLocation.getLatitude()));
                    Log.e("Longitude igual ?", String.valueOf(mLocationMarkers.get(i).longitude == deviceLocation.currentBestLocation.getLongitude()));


                    //VERIFICA SE A LATITUDE E LONGITUDE SETADAS SÃO IGUAIS AS CORRENTES
//                    if ((mLocationMarkers.get(i).latitude == deviceLocation.currentBestLocation.getLatitude()) && (mLocationMarkers.get(i).longitude == deviceLocation.currentBestLocation.getLongitude())){
                        markerBearing = markerBearing + bearingAdjustment;
                        markerBearing = markerBearing % 360;

                        double rotation = Math.floor(markerBearing);

                        if (deviceOrientation.pitch > -25)
                            rotation = rotation * Math.PI / 180;

                        int renderDistance = markerDistance;

                        if (renderDistance > distanceLimit)
                            renderDistance = distanceLimit;

                        double heightAdjustment = 0;


                        int cappedRealDistance = markerDistance > 500 ? 500 : markerDistance;
                        if (renderDistance != markerDistance)
                            heightAdjustment += 0.005F * (cappedRealDistance - renderDistance);

                        float x = 0;
                        float z = -renderDistance;

                        float zRotated = (float) (z * Math.cos(rotation) - x * Math.sin(rotation));
                        float xRotated = (float) -(z * Math.sin(rotation) + x * Math.cos(rotation));


                        float y = frame.getCamera().getDisplayOrientedPose().ty();

                        if (mLocationMarkers.get(i).anchorNode != null &&
                                mLocationMarkers.get(i).anchorNode.getAnchor() != null) {
                            mLocationMarkers.get(i).anchorNode.getAnchor().detach();
                            mLocationMarkers.get(i).anchorNode.setAnchor(null);
                            mLocationMarkers.get(i).anchorNode.setEnabled(false);
                            mLocationMarkers.get(i).anchorNode = null;
                        }

                        Anchor newAnchor = mSession.createAnchor(
                                frame.getCamera().getDisplayOrientedPose()
                                        .compose(Pose.makeTranslation(xRotated, y + (float) heightAdjustment, zRotated)));


                        mLocationMarkers.get(i).anchorNode = new LocationNode(newAnchor, mLocationMarkers.get(i), this);
                        mLocationMarkers.get(i).anchorNode.setParent(mArSceneView.getScene());
                        mLocationMarkers.get(i).anchorNode.addChild(mLocationMarkers.get(i).node);

                        if (mLocationMarkers.get(i).getRenderEvent() != null) {
                            mLocationMarkers.get(i).anchorNode.setRenderEvent(mLocationMarkers.get(i).getRenderEvent());
                        }

                        mLocationMarkers.get(i).anchorNode.setScaleModifier(mLocationMarkers.get(i).getScaleModifier());
                        mLocationMarkers.get(i).anchorNode.setScalingMode(mLocationMarkers.get(i).getScalingMode());
                        mLocationMarkers.get(i).anchorNode.setGradualScalingMaxScale(mLocationMarkers.get(i).getGradualScalingMaxScale());
                        mLocationMarkers.get(i).anchorNode.setGradualScalingMinScale(mLocationMarkers.get(i).getGradualScalingMinScale());
                        mLocationMarkers.get(i).anchorNode.setHeight(mLocationMarkers.get(i).getHeight());

                        if (minimalRefreshing)
                            mLocationMarkers.get(i).anchorNode.scaleAndRotate();
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

//            System.gc();
        }
    }

    public int getBearingAdjustment() {
        return bearingAdjustment;
    }

    public void setBearingAdjustment(int i) {
        bearingAdjustment = i;
        anchorsNeedRefresh = true;
    }


    public void resume() {
        deviceOrientation.resume();
    }


    public void pause() {
        deviceOrientation.pause();
    }

    void startCalculationTask() {
        anchorRefreshTask.run();
    }

    void stopCalculationTask() {
        mHandler.removeCallbacks(anchorRefreshTask);
    }
}
