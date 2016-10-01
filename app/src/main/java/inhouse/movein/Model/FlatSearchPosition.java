package inhouse.movein.Model;

import android.support.annotation.NonNull;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by MMT5575 on 21-09-2016.
 */

public class FlatSearchPosition {
    @NonNull
    private final LatLng mPosition;

    public FlatSearchPosition(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
    }

    @NonNull
    public LatLng getPosition() {
        return mPosition;
    }
}
