package com.ripple.effects.fb.java.module.discover.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.java.core.helper.ImageHelper;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.models.homestay.Homestay;
import com.ripple.effects.fb.java.module.base.IBaseItemListener;

import java.util.List;
import java.util.Random;

import static com.base.java.core.utils.DimenUtils.*;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.ViewHolder> {

    public Context mContext;
    public IBaseItemListener mIBaseItemListener;
    private List<Homestay> mHomestayList;

    public RecommendAdapter(Context context, List<Homestay> homestayList) {
        mHomestayList = homestayList;
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
        viewHolder.bindView(mHomestayList.get(position));
        viewHolder.mImageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo: update status favorite of homestay item
                viewHolder.mImageViewFavorite.setSelected((new Random()).nextBoolean());
                mIBaseItemListener.onClickFavoriteHomestay(mHomestayList.get(position).getId(), true);

            }
        });

        viewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIBaseItemListener.openDetailHomestay(mHomestayList.get(position).getId());
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
        ImageView mImvCenter;
        TextView mTvNameHomestay;
        TextView mTvPrice;
        TextView mTvScore;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mCardView = itemView.findViewById(R.id.cardview);
            mImageViewFavorite = itemView.findViewById(R.id.imv_favorite);
            mTvNameHomestay = itemView.findViewById(R.id.tv_name_homestay);
            mTvPrice = itemView.findViewById(R.id.tv_price);
            mTvScore = itemView.findViewById(R.id.tv_star_num);
            mImvCenter = itemView.findViewById(R.id.iv_homestay);
        }

        public void bindView(Homestay homestay) {
            if (!TextUtils.isEmpty(homestay.getName())) {
                mTvNameHomestay.setText(homestay.getName());
            }
            if (!TextUtils.isEmpty(homestay.getReviewScore() + "")) {
                mTvScore.setText(homestay.getReviewScore() + "");
            }
            if (!TextUtils.isEmpty(homestay.getPrice())) {
                mTvPrice.setText(homestay.getPrice());
            }
            if (!TextUtils.isEmpty(homestay.getImageCenter())) {
                ImageHelper.load(mContext, mImvCenter, R.drawable.img_center, homestay.getImageCenter());
            }
        }
    }
}
