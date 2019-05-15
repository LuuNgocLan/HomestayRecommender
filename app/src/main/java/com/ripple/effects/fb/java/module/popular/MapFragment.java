package com.ripple.effects.fb.java.module.popular;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MapFragment
        extends BaseFragment
        implements IMapContract.IMapView, OnMapReadyCallback, OnLocationClickListener, PermissionsListener, OnCameraTrackingChangedListener {

    IMapContract.IMapPresenter mIPopularPresenter;

    @BindView(R.id.pb_loading)
    ProgressBar mProgressBar;
    @BindView(R.id.mapView)
    MapView mMapView;

    private PermissionsManager mPermissionsManager;
    private MapboxMap mMapboxMap;
    private LocationComponent mLocationComponent;
    private boolean isInTrackingMode;

    private List<LatLng> mLatLngs = new ArrayList<>();


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

        return view;
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
        mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
            @Override
            public void onStyleLoaded(@NonNull Style style) {
//                enableLocationComponent(style);

                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(16.080234, 108.2199936))
                        .icon(IconFactory.getInstance(getContext()).fromResource(R.drawable.ic_land_marker))
                        .title("FHome"));
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(16.07935, 108.2160235))
                        .title("Dong Da Market"))
                        .setSnippet("H St NW with 15th St NW");
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(16.0798505, 108.2184633))
                        .title("Pho Garden"));
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(16.0797584,108.2186051))
                        .title("H.I.S. ダナン支店"));
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
                    .foregroundDrawable(R.drawable.ic_map_selected)
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

}
