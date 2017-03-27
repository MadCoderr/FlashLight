package com.sfstudio.flashlight;


public interface FlashLightContract {


    // View (Display Fragment) will handle this method
    interface publishToView {
        void toggleButtonImage(boolean status);
        void showToastMessage(String message);
    }

    // Pass Click event from view (Display Fragment) to presenter
    interface ForwardDisplayInteractionToPresenter {
        void onImageButtonClick();
    }
}
