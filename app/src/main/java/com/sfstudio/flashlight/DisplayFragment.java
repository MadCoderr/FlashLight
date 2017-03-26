package com.sfstudio.flashlight;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment implements
        FlashLightContract.publishToView {


    private FlashLightContract.ForwardDisplayInteractionToPresenter forwardInteraction;

    public void setPresenter(FlashLightContract.ForwardDisplayInteractionToPresenter
                                     forwardInteraction) {
        this.forwardInteraction = forwardInteraction;
    }


    private ImageButton imageButton;


    public DisplayFragment() {
        // Required empty public constructor
    }


    public static DisplayFragment newInstance() {
        return new DisplayFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_display, container, false);;

        imageButton = (ImageButton) v.findViewById(R.id.imb_display_frag);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forwardInteraction.onImageButtonClick();
            }
        });
        return v;
    }

    // to toggle on/off  image in imageButton
    @Override
    public void toggleButtonImage(boolean status) {
        if (status) {
            imageButton.setImageResource(R.drawable.onbutton);
        } else {
            imageButton.setImageResource(R.drawable.offbutton);
        }
    }

    // to show error message if device does not support flash
    @Override
    public void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }
}
