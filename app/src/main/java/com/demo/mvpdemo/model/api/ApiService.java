package com.demo.mvpdemo.model.api;

import com.demo.mvpdemo.model.entity.TopicRsp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by ck on 2016/11/4.
 */

public interface ApiService {
    @GET("topics")
    Call<TopicRsp> listRepos();
}
