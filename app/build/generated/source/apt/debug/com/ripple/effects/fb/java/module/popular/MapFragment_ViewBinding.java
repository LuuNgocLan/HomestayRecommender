// Generated code from Butter Knife. Do not modify!
package com.ripple.effects.fb.java.module.popular;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.mapbox.mapboxsdk.maps.MapView;
import com.ripple.effects.fb.java.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MapFragment_ViewBinding implements Unbinder {
  private MapFragment target;

  @UiThread
  public MapFragment_ViewBinding(MapFragment target, View source) {
    this.target = target;

    target.mProgressBar = Utils.findRequiredViewAsType(source, R.id.pb_loading, "field 'mProgressBar'", ProgressBar.class);
    target.mMapView = Utils.findRequiredViewAsType(source, R.id.mapView, "field 'mMapView'", MapView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MapFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mProgressBar = null;
    target.mMapView = null;
  }
}
