package inhouse.movein.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import inhouse.movein.Activity.MainActivity;
import inhouse.movein.Fragment.FlatSearchGalleryFragment;
import inhouse.movein.Fragment.FlatSearchListFragment;
import inhouse.movein.network.NetworkModule;

@Singleton
@Component(modules ={NetworkModule.class,})
public interface ApplicationComponent {

    void inject(FlatSearchListFragment flatSearchListFragment);
    void inject(FlatSearchGalleryFragment flatSearchGalleryFragment);


}
