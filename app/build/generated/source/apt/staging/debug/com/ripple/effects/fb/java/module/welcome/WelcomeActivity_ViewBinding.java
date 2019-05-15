// Generated code from Butter Knife. Do not modify!
package com.ripple.effects.fb.java.module.welcome;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ripple.effects.fb.java.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class WelcomeActivity_ViewBinding implements Unbinder {
  private WelcomeActivity target;

  private View view2131296298;

  @UiThread
  public WelcomeActivity_ViewBinding(WelcomeActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public WelcomeActivity_ViewBinding(final WelcomeActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.btn_login, "field 'mBtnLogin' and method 'onClickLogin'");
    target.mBtnLogin = Utils.castView(view, R.id.btn_login, "field 'mBtnLogin'", Button.class);
    view2131296298 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickLogin(p0);
      }
    });
    target.mBtnLoginFb = Utils.findRequiredViewAsType(source, R.id.btn_login_with_fb, "field 'mBtnLoginFb'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    WelcomeActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mBtnLogin = null;
    target.mBtnLoginFb = null;

    view2131296298.setOnClickListener(null);
    view2131296298 = null;
  }
}
