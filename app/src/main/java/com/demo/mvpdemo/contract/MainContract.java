package com.demo.mvpdemo.contract;

import com.demo.mvpdemo.base.BasePresenter;
import com.demo.mvpdemo.model.entity.Topic;

import java.util.List;

/**
 * Created by ck on 2016/11/4.
 */

public interface MainContract {
    interface View  {
        void showLoading();
        void hideLoading();
        void showList(List<Topic> list);
        void showError(String msg);

    }
    interface Presenter extends BasePresenter {
        void showList();
    }
}
