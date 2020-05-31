package com.hdj.frame;

import java.util.Map;

public class MyModel implements IContract.IModel {
    MyApi instance = MyApi.getInstance();
    @Override
    public void getData(IContract.IPresenter presenter, int whichApi, Object[] params) {
        final int loadType = (int) params[0];
        Map param = (Map) params[1];
        int pageId = (int) params[2];
        switch (whichApi) {
            case ApiConfig.TEST_GET:
                instance.netWork(instance.getApiService().getInfoData(param, pageId), presenter, whichApi, loadType);
                break;
        }
    }
}
