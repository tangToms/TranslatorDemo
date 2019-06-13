package com.example.translatordemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.translatordemo.R;

public class MainActivity extends Activity  implements View.OnClickListener{
    //上下文
    private Context mContext;
    //按鈕，歡迎使用
    private Button btn_main;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        //初始化
        mContext=MainActivity.this;
        btn_main=findViewById(R.id.btn_main);

        //設置按鈕點擊監聽
        btn_main.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_main:
                Intent intent=new Intent();
                intent.setClass(mContext, SearchPageActivity.class);
                startActivity(intent);
                break;
        }
    }
}
