// Generated code from Butter Knife. Do not modify!
package com.ripple.effects.fb.java.module.discover;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ripple.effects.fb.java.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DiscoverFragment_ViewBinding implements Unbinder {
  private DiscoverFragment target;

  @UiThread
  public DiscoverFragment_ViewBinding(DiscoverFragment target, View source) {
    this.target = target;

    target.mRvRecommend = Utils.findRequiredViewAsType(source, R.id.rv_recommend, "field 'mRvRecommend'", RecyclerView.class);
    target.mRvSpots = Utils.findRequiredViewAsType(source, R.id.rv_spots, "field 'mRvSpots'", RecyclerView.class);
    target.mRvDestinations = Utils.findRequiredViewAsType(source, R.id.rv_destinations, "field 'mRvDestinations'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    DiscoverFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRvRecommend = null;
    target.mRvSpots = null;
    target.mRvDestinations = null;
  }
}
