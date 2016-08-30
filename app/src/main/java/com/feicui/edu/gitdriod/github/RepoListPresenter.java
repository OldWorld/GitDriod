package com.feicui.edu.gitdriod.github;

import android.os.AsyncTask;

import com.feicui.edu.gitdriod.github.model.Repo;
import com.feicui.edu.gitdriod.github.model.RepoResult;
import com.feicui.edu.gitdriod.github.view.Language;
import com.feicui.edu.gitdriod.network.GithubClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RepoListPresenter {

    private RepoListView repoListView;
    private Language language;
    private int nextPage = 1;
    private Call<RepoResult> repoCall;

    public RepoListPresenter(RepoListView repoListView, Language language) {
        this.repoListView = repoListView;
        this.language = language;
    }

    public void refresh(){
        //显示刷新
        repoListView.showContentView();
        nextPage = 1;
        repoCall = GithubClient.getInstance().searchRepos("language:" + language.getPath(), nextPage);
        repoCall.enqueue(repoCallback);
    }

    //加载更多的方法
    public void loadMore(){
        repoListView.showLoadingView();
        repoCall = GithubClient.getInstance().searchRepos("language:" + language.getPath(), nextPage);
        repoCall.enqueue(loadMoreCallBack);
    }

    //异步获取仓库
    private Callback<RepoResult> repoCallback = new Callback<RepoResult>() {
        @Override public void onResponse(Call<RepoResult> call, Response<RepoResult> response) {
            repoListView.stopRefresh();
            RepoResult repoResult = response.body();

            //结果为空null
            if (repoResult == null) {
                repoListView.showErrorView("结果为空");
                return;
            }

            //结果不为空0/有数据
            if (repoResult.getTotalCount() <= 0) {
                repoListView.refreshData(null);
                repoListView.showEmptyView();
                return;
            }

            List<Repo> repoList = repoResult.getRepoList();
            repoListView.refreshData(repoList);
            //下拉刷新第一页数据完成，下一次进行加载从第二页开始
            nextPage = 2;
        }

        //失败的处理
        @Override public void onFailure(Call<RepoResult> call, Throwable t) {
            repoListView.stopRefresh();
            repoListView.showMessage(t.getMessage());
        }
    };

    private Callback<RepoResult> loadMoreCallBack = new Callback<RepoResult>() {
        @Override public void onResponse(Call<RepoResult> call, Response<RepoResult> response) {
            RepoResult repoResult = response.body();
            if (repoResult==null){
                repoListView.showLoadError("结果为空");
                return;
            }

            //不为空
            List<Repo> repos = repoResult.getRepoList();
            repoListView.addLoadData(repos);
            nextPage++;
        }

        @Override public void onFailure(Call<RepoResult> call, Throwable t) {
            repoListView.hideLoadView();
            repoListView.showMessage(t.getMessage());
        }
    };

}
