package com.sfstudio.flashlight;

import android.app.Activity;

import android.content.pm.PackageManager;
import android.hardware.Camera;

@SuppressWarnings( "deprecation" )
public class FlashLightPresenter implements
        FlashLightContract.ForwardDisplayInteractionToPresenter {

    private FlashLightContract.publishToView publishResult;
    private Activity activity; // to obtain getApplicationContext()

    private Camera camera;
    private boolean hasFlash;
    private boolean isFlashOn;
    private Camera.Parameters parameters;


    public FlashLightPresenter(FlashLightContract.publishToView publishResult,
                               Activity activity) {
        this.publishResult = publishResult;
        this.activity = activity;
    }


    // check does device support flash
    public void checkFlash() {
        hasFlash = activity.getApplicationContext().
                getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if (!hasFlash) {
            publishResult.showToastMessage("Flash not supported");
        }
    }

    public void getCamera() {
        if (camera == null) {
            try {
                camera = Camera.open();
                parameters = camera.getParameters();
            } catch (Exception e) {
                publishResult.showToastMessage("Camera Error. Failed to Open. Error");
            }
        }
    }

    public void turnOnFlash() {
        if (!isFlashOn) {
            if (camera == null || parameters == null) {
                return;
            }

            parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters(parameters);
            camera.startPreview();
            isFlashOn = true;
            publishResult.toggleButtonImage(isFlashOn);

        }
    }

    public void turnOffFlash() {
        if (isFlashOn) {
            if (camera == null || parameters == null) {
                return;
            }

            parameters = camera.getParameters();
            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters(parameters);
            camera.stopPreview();
            isFlashOn = false;
            publishResult.toggleButtonImage(isFlashOn);
        }
    }

    @Override
    public void onImageButtonClick() {
        checkFlash();
        getCamera();

        if (isFlashOn) {
            turnOffFlash();
        } else {
            turnOnFlash();
        }

    }
    
}
