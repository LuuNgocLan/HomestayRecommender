// Generated code from Butter Knife. Do not modify!
package com.ripple.effects.fb.java.module.favorite;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.ripple.effects.fb.java.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class FavoriteFragment_ViewBinding implements Unbinder {
  private FavoriteFragment target;

  @UiThread
  public FavoriteFragment_ViewBinding(FavoriteFragment target, View source) {
    this.target = target;

    target.mProgressBar = Utils.findRequiredViewAsType(source, R.id.pb_loading, "field 'mProgressBar'", ProgressBar.class);
    target.mTextViewMessage = Utils.findRequiredViewAsType(source, R.id.tv_message, "field 'mTextViewMessage'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    FavoriteFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mProgressBar = null;
    target.mTextViewMessage = null;
  }
}
