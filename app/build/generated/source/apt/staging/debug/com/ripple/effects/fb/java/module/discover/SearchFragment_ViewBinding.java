// Generated code from Butter Knife. Do not modify!
package com.ripple.effects.fb.java.module.discover;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.ripple.effects.fb.java.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SearchFragment_ViewBinding implements Unbinder {
  private SearchFragment target;

  private View view2131296370;

  @UiThread
  public SearchFragment_ViewBinding(final SearchFragment target, View source) {
    this.target = target;

    View view;
    target.mEdtSearch = Utils.findRequiredViewAsType(source, R.id.edt_search, "field 'mEdtSearch'", EditText.class);
    view = Utils.findRequiredView(source, R.id.imv_search, "method 'onClickSearch'");
    view2131296370 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onClickSearch(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    SearchFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mEdtSearch = null;

    view2131296370.setOnClickListener(null);
    view2131296370 = null;
  }
}
