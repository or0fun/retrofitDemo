package com.baiwanlu.android.retrofit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lufei on 6/26/16.
 */
public abstract class BaseRequest<T> {

    CallBack<T> callBack;

    public BaseRequest() {
    }

    public <T> T getInterfaceImp(Class<T> tClass) {
        return RetrofitManager
                .get(getHost())
                .create(tClass);
    }

    public abstract String getHost();

    public abstract Observable<T> getObservable();


    public void start(CallBack<T> callBack) {
        this.callBack = callBack;

                getObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public void cancel() {
        if (!subscriber.isUnsubscribed()) {
            subscriber.unsubscribe();
        }
    }

    Subscriber<T> subscriber = new Subscriber<T>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            if (null != callBack) {
                callBack.onError(0);
            }
        }

        @Override
        public void onNext(T t) {
            if (null != callBack) {
                callBack.onSuccess(t);
            }
        }
    };
}
