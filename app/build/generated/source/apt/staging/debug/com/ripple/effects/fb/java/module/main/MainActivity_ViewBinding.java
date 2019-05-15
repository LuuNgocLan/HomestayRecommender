// Generated code from Butter Knife. Do not modify!
package com.ripple.effects.fb.java.module.main;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.widget.CustomTab;
import com.ripple.effects.fb.java.widget.MainViewPager;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.mMainViewPager = Utils.findRequiredViewAsType(source, R.id.mvp_main, "field 'mMainViewPager'", MainViewPager.class);
    target.mCustomTab = Utils.findRequiredViewAsType(source, R.id.tab, "field 'mCustomTab'", CustomTab.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mMainViewPager = null;
    target.mCustomTab = null;
  }
}
