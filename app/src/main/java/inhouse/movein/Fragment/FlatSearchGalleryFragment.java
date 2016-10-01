package inhouse.movein.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import inhouse.movein.Activity.FlatSearchView;
import inhouse.movein.Adapter.FlatSearchGalleryAdapter;
import inhouse.movein.Model.SearchModel;
import inhouse.movein.Presenter.FlatSearchPresenter;
import inhouse.movein.R;
import inhouse.movein.Service.FlatSearchService;

/**
 * Created by MMT5575 on 17-09-2016.
 */

public class FlatSearchGalleryFragment extends BaseFragment implements FlatSearchView {
    @Bind(R.id.flt_srch_gallery_rc_view)
    RecyclerView mRecyclerView;
    private FlatSearchGalleryAdapter mFlatSearchGalleryAdapter;
    @Bind(R.id.flt_srch_gallery_progress_bar)
    ProgressBar mProgressBar;
    private FlatSearchPresenter flatSearchPresenter;
    @Inject
    FlatSearchService service;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.flat_search_gallery_layout, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        flatSearchPresenter = new FlatSearchPresenter(service, this);
        flatSearchPresenter.getSearchResponse();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    @Override
    public void showWait() {
        if (null != mProgressBar) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void removeWait() {
        if (null != mProgressBar) {
            mProgressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFailure(String appErrorMessage) {
        Toast.makeText(getActivity(), appErrorMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void getSearchResponse(SearchModel searchModel) {
        if (null == mFlatSearchGalleryAdapter) {
            mFlatSearchGalleryAdapter = new FlatSearchGalleryAdapter(this, searchModel);
            mRecyclerView.setAdapter(mFlatSearchGalleryAdapter);
        } else {
            mFlatSearchGalleryAdapter.notifyDataSetChanged();
        }
    }
}
