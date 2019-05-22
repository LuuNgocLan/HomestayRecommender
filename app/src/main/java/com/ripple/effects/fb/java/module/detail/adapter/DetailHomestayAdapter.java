package com.ripple.effects.fb.java.module.detail.adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.java.core.utils.ViewUtils;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.models.detailhomestay.Homestay;
import com.ripple.effects.fb.java.models.detailhomestay.Review;
import com.ripple.effects.fb.java.widget.AvatarListCustomView;
import com.ripple.effects.fb.java.widget.ExpandableTextView;
import com.ripple.effects.fb.java.widget.SectioningAdapter;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.internal.fuseable.HasUpstreamObservableSource;

import static com.base.java.core.utils.DimenUtils.dpToPx;

public class DetailHomestayAdapter extends SectioningAdapter {

    private final int TYPE_INFOR = 0;
    private final int TYPE_FEATURE = 1;
    private final int TYPE_MAP = 2;
    private final int TYPE_REVIEW = 3;

    private Context mContext;
    private Homestay mHomestay;
    private List<HomestayDetailSection> mHomestayDetailSections;

    public DetailHomestayAdapter(Context context, Homestay homestay,
                                 List<HomestayDetailSection> detailSectionList) {
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
                break;
            default:
        }
    }

    public void setHideViewTop(boolean isHide) {

    }

    class InforHomestayViewHolder extends ItemViewHolder implements View.OnClickListener {

        TextView mTVNameHomestay;
        ExpandableTextView mTvDescription;
        TextView mTvPrice;
        TextView mTvScore;
        TextView mTvNumReview;
        AvatarListCustomView mAvatarListCustomView;

        public InforHomestayViewHolder(View itemView) {
            super(itemView);
            mTVNameHomestay = itemView.findViewById(R.id.tv_name_homestay);
            mTvDescription = itemView.findViewById(R.id.tv_description);
            mTvPrice = itemView.findViewById(R.id.tv_price_homestay);
            mTvScore = itemView.findViewById(R.id.tv_star_homestay);
            mTvNumReview = itemView.findViewById(R.id.tv_review_num);
            mAvatarListCustomView = itemView.findViewById(R.id.list_avatar);
            mTvDescription.setOnClickListener(this);
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
                if (!TextUtils.isEmpty(String.valueOf(homestay.getNumReviews()))) {
                    mTvNumReview.setText(homestay.getNumReviews() + "");
                }
                if (homestay.getReview() != null && homestay.getReview().size() > 0) {
                    List<String> imageUrl = new ArrayList<>();
                    for (Review review : homestay.getReview()
                    ) {
                        imageUrl.add(review.getAvatar());
                    }
                    mAvatarListCustomView.setDataImage(imageUrl);
                }
            }

        }


        @Override
        public void onClick(View v) {
            mTvDescription.setAnimationDuration(0);
            mTvDescription.toggle();
        }
    }

    class FeatureHomestayViewHolder extends ItemViewHolder {

        public FeatureHomestayViewHolder(View itemView) {
            super(itemView);
        }
    }

    class MapHomestayViewHolder extends ItemViewHolder {
        ImageView mMapView;
        TextView mTvNameHomestay;
        TextView mTvAdrress;
        LinearLayout mLlError;

        public MapHomestayViewHolder(View itemView) {
            super(itemView);
            mMapView = itemView.findViewById(R.id.item_mapView);
            mTvNameHomestay = itemView.findViewById(R.id.tv_name_homestay);
            mTvAdrress = itemView.findViewById(R.id.tv_address);
            mLlError = itemView.findViewById(R.id.view_error);
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
            }

        }

        private void initMapViewWithPosition(Bundle savedInstanceState, LatLng latLng) {
//            Mapbox.getInstance(mContext, BuildConfig.MAPBOX_TOKEN_ACCESS);
//            mMapView.onCreate(savedInstanceState);
//            mMapView.getMapAsync(new OnMapReadyCallback() {
//                @Override
//                public void onMapReady(@NonNull MapboxMap mapboxMap) {
//                    mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
//                        @Override
//                        public void onStyleLoaded(@NonNull Style style) {
//
//                            mapboxMap.addMarker(new MarkerOptions()
//                                    .position(latLng)
//                                    .icon(IconFactory.getInstance(mContext).fromResource(R.drawable.ic_land_marker))
//                                    .title("FHome"));
////                            // Add the marker to the map
////                            mapboxMap.addMarker(new MarkerViewOptions()
////                                    .position(new LatLng(-37.821648, 144.978594))
////                                    .icon(icon));
//
//                        }
//                    });
//                }
//            });
        }

    }

    class ReviewHomestayViewHolder extends ItemViewHolder {

        public ReviewHomestayViewHolder(View itemView) {
            super(itemView);
        }
    }


}