package com.ripple.effects.fb.java.module.detail.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.ripple.effects.fb.java.BuildConfig;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.models.Homestay;
import com.ripple.effects.fb.java.widget.SectioningAdapter;

import java.util.List;

import static com.base.java.core.utils.DimenUtils.dpToPx;

public class DetailHomestayAdapter extends SectioningAdapter {

    private final int TYPE_INFOR = 0;
    private final int TYPE_FEATURE = 1;
    private final int TYPE_MAP = 2;
    private final int TYPE_REVIEW = 3;

    private Context mContext;
    private Homestay mHomestay;
    private List<HomestayDetailSection> mHomestayDetailSections;
    private Bundle mSavedInstanceState;

    public DetailHomestayAdapter(Bundle savedInstanceState, Context context, Homestay homestay,
                                 List<HomestayDetailSection> detailSectionList) {
        mContext = context;
        mHomestay = homestay;
        mHomestayDetailSections = detailSectionList;
        mSavedInstanceState = savedInstanceState;

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
                break;
            case TYPE_FEATURE:
                break;
            case TYPE_MAP:
                Log.d("item type", mHomestayDetailSections.get(itemIndex) + "");
                ((MapHomestayViewHolder) viewHolder)
                        .initMapViewWithPosition(mSavedInstanceState, new LatLng(16.080234, 108.2199936));
                break;
            case TYPE_REVIEW:
                break;
            default:
        }
    }

    public void setHideViewTop(boolean isHide) {

    }

    class InforHomestayViewHolder extends ItemViewHolder {

        TextView mTVNameHomestay;

        public InforHomestayViewHolder(View itemView) {
            super(itemView);
            mTVNameHomestay = itemView.findViewById(R.id.tv_name_homestay);
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


    }

    class FeatureHomestayViewHolder extends ItemViewHolder {

        public FeatureHomestayViewHolder(View itemView) {
            super(itemView);
        }
    }

    class MapHomestayViewHolder extends ItemViewHolder {

        MapView mMapView;

        public MapHomestayViewHolder(View itemView) {
            super(itemView);
            mMapView = itemView.findViewById(R.id.item_mapView);
        }

        public void setHideViewTop(boolean isHide) {
            if (isHide) {
                mMapView.setVisibility(View.GONE);
            } else {
                mMapView.setVisibility(View.VISIBLE);
            }
        }

        private void initMapViewWithPosition(Bundle savedInstanceState, LatLng latLng) {
            Mapbox.getInstance(mContext, BuildConfig.MAPBOX_TOKEN_ACCESS);
            mMapView.onCreate(savedInstanceState);
            mMapView.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(@NonNull MapboxMap mapboxMap) {
                    mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
                        @Override
                        public void onStyleLoaded(@NonNull Style style) {

                            mapboxMap.addMarker(new MarkerOptions()
                                    .position(latLng)
                                    .icon(IconFactory.getInstance(mContext).fromResource(R.drawable.ic_land_marker))
                                    .title("FHome"));
//                            // Add the marker to the map
//                            mapboxMap.addMarker(new MarkerViewOptions()
//                                    .position(new LatLng(-37.821648, 144.978594))
//                                    .icon(icon));

                        }
                    });
                }
            });
        }

    }

    class ReviewHomestayViewHolder extends ItemViewHolder {

        public ReviewHomestayViewHolder(View itemView) {
            super(itemView);
        }
    }


}