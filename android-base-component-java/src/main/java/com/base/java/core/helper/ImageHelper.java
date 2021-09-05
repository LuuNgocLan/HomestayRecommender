package com.base.java.core.helper;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.base.java.R;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import static android.widget.ImageView.ScaleType.CENTER_CROP;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ImageHelper {

    private Context mContext;
    private Activity mActivity;
    private Fragment mFragment;

    public ImageHelper(Context context) {
        mContext = context;
    }

    public ImageHelper(Activity activity) {
        mActivity = activity;
    }

    public ImageHelper(Fragment fragment) {
        mFragment = fragment;
    }

    public static void load(Context context, ImageView view, String url) {
        load(context, view, CENTER_CROP, R.drawable.shape_image_placeholder, url);
    }

    public static void load(Context context, ImageView view, String url, ImageView.ScaleType scaleType) {
        load(context, view, scaleType, R.drawable.shape_image_placeholder, url);
    }

    public static void load(Context context, ImageView view, int placeHolder, String url) {
        load(context, view, CENTER_CROP, placeHolder, url);
    }

    private static void load(Context context, ImageView view, ImageView.ScaleType scaleType, int placeHolder, String url) {
        if (url == null) {
            url = "";
        }

        RequestOptions requestOptions = new RequestOptions();
        if (scaleType == CENTER_CROP) {
            requestOptions.centerCrop();
        }
        requestOptions.placeholder(placeHolder);
        requestOptions.error(placeHolder);
        Glide.with(context).load(url).apply(requestOptions).into(view);
    }

    public static void load(Context context, String url, final ImageHelperListener listener) {
        Glide.with(context)
                .asBitmap()
                .load(url)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        if (listener != null) {
                            listener.onRequestBitmapFinish(null);
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        if (listener != null) {
                            listener.onRequestBitmapFinish(resource);
                        }
                        return false;
                    }
                }).submit();
    }

    public static void load(Context context, String url, ImageView imageView, int placeholder, int errorHolder) {
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(placeholder).error(errorHolder);
        Glide.with(context).load(url).apply(requestOptions).into(imageView);
    }

    public static void clear(Context context, ImageView imageView) {
        Glide.with(context).clear(imageView);
    }

    public static void load(Activity context, ImageView view, String url) {
        load(context, view, CENTER_CROP, R.drawable.shape_image_placeholder, url);
    }

    public static void load(Activity context, ImageView view, String url, ImageView.ScaleType scaleType) {
        load(context, view, scaleType, R.drawable.shape_image_placeholder, url);
    }

    public static void load(Activity context, ImageView view, int placeHolder, String url) {
        load(context, view, CENTER_CROP, placeHolder, url);
    }

    private static void load(Activity context, ImageView view, ImageView.ScaleType scaleType, int placeHolder, String url) {
        RequestOptions requestOptions = new RequestOptions();
        if (scaleType == CENTER_CROP) {
            requestOptions.centerCrop();
        }
        requestOptions.placeholder(placeHolder);
        Glide.with(context).load(url).apply(requestOptions).into(view);
    }

    public static void load(Activity context, String url, final ImageHelperListener listener) {
        Glide.with(context)
                .asBitmap()
                .load(url)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        if (listener != null) {
                            listener.onRequestBitmapFinish(null);
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        if (listener != null) {
                            listener.onRequestBitmapFinish(resource);
                        }
                        return false;
                    }
                }).submit();
    }

    public static void load(Activity context, String url, ImageView imageView, int placeholder, int errorHolder) {
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(placeholder).error(errorHolder);
        Glide.with(context).load(url).apply(requestOptions).into(imageView);
    }

    public static void load(Fragment context, ImageView view, String url) {
        load(context, view, CENTER_CROP, R.drawable.shape_image_placeholder, url);
    }

    public static void load(Fragment context, ImageView view, String url, ImageView.ScaleType scaleType) {
        load(context, view, scaleType, R.drawable.shape_image_placeholder, url);
    }

    public static void load(Fragment context, ImageView view, int placeHolder, String url) {
        load(context, view, CENTER_CROP, placeHolder, url);
    }

    private static void load(Fragment context, ImageView view, ImageView.ScaleType scaleType, int placeHolder, String url) {
        RequestOptions requestOptions = new RequestOptions();
        if (scaleType == CENTER_CROP) {
            requestOptions.centerCrop();
        }
        requestOptions.placeholder(placeHolder);
        Glide.with(context).load(url).apply(requestOptions).into(view);
    }

    public static void load(Fragment context, String url, final ImageHelperListener listener) {
        Glide.with(context)
                .asBitmap()
                .load(url)
                .listener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        if (listener != null) {
                            listener.onRequestBitmapFinish(null);
                        }
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                        if (listener != null) {
                            listener.onRequestBitmapFinish(resource);
                        }
                        return false;
                    }
                }).submit();
    }

    public static void load(Context context, String url, ImageView imageView, int placeholder, int errorHolder, int roundRadius) {
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(placeholder).error(errorHolder);
        requestOptions = requestOptions.transforms(new CenterCrop(), new RoundedCorners(roundRadius));
        Glide.with(context)
                .load(url)
                .apply(requestOptions)
                .into(imageView);
    }

    public static void load(Fragment context, String url, ImageView imageView, int placeholder, int errorHolder) {
        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(placeholder).error(errorHolder);
        Glide.with(context).load(url).apply(requestOptions).into(imageView);
    }

    public void load(ImageView view, String url) {
        if (mContext != null) {
            load(mContext, view, R.drawable.shape_image_placeholder, url);
        } else if (mActivity != null) {
            load(mActivity, view, R.drawable.shape_image_placeholder, url);
        } else if (mFragment != null) {
            load(mFragment, view, R.drawable.shape_image_placeholder, url);
        }
    }

    public void load(ImageView view, int placeHolder, String url) {
        if (mContext != null) {
            load(mContext, view, placeHolder, url);
        } else if (mActivity != null) {
            load(mActivity, view, placeHolder, url);
        } else if (mFragment != null) {
            load(mFragment, view, placeHolder, url);
        }
    }

    public interface ImageHelperListener {

        void onRequestBitmapFinish(Bitmap bitmap);
    }
}
