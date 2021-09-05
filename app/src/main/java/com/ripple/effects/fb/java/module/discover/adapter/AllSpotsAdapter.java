package com.ripple.effects.fb.java.module.discover.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.java.core.helper.ImageHelper;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.models.data.DataCenter;
import com.ripple.effects.fb.java.models.homestay.Homestay;
import com.ripple.effects.fb.java.module.base.IBaseItemListener;


import java.util.List;

import static com.base.java.core.utils.DimenUtils.dpToPx;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AllSpotsAdapter extends RecyclerView.Adapter<AllSpotsAdapter.ViewHolder> {

    public Context mContext;
    public IBaseItemListener mIBaseItemListener;
    public int mMarginLeftFirstItem = 0;
    private List<Homestay> mHomestayList;
    private DataCenter mDataCenter = DataCenter.getInstance();

    public AllSpotsAdapter(Context context, int marginLeftFirstItem, List<Homestay> homestays) {
        mContext = context;
        mMarginLeftFirstItem = marginLeftFirstItem;
        mHomestayList = homestays;
    }

    public AllSpotsAdapter(Context context, int marginLeftFirstItem) {
        mContext = context;
        mMarginLeftFirstItem = marginLeftFirstItem;
    }

    @NonNull
    @Override
    public AllSpotsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_portrait_homestay, viewGroup, false));
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
        if (mHomestayList != null) {
            viewHolder.bindView(mHomestayList.get(position));

            viewHolder.mImageViewFavorite.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mIBaseItemListener.onClickFavoriteHomestay(mHomestayList.get(position).getId(), true);
                }
            });
            viewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mHomestayList.get(position) != null)
                        mIBaseItemListener.openDetailHomestay(mHomestayList.get(position).getId());
                }
            });
        }

    }

    public void setIBaseItemListener(IBaseItemListener iBaseItemListener) {
        this.mIBaseItemListener = iBaseItemListener;
    }

    @Override
    public int getItemCount() {
        if (mHomestayList != null)
            return mHomestayList.size();
        return 8;
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder {

        CardView mCardView;
        ImageView mImageViewFavorite;
        ImageView mImvCenter;
        TextView mTvNameHomestay;
        TextView mTvPrice;
        TextView mTvScore;
        ImageView mImvStar;
        TextView mTvText;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.cardview);
            mImageViewFavorite = itemView.findViewById(R.id.imv_favorite);
            mTvNameHomestay = itemView.findViewById(R.id.tv_name_homestay);
            mTvPrice = itemView.findViewById(R.id.tv_price);
            mTvScore = itemView.findViewById(R.id.tv_star_num);
            mImvCenter = itemView.findViewById(R.id.iv_homestay);
            mImvStar = itemView.findViewById(R.id.imv_star);
            mTvText = itemView.findViewById(R.id.text);
        }

        public void bindView(Homestay homestay) {
            if (mDataCenter.isFavoritedHomestay(homestay.getId())) {
                mImageViewFavorite.setSelected(true);
            } else {
                mImageViewFavorite.setSelected(false);
            }

            if (!TextUtils.isEmpty(homestay.getName())) {
                mTvNameHomestay.setText(homestay.getName());
            }
            if (homestay.getReviewScore() != null) {
                mTvScore.setText(homestay.getReviewScore() + "");
            } else {
                mTvScore.setVisibility(View.GONE);
                mTvText.setVisibility(View.GONE);
                mImvStar.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(homestay.getPrice())) {
                mTvPrice.setText(homestay.getPrice());
            }
            if (!TextUtils.isEmpty(homestay.getImageCenter())) {
                ImageHelper.load(mContext, mImvCenter, R.drawable.img_center, homestay.getImageCenter());
            }
        }

        public void setMargin(int left, int top, int right, int bottom) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int) dpToPx(165), (int) dpToPx(240));
            lp.setMargins((int) dpToPx(left), (int) dpToPx(top), (int) dpToPx(right), (int) dpToPx(bottom));
            mCardView.setLayoutParams(lp);
        }

    }
}
