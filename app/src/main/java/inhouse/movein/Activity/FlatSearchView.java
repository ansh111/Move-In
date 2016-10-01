package inhouse.movein.Activity;

import inhouse.movein.Model.SearchModel;

/**
 * Created by MMT5575 on 24-09-2016.
 */

public interface FlatSearchView {
    void showWait();

    void removeWait();

    void onFailure(String appErrorMessage);

    void getSearchResponse(SearchModel searchModel);
}
