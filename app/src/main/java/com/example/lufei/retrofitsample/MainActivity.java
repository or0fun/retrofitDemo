package com.example.lufei.retrofitsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.baiwanlu.android.retrofit.CallBack;
import com.example.lufei.retrofitsample.entity.IPResult;
import com.example.lufei.retrofitsample.request.IPRequest;
import com.example.lufei.retrofitsample.request.UploadRequest;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.text_view)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        new IPRequest("111.32.23.2").start(new CallBack<IPResult>() {
            @Override
            public void onSuccess(IPResult data) {
                textView.setText(data.data.country);
            }

            @Override
            public void onError(int errorCode) {

            }
        });

        new UploadRequest("test").start(new CallBack<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody data) {

            }

            @Override
            public void onError(int errorCode) {

            }
        });
    }
}
