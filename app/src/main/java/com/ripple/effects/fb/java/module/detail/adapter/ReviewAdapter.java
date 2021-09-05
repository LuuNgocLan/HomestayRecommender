package com.ripple.effects.fb.java.module.detail.adapter;

import android.content.Context;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.base.java.core.helper.ImageHelper;
import com.ripple.effects.fb.java.R;
import com.ripple.effects.fb.java.models.detailhomestay.Review;

import java.util.List;


public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    public Context mContext;
    private List<Review> mReviews;

    public ReviewAdapter(Context context, List<Review> reviews) {
        mContext = context;
        mReviews = reviews;
    }

    @NonNull
    @Override
    public ReviewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        return new ViewHolder(LayoutInflater.from(mContext)
                .inflate(R.layout.item_review, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        viewHolder.bindView(mReviews.get(position));
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    public class ViewHolder
            extends RecyclerView.ViewHolder {
        TextView mTvName;
        TextView mTvContent;
        TextView mTvCountry;
        TextView mTvDate;
        TextView mTvScore;
        ImageView mImvAvatar;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvName = itemView.findViewById(R.id.name);
            mTvContent = itemView.findViewById(R.id.content);
            mTvCountry = itemView.findViewById(R.id.country);
            mTvDate = itemView.findViewById(R.id.date);
            mTvScore = itemView.findViewById(R.id.tv_star_homestay);
            mImvAvatar = itemView.findViewById(R.id.imv_avatar);

        }

        public void bindView(Review review) {
            if (review != null) {
                if (!TextUtils.isEmpty(review.getAvatar())) {
                    ImageHelper.load(mContext, mImvAvatar, R.drawable.ic_avatar_default, review.getAvatar());
                }
                if (!TextUtils.isEmpty(review.getContent())) {
                    mTvContent.setText(review.getContent());
                }
                if (!TextUtils.isEmpty(review.getName())) {
                    mTvName.setText(review.getName());
                }
                if (!TextUtils.isEmpty(review.getCountry())) {
                    mTvCountry.setText(review.getCountry());
                }
                if (!TextUtils.isEmpty(review.getDate())) {
                    mTvDate.setText(review.getDate());
                }
                if (!TextUtils.isEmpty(review.getScore() + "")) {
                    mTvScore.setText(review.getScore() + "");
                }

            }
        }


    }
}
