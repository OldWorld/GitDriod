package com.feicui.edu.gitdriod.login;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.feicui.edu.gitdriod.MainActivity;
import com.feicui.edu.gitdriod.R;
import com.feicui.edu.gitdriod.commons.ActivityUtils;
import com.feicui.edu.gitdriod.network.GithubApi;

import java.net.URI;

import butterknife.BindView;
import butterknife.ButterKnife;
import pl.droidsonroids.gif.GifImageView;


public class LoginActivity extends AppCompatActivity implements LoginView{

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.webView) WebView webView;
    @BindView(R.id.gifImageView) GifImageView gifImageView;
    private LoginPresenter presenter;
    private ActivityUtils activityUtils;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityUtils = new ActivityUtils(this);
        setContentView(R.layout.activity_login);

    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initWebView();
    }
    public void initWebView(){
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeAllCookie();
        webView.loadUrl(GithubApi.AUTH_URL);
        webView.setFocusable(true);
        webView.setFocusableInTouchMode(true);
        webView.setWebChromeClient(webChromeClient);
        webView.setWebViewClient(webViewClient);
    }
    private WebChromeClient webChromeClient = new WebChromeClient(){
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if(newProgress>=100){
                gifImageView.setVisibility(View.GONE);
            }
        }
    };
    private WebViewClient webViewClient = new WebViewClient(){

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            Uri uri = Uri.parse(url);
            if(GithubApi.CALLBACK.equals(uri.getScheme())){
                String code = uri.getQueryParameter("code");

                return true;
            }
            return super.shouldOverrideUrlLoading(view, url);
        }
    };


    /**
     * 需要实现的视图
     */
    @Override public void gotoMainActivity() {
        activityUtils.startActivity(MainActivity.class);
        finish();
    }

    @Override public void showMessage(String msg) {
        activityUtils.showToast(msg);
    }

    @Override public void showProgress() {
        gifImageView.setVisibility(View.VISIBLE);
    }

    @Override public void resetWebView() {
        initWebView();
    }
}
