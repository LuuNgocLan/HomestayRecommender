package com.ltn.travel.module.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.base.java.mvp.BaseFragment;
import com.base.java.mvp.IBasePresenter;
import com.ltn.travel.R;
import com.ltn.travel.models.homestay.Homestay;
import com.ltn.travel.module.base.IBaseItemListener;
import com.ltn.travel.module.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ltn.travel.utils.AppUtils.ID_HOMESTAY;

public class SearchFragment extends BaseFragment implements ISearchContract.IView, IBaseItemListener {
    @BindView(R.id.edt_search)
    EditText mEdtSearch;
    @BindView(R.id.container_text)
    ConstraintLayout mLayoutText;
    @BindView(R.id.rv_search_result)
    RecyclerView mRvSearchResult;
    @BindView(R.id.tv_keyword)
    TextView mTvKeyword;
    @BindView(R.id.imv_research)
    ImageView mImvResearch;

    ISearchContract.IPresenter mIPresenter;
    private String keyword = "";
    private List<Homestay> mHomestayList = new ArrayList<>();
    private SearchAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected IBasePresenter initPresenter() {
        mIPresenter = new SearchPresenter(getContext());
        return mIPresenter;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_search;
    }

    @OnClick(R.id.imv_search)
    public void onClickSearch(View view) {
        hideKeyboard(getView());
        keyword = mEdtSearch.getText().toString();
        if (mIPresenter != null) {
            mIPresenter.search(keyword);
        }
    }

    private void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputMethodManager != null) {
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override
    public void onSearchSuccess(List<Homestay> homestays) {
        mLayoutText.setVisibility(View.GONE);
        mLayoutText.animate()
                .translationY(mLayoutText.getHeight())
                .alpha(0.0f)
                .setDuration(300);
        mTvKeyword.setText(homestays.size() + " results for " + keyword);
        mImvResearch.setVisibility(View.GONE);
        if (homestays != null && homestays.size() > 0) {
            mHomestayList = homestays;
            initRecyclerView();
        }
    }

    @OnClick(R.id.imv_research)
    public void onClickReSearch(View view) {

    }

    private void initRecyclerView() {
        Log.d("Search", mHomestayList.size() + " ");
        mAdapter = new SearchAdapter(getContext(), mHomestayList);
        mAdapter.setIBaseItemListener(this);
        mRvSearchResult.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        mRvSearchResult.setAdapter(mAdapter);
    }

    @Override
    public void onError() {
        Toast.makeText(getContext(), "Sorry Search failed!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openDetailHomestay(String idHomestay) {
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(ID_HOMESTAY, idHomestay);
        startActivity(intent);
    }

    @Override
    public void onClickFavoriteHomestay(String idHomestay, boolean isFavorite) {

    }
}
