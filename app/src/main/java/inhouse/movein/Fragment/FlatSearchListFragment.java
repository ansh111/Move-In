package inhouse.movein.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
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
import inhouse.movein.Adapter.FlatSearchListAdapter;
import inhouse.movein.Model.SearchModel;
import inhouse.movein.Presenter.FlatSearchPresenter;
import inhouse.movein.R;
import inhouse.movein.Service.FlatSearchService;

/**
 * Created by MMT5575 on 17-09-2016.
 */

public class FlatSearchListFragment extends BaseFragment implements FlatSearchView, SwipeRefreshLayout.OnRefreshListener {

    @Nullable
    @Bind(R.id.flt_srch_list_rc_view)
    RecyclerView mRecyclerView;
    private FlatSearchListAdapter mFlatSearchListAdapter;
    @Nullable
    @Bind(R.id.flt_srch_list_progress_bar)
    ProgressBar mProgressBar;
    @Bind(R.id.flt_srch_list_refresh)
    SwipeRefreshLayout mSwipeRefreshLayout;
    @Inject
    public FlatSearchService service;
    private FlatSearchPresenter flatSearchPresenter;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getApplicationComponent().inject(this);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.flat_search_list_layout, container, false);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        flatSearchPresenter = new FlatSearchPresenter(service, this);
        flatSearchPresenter.getSearchResponse();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mSwipeRefreshLayout.setOnRefreshListener(this);

    }
    @Override
    public void showWait() {
        if (null != mProgressBar && !mSwipeRefreshLayout.isRefreshing()) {
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
        if(null != getActivity()) {
            Toast.makeText(getActivity(), appErrorMessage, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void getSearchResponse(SearchModel searchModel) {
        if (null == mFlatSearchListAdapter) {
            mFlatSearchListAdapter = new FlatSearchListAdapter(this, searchModel);
            mRecyclerView.setAdapter(mFlatSearchListAdapter);
        } else {
            mFlatSearchListAdapter.notifyDataSetChanged();
        }
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onRefresh() {
        flatSearchPresenter.getSearchResponse();
    }

}
