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

public class AllSpotsFragment_ViewBinding implements Unbinder {
  private AllSpotsFragment target;

  @UiThread
  public AllSpotsFragment_ViewBinding(AllSpotsFragment target, View source) {
    this.target = target;

    target.rvSpots = Utils.findRequiredViewAsType(source, R.id.rv_spots, "field 'rvSpots'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    AllSpotsFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.rvSpots = null;
  }
}
