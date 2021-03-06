package br.com.berson.artourism.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.widget.Toast;
import com.google.ar.core.Anchor;
import com.google.ar.core.HitResult;
import com.google.ar.core.Plane;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import br.com.berson.artourism.R;

public class CameraActivity extends AppCompatActivity {

    private static final String TAG = CameraActivity.class.getSimpleName();
    private static final double MIN_OPENGL_VERSION = 3.0;

    private ArFragment arFragment;
    private ModelRenderable placa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!checkIsSupportedDeviceOrFinish(this)) {
            return;
        }

        setContentView(R.layout.activity_camera);

        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.ux_fragment);


        ModelRenderable
                .builder()
                .setSource(this, R  .raw.board)
                .build()
                .thenAccept(renderable -> placa = renderable)
                .exceptionally(
                        throwable -> {
                            Toast toast =
                                    Toast.makeText(this, "Unable to load model renderable", Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.CENTER, 0, 0);
                            toast.show();
                            return null;
                        });

        //SETA O OBJETO 3D AO CLIQUE, AQUI SERÁ PASSADO A GEOLOCALIZAÇÃO
        arFragment.setOnTapArPlaneListener(
                (HitResult hitResult, Plane plane, MotionEvent motionEvent) -> {
                    if (placa == null) {
                        return;
                    }


                    //CRIA UMA ÂNCORA
                    Anchor anchor = hitResult.createAnchor();
                    AnchorNode anchorNode = new AnchorNode(anchor);
                    anchorNode.setParent(arFragment.getArSceneView().getScene());


                    //SETA O OBJETO 3D NO NÓ DA ÂNCORA
                    TransformableNode placaNode = new TransformableNode(arFragment.getTransformationSystem());
                    placaNode.setParent(anchorNode);
                    placaNode.setRenderable(placa);
                    placaNode.select();
                });
    }





    //VERIFICAÇÕES DE CONDIÇÕES DO TELEFONE, E USO DO SDK SCENEFORM ARCORE E OpenGL ES 3.0
    public static boolean checkIsSupportedDeviceOrFinish(final Activity activity) {
        if (Build.VERSION.SDK_INT < VERSION_CODES.N) {
            Log.e(TAG, "Sceneform requires Android N or later");
            Toast.makeText(activity, "Sceneform requires Android N or later", Toast.LENGTH_LONG).show();
            activity.finish();
            return false;
        }
        String openGlVersionString =
                ((ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE))
                        .getDeviceConfigurationInfo()
                        .getGlEsVersion();
        if (Double.parseDouble(openGlVersionString) < MIN_OPENGL_VERSION) {
            Log.e(TAG, "Sceneform requires OpenGL ES 3.0 later");
            Toast.makeText(activity, "Sceneform requires OpenGL ES 3.0 or later", Toast.LENGTH_LONG)
                    .show();
            activity.finish();
            return false;
        }
        return true;
    }
}
