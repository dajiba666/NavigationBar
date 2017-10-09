package com.chao.lib;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yang2 on 2017/10/9.
 */

public abstract class AbsNavigationBar<P extends AbsNavigationBar.Builder.AbsNavigationParams> implements INavigationBar {

    private P mParams;
    private View mNavigationView;

    public P getParams() {
        return mParams;
    }

    public AbsNavigationBar(P params){
        this.mParams = params;
        createAndBindView();
    }
    protected void setText(int title, String mTitle) {
        TextView tv = getView(title);
        if (mTitle != null){
            tv.setVisibility(View.VISIBLE);
            tv.setText(mTitle);
        }
    }

    protected  <T extends View> T  getView(int viewId){
        return (T) mNavigationView.findViewById(viewId);
    }
    protected void setRightText(int tv_right_title, String mRightTitle) {
        TextView tv =  getView(tv_right_title);
        if (mRightTitle != null){
            tv.setVisibility(View.VISIBLE);
            tv.setText(mRightTitle);
        }
    }
    protected void setRightIcon(int ivRight, int icon) {
        ImageView iv =  getView(ivRight);
        if (icon != 0){
            iv.setVisibility(View.VISIBLE);
            iv.setImageResource(icon);
        }
    }
    protected void setOnClickListener(int viewId,View.OnClickListener listener){
        View view = getView(viewId);
        view.setOnClickListener(listener);
    }

    private void createAndBindView(){

        if (mParams.mParent ==null){
            ViewGroup activityRoot = (ViewGroup)((Activity)mParams.mContext).findViewById(android.R.id.content);
            mParams.mParent = (ViewGroup) activityRoot.getChildAt(0);
        }

        if (mParams.mParent == null){

            return;
        }

        mNavigationView = LayoutInflater.from(mParams.mContext).inflate(bindLayoutId(),mParams.mParent,false);

        mParams.mParent.addView(mNavigationView,0);

        applyView();
    }

    @Override
    public abstract int bindLayoutId() ;

    @Override
    public abstract void applyView() ;
    public abstract static class Builder{
        AbsNavigationParams P;

        public Builder(Context context, ViewGroup parent){
        }

        public Builder(Context context) {
        }

        public abstract AbsNavigationBar builer();
        public static class  AbsNavigationParams{

            public Context mContext;

            public ViewGroup mParent;

            public AbsNavigationParams(Context context,ViewGroup parent){
                this.mContext = context;

                this.mParent = parent;
            }

            public AbsNavigationParams(Context context) {
                this.mContext = context;
            }
        }
    }

}