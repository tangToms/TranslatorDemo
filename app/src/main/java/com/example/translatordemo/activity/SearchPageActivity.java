package com.example.translatordemo.activity;

import android.app.Activity;
import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.translatordemo.R;
import com.example.translatordemo.adapter.SampleSentenceAdapter;
import com.example.translatordemo.entity.SampleSentence;
import com.example.translatordemo.entity.WordsInfo;
import com.example.translatordemo.util.WebServiceUtil;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchPageActivity extends Activity implements View.OnClickListener,MediaPlayer.OnCompletionListener {
    //上下文
    private Context mContext;
    //組件
    //查詢單詞
    private EditText et_text;
    //查詢按鈕
    private Button btn_search;
    //翻譯文本
    private TextView tv_transText;
    //原文本
    private TextView tv_originText;
    //拼讀文本
    private TextView tv_spellText;
    //播放音頻
    private Button btn_voice;
    //例句
    private ListView lv_sample;

    //保存返回信息
    private WordsInfo wordsInfo=new WordsInfo();
    //例句
    private List<SampleSentence> sampleSentences=new ArrayList<>();
    //例句適配器
    private SampleSentenceAdapter sampleSentenceAdapter;
    //音頻編碼
    private String voiceCode;

    //消息處理頁面更新
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 0x001:
                    //更新頁面
                    tv_originText.setText(wordsInfo.getOriginText());
                    tv_transText.setText(wordsInfo.getTransText());
                    tv_spellText.setText(wordsInfo.getPron());
                    //刷新例句
                    sampleSentenceAdapter.notifyDataSetChanged();
                    //查詢完成，查詢按鈕變為可用
                    btn_search.setEnabled(true);
                    break;
                case 0x002:
                    Toast.makeText(mContext,msg.getData().getString("error"),Toast.LENGTH_LONG).show();
                    break;
            }
        }
    };
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_page);

        //初始化
        mContext =SearchPageActivity.this;
        et_text=findViewById(R.id.et_text);
        btn_search=findViewById(R.id.btn_search);
        tv_transText=findViewById(R.id.tv_transText);
        tv_originText=findViewById(R.id.tv_originText);
        tv_spellText=findViewById(R.id.tv_spellText);
        btn_voice=findViewById(R.id.btn_spellVoice);
        lv_sample=findViewById(R.id.lv_sample);

        //設置例句適配器
        wordsInfo.setSampleSentences(sampleSentences);
        sampleSentenceAdapter=new SampleSentenceAdapter(sampleSentences,mContext);
        lv_sample.setAdapter(sampleSentenceAdapter);

        //設置按鈕點擊監聽
        btn_search.setOnClickListener(this);
        btn_voice.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        String url="  http://fy.webxml.com.cn/webservices/EnglishChinese.asmx";
        String soapAction="http://WebXml.com.cn/Translator ";
        String addressNameSpace="http://WebXml.com.cn/";
        switch (v.getId()){
            case R.id.btn_search:
                //按鈕設置為不可用
                btn_search.setEnabled(false);
                //獲取輸入框查詢word
                String word=et_text.getText().toString().trim();
                String method="Translator ";
                //新建WebServiceUtil對象，調用webService
                WebServiceUtil webServiceUtil=new WebServiceUtil(url,soapAction,addressNameSpace,method,wordsInfo,handler);
                try {
                    webServiceUtil.translator(word);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_spellVoice:
                //設置按鈕背景
               btn_voice.setBackgroundResource(R.drawable.voice1);
               //獲取mp3名稱
                String mp3=wordsInfo.getVoice();
                if(mp3!=null&&mp3!=""){
                    //新建MediaPlayer對象
                    MediaPlayer mediaPlayer=new MediaPlayer();
                    //設置MediaPlayer播放結束監聽器
                    mediaPlayer.setOnCompletionListener(this);
                    try {
                        //設置網絡資源路徑
                        mediaPlayer.setDataSource("http://fy.webxml.com.cn/sound/"+mp3);
                        mediaPlayer.prepare();
                        mediaPlayer.start();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(mContext,"沒有音頻文件",Toast.LENGTH_LONG).show();
                }
                break;

        }
    }

    //音頻播放完成
    @Override
    public void onCompletion(MediaPlayer mp) {
        //設置音頻播放按鈕背景
        btn_voice.setBackgroundResource(R.drawable.voice2);
    }
}
