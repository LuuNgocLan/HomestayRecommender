package com.ripple.effects.fb.java.widget;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import com.ripple.effects.fb.java.R;

public class AvatarListCustomView extends ConstraintLayout {

    private ImageView mImvAvatar0, mImvAvatar1, mImvAvatar2;

    public AvatarListCustomView(Context context) {
        super(context);
        init();
    }

    public AvatarListCustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AvatarListCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View view = inflate(getContext(), R.layout.partial_layout_avatar_list, this);
        mImvAvatar0 = view.findViewById(R.id.profile_image_0);
        mImvAvatar1 = view.findViewById(R.id.profile_image_1);
        mImvAvatar2 = view.findViewById(R.id.profile_image_2);
    }

}
