package inhouse.movein;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.multidex.MultiDexApplication;

import inhouse.movein.injection.component.ApplicationComponent;
import timber.log.Timber;


/**
 * Created by MMT5575 on 21-09-2016.
 */

public class MoveinApplication extends MultiDexApplication{

    private ApplicationComponent mApplicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
           // Fabric.with(this, new Crashlytics());
        }

    }
    @NonNull
    public static MoveinApplication get(@NonNull Context context) {
        return (MoveinApplication) context.getApplicationContext();
    }
    public ApplicationComponent getComponent() {
        return mApplicationComponent;
    }
    // Needed to replace the component with a test specific one
    public void setComponent(ApplicationComponent applicationComponent) {
        mApplicationComponent = applicationComponent;
    }
}
