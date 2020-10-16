package br.com.berson.locationar;

import android.util.Log;

import com.google.ar.sceneform.Node;

import br.com.berson.locationar.rendering.LocationNode;
import br.com.berson.locationar.rendering.LocationNodeRender;

public class LocationMarker {

    //Variáveis de localização no mundo real, lat e long
    public double longitude;
    public double latitude;

    // Ancora que representam a localização para a AR
    public LocationNode anchorNode;

    // Nó a ser renderizado
    public Node node;

    // Aqui fica a chamada de cada frame se o mesmo não form nulo
    private LocationNodeRender renderEvent;
    private float scaleModifier = 1F;
    private float height = 0F;
    private int onlyRenderWhenWithin = Integer.MAX_VALUE;
    private ScalingMode scalingMode = ScalingMode.FIXED_SIZE_ON_SCREEN;
    private float gradualScalingMinScale = 0.8F;
    private float gradualScalingMaxScale = 1.4F;


    public LocationMarker(double longitude, double latitude, Node node) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.node = node;
        Log.e("LATITUDE OBJ", this.latitude+"");
        Log.e("LONGITUDE OBJ ", this.longitude+"");
    }

    public float getGradualScalingMinScale() {
        return gradualScalingMinScale;
    }

    public void setGradualScalingMinScale(float gradualScalingMinScale) {
        this.gradualScalingMinScale = gradualScalingMinScale;
    }

    public float getGradualScalingMaxScale() {
        return gradualScalingMaxScale;
    }

    public void setGradualScalingMaxScale(float gradualScalingMaxScale) {
        this.gradualScalingMaxScale = gradualScalingMaxScale;
    }


    public int getOnlyRenderWhenWithin() {
        return onlyRenderWhenWithin;
    }


    public void setOnlyRenderWhenWithin(int onlyRenderWhenWithin) {
        this.onlyRenderWhenWithin = onlyRenderWhenWithin;
    }


    public float getHeight() {
        return height;
    }


    public void setHeight(float height) {
        this.height = height;
    }


    public ScalingMode getScalingMode() {
        return scalingMode;
    }


    public void setScalingMode(ScalingMode scalingMode) {
        this.scalingMode = scalingMode;
    }


    public float getScaleModifier() {
        return scaleModifier;
    }


    public void setScaleModifier(float scaleModifier) {
        this.scaleModifier = scaleModifier;
    }


    public LocationNodeRender getRenderEvent() {
        return renderEvent;
    }


    public void setRenderEvent(LocationNodeRender renderEvent) {
        this.renderEvent = renderEvent;
    }

    public enum ScalingMode {
        FIXED_SIZE_ON_SCREEN,
        NO_SCALING,
        GRADUAL_TO_MAX_RENDER_DISTANCE
    }

}
