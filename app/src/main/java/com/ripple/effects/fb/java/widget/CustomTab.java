package com.ripple.effects.fb.java.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.ripple.effects.fb.java.R;


public class CustomTab
        extends TabLayout {

    private Context mContext;

    public CustomTab(Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomTab(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
    }

    @Override
    public void setupWithViewPager(@Nullable ViewPager viewPager) {
        super.setupWithViewPager(viewPager);
        if (viewPager.getAdapter() == null)
            return;
        setupTab();
    }

    private void setupTab() {
        this.setSelectedTabIndicatorColor(getResources().getColor(android.R.color.transparent));
        this.addOnTabSelectedListener(new OnTabSelectedListener() {
            @Override
            public void onTabSelected(Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        ((ImageView) tab.getCustomView().findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_discover_selected);
                        break;
                    case 1:
                        ((ImageView) tab.getCustomView().findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_map_selected);
                        break;
                    case 2:
                        ((ImageView) tab.getCustomView().findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_favorite_selected);
                        break;
                    case 3:
                        ((ImageView) tab.getCustomView().findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_profile_selected);
                        break;
                }
            }

            @Override
            public void onTabUnselected(Tab tab) {
                switch (tab.getPosition()) {
                    case 0:
                        ((ImageView) tab.getCustomView().findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_discover);
                        break;
                    case 1:
                        ((ImageView) tab.getCustomView().findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_map);
                        break;
                    case 2:
                        ((ImageView) tab.getCustomView().findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_favorite);
                        break;
                    case 3:
                        ((ImageView) tab.getCustomView().findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_profile);
                        break;
                }
            }

            @Override
            public void onTabReselected(Tab tab) {

            }
        });

        ConstraintLayout tabHome = (ConstraintLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_tab, null);
        ((ImageView) tabHome.findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_discover_selected);
        this.getTabAt(0).setCustomView(tabHome);

        ConstraintLayout tabPopular = (ConstraintLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_tab, null);
        ((ImageView) tabPopular.findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_map);
        this.getTabAt(1).setCustomView(tabPopular);

        ConstraintLayout tabFavorite = (ConstraintLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_tab, null);
        ((ImageView) tabFavorite.findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_favorite);
        this.getTabAt(2).setCustomView(tabFavorite);

        ConstraintLayout tabSave = (ConstraintLayout) LayoutInflater.from(getContext()).inflate(R.layout.item_tab, null);
        ((ImageView) tabSave.findViewById(R.id.tab_icon)).setImageResource(R.drawable.ic_profile);
        this.getTabAt(3).setCustomView(tabSave);

    }
}
