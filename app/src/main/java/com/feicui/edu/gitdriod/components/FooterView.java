package com.feicui.edu.gitdriod.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


import com.feicui.edu.gitdriod.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FooterView extends FrameLayout {

    private static final int STATE_LOADING = 0;
    private static final int STATE_ERROR = 1;
    private static final int STATE_COMPLETE = 2;

    private int state = STATE_LOADING;

    @BindView(R.id.progressBar) ProgressBar progressBar;
    @BindView(R.id.tv_error) TextView tvError;

    public FooterView(Context context) {
        this(context, null);
    }

    public FooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FooterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_load_footer, this, true);
        ButterKnife.bind(this);
    }

    /**
     * 1.进行加载的时候，显示进度条
     * 2.出现错误的视图
     * 3.是不是正在加载
     * 4.是不是加载完成
     */

//    是不是正在加载
    public boolean isLoading() {
        return state==STATE_LOADING;
    }

//    是不是加载完成
    public boolean isComplete(){
        return state==STATE_COMPLETE;
    }

    //    进行加载的时候，显示进度条
    public void showLoading() {
        state=STATE_LOADING;
        progressBar.setVisibility(VISIBLE);
        tvError.setVisibility(GONE);
    }

    //    出现错误的视图
    public void showError() {
        state=STATE_ERROR;
        progressBar.setVisibility(GONE);
        tvError.setVisibility(VISIBLE);
    }

}
