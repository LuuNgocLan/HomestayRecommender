package com.ripple.effects.fb.java.module.discover.allSpots;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.base.java.mvp.BaseFragment;
import com.base.java.mvp.IBasePresenter;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.models.homestay.Homestay;
import com.ripple.effects.fb.java.module.base.IBaseItemListener;
import com.ripple.effects.fb.java.module.detail.DetailActivity;
import com.ripple.effects.fb.java.module.discover.adapter.AllSpotsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ripple.effects.fb.java.utils.AppUtils.ID_HOMESTAY;

public class AllSpotsFragment extends BaseFragment implements IBaseItemListener, IAllSpotsContract.View {

    @BindView(R.id.rv_spots)
    RecyclerView rvSpots;

    private AllSpotsAdapter mAdapter;
    private AllSpotsPresenter mPresenter;
    List<Homestay> mHomestayList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
//        initRecyclerView();
        getData();
        return view;
    }

    private void getData() {
        if (mPresenter != null) {
            mPresenter.fetchData();
        }
    }

    private void initRecyclerView() {
        mAdapter = new AllSpotsAdapter(getContext(), 0, mHomestayList);
        mAdapter.setIBaseItemListener(this);
        rvSpots.setLayoutManager(new GridLayoutManager(getContext(), 2));
        rvSpots.setAdapter(mAdapter);
    }

    @Override
    protected IBasePresenter initPresenter() {
        return mPresenter = new AllSpotsPresenter(getContext());
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_all_spots;
    }

    @Override
    public void openDetailHomestay(String idHomestay) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(ID_HOMESTAY, idHomestay);
        startActivity(intent);
    }

    @Override
    public void onClickFavoriteHomestay(int isHomestay, boolean isFavorite) {

    }

    @Override
    public void onLoading() {

    }

    @Override
    public void onGetDataSuccess(List<Homestay> homestays) {
        if (homestays != null && homestays.size() > 0) {
            Log.d("SIZE", homestays.size() + "");
        }
        mHomestayList = homestays;
        initRecyclerView();
    }
}
