package inhouse.movein.Service;


import android.support.annotation.NonNull;

import inhouse.movein.Model.SearchModel;
import inhouse.movein.network.NetworkError;
import inhouse.movein.network.NetworkService;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by ennur on 6/25/16.
 */
public class FlatSearchService {
    private final NetworkService networkService;

    public FlatSearchService(NetworkService networkService) {
        this.networkService = networkService;
    }

    public Subscription getSearchResponse(@NonNull final GetSearchResponseCallback callback) {

        return networkService.getSearchResponse()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(new Func1<Throwable, Observable<? extends SearchModel>>() {
                    @NonNull
                    @Override
                    public Observable<? extends SearchModel> call(Throwable throwable) {
                        return Observable.error(throwable);
                    }
                })
                .subscribe(new Subscriber<SearchModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onError(new NetworkError(e));

                    }

                    @Override
                    public void onNext(SearchModel searchResponse) {
                        callback.onSuccess(searchResponse);

                    }
                });
    }

    public interface GetSearchResponseCallback {
        void onSuccess(SearchModel searchResponse);

        void onError(NetworkError networkError);
    }
}
