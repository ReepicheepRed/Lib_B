package com.yancy.gallerypick.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yancy.gallerypick.R;
import com.yancy.gallerypick.config.GalleryConfig;
import com.yancy.gallerypick.config.GalleryPick;
import com.yancy.gallerypick.presenter.GalleryPickerPresenter;
import com.yancy.gallerypick.utils.ScreenUtils;
import com.yancy.gallerypick.widget.GalleryImageView;

import java.util.List;

/**
 * Mini选择器 适配器
 * Created by Yancy on 2016/2/3.
 */
public class SelectedPhotoAdapter extends RecyclerView.Adapter<SelectedPhotoAdapter.ViewHolder> {

    private Context mContext;
    private Activity mActivity;
    private LayoutInflater mLayoutInflater;
    private List<String> photoInfoList;

    private GalleryConfig galleryConfig = GalleryPick.getInstance().getGalleryConfig();

    private GalleryPickerPresenter click;

    public SelectedPhotoAdapter(Context context, List<String> photoInfoList) {
        mLayoutInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.photoInfoList = photoInfoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(mLayoutInflater.inflate(R.layout.item_selected, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        galleryConfig.getImageLoader().displayImage(mActivity, mContext, photoInfoList.get(position),
                holder.ivPhotoImage, ScreenUtils.getScreenWidth(mContext), ScreenUtils.getScreenWidth(mContext));

        holder.chkPhotoSelector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.click(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return photoInfoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private GalleryImageView ivPhotoImage;
        private ImageView chkPhotoSelector;

        public ViewHolder(View itemView) {
            super(itemView);
            ivPhotoImage = (GalleryImageView) itemView.findViewById(R.id.iSelected_icon);
            chkPhotoSelector = (ImageView) itemView.findViewById(R.id.iSelected_remove_iv);
        }
    }

    public void setmActivity(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public void setClick(GalleryPickerPresenter click) {
        this.click = click;
    }
}
/*
 *   ┏┓　　　┏┓
 * ┏┛┻━━━┛┻┓
 * ┃　　　　　　　┃
 * ┃　　　━　　　┃
 * ┃　┳┛　┗┳　┃
 * ┃　　　　　　　┃
 * ┃　　　┻　　　┃
 * ┃　　　　　　　┃
 * ┗━┓　　　┏━┛
 *     ┃　　　┃
 *     ┃　　　┃
 *     ┃　　　┗━━━┓
 *     ┃　　　　　　　┣┓
 *     ┃　　　　　　　┏┛
 *     ┗┓┓┏━┳┓┏┛
 *       ┃┫┫　┃┫┫
 *       ┗┻┛　┗┻┛
 *        神兽保佑
 *        代码无BUG!
 */