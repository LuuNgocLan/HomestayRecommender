// Generated code from Butter Knife. Do not modify!
package com.ripple.effects.fb.java.module.discover.adapter;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ripple.effects.fb.java.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class AllDestinationFragment_ViewBinding implements Unbinder {
  private AllDestinationFragment target;

  @UiThread
  public AllDestinationFragment_ViewBinding(AllDestinationFragment target, View source) {
    this.target = target;

    target.mRvDestinations = Utils.findRequiredViewAsType(source, R.id.rv_destinations, "field 'mRvDestinations'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AllDestinationFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mRvDestinations = null;
  }
}
