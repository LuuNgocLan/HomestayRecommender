package com.ripple.effects.fb.java.module.detail.adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.base.java.core.utils.ViewUtils;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.models.detailhomestay.Homestay;
import com.ripple.effects.fb.java.models.detailhomestay.Review;
import com.ripple.effects.fb.java.widget.AvatarListCustomView;
import com.ripple.effects.fb.java.widget.ExpandableTextView;
import com.ripple.effects.fb.java.widget.SectioningAdapter;
import com.ripple.effects.fb.java.widget.indicator.CircleIndicator;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.internal.fuseable.HasUpstreamObservableSource;
import retrofit2.http.PUT;

import static com.base.java.core.utils.DimenUtils.dpToPx;

public class DetailHomestayAdapter extends SectioningAdapter {

    private final int TYPE_INFOR = 0;
    private final int TYPE_FEATURE = 1;
    private final int TYPE_MAP = 2;
    private final int TYPE_REVIEW = 3;

    private Context mContext;
    private Activity mActivity;
    private Homestay mHomestay;
    private List<HomestayDetailSection> mHomestayDetailSections;
    private OnClickReviewTag mOnClickReviewTag;

    public DetailHomestayAdapter(Activity activity, Context context, Homestay homestay,
                                 List<HomestayDetailSection> detailSectionList) {
        mActivity = activity;
        mContext = context;
        mHomestay = homestay;
        mHomestayDetailSections = detailSectionList;

    }

    @Override
    public int getNumberOfSections() {
        return mHomestayDetailSections.size();
    }

    @Override
    public int getNumberOfItemsInSection(int sectionIndex) {
        switch (mHomestayDetailSections.get(sectionIndex)) {
            case INFOR:
                return mHomestay != null ? 1 : 0;
            case FEATURE:
                return mHomestay != null ? 1 : 0;
            case MAP:
                return mHomestay != null ? 1 : 0;
            case REVIEW:
                return mHomestay != null ? 1 : 0;
            default:
                return 0;
        }

    }

    @Override
    public int getSectionItemUserType(int sectionIndex, int itemIndex) {
        return mHomestayDetailSections.get(sectionIndex).getValue();
    }

    @Override
    public boolean doesSectionHaveHeader(int sectionIndex) {
        return false;
    }

    @Override
    public boolean doesSectionHaveFooter(int sectionIndex) {
        return false;
    }

    @Override
    public ItemViewHolder onCreateItemViewHolder(ViewGroup parent, int itemUserType) {
        switch (itemUserType) {
            case TYPE_INFOR:
                return new InforHomestayViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.item_infor_homestay, parent, false));
            case TYPE_FEATURE:
                return new FeatureHomestayViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.item_feature_homestay, parent, false));
            case TYPE_MAP:
                return new MapHomestayViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.item_map_homestay, parent, false));
            case TYPE_REVIEW:
                return new ReviewHomestayViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.item_review_homestay, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindItemViewHolder(ItemViewHolder viewHolder, int sectionIndex, int itemIndex, int itemUserType) {
        switch (itemUserType) {
            case TYPE_INFOR:
//                ((InforHomestayViewHolder) viewHolder).resetMarginTopView(true);
                ((InforHomestayViewHolder) viewHolder).bindView(mHomestay);
                break;
            case TYPE_FEATURE:
                break;
            case TYPE_MAP:
                ((MapHomestayViewHolder) viewHolder).bindView(mHomestay);
                break;
            case TYPE_REVIEW:
                ((ReviewHomestayViewHolder) viewHolder).onBindview(mHomestay);
                break;
            default:
        }
    }

    public void setHideViewTop(boolean isHide) {

    }

    public void setOnClickReviewTag(OnClickReviewTag onClickReviewTag) {
        mOnClickReviewTag = onClickReviewTag;
    }

    class InforHomestayViewHolder extends ItemViewHolder implements View.OnClickListener {

        TextView mTVNameHomestay;
        ExpandableTextView mTvDescription;
        TextView mTvPrice;
        TextView mTvScore;
        TextView mTvNumReview;
        AvatarListCustomView mAvatarListCustomView;
        ConstraintLayout mContainerReview;

        public InforHomestayViewHolder(View itemView) {
            super(itemView);
            mTVNameHomestay = itemView.findViewById(R.id.tv_name_homestay);
            mTvDescription = itemView.findViewById(R.id.tv_description);
            mTvPrice = itemView.findViewById(R.id.tv_price_homestay);
            mTvScore = itemView.findViewById(R.id.tv_star_homestay);
            mTvNumReview = itemView.findViewById(R.id.tv_review_num);
            mAvatarListCustomView = itemView.findViewById(R.id.list_avatar);
            mContainerReview = itemView.findViewById(R.id.container_review);
            mTvDescription.setOnClickListener(this);
            mContainerReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickReviewTag.onClickReviewTag();
                }
            });
        }

        public void resetMarginTopView(boolean isHide) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (isHide) {
                lp.setMargins((int) dpToPx(27), 40, (int) dpToPx(0), (int) dpToPx(0));
            } else {
                lp.setMargins((int) dpToPx(27),
                        (int) (40 + mContext.getResources().getDimension(R.dimen.icon_toolbar_margin_top)),
                        (int) dpToPx(0), (int) dpToPx(0));

            }
            mTVNameHomestay.setLayoutParams(lp);
        }

        public void bindView(Homestay homestay) {
            if (homestay != null) {
                if (!TextUtils.isEmpty(homestay.getName())) {
                    mTVNameHomestay.setText(homestay.getName());
                }
                if (!TextUtils.isEmpty(homestay.getPrice())) {
                    mTvPrice.setText(homestay.getPrice());
                }
                if (!TextUtils.isEmpty(String.valueOf(homestay.getReviewScore()))) {
                    mTvScore.setText(homestay.getReviewScore() + "");
                }
                if (!TextUtils.isEmpty(homestay.getDescription())) {
                    mTvDescription.setText(homestay.getDescription());
                }

                if (homestay.getNumReviews() != null) {
                    int numReview = homestay.getNumReviews();
                    if (numReview > 0) {
                        mTvNumReview.setText(homestay.getNumReviews() + "");
                        if (homestay.getReview() != null && homestay.getReview().size() > 0) {
                            List<String> imageUrl = new ArrayList<>();
                            for (Review review : homestay.getReview()
                            ) {
                                imageUrl.add(review.getAvatar());
                            }
                            mAvatarListCustomView.setDataImage(imageUrl);
                        }
                    } else {
                        mContainerReview.setVisibility(View.GONE);
                    }
                }
            }
        }


        @Override
        public void onClick(View v) {
            mTvDescription.setAnimationDuration(0);
            mTvDescription.toggle();
        }
    }

    public interface OnClickReviewTag {
        void onClickReviewTag();
    }

    class FeatureHomestayViewHolder extends ItemViewHolder {
        ViewPager mViewPager;
        CircleIndicator mIndicator;

        FeatureAdapter mAdapter;

        public FeatureHomestayViewHolder(View itemView) {
            super(itemView);
            mViewPager = itemView.findViewById(R.id.view_pager);
            mIndicator = itemView.findViewById(R.id.indicator);
            mAdapter = new FeatureAdapter(((AppCompatActivity) mActivity).getSupportFragmentManager());
            mViewPager.setAdapter(mAdapter);
            mIndicator.setViewPager(mViewPager, mAdapter.getCount());
            mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixel) {

                }

                @Override
                public void onPageSelected(int position) {
                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });


        }

    }

    private class FeatureAdapter extends FragmentStatePagerAdapter {

        FeatureAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return FeatureFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public Parcelable saveState() {
            Bundle bundle = (Bundle) super.saveState();
            if (bundle != null) {
                bundle.putParcelableArray("states", null);
            }
            return bundle;
        }
    }

    public static class FeatureFragment extends Fragment {
        private final int[] PAGE_LAYOUT = {R.layout.layout_feature_1, R.layout.layout_feature_2,
                R.layout.layout_feature_3};

        int currentPage;

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View view = inflater.inflate(PAGE_LAYOUT[currentPage], container, false);
            return view;
        }

        public static FeatureFragment newInstance(int currentPage) {

            FeatureFragment f = new FeatureFragment();
            f.setCurrentPage(currentPage);
            return f;
        }


        public void setCurrentPage(int currentPage) {
            this.currentPage = currentPage;
        }

    }

    class MapHomestayViewHolder extends ItemViewHolder {
        ImageView mMapView;
        TextView mTvNameHomestay;
        TextView mTvAdrress;
        LinearLayout mLlError;
        TextView mTvAboutArea;

        public MapHomestayViewHolder(View itemView) {
            super(itemView);
            mMapView = itemView.findViewById(R.id.item_mapView);
            mTvNameHomestay = itemView.findViewById(R.id.tv_name_homestay);
            mTvAdrress = itemView.findViewById(R.id.tv_address);
            mLlError = itemView.findViewById(R.id.view_error);
            mTvAboutArea = itemView.findViewById(R.id.about_area);
        }

        public void setHideViewTop(boolean isHide) {
            if (isHide) {
                mMapView.setVisibility(View.GONE);
            } else {
                mMapView.setVisibility(View.VISIBLE);
            }
        }

        public void bindView(Homestay homestay) {
            if (homestay != null) {
                if (!TextUtils.isEmpty(homestay.getName())) {
                    mTvNameHomestay.setText(homestay.getName());
                }
                if (!TextUtils.isEmpty(homestay.getAddress())) {
                    mTvAdrress.setText(homestay.getAddress());
                }
                if (!TextUtils.isEmpty("")) {
                    mLlError.setVisibility(View.GONE);
                } else {
                    mLlError.setVisibility(View.VISIBLE);
                }
                if (!TextUtils.isEmpty(homestay.getAboutArea())) {
                    mTvAboutArea.setText(homestay.getAboutArea());
                }
            }

        }

    }

    class ReviewHomestayViewHolder extends ItemViewHolder {
        private RecyclerView mRvReviewList;
        private ReviewAdapter mAdapter;

        public ReviewHomestayViewHolder(View itemView) {
            super(itemView);
            mRvReviewList = itemView.findViewById(R.id.rv_reviews);
        }

        public void onBindview(Homestay homestay) {
            if (homestay != null && homestay.getReview() != null) {
                mAdapter = new ReviewAdapter(mContext, homestay.getReview());
                mRvReviewList.setLayoutManager(new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false));
                mRvReviewList.setAdapter(mAdapter);
            }
        }
    }


}