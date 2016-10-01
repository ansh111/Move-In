package inhouse.movein.Service;

import android.app.IntentService;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import inhouse.movein.Constant.FlatSearchConstant;
import inhouse.movein.R;

import static com.google.android.gms.wearable.DataMap.TAG;

/**
 * Created by MMT5575 on 17-09-2016.
 */

public class FetchLocationAddressService extends IntentService {
    private String errorMessage;
    private ResultReceiver mReceiver;

    public FetchLocationAddressService() {
        super("FetchLocationAddressService");
    }
    public FetchLocationAddressService(String name) {
        super(name);
    }

    @Override
    protected void onHandleIntent(@NonNull Intent intent) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        Location location = intent.getParcelableExtra(
                FlatSearchConstant.LOCATION_DATA_EXTRA);
        mReceiver = intent.getParcelableExtra(FlatSearchConstant.RECEIVER);
        List<Address> addresses = null;

        try {
            addresses = geocoder.getFromLocation(
                    location.getLatitude(),
                    location.getLongitude(),
                    // In this sample, get just a single address.
                    1);
        } catch (IOException ioException) {
            // Catch network or other I/O problems.
            errorMessage = getString(R.string.service_not_available);
            Log.e(TAG, errorMessage, ioException);
        } catch (IllegalArgumentException illegalArgumentException) {
            // Catch invalid latitude or longitude values.
            errorMessage = getString(R.string.invalid_lat_long_used);
            Log.e(TAG, errorMessage + ". " +
                    "Latitude = " + location.getLatitude() +
                    ", Longitude = " +
                    location.getLongitude(), illegalArgumentException);
        }

        // Handle case where no address was found.
        if (addresses == null || addresses.size()  == 0) {
            if (errorMessage.isEmpty()) {
                errorMessage = getString(R.string.no_address_found);
                Log.e(TAG, errorMessage);
            }
            deliverResultToReceiver(FlatSearchConstant.FAILURE_RESULT, errorMessage);
        } else {
            Address address = addresses.get(0);
            ArrayList<String> addressFragments = new ArrayList<String>();

            // Fetch the address lines using getAddressLine,
            // join them, and send them to the thread.
            for(int i = 0; i < address.getMaxAddressLineIndex(); i++) {
                addressFragments.add(address.getAddressLine(i));
            }
            Log.i(TAG, getString(R.string.address_found));
            deliverResultToReceiver(FlatSearchConstant.SUCCESS_RESULT,
                    TextUtils.join(System.getProperty("line.separator"),
                            addressFragments));
        }

    }

    private void deliverResultToReceiver(int failureResult, String errorMessage) {
        Bundle bundle = new Bundle();
        bundle.putString(FlatSearchConstant.RESULT_DATA_KEY, errorMessage);
        mReceiver.send(failureResult, bundle);
    }
}
