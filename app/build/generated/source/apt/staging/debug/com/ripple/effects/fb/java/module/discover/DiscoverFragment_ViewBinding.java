// Generated code from Butter Knife. Do not modify!
package com.ripple.effects.fb.java.module.discover;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ripple.effects.fb.java.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DiscoverFragment_ViewBinding implements Unbinder {
  private DiscoverFragment target;

  private View view2131296373;

  private View view2131296370;

  private View view2131296372;

  private View view2131296371;

  @UiThread
  public DiscoverFragment_ViewBinding(final DiscoverFragment target, View source) {
    this.target = target;

    View view;
    target.mRvRecommend = Utils.findRequiredViewAsType(source, R.id.rv_recommend, "field 'mRvRecommend'", RecyclerView.class);
    target.mRvSpots = Utils.findRequiredViewAsType(source, R.id.rv_spots, "field 'mRvSpots'", RecyclerView.class);
    target.mRvDestinations = Utils.findRequiredViewAsType(source, R.id.rv_destinations, "field 'mRvDestinations'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.imv_setting, "method 'onClickOption'");
    view2131296373 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickOption(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.imv_search, "method 'onClickSearch'");
    view2131296370 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickSearch(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.imv_see_more_spot, "method 'onClickSeeMoreSpots'");
    view2131296372 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickSeeMoreSpots(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.imv_see_more_destination, "method 'onClickSeeMoreDestination'");
    view2131296371 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickSeeMoreDestination(p0);
      }
    });
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

    view2131296373.setOnClickListener(null);
    view2131296373 = null;
    view2131296370.setOnClickListener(null);
    view2131296370 = null;
    view2131296372.setOnClickListener(null);
    view2131296372 = null;
    view2131296371.setOnClickListener(null);
    view2131296371 = null;
  }
}
