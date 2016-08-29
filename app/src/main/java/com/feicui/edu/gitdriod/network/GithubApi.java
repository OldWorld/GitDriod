package com.feicui.edu.gitdriod.network;

import com.feicui.edu.gitdriod.login.AccessToken;
import com.feicui.edu.gitdriod.login.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
/**
 * Created by Administrator on 16-8-29.
 */
public interface GithubApi {

  /*  Client ID
    bc32af01ebfe7aa65194
    Client Secret
    1306356429717edeeda258fd0f2d6bb90db5e6af*/

    String CLIENT_ID = "bc32af01ebfe7aa65194";
    String CLIENT_SECRET = "1306356429717edeeda258fd0f2d6bb90db5e6af";

    String CALLBACK = "feicui";
    String AUTH_SCOPE = "user,public_repo,repo";
    String AUTH_URL = "https://github.com/login/oauth/authorize?client_id="+CLIENT_ID+"&scope="+AUTH_SCOPE;

    /**
     * 获得访问令牌Token
     * @return
     */
    @FormUrlEncoded
    @POST("https://github.com/login/oauth/access_token")
    @Headers("Accept: application/json")
    Call<AccessToken> getOAuthToken(
            @Field("client_id") String clientId,
            @Field("client_secret")String clientSecret,
            @Field("code") String code);

  /**
   * 使用Token获取用户信息
   * @return
   */
  @GET("user")
  Call<User> getUser();
}
