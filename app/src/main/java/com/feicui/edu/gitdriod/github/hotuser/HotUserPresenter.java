package com.feicui.edu.gitdriod.github.hotuser;


import com.feicui.edu.gitdriod.login.User;
import com.feicui.edu.gitdriod.network.GithubClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by 123 on 2016/8/30.
 */
public class HotUserPresenter {

    public interface HotUserView{
        /**
         * 1.显示刷新视图
         2.获取到数据，通知视图添加数据
         3.停止刷新
         4.加载失败，失败信息
         5.加载数据为空
         */

        void showRefreshView();

        void refreshData(List<User> users);

        void stopRefresh();

        void showMessage(String msg);

        void showErrorView();

        void showEmptyView();

        /**
         * 上拉加载视图分析
         * 1.显示加载视图
         * 2.隐藏加载视图
         * 3.拿到加载出来的数据
         * 4.显示加载出错的视图
         */
        void showLoadView();

        void hideLoadView();

        void addLoadData(List<User> list);
    }

    private int nextPage = 1;
    private Call<HotUserResult> userCall;
    private HotUserView hotUserView;

    public HotUserPresenter(HotUserView hotUserView) {
        this.hotUserView = hotUserView;
    }

    //刷新的业务
    public void refresh(){
        hotUserView.showRefreshView();
        hotUserView.hideLoadView();
        nextPage = 1;
        if (userCall!=null){
            userCall.cancel();
        }
        userCall = GithubClient.getInstance().searchUsers("followers:>1000", nextPage);
        userCall.enqueue(userCallback);
    }

    //加载的业务
    public void loadMore(){
        hotUserView.showLoadView();
        if (userCall!=null){
            userCall.cancel();
        }
        userCall = GithubClient.getInstance().searchUsers("followers:>1000", nextPage);
        userCall.enqueue(loadMoreCallback);
    }

    private Callback<HotUserResult> loadMoreCallback = new Callback<HotUserResult>() {
        @Override
        public void onResponse(Call<HotUserResult> call, Response<HotUserResult> response) {

            hotUserView.hideLoadView();
            if (response.isSuccessful()){
                HotUserResult hotUserResult = response.body();
               if (hotUserResult==null){
                   //数据为空，空页面
                   hotUserView.showErrorView();
                   hotUserView.showMessage("结果为空");
                   return;
               }
                List<User> users = hotUserResult.getUsers();
                //数据拿到了，是不是要通知视图拿到数据
                hotUserView.addLoadData(users);
                nextPage++;
                return;
            }
            //显示个错误信息
            hotUserView.showMessage("响应码："+response.code());
        }

        @Override
        public void onFailure(Call<HotUserResult> call, Throwable t) {
            hotUserView.hideLoadView();
            hotUserView.showMessage(t.getMessage());
        }
    };


    private Callback<HotUserResult> userCallback = new Callback<HotUserResult>() {
        @Override
        public void onResponse(Call<HotUserResult> call, Response<HotUserResult> response) {
            if (response.isSuccessful()){
                HotUserResult hotUserResult = response.body();
                if (hotUserResult==null){
                    //显示加载的结果为空
                    hotUserView.showMessage("结果为空！");
                    return;
                }
                //通知视图进行数据填充
                List<User> users = hotUserResult.getUsers();
                hotUserView.refreshData(users);
                hotUserView.stopRefresh();
                nextPage = 2;
            }
        }

        @Override
        public void onFailure(Call<HotUserResult> call, Throwable t) {
            hotUserView.stopRefresh();
            hotUserView.showMessage(t.getMessage());
        }
    };
}
