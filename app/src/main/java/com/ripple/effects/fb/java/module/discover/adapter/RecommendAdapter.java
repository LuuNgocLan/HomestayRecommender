package com.ripple.effects.fb.java.module.discover.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.module.base.IBaseItemListener;

import java.util.Random;

import static com.base.java.core.utils.DimenUtils.*;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {

    public Context mContext;
    public IBaseItemListener mIBaseItemListener;

    public RecommendAdapter(Context context) {
        mContext = context;
    }

    @NonNull
    @Override
    public RecommendAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_near_homestay, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        viewHolder.mImageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo: update status favorite of homestay item
                viewHolder.mImageViewFavorite.setSelected((new Random()).nextBoolean());
                mIBaseItemListener.onClickFavoriteHomestay(1, true);

            }
        });

        viewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIBaseItemListener.openDetailHomestay("");
            }
        });
    }

    public void setIBaseItemListener(IBaseItemListener iBaseItemListener) {
        this.mIBaseItemListener = iBaseItemListener;
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder {

        CardView mCardView;
        ImageView mImageViewFavorite;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.cardview);
            mImageViewFavorite = itemView.findViewById(R.id.imv_favorite);
        }

        public void setMarginLeft(int margin) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int) dpToPx(215), (int) dpToPx(260));
            lp.setMargins((int) dpToPx(margin), 0, (int) dpToPx(15), (int) dpToPx(15));
            mCardView.setLayoutParams(lp);
        }
    }
}
