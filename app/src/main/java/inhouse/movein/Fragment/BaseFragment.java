package inhouse.movein.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import java.io.File;

import butterknife.ButterKnife;
import inhouse.movein.injection.component.ApplicationComponent;
import inhouse.movein.injection.component.DaggerApplicationComponent;
import inhouse.movein.network.NetworkModule;

/**
 * Created by MMT5575 on 24-09-2016.
 */

public class BaseFragment extends Fragment {
    ApplicationComponent mApplicationComponent;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        File cacheFile = new File(getActivity().getCacheDir(), "responses");
        mApplicationComponent = DaggerApplicationComponent.builder().networkModule(new NetworkModule(cacheFile)).build();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this,view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
