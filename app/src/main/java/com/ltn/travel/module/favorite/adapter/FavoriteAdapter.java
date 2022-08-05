package com.ltn.travel.module.favorite.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.base.java.core.helper.ImageHelper;
import com.ltn.travel.R;
import com.ltn.travel.models.detailhomestay.Homestay;
import com.ltn.travel.models.favorite.Favorite;
import com.ltn.travel.module.base.IBaseItemListener;

import java.util.List;
import java.util.Random;

import static com.base.java.core.utils.DimenUtils.dpToPx;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    public Context mContext;
    public IBaseItemListener mIBaseItemListener;
    private List<Favorite> mFavorites;

    public FavoriteAdapter(Context context, List<Favorite> favorites) {
        mContext = context;
        mFavorites = favorites;
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_landscape_homestay, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        viewHolder.setMargin(15, 0, 15, 15);
        viewHolder.bindView(mFavorites.get(position).getHomestay());
        viewHolder.mImageViewFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo: update status favorite of homestay item
                viewHolder.mImageViewFavorite.setSelected((new Random()).nextBoolean());
                mIBaseItemListener.onClickFavoriteHomestay(mFavorites.get(position).getHomestay().getId(), true);

            }
        });

        viewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIBaseItemListener.openDetailHomestay(mFavorites.get(position).getHomestay().getId());
            }
        });
    }

    public void setIBaseItemListener(IBaseItemListener iBaseItemListener) {
        this.mIBaseItemListener = iBaseItemListener;
    }

    @Override
    public int getItemCount() {
        return mFavorites.size();
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder {

        CardView mCardView;
        ImageView mImageViewFavorite;
        ImageView mImvHomestay;
        TextView mTvName;
        TextView mTvPrice;
        TextView mTvStarNum;
        TextView mTvText;
        ImageView mImvStar;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvText = itemView.findViewById(R.id.text);
            mImvStar = itemView.findViewById(R.id.iv_star);
            mTvStarNum = itemView.findViewById(R.id.tv_star_num);
            mTvPrice = itemView.findViewById(R.id.tv_price);
            mTvName = itemView.findViewById(R.id.tv_name_homestay);
            mImvHomestay = itemView.findViewById(R.id.iv_homestay);
            mCardView = itemView.findViewById(R.id.cardview);
            mImageViewFavorite = itemView.findViewById(R.id.imv_favorite);
        }

        public void bindView(Homestay homestay) {
            mImageViewFavorite.setSelected(true);
            if (!TextUtils.isEmpty(homestay.getName())) {
                mTvName.setText(homestay.getName());
            }
            if (homestay.getReviewScore() != null) {
                mTvStarNum.setText(homestay.getReviewScore() + "");
            } else {
                mTvStarNum.setVisibility(View.GONE);
                mTvText.setVisibility(View.GONE);
                mImvStar.setVisibility(View.GONE);
            }
            if (!TextUtils.isEmpty(homestay.getPrice())) {
                mTvPrice.setText(homestay.getPrice());
            }
            if (!TextUtils.isEmpty(homestay.getImageCenter())) {
                ImageHelper.load(mContext, mImvHomestay, R.drawable.img_center, homestay.getImageCenter());
            }
        }

        public void setMargin(int left, int top, int right, int bottom) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams((int) dpToPx(344), (int) dpToPx(187));
            lp.setMargins(left, top, right, bottom);
            mCardView.setLayoutParams(lp);
        }

    }
}
