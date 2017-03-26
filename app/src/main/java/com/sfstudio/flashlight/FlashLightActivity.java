package com.sfstudio.flashlight;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FlashLightActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_light);

        DisplayFragment displayFragment = (DisplayFragment)
                getSupportFragmentManager().findFragmentById(R.id.frag_display);

        FlashLightPresenter presenter = new FlashLightPresenter(displayFragment, this);

        // set presenter for display fragment
        displayFragment.setPresenter(presenter);
    }
}
