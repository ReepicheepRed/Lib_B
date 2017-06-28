package com.yancy.gallerypick.presenter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.yancy.gallerypick.R;
import com.yancy.gallerypick.adapter.SelectedPhotoAdapter;
import com.yancy.gallerypick.inter.IHandlerCallBack;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhiPeng.S on 2017/6/28.
 */

public class GalleryPickerPresenter {
    private String TAG = getClass().getSimpleName();
    private Activity mActivity;
    private ArrayList<String> photos = new ArrayList<>();
    private RecyclerView recyclerView;
    private SelectedPhotoAdapter adapter;
    private IHandlerCallBack iHandlerCallBack;
    public GalleryPickerPresenter(Activity mActivity,RecyclerView recyclerView) {
        this.mActivity = mActivity;
        this.recyclerView = recyclerView;
        init();
    }
    
    private void init(){
        adapter = new SelectedPhotoAdapter(mActivity,photos);
        adapter.setClick(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity,LinearLayoutManager.HORIZONTAL,false));
        iHandlerCallBack = new IHandlerCallBack() {
            @Override
            public void onStart() {
                Log.i(TAG, "onStart: 开启");
            }

            @Override
            public void onSuccess(List<String> photoList) {
                Log.i(TAG, "onSuccess: 返回数据");
                update(photoList);
            }

            @Override
            public void onCancel() {
                Log.i(TAG, "onCancel: 取消");
            }

            @Override
            public void onFinish() {
                Log.i(TAG, "onFinish: 结束");
            }

            @Override
            public void onError() {
                Log.i(TAG, "onError: 出错");
            }
        };
    }

    public void click(View view, int position) {
        int i = view.getId();
        if (i == R.id.iSelected_remove_iv) {
            photos.remove(position);
            update(photos);
        }
    }
    
    public void update(List<String> data){
        photos.clear();
        photos.addAll(data);
        adapter.notifyDataSetChanged();
    }

    public IHandlerCallBack getiHandlerCallBack() {
        return iHandlerCallBack;
    }
}
