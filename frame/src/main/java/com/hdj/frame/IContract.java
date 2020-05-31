package com.hdj.frame;

import io.reactivex.disposables.Disposable;

public interface IContract {
    interface IView<D> {
        void onSuccess(int whichApi, int loadType, D... d);

        void onFailed(int whichApi, Throwable throwable);
    }

    interface IPresenter<P> extends IView {
        void getData(int whichApi,P... p);
        void addObServer(Disposable disposable);
    }

    interface IModel<T>{
        void getData(IPresenter presenter,int whichApi,T... params);
    }
}
