package com.hdj.test5_31;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.hdj.base.BaseMvpActivity;
import com.hdj.data.TestInfo;
import com.hdj.frame.ApiConfig;
import com.hdj.frame.LoadTypeConfig;
import com.hdj.frame.MyModel;
import com.hdj.frame.ParamHashMap;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

public class MainActivity extends BaseMvpActivity {


    private ParamHashMap paramHashMap;
    int pageId = 0;
    private InfoAdapter infoAdapter;
    private RecyclerView recyclerView;
    private SmartRefreshLayout refresh;

    @Override
    protected void initData() {
        myPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.NORMAL, paramHashMap, pageId);
    }

    @Override
    protected void initView() {
        recyclerView = findViewById(R.id.recyclerView);
        refresh = findViewById(R.id.refresh);
        paramHashMap = new ParamHashMap().add("c", "api").add("a", "getList");
        initRecyclerView(recyclerView, refresh, new DataListener() {
            @Override
            public void dataType(int mode) {
                switch (mode) {
                    case LoadTypeConfig.REFRESH:
                        pageId = 0;
                        myPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.REFRESH, paramHashMap, pageId);
                        break;
                    case LoadTypeConfig.MORE:
                        pageId++;
                        myPresenter.getData(ApiConfig.TEST_GET, LoadTypeConfig.MORE, paramHashMap, pageId);
                        break;
                }
            }
        });
        infoAdapter = new InfoAdapter(this);
        recyclerView.setAdapter(infoAdapter);
    }

    @Override
    protected MyModel initModel() {
        return new MyModel();
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void netSuccess(int whichApi, int loadType, Object[] pD) {
        switch (whichApi) {
            case ApiConfig.TEST_GET:
                if (loadType == LoadTypeConfig.MORE) {
                    refresh.finishLoadMore();
                    infoAdapter.setDatasBeans(((TestInfo) pD[0]).datas);
                } else {
                    refresh.finishRefresh();
                    infoAdapter.setReDatasBeans(((TestInfo) pD[0]).datas);
                }
                break;

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) and run LayoutCreator again
    }
}
