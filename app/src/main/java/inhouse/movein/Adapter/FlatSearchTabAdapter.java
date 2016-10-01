package inhouse.movein.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;

import inhouse.movein.Fragment.FlatSearchGalleryFragment;
import inhouse.movein.Fragment.FlatSearchListFragment;
import inhouse.movein.Fragment.FlatSearchMapFragment;

/**
 * Created by MMT5575 on 17-09-2016.
 */

public class FlatSearchTabAdapter extends FragmentPagerAdapter {


    private static final int LIST_TAB = 0;
    private static final int GALLERY_TAB = 1;
    private static final int MAPS_TAB = 2;
    private static final String TAG = FlatSearchTabAdapter.class.getName();
    private int tabCount;

    public FlatSearchTabAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }


    @Nullable
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case LIST_TAB: {
                FlatSearchListFragment flatSearchListFragment = new FlatSearchListFragment();
                return flatSearchListFragment;
            }
            case GALLERY_TAB: {
                FlatSearchGalleryFragment flatSearchGalleryFragment = new FlatSearchGalleryFragment();
                return flatSearchGalleryFragment;
            }
            case MAPS_TAB: {
                FlatSearchMapFragment  flatSearchMapFragment = new FlatSearchMapFragment();
                return flatSearchMapFragment;
            }
            default:
                Log.i(TAG, "None of them clicked");
                break;

        }
        return null;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
