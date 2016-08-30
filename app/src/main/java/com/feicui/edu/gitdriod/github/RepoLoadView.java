package com.feicui.edu.gitdriod.github;

import com.feicui.edu.gitdriod.github.model.Repo;

import java.util.List;


public interface RepoLoadView {

    /**
     1. 显示加载视图
     2. 隐藏加载视图
     3. 加载失败视图
     4. 加载完成，拿到数据进行视图更新
     */
//    显示加载视图
    void showLoadingView();

//    隐藏加载视图
    void hideLoadView();

//    加载失败视图
    void showLoadError(String msg);
    //    加载完成，拿到数据进行视图更新
    void addLoadData(List<Repo> list);
}
