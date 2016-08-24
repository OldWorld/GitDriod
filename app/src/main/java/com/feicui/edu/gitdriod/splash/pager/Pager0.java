package com.feicui.edu.gitdriod.splash.pager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.feicui.edu.gitdriod.R;


public class Pager0 extends FrameLayout {

    public Pager0(Context context) {
        this(context, null);
    }

    public Pager0(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public Pager0(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.content_pager_0, this, true);
    }
}
