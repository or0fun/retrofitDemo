package com.baiwanlu.android.retrofit;

/**
 * Created by lufei on 6/26/16.
 */
public interface CallBack<T> {

    void onSuccess(T data);

    void onError(int errorCode);
}
