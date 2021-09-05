package com.ripple.effects.fb.java.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.base.java.core.helper.ImageHelper;
import com.ripple.effects.fb.java.R;

import java.util.ArrayList;
import java.util.List;

public class AvatarListCustomView extends ConstraintLayout {

    private List<ImageView> mImageViewList = new ArrayList<>();
    private int[] idImage = {R.id.profile_image_0, R.id.profile_image_1, R.id.profile_image_2};

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
        for (int i = 0; i < 3; i++) {
            mImageViewList.add(view.findViewById(idImage[i]));
        }

    }

    public void setDataImage(List<String> imageUrl) {
        if (imageUrl.size() >= 3) {
            for (int i = 0; i < 3; i++) {
                ImageHelper.load(getContext(), mImageViewList.get(i), R.drawable.ic_avatar_default, imageUrl.get(i));
            }
        } else {
            for (int i = 0; i < imageUrl.size(); i++) {
                ImageHelper.load(getContext(), mImageViewList.get(i), R.drawable.ic_avatar_default, imageUrl.get(i));
            }
        }

    }

}
