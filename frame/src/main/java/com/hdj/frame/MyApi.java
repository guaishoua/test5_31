package com.hdj.frame;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApi {
    public static MyApi myApi;

    public MyApi() {

    }

    public static MyApi getInstance() {
        if (myApi == null) {
            synchronized (MyApi.class) {
                myApi = new MyApi();
            }
        }
        return myApi;
    }

    public <T> ApiService getApiService(T... ts) {
        String baseUrl = ServerAddressConfig.BASE_URL;
        if (ts != null && ts.length != 0) {
            baseUrl = (String) ts[0];
        }
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient())
                .build().create(ApiService.class);
    }

    public <T,O> void netWork(Observable<T> localTestInfo, final IContract.IPresenter iPresenter, final int whichApi, final int dataType, O... o){
        localTestInfo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        super.onSubscribe(d);
                        iPresenter.addObServer(d);
                    }
                    @Override
                    protected void onSuccess(Object value) {
                        iPresenter.onSuccess(whichApi,dataType,value);
                    }

                    @Override
                    protected void onFailed(Throwable e) {
                        iPresenter.onFailed(whichApi,e);
                    }
                });
    }
}
