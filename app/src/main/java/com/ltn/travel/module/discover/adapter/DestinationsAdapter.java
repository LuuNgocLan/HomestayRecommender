package com.ltn.travel.module.discover.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ltn.travel.R;
import com.ltn.travel.module.base.IBaseItemListener;

import static com.base.java.core.utils.DimenUtils.dpToPx;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DestinationsAdapter extends RecyclerView.Adapter<DestinationsAdapter.ViewHolder> {

    public Context mContext;
    public IBaseItemListener mIBaseItemListener;
    public int mMarginLeftFirstItem = 0;

    public DestinationsAdapter(Context context, int marginLeftFirstItem) {
        mContext = context;
        mMarginLeftFirstItem = marginLeftFirstItem;
    }

    @NonNull
    @Override
    public DestinationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_place, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        if (position == 0) {
            if (mMarginLeftFirstItem == 0) {
                viewHolder.setMargin(10, 10, 5, 5);
            } else {
                viewHolder.setIsRecyclable(false);
                viewHolder.setMargin(mMarginLeftFirstItem, 10, 5, 5);
            }
        } else {
            viewHolder.setMargin(10, 10, 5, 5);
        }

    }

    public void setIBaseItemListener(IBaseItemListener iBaseItemListener) {
        this.mIBaseItemListener = iBaseItemListener;
    }

    @Override
    public int getItemCount() {
        return 10;
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

        public void setMargin(int left, int top, int right, int bottom) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int) dpToPx(300), (int) dpToPx(187));
            lp.setMargins((int) dpToPx(left), (int) dpToPx(top), (int) dpToPx(right), (int) dpToPx(bottom));
            mCardView.setLayoutParams(lp);
        }
    }
}
