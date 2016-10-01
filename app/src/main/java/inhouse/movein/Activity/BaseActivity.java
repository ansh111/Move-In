package inhouse.movein.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;

import inhouse.movein.injection.component.ApplicationComponent;
import inhouse.movein.injection.component.DaggerApplicationComponent;
import inhouse.movein.network.NetworkModule;

/**
 * Created by MMT5575 on 24-09-2016.
 */

public class BaseActivity extends AppCompatActivity {


    ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getCacheDir(), "responses");
        mApplicationComponent = DaggerApplicationComponent.builder().networkModule(new NetworkModule(cacheFile)).build();
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
