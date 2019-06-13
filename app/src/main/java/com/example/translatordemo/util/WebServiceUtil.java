package com.example.translatordemo.util;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.translatordemo.entity.SampleSentence;
import com.example.translatordemo.entity.WordsInfo;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;


public class WebServiceUtil {
    //服務url地址
    private String url;
    //地址命名空間
    private String addressNameSpace;
    //SoapAction
    private String soapAction;
    //方法
    private String method;

    //獲取返回信息
    private WordsInfo wordsInfo;

    //例句
    private SampleSentence sampleSentence;

    //消息handler
    private Handler handler;

    public WebServiceUtil(String url, String soapAction, String addressNameSpace, String method, WordsInfo wordsInfo, Handler handler) {
        this.url=url;
        this.addressNameSpace = addressNameSpace;
        this.method = method;
        this.soapAction=soapAction;
        this.wordsInfo=wordsInfo;
        this.handler=handler;
    }

    //翻譯
    public void translator(String word) throws IOException, XmlPullParserException {

        //清空上一次查詢結果
        if(wordsInfo.getSampleSentences()!=null){
            wordsInfo.getSampleSentences().clear();
        };
        wordsInfo.setOriginText("");
        wordsInfo.setTransText("");
        wordsInfo.setPron("");
        wordsInfo.setVoice("");

        final SoapObject soapObject=new SoapObject(addressNameSpace,method);
        soapObject.addProperty("wordKey",word);
        final SoapSerializationEnvelope envelope=new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.bodyOut=soapObject;
        envelope.dotNet=true;
        envelope.setOutputSoapObject(soapObject);
        new Thread(){
            @Override
            public void run() {
                try {
                    HttpTransportSE httpTransportSE=new HttpTransportSE(url);
                    httpTransportSE.call(soapAction,envelope);

                    //獲取返回數據，進行解析
                    //  SoapObject object= (SoapObject) envelope.bodyIn;
                    SoapObject object=(SoapObject) envelope.getResponse();
                    SoapObject object1= (SoapObject) object.getProperty(1);
                    SoapObject object2= (SoapObject) object1.getProperty(0);
                    int length=object2.getPropertyCount();
                    if(length==4){
                        SoapObject object3= (SoapObject) object2.getProperty(0);
                        wordsInfo.setOriginText(object3.getProperty("WordKey").toString());
                        wordsInfo.setTransText(object3.getProperty("Translation").toString());
                        wordsInfo.setPron(object3.getProperty("Pron").toString());
                        wordsInfo.setVoice(object3.getProperty("Mp3").toString());

                        object3= (SoapObject) object2.getProperty(1);
                        sampleSentence=new SampleSentence();
                        sampleSentence.setId("sentence1");
                        sampleSentence.setOrginSentence(object3.getProperty("Orig").toString());
                        sampleSentence.setTransSentence(object3.getProperty("Trans").toString());
                        wordsInfo.getSampleSentences().add(sampleSentence);

                        object3= (SoapObject) object2.getProperty(2);
                        sampleSentence=new SampleSentence();
                        sampleSentence.setId("sentence2");
                        sampleSentence.setOrginSentence(object3.getProperty("Orig").toString());
                        sampleSentence.setTransSentence(object3.getProperty("Trans").toString());
                        wordsInfo.getSampleSentences().add(sampleSentence);

                        object3= (SoapObject) object2.getProperty(3);
                        sampleSentence=new SampleSentence();
                        sampleSentence.setId("sentence3");
                        sampleSentence.setOrginSentence(object3.getProperty("Orig").toString());
                        sampleSentence.setTransSentence(object3.getProperty("Trans").toString());
                        wordsInfo.getSampleSentences().add(sampleSentence);

                    }else {
                        SoapObject object3= (SoapObject) object2.getProperty(0);
                        wordsInfo.setOriginText(object3.getProperty("WordKey").toString());
                        wordsInfo.setTransText(object3.getProperty("Translation").toString());
                    }
                    Message message=new Message();
                    message.what=0x001;
                    handler.sendMessage(message);

                } catch (IOException e) {
                    Message message=new Message();
                    message.what=0x002;
                    Bundle bundle=new Bundle();
                    bundle.putString("error",e.getMessage());
                    message.setData(bundle);
                    handler.sendMessage(message);
                    e.printStackTrace();
                } catch (XmlPullParserException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
