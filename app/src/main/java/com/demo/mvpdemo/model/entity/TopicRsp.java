package com.demo.mvpdemo.model.entity;

import java.util.List;

/**
 * Created by ck on 2016/11/4.
 */

public class TopicRsp {
    private  boolean success;


    private List<Topic> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public List<Topic> getData() {
        return data;
    }

    public void setData(List<Topic> data) {
        this.data = data;
    }
}
