package com.example.lufei.retrofitsample;

import com.example.lufei.retrofitsample.entity.IPResult;

import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by lufei on 6/26/16.
 */
public interface IPRequest {

    @GET("service/getIpInfo.php")
    Observable<IPResult> getIPInfo(@Query("ip") String ip);
}
