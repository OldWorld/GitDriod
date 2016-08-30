package com.feicui.edu.gitdriod.github;

import com.feicui.edu.gitdriod.github.model.Repo;

import java.util.List;


public interface RepoPtrView {

    /**
     * 1.显示刷新,显示刷新的视图
     * 2.停止刷新
     * 3.加载错误、网络加载失败
     * 4.刷新的数据为空，空页面
     * 5.拿到刷新得到数据
     */

//    拿到刷新得到数据
    void refreshData(List<Repo> list);

//    显示刷新的视图
    void showContentView();

//    停止刷新
    void stopRefresh();

//    空页面
    void showEmptyView();

//    加载错误
    void showErrorView(String errormsg);
    void showMessage(String msg);
}
