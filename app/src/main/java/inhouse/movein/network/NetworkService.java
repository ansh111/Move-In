package inhouse.movein.network;

import android.support.annotation.NonNull;

import inhouse.movein.Model.SearchModel;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by ennur on 6/25/16.
 */
public interface NetworkService {

    @NonNull
    @GET("feed/")
    Observable<SearchModel> getSearchResponse();
}
