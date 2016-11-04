package com.demo.mvpdemo.presenter;

import com.demo.mvpdemo.activity.MainActivity;
import com.demo.mvpdemo.contract.MainContract;
import com.demo.mvpdemo.model.api.ApiService;
import com.demo.mvpdemo.model.entity.TopicRsp;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ck on 2016/11/4.
 */

public class MainPresenter implements MainContract.Presenter {
    private MainContract.View mMainActivity;

    public MainPresenter(MainContract.View mainActivity) {
        mMainActivity = mainActivity;
    }

    @Override
    public void start() {
        showList();
    }
    @Override
    public void showList(){
        mMainActivity.showLoading();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cnodejs.org/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService service = retrofit.create(ApiService.class);
        Call<TopicRsp> call = service.listRepos();
        call.enqueue(new Callback<TopicRsp>() {
            @Override
            public void onResponse(Call<TopicRsp> call, Response<TopicRsp> response) {
                mMainActivity.hideLoading();
                if(response.body().isSuccess()){
                    mMainActivity.showList(response.body().getData());
                }else{
                    mMainActivity.showError("接口异常");
                }
            }

            @Override
            public void onFailure(Call<TopicRsp> call, Throwable t) {
                mMainActivity.hideLoading();
                mMainActivity.showError("网络异常");
            }
        });

    }
}
