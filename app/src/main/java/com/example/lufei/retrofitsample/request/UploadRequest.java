package com.example.lufei.retrofitsample.request;

import com.baiwanlu.android.retrofit.BaseRequest;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by lufei on 6/26/16.
 */
public class UploadRequest extends BaseRequest<ResponseBody> {

    String fileName;

    public UploadRequest(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String getHost() {
        return null;
    }

    @Override
    public Observable<ResponseBody> getObservable() {
        File file = new File(fileName);
        RequestBody requestFile = RequestBody.create(MediaType.parse("application/otcet-tream"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("aFile", file.getName(), requestFile);
        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), "this is a description");

        return getInterfaceImp(UploadInterface.class).upload(requestBody, part);
    }

    interface UploadInterface {
        @Multipart
        @POST("upload")
        Observable<ResponseBody> upload(@Part("description") RequestBody description,
                                  @Part MultipartBody.Part file);
    }
}
