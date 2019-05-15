// Generated code from Butter Knife. Do not modify!
package com.ripple.effects.fb.java.module.detail;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ripple.effects.fb.java.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class DetailActivity_ViewBinding implements Unbinder {
  private DetailActivity target;

  private View view2131296365;

  private View view2131296366;

  private View view2131296368;

  @UiThread
  public DetailActivity_ViewBinding(DetailActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public DetailActivity_ViewBinding(final DetailActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.imv_center, "field 'mImvCenter' and method 'onClickImage'");
    target.mImvCenter = Utils.castView(view, R.id.imv_center, "field 'mImvCenter'", ImageView.class);
    view2131296365 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickImage(p0);
      }
    });
    target.mLlBottomSheet = Utils.findRequiredViewAsType(source, R.id.bottom_sheet, "field 'mLlBottomSheet'", LinearLayout.class);
    target.mRvDetailHomeStay = Utils.findRequiredViewAsType(source, R.id.rvDetail, "field 'mRvDetailHomeStay'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.imv_close, "field 'mImvClose' and method 'onClose'");
    target.mImvClose = Utils.castView(view, R.id.imv_close, "field 'mImvClose'", ImageView.class);
    view2131296366 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClose(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.imv_option, "field 'mImvOption' and method 'onOptionClick'");
    target.mImvOption = Utils.castView(view, R.id.imv_option, "field 'mImvOption'", ImageView.class);
    view2131296368 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onOptionClick(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    DetailActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mImvCenter = null;
    target.mLlBottomSheet = null;
    target.mRvDetailHomeStay = null;
    target.mImvClose = null;
    target.mImvOption = null;

    view2131296365.setOnClickListener(null);
    view2131296365 = null;
    view2131296366.setOnClickListener(null);
    view2131296366 = null;
    view2131296368.setOnClickListener(null);
    view2131296368 = null;
  }
}
