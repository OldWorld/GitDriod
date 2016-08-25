package com.feicui.edu.gitdriod.github;

import android.os.AsyncTask;

import java.util.ArrayList;


public class RepoListPresenter {

    private RepoListView repoListView;

    private int count = 0;

    public RepoListPresenter(RepoListView repoListView) {

        this.repoListView = repoListView;
    }

    public void refresh(){
        //显示刷新
        repoListView.showContentView();
        new Refresh().execute();
    }

    //加载更多的方法
    public void loadMore(){
        repoListView.showLoadingView();
        new LoadMore().execute();
    }


//    下拉刷新数据加载
    class Refresh extends AsyncTask<Void,Void,Void> {
        @Override protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                list.add("刷新出来的数据"+(count++));
            }
            repoListView.refreshData(list);
            //停止刷新
            repoListView.stopRefresh();
        }
    }

    //    上拉加载数据
    class LoadMore extends AsyncTask<Void,Void,Void> {
        @Override protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
        @Override protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            ArrayList<String> list = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                list.add("加载出来的数据"+(count++));
            }
            repoListView.addLoadData(list);
            repoListView.hideLoadView();
        }
    }
}
