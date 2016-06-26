package com.example.lufei.retrofitsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.lufei.retrofitsample.entity.IPResult;

import butterknife.Bind;
import butterknife.ButterKnife;

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

    }
}
