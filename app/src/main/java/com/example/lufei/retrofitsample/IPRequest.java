package com.example.lufei.retrofitsample;

import com.baiwanlu.android.retrofit.BaseRequest;
import com.baiwanlu.android.retrofit.CallBack;
import com.example.lufei.retrofitsample.entity.IPResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lufei on 6/26/16.
 */
public class IPRequest extends BaseRequest<IPResult> {

    String ip;

    public IPRequest(String ip) {
        super();
        this.ip = ip;
    }

    @Override
    public String getHost() {
        return "http://ip.taobao.com";
    }

    @Override
    public Observable<IPResult> getObservable() {
        return getInterfaceImp(IPRequestInterface.class)
                .getIPInfo(ip);
    }

    @Override
    public void start(CallBack<IPResult> callBack) {
        super.start(callBack);
    }

    interface IPRequestInterface {
        @GET("service/getIpInfo.php")
        Observable<IPResult> getIPInfo(@Query("ip") String ip);
    }
}
