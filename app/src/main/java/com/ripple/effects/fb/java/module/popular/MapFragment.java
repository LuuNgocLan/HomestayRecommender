package com.ripple.effects.fb.java.module.popular;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.base.java.IBaseListener;
import com.base.java.mvp.BaseFragment;
import com.base.java.mvp.IBasePresenter;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.IconFactory;
import com.mapbox.mapboxsdk.annotations.Marker;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.annotations.PolygonOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.LocationComponentOptions;
import com.mapbox.mapboxsdk.location.OnCameraTrackingChangedListener;
import com.mapbox.mapboxsdk.location.OnLocationClickListener;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.ripple.effects.fb.java.BuildConfig;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.models.data.DataCenter;
import com.ripple.effects.fb.java.models.homestay.Homestay;
import com.ripple.effects.fb.java.module.detail.DetailActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ripple.effects.fb.java.utils.AppUtils.ID_HOMESTAY;

public class MapFragment
        extends BaseFragment
        implements IMapContract.IMapView, OnMapReadyCallback, OnLocationClickListener, PermissionsListener, OnCameraTrackingChangedListener, MapboxMap.OnMarkerClickListener {

    IMapContract.IMapPresenter mIPopularPresenter;

    @BindView(R.id.pb_loading)
    ProgressBar mProgressBar;
    @BindView(R.id.mapView)
    MapView mMapView;

    private PermissionsManager mPermissionsManager;
    private MapboxMap mMapboxMap;
    private LocationComponent mLocationComponent;
    private boolean isInTrackingMode;
    private List<Homestay> mHomestayList;
    private DataCenter mDataCenter = DataCenter.getInstance();
    private List<MarkerOptions> mMarkerOptions = new ArrayList<>();
    HashMap<String, MarkerOptions> mOptionsHashMap = new HashMap<>();

    @Override
    protected IBasePresenter initPresenter() {
        mIPopularPresenter = new MapPresenter(getContext());
        return mIPopularPresenter;
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.fragment_map;
    }

    public static MapFragment newInstance(IBaseListener iBaseListener) {
        MapFragment fragment = new MapFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(getContext(), BuildConfig.MAPBOX_TOKEN_ACCESS);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, view);
        mMapView.onCreate(savedInstanceState);
        mMapView.getMapAsync(this);
        checkData();
        return view;
    }

    private void checkData() {
        if (mDataCenter.getHomestayList() != null && mDataCenter.getHomestayList().size() > 0) {
            mHomestayList = mDataCenter.getHomestayList();
        } else {
            mDataCenter.getAllSpots(new DataCenter.OnGetDataListener() {
                @Override
                public void onSuccess() {
                    mHomestayList = mDataCenter.getHomestayList();
                }

                @Override
                public void onError() {
                    mHomestayList = null;
                }
            });
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        mMapView.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mMapView.onStop();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    public void smoothScrollToTop() {

    }

    public void requestLoadPopularData() {
        if (mIPopularPresenter != null) {
            mIPopularPresenter.fetchData();
        }
    }

    @Override
    public void showLoading() {
        if (mProgressBar != null)
            mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        if (mProgressBar != null)
            mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMessage() {

    }

    @Override
    public void onMapReady(@NonNull MapboxMap mapboxMap) {
        this.mMapboxMap = mapboxMap;
        mMapboxMap.setOnMarkerClickListener(this);
        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
                enableLocationComponent(style);
                if (mHomestayList != null) {
                    for (Homestay homestay :
                            mHomestayList) {
                        if (homestay.getLat() != null && homestay.getLng() != null) {
                            MarkerOptions markerOptions = new MarkerOptions()
                                    .position(new LatLng(homestay.getLat(), homestay.getLng()))
                                    .icon(IconFactory.getInstance(getContext()).fromResource(R.drawable.ic_land_marker))
                                    .title(homestay.getName())
                                    .snippet(homestay.getPrice());
                            mOptionsHashMap.put(homestay.getId(), markerOptions);
                            mMarkerOptions.add(markerOptions);
                            mapboxMap.addMarker(markerOptions);

                        }

                    }
                }

            }
        });
    }

    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
        // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(getContext())) {

            // Create and customize the LocationComponent's options
            LocationComponentOptions customLocationComponentOptions = LocationComponentOptions.builder(getContext())
                    .elevation(5)
                    .accuracyAlpha(.6f)
                    .accuracyColor(Color.RED)
                    .backgroundTintColor(R.color.main_background_color)
                    .foregroundDrawable(R.drawable.ic_my_position)
                    .build();

            // Get an instance of the component
            mLocationComponent = mMapboxMap.getLocationComponent();

            LocationComponentActivationOptions locationComponentActivationOptions =
                    LocationComponentActivationOptions.builder(getContext(), loadedMapStyle)
                            .locationComponentOptions(customLocationComponentOptions)
                            .build();

            // Activate with options
            mLocationComponent.activateLocationComponent(locationComponentActivationOptions);

            // Enable to make component visible
            mLocationComponent.setLocationComponentEnabled(true);

            // Set the component's camera mode
            mLocationComponent.setCameraMode(CameraMode.TRACKING);

            // Set the component's render mode
            mLocationComponent.setRenderMode(RenderMode.COMPASS);

            // Add the location icon click listener
            mLocationComponent.addOnLocationClickListener(this);

            // Add the camera tracking listener. Fires if the map camera is manually moved.
            mLocationComponent.addOnCameraTrackingChangedListener(this);

//            findViewById(R.id.back_to_camera_tracking_mode).setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    if (!isInTrackingMode) {
//                        isInTrackingMode = true;
//                        mLocationComponent.setCameraMode(CameraMode.TRACKING);
//                        mLocationComponent.zoomWhileTracking(16f);
//                        Toast.makeText(getContext(), getString(R.string.tracking_enabled),
//                                Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(getContext(), getString(R.string.tracking_already_enabled),
//                                Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });

        } else {
            mPermissionsManager = new PermissionsManager(this);
            mPermissionsManager.requestLocationPermissions(getActivity());
        }
    }

    @SuppressWarnings({"MissingPermission"})
    @Override
    public void onLocationComponentClick() {
        if (mLocationComponent.getLastKnownLocation() != null) {
            Toast.makeText(getContext(),
                    mLocationComponent.getLastKnownLocation().getLatitude() + " " +
                            mLocationComponent.getLastKnownLocation().getLongitude(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onCameraTrackingDismissed() {
        isInTrackingMode = false;

    }

    @Override
    public void onCameraTrackingChanged(int currentMode) {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mPermissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {
        Toast.makeText(getContext(), "user_location_permission_explanation", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionResult(boolean granted) {
        if (granted) {
            mMapboxMap.getStyle(new Style.OnStyleLoaded() {
                @Override
                public void onStyleLoaded(@NonNull Style style) {
                    enableLocationComponent(style);
                }
            });
        } else {
            Toast.makeText(getContext(), "user_location_permission_not_granted", Toast.LENGTH_LONG).show();
            getActivity().finish();
        }
    }

    @Override
    public boolean onMarkerClick(@NonNull Marker marker) {
        Log.d("POSITION", mOptionsHashMap.size() + "");
        String id = "";
        for (String keyId : mOptionsHashMap.keySet()) {
            Homestay homestay = checkKeyInList(keyId);
            if (homestay.getLng() == marker.getPosition().getLongitude()
                    && homestay.getLat() == marker.getPosition().getLatitude()) {
                id = keyId;
                break;
            }
        }
        Intent intent = new Intent(getContext(), DetailActivity.class);
        intent.putExtra(ID_HOMESTAY, id);
        startActivity(intent);

        return false;
    }

    public Homestay checkKeyInList(String keyId) {
        Homestay homestay_ = new Homestay();
        for (Homestay homestay :
                mHomestayList) {
            if (homestay.getId().equals(keyId)) {
                homestay_ = homestay;
                break;
            }
        }
        return homestay_;
    }
}
