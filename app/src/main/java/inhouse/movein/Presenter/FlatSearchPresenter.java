package inhouse.movein.Presenter;

import android.support.annotation.NonNull;

import inhouse.movein.Activity.FlatSearchView;
import inhouse.movein.Model.SearchModel;
import inhouse.movein.Service.FlatSearchService;
import inhouse.movein.network.NetworkError;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by MMT5575 on 24-09-2016.
 */

public class FlatSearchPresenter {
   private FlatSearchService mService;
    private FlatSearchView mFlatSearchView;
    private CompositeSubscription mCompositeSubscription;

    public FlatSearchPresenter(FlatSearchService mService, FlatSearchView mFlatSearchView) {
        this.mService = mService;
        this.mFlatSearchView = mFlatSearchView;
        this.mCompositeSubscription =new CompositeSubscription();
    }
    public void getSearchResponse() {
        mFlatSearchView.showWait();

        Subscription subscription = mService.getSearchResponse(new FlatSearchService.GetSearchResponseCallback() {
            @Override
            public void onSuccess(SearchModel searchResponse) {
                mFlatSearchView.removeWait();
                mFlatSearchView.getSearchResponse(searchResponse);
            }

            @Override
            public void onError(@NonNull NetworkError networkError) {
                mFlatSearchView.removeWait();
                mFlatSearchView.onFailure(networkError.getAppErrorMessage());
            }

        });

        mCompositeSubscription.add(subscription);
    }
    public void onStop() {
        mCompositeSubscription.unsubscribe();
    }
}
