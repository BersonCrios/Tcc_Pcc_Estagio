package br.com.berson.arlocation.Activities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import android.support.design.widget.Snackbar;

import com.google.android.gms.maps.GoogleMap;
import com.google.ar.core.Frame;
import com.google.ar.core.Plane;
import com.google.ar.core.Session;
import com.google.ar.core.TrackingState;
import com.google.ar.core.exceptions.CameraNotAvailableException;
import com.google.ar.core.exceptions.UnavailableException;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.Node;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import br.com.berson.arlocation.Controllers.RetrofitInit;
import br.com.berson.arlocation.Models.Locais;
import br.com.berson.arlocation.Models.Local;
import br.com.berson.arlocation.Utils.DemoUtils;
import br.com.berson.arlocation.R;
import br.com.berson.locationar.LocationMarker;
import br.com.berson.locationar.LocationScene;
import br.com.berson.locationar.sensor.DeviceLocation;
import br.com.berson.locationar.utils.ARLocationPermissionHelper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    private boolean installRequested;
    private boolean hasFinishedLoading = false;

    private Snackbar loadingMessageSnackbar = null;
    private ArSceneView arSceneView;

    private ModelRenderable quadroRenderable,casaRenderable, casteloRenderable, arteRenderable, dadoRender, coutionRender;

    private LocationScene locationScene;

    private TextView lat1, long1;

    private ArrayList<Local> listaLocais = new ArrayList<>();

    private TextView infos;

    @Override
    @SuppressWarnings({"AndroidApiChecker", "FutureReturnValueIgnored"})
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        widgets();

        getLocais();
    }


    public void getLocais(){
        Call<Locais> call = RetrofitInit.acessar().getLocais();
        call.enqueue(new Callback<Locais>() {
            @Override
            public void onResponse(Call<Locais> call, Response<Locais> response) {
                Log.e("Locais", response.body()+"");
                listaLocais.addAll(response.body().getLocais());


                Log.e("LIstinha ae ", listaLocais+"");


                criaModeloRenderable();
                setaObjPosi();
                askPermission();
            }

            @Override
            public void onFailure(Call<Locais> call, Throwable t) {
                Log.e("Throw", t.getMessage());
            }
        });
    }

    private void askPermission() {
        ARLocationPermissionHelper.requestPermission(this);
    }

    private void widgets() {
        arSceneView = findViewById(R.id.ar_scene_view);
        infos = findViewById(R.id.infos);
    }

    private void setaObjPosi() {

        for (int i = 0; i < listaLocais.size(); i++){
            Log.e("Longitude", listaLocais.get(i).getLongitude()+"");
            Log.e("Latitude", listaLocais.get(i).getLatitude()+"");
            Log.e("Nome", listaLocais.get(i).getNome());
        }


        arSceneView
                .getScene()
                .addOnUpdateListener(
                        frameTime -> {
                            if (!hasFinishedLoading) {
                                return;
                            }

                            if (locationScene == null) {

                                locationScene = new LocationScene(this, this, arSceneView);
                                for (int i = 0; i < listaLocais.size(); i++){
                                    Log.e("Longitude", listaLocais.get(i).getLongitude()+"");
                                    Log.e("Latitude", listaLocais.get(i).getLatitude()+"");
                                    Log.e("Nome", listaLocais.get(i).getNome());



                                    locationScene.mLocationMarkers.add(
                                            new LocationMarker(
                                                    Double.parseDouble(listaLocais.get(0).getLongitude()),
                                                    Double.parseDouble(listaLocais.get(0).getLatitude()),
                                                    getQuadro()));

                                    locationScene.mLocationMarkers.add(
                                            new LocationMarker(
                                                    Double.parseDouble(listaLocais.get(1).getLongitude()),
                                                    Double.parseDouble(listaLocais.get(1).getLatitude()),
                                                    getCasa()));

                                    //ESSE
                                    locationScene.mLocationMarkers.add(
                                            new LocationMarker(
//                                                    -48.2553061,
//                                                    -7.2014019,
                                                    Double.parseDouble(listaLocais.get(2).getLongitude()),
                                                    Double.parseDouble(listaLocais.get(2).getLatitude()),
                                                    getCastelo()));

                                    locationScene.mLocationMarkers.add(
                                            new LocationMarker(
                                                    Double.parseDouble(listaLocais.get(3).getLongitude()),
                                                    Double.parseDouble(listaLocais.get(3).getLatitude()),
                                                    getArte()));

                                    locationScene.mLocationMarkers.add(
                                            new LocationMarker(
                                                    Double.parseDouble(listaLocais.get(4).getLongitude()),
                                                    Double.parseDouble(listaLocais.get(4).getLatitude()),
                                                    getCoution()));


//                                    locationScene.mLocationMarkers.add(
//                                            new LocationMarker(
//                                                    -48.3415204,
//                                                    -10.1879355,
//                                                    getCoution()));
                                }


                            }

                            Frame frame = arSceneView.getArFrame();
                            if (frame == null) {
                                return;
                            }

                            if (frame.getCamera().getTrackingState() != TrackingState.TRACKING) {
                                return;
                            }

                            if (locationScene != null) {
                                locationScene.processFrame(frame);
                            }

                            if (loadingMessageSnackbar != null) {
                                for (Plane plane : frame.getUpdatedTrackables(Plane.class)) {
                                    if (plane.getTrackingState() == TrackingState.TRACKING) {
                                        hideLoadingMessage();
                                    }
                                }
                            }
                        });
    }

    private void criaModeloRenderable() {
        CompletableFuture<ModelRenderable> quadro = ModelRenderable.builder()
                .setSource(this, R.raw.model)
                .build();


        CompletableFuture<ModelRenderable> casa = ModelRenderable.builder()
                .setSource(this, R.raw.casa)
                .build();

        CompletableFuture<ModelRenderable> castelo = ModelRenderable.builder()
                .setSource(this, R.raw.model159)
                .build();

        CompletableFuture<ModelRenderable> arte = ModelRenderable.builder()
                .setSource(this, R.raw.model123)
                .build();


        CompletableFuture<ModelRenderable> coution = ModelRenderable.builder()
                .setSource(this, R.raw.caution)
                .build();

        CompletableFuture.allOf(quadro, casa, castelo, arte, coution).handle((notUsed, throwable) -> {
            if (throwable != null) {
                DemoUtils.displayError(this, "Unable to load renderables", throwable);
                return null;
            }
            try {
                quadroRenderable = quadro.get();
                casaRenderable = casa.get();
                casteloRenderable = castelo.get();
                arteRenderable = arte.get();
                coutionRender = coution.get();

                hasFinishedLoading = true;

            } catch (InterruptedException | ExecutionException ex) {
                DemoUtils.displayError(this, "Unable to load renderables", ex);
            }
            return null;
        });

    }



    private Node getQuadro() {
        Node base = new Node();
        base.setRenderable(quadroRenderable);
        Context c = this;
        base.setOnTapListener((v, event) -> {
            Toast.makeText(
                    c, "Quadro tocado.", Toast.LENGTH_LONG)
                    .show();
        });
        return base;
    }

    private Node getCasa() {
        Node base = new Node();
        base.setRenderable(casaRenderable);
        Context c = this;
        base.setOnTapListener((v, event) -> {
            Toast.makeText(
                    c, "Casa tocada.", Toast.LENGTH_LONG)
                    .show();
        });
        return base;
    }

    private Node getCastelo() {
        Node base = new Node();
        base.setRenderable(casteloRenderable);
        base.setOnTapListener((v, event) -> {
            infos.setText("Castelo (em latim: castellum) é um tipo de estrutura fortificada, " +
                    "construída na Europa e Oriente Médio durante a Idade Média pelos nobres europeus." +
                    " A palavra castelo é definida pelos estudiosos, como sendo a residência particular fortificada de um lorde ou nobre, " +
                    "sendo diferente de palácio, que não é fortificado;");
        });
        return base;
    }
    private Node getArte() {
        Node base = new Node();
        base.setRenderable(arteRenderable);
        Context c = this;
        base.setOnTapListener((v, event) -> {
            Toast.makeText(
                    c, "arte tocada.", Toast.LENGTH_LONG)
                    .show();
        });
        return base;
    }

    private Node getDado() {
        Node base = new Node();
        base.setRenderable(dadoRender);
        Context c = this;
        base.setOnTapListener((v, event) -> {
            Toast.makeText(
                    c, "dado tocada.", Toast.LENGTH_LONG)
                    .show();
        });
        return base;
    }
    private Node getCoution() {
        Node base = new Node();
        base.setRenderable(coutionRender);
        Context c = this;
        base.setOnTapListener((v, event) -> {
            Toast.makeText(
                    c, "coution tocada.", Toast.LENGTH_LONG)
                    .show();
        });
        return base;
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (locationScene != null) {
            locationScene.resume();
        }

        if (arSceneView.getSession() == null) {
            try {
                Session session = DemoUtils.createArSession(this, installRequested);
                if (session == null) {
                    installRequested = ARLocationPermissionHelper.hasPermission(this);
                    return;
                } else {
                    arSceneView.setupSession(session);
                }
            } catch (UnavailableException e) {
                DemoUtils.handleSessionException(this, e);
            }
        }

        try {
            arSceneView.resume();
        } catch (CameraNotAvailableException ex) {
            DemoUtils.displayError(this, "Unable to get camera", ex);
            finish();
            return;
        }

        if (arSceneView.getSession() != null) {
            showLoadingMessage();
        }
    }

    @Override
    public void onPause() {
        super.onPause();

        if (locationScene != null) {
            locationScene.pause();
        }

        arSceneView.pause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        arSceneView.destroy();
    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode, @NonNull String[] permissions, @NonNull int[] results) {
        if (!ARLocationPermissionHelper.hasPermission(this)) {
            if (!ARLocationPermissionHelper.shouldShowRequestPermissionRationale(this)) {
                // Permission denied with checking "Do not ask again".
                ARLocationPermissionHelper.launchPermissionSettings(this);
            } else {
                Toast.makeText(
                        this, "Camera permission is needed to run this application", Toast.LENGTH_LONG)
                        .show();
            }
            finish();
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            // Standard Android full-screen functionality.
            getWindow()
                    .getDecorView()
                    .setSystemUiVisibility(
                            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
    }

    private void showLoadingMessage() {
        if (loadingMessageSnackbar != null && loadingMessageSnackbar.isShownOrQueued()) {
            return;
        }

        loadingMessageSnackbar =
                Snackbar.make(
                        MainActivity.this.findViewById(android.R.id.content),
                        R.string.plane_finding,
                        Snackbar.LENGTH_INDEFINITE);
        loadingMessageSnackbar.getView().setBackgroundColor(0xbf323232);
        loadingMessageSnackbar.show();
    }

    private void hideLoadingMessage() {
        if (loadingMessageSnackbar == null) {
            return;
        }

        loadingMessageSnackbar.dismiss();
        loadingMessageSnackbar = null;
    }
}






















//public class MainActivity extends AppCompatActivity {
//
//    private static final String TAG = MainActivity.class.getSimpleName();
//    private static final double MIN_OPENGL_VERSION = 3.0;
//
//    private ArFragment arFragment;
//    private ModelRenderable placa;
//
//
//    private LocationScene locationScene;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.camera_ar);
//
//        renderModel();
//        setObject();
//    }
//
//    private void renderModel() {
//        ModelRenderable
//                .builder()
//                .setSource(this, R.raw.model)
//                .build()
//                .thenAccept(renderable -> placa = renderable)
//                .exceptionally(
//                        throwable -> {
//                            Toast toast =
//                                    Toast.makeText(this, "Unable to load model renderable", Toast.LENGTH_LONG);
//                            toast.setGravity(Gravity.CENTER, 0, 0);
//                            toast.show();
//                            return null;
//                        });
//    }
//
//    private void setObject() {
//
//        arFragment.setOnTapArPlaneListener(
//                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
//                    if (placa == null) {
//                        return;
//                    }
//
//                    if (plane.getType() != Plane.Type.HORIZONTAL_UPWARD_FACING){
//                        return;
//                    }
//
//                    //CRIA UMA ANCORA
//                    Anchor anchor = hitResult.createAnchor();
//                    AnchorNode anchorNode = new AnchorNode(anchor);
//                    anchorNode.setParent(arFragment.getArSceneView().getScene());
//
//                    TransformableNode placaNode = new TransformableNode(arFragment.getTransformationSystem());
//                    placaNode.setParent(anchorNode);
//                    placaNode.setRenderable(placa);
//                    placaNode.setLocalPosition(new Vector3(0.0f, 0.25f, 0.5f));
//                    placaNode.select();
//                });
//    }
//}
