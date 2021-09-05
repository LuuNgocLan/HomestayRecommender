package com.ripple.effects.fb.java.module.filter.adapter;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.module.base.IBaseItemListener;
import com.ripple.effects.fb.java.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;

import static com.base.java.core.utils.DimenUtils.dpToPx;
import static com.ripple.effects.fb.java.utils.Feature.AIR_CONDITIONING;
import static com.ripple.effects.fb.java.utils.Feature.BARBECUE;
import static com.ripple.effects.fb.java.utils.Feature.BIKE_FOR_USE;
import static com.ripple.effects.fb.java.utils.Feature.COMPUTER;
import static com.ripple.effects.fb.java.utils.Feature.GAME_ROOM;
import static com.ripple.effects.fb.java.utils.Feature.GARDEN;
import static com.ripple.effects.fb.java.utils.Feature.GYM_AT_HOME;
import static com.ripple.effects.fb.java.utils.Feature.INTERNET_ACCESS;
import static com.ripple.effects.fb.java.utils.Feature.LAUNDRY;
import static com.ripple.effects.fb.java.utils.Feature.PARKING;
import static com.ripple.effects.fb.java.utils.Feature.PATIO;
import static com.ripple.effects.fb.java.utils.Feature.SWIMMING_POOL;
import static com.ripple.effects.fb.java.utils.Feature.TV;

public class HighlightAdapter extends RecyclerView.Adapter<HighlightAdapter.ViewHolder> {

    private Context mContext;
    private List<String> mHighlightList = new ArrayList<>();

    public HighlightAdapter(Context context) {
        mContext = context;
        makeList();
    }

    private void makeList() {
        mHighlightList.add(GARDEN);
        mHighlightList.add(BIKE_FOR_USE);
        mHighlightList.add(COMPUTER);
        mHighlightList.add(SWIMMING_POOL);
        mHighlightList.add(LAUNDRY);
        mHighlightList.add(PARKING);
        mHighlightList.add(INTERNET_ACCESS);
        mHighlightList.add(AIR_CONDITIONING);
        mHighlightList.add(GAME_ROOM);
        mHighlightList.add(PATIO);
        mHighlightList.add(BARBECUE);
        mHighlightList.add(TV);
        mHighlightList.add(GYM_AT_HOME);
    }

    @NonNull
    @Override
    public HighlightAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_highlight, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        if (position == 0) {
            viewHolder.setMargin(80, 0, 11, 0);
            viewHolder.setIsRecyclable(false);
        } else {
            viewHolder.setMargin(0, 0, 11, 0);
        }
        viewHolder.bindView(mHighlightList.get(position));

    }

    public void setIBaseItemListener(IBaseItemListener iBaseItemListener) {

    }

    @Override
    public int getItemCount() {
        return mHighlightList.size();
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder {

        ImageView mImvToogle;
        RelativeLayout mRlContainer;

        boolean isSelected = false;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImvToogle = itemView.findViewById(R.id.btn_toggle);
            mRlContainer = itemView.findViewById(R.id.container);
        }

        public void bindView(String itemHighlight) {
            ViewUtils.setTintColor(mContext, mImvToogle, R.color.color_icon_nomarl);
            switch (itemHighlight) {
                case "GARDEN":
                    mImvToogle.setImageResource(R.drawable.ic_garden);
                    break;
                case "BIKE FOR USE":
                    mImvToogle.setImageResource(R.drawable.ic_bike);
                    break;
                case "COMPUTER":
                    mImvToogle.setImageResource(R.drawable.ic_computer);
                    break;
                case "SWIMMING POOL":
                    mImvToogle.setImageResource(R.drawable.ic_swim);
                    break;
                case "LAUNDRY":
                    mImvToogle.setImageResource(R.drawable.ic_laundry);
                    break;
                case "PARKING":
                    mImvToogle.setImageResource(R.drawable.ic_parking);
                    break;
                case "INTERNET ACCESS":
                    mImvToogle.setImageResource(R.drawable.ic_wifi);
                    break;
                case "AIR CONDITIONING":
                    mImvToogle.setImageResource(R.drawable.ic_air_conditioning);
                    break;
                case "GAME ROOM":
                    mImvToogle.setImageResource(R.drawable.ic_game_room);
                    break;
                case "PATIO":
                    mImvToogle.setImageResource(R.drawable.ic_patio);
                    break;
                case "BARBECUE":
                    mImvToogle.setImageResource(R.drawable.ic_barbecue);
                    break;
                case "TV":
                    mImvToogle.setImageResource(R.drawable.ic_tv);
                    break;
                case "GYM AT HOME":
                    mImvToogle.setImageResource(R.drawable.ic_gym);
                    break;
            }

            mImvToogle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mImvToogle.setSelected(!isSelected);
                    isSelected = !isSelected;
                    if (isSelected) {
                        ViewUtils.setTintColor(mContext, mImvToogle, R.color.paint_white);
                    } else {
                        ViewUtils.setTintColor(mContext, mImvToogle, R.color.color_icon_nomarl);
                    }
                }
            });
        }

        public void setMargin(int left, int top, int right, int bottom) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int) dpToPx(60), (int) dpToPx(60));
            lp.setMargins(left, top, right, bottom);
            mRlContainer.setLayoutParams(lp);
        }


    }
}
