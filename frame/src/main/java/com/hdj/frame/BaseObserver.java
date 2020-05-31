package com.hdj.frame;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver implements Observer {
    private Disposable disposable;

    @Override
    public void onSubscribe(Disposable d) {
        disposable = d;
    }

    @Override
    public void onNext(Object o) {
        onSuccess(o);
        disposable.dispose();
    }

    protected abstract void onSuccess(Object value);

    protected abstract void onFailed(Throwable e);

    @Override
    public void onError(Throwable e) {
        onFailed(e);
        disposable.dispose();
    }

    @Override
    public void onComplete() {
        disposable.dispose();
    }

    public void dispose() {
        if(disposable!=null&&!disposable.isDisposed()){
            disposable.dispose();;
        }
    }
}
