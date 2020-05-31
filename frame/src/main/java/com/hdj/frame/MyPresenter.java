package com.hdj.frame;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class MyPresenter<V extends IContract.IView, M extends IContract.IModel> implements IContract.IPresenter {
    private SoftReference<V> mView;
    private SoftReference<M> mModel;
    private List<Disposable> disposables;

    public  MyPresenter(V pView, M mpModel) {
        this.mView = new SoftReference<>(pView);
        this.mModel = new SoftReference<>(mpModel);
        disposables = new ArrayList<>();
    }

    @Override
    public void onSuccess(int witchApi, int loadType, Object... d) {
        if (mView != null && mView.get() != null) mView.get().onSuccess(witchApi, loadType, d);
    }

    @Override
    public void onFailed(int witchApi, Throwable throwable) {
        if (mView != null && mView.get() != null) mView.get().onFailed(witchApi, throwable);
    }

    @Override
    public void getData(int whichApi, Object... p) {
        if (mModel != null && mModel.get() != null) mModel.get().getData(this, whichApi, p);
    }

    @Override
    public void addObServer(Disposable disposable) {
        if (disposable == null) return;
        disposables.add(disposable);
    }

    public void clear() {
        for (int i = 0; i < disposables.size(); i++) {
            if (disposables.get(i)!=null&&!disposables.get(i).isDisposed()){
                disposables.get(i).dispose();
            }
        }
        if (mModel != null) {
            mModel.clear();
            mModel = null;
        }
        if (mView != null) {
            mView.clear();
            mView = null;
        }
    }
}
