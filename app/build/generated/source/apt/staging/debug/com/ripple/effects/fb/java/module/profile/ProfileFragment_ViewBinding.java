// Generated code from Butter Knife. Do not modify!
package com.ripple.effects.fb.java.module.profile;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ripple.effects.fb.java.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ProfileFragment_ViewBinding implements Unbinder {
  private ProfileFragment target;

  @UiThread
  public ProfileFragment_ViewBinding(ProfileFragment target, View source) {
    this.target = target;

    target.mTvName = Utils.findRequiredViewAsType(source, R.id.tv_name, "field 'mTvName'", TextView.class);
    target.mBtnLogin = Utils.findRequiredViewAsType(source, R.id.btn_login, "field 'mBtnLogin'", Button.class);
    target.mBtnRegister = Utils.findRequiredViewAsType(source, R.id.btn_register, "field 'mBtnRegister'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ProfileFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mTvName = null;
    target.mBtnLogin = null;
    target.mBtnRegister = null;
  }
}
