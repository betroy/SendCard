package com.troy.sendcard.ui.adapter;

import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.squareup.picasso.Picasso;
import com.troy.sendcard.R;

import java.io.File;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by chenlongfei on 16/4/13.
 */
public class PictureAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> mPics;
    private int mGridWidth;
    private ImageLoader mImageLoader;
    private static final int MAX_SELECTOR_AMOUNT = 9;

    public PictureAdapter(Context context, List<String> images) {
        mContext = context;
        this.mPics = images;
        mImageLoader = ImageLoader.getInstance();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int width = 0;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            Point size = new Point();
            wm.getDefaultDisplay().getSize(size);
            width = size.x;
        } else {
            width = wm.getDefaultDisplay().getWidth();
        }
        mGridWidth = width / 4;
    }

    @Override
    public int getCount() {
        return null == mPics ? 1 : mPics.size() == 9 ? 9 : mPics.size() + 1;
    }

    @Override
    public Object getItem(int position) {
        return mPics.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_pic_choose, parent, false);
            viewHolder = new ViewHolder(convertView);
            viewHolder.pic = (ImageView) convertView.findViewById(R.id.iv_pic);
            viewHolder.tips = (TextView) convertView.findViewById(R.id.tv_tips);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (position == mPics.size()) {
            Picasso.with(mContext).load(R.drawable.icon_addpic).centerCrop().resize(mGridWidth, mGridWidth).into(viewHolder.pic);
            if (position == MAX_SELECTOR_AMOUNT) {
                viewHolder.pic.setVisibility(View.GONE);
            }
        } else {
            File imageFile = new File(mPics.get(position));
            if (imageFile.exists()) {
                Picasso.with(mContext).load(imageFile).centerCrop().resize(mGridWidth, mGridWidth).into(viewHolder.pic);
            }
        }
        return convertView;
    }

    public class ViewHolder {
        @Bind(R.id.iv_pic)
        ImageView pic;

        @Bind(R.id.tv_tips)
        TextView tips;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
