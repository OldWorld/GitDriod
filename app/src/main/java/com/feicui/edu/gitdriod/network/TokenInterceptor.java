package com.feicui.edu.gitdriod.network;


import com.feicui.edu.gitdriod.login.UserRepo;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class TokenInterceptor implements Interceptor{

    @Override public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        if (UserRepo.hasAccessToken()){
            //Authorization: token OAUTH-TOKEN
            builder.header("Authorization","token "+UserRepo.getAccessToken());
        }
        Response response = chain.proceed(builder.build());
        if (response.isSuccessful()){
            return response;
        }
        if (response.code()==401 || response.code()==403){
            throw new IOException("未经授权的");
        }else {
            throw new IOException("响应码："+response.code());
        }
    }
}
