package com.demo.mvpdemo.activity;

import android.os.Handler;
import android.os.Looper;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.demo.mvpdemo.R;
import com.demo.mvpdemo.contract.MainContract;
import com.demo.mvpdemo.model.entity.Topic;
import com.demo.mvpdemo.presenter.MainPresenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements MainContract.View {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private MainContract.Presenter mPresenter;
    private SimpleAdapter mSimpleAdapter;
    private List<Map<String, Object>> listData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.activity_main);
        mListView = (ListView)findViewById(R.id.listview);
        mSimpleAdapter = new SimpleAdapter(this,listData,android.R.layout.simple_list_item_2,new String[] { "title" },new int[]{android.R.id.text1});
        mListView.setAdapter(mSimpleAdapter);
        mPresenter = new MainPresenter(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void showLoading() {
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                mSwipeRefreshLayout.setRefreshing(true);
            }
        });

    }

    @Override
    public void hideLoading() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showList(List<Topic> list) {
        for(Topic topic : list){
            HashMap map = new HashMap();
            map.put("title",topic.getTitle());
            listData.add(map);
        }
        mSimpleAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
    }
}
