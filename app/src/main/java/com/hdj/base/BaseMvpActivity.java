package com.hdj.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.hdj.frame.IContract;
import com.hdj.frame.MyModel;
import com.hdj.frame.MyPresenter;




public abstract class BaseMvpActivity<M extends MyModel> extends BaseActivity implements IContract.IView {
    private M model;
    public MyPresenter myPresenter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        model = initModel();
        myPresenter = new MyPresenter(this,model);
        initView();
        initData();
    }

    protected abstract void initData();

    protected abstract void initView();

    protected abstract M initModel();

    protected abstract int initLayout();

    public abstract void netSuccess(int whichApi, int loadType, Object[] pD);

    public void netFailed(int whichApi, Throwable pThrowable){}

    @Override
    public void onSuccess(int whichApi, int loadType, Object[] d) {
        netSuccess(whichApi,loadType,d);
    }

    @Override
    public void onFailed(int whichApi, Throwable throwable) {
        Toast.makeText(this, throwable.getMessage()!=null ? throwable.getMessage():"网络请求发生错误", Toast.LENGTH_SHORT).show();
        netFailed(whichApi,throwable);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        myPresenter.clear();
    }
}
