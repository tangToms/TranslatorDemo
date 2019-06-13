package com.example.translatordemo.entity;

import java.util.List;

/***
 *
 * <Dictionary>
 *     <Trans diffgr:id="Trans1" msdata:rowOrder="0">
 *         <WordKey>hello</WordKey>
 *         <Pron>'heləu, he'ləu</Pron>
 *         <Info/>
 *         <Translation>int.（见面打招呼或打电话用语）喂,哈罗</Translation>
 *         <Mp3>1059.mp3</Mp3>
 *         </Trans>
 *         <Sentence diffgr:id="Sentence1" msdata:rowOrder="0">
 *             <Orig>She actually condescended to say hello to me in the street today.</Orig>
 *             <Trans>她今天在街上竟能屈尊跟我打招呼.</Trans>
 *             </Sentence>
 *             <Sentence diffgr:id="Sentence2" msdata:rowOrder="1">
 *                 <Orig>I said hello to her, but she ignored me completely!</Orig>
 *                 <Trans>我向她打招呼, 可她根本不理我!</Trans>
 *                 </Sentence>
 *                 <Sentence diffgr:id="Sentence3" msdata:rowOrder="2">
 *                     <Orig>Hello there, what a coincidence!</Orig>
 *                     <Trans>你好，真巧啊!</Trans>
 *                   </Sentence>
 * </Dictionary>
 *
 *
 */
public class WordsInfo {
    //原文本
    private String originText;
    //翻譯文本
    private String transText;
    //拼讀
    private String pron;
    //mp3
    private String voice;
    //例句
    private List<SampleSentence> sampleSentences;

    public WordsInfo() {
    }

    public WordsInfo(String originText, String transText, String pron, String voice, List<SampleSentence> sampleSentences) {
        this.originText = originText;
        this.transText = transText;
        this.pron = pron;
        this.voice = voice;
        this.sampleSentences = sampleSentences;
    }

    public String getOriginText() {
        return originText;
    }

    public void setOriginText(String originText) {
        this.originText = originText;
    }

    public String getTransText() {
        return transText;
    }

    public void setTransText(String transText) {
        this.transText = transText;
    }

    public String getPron() {
        return pron;
    }

    public void setPron(String pron) {
        this.pron = pron;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public List<SampleSentence> getSampleSentences() {
        return sampleSentences;
    }

    public void setSampleSentences(List<SampleSentence> sampleSentences) {
        this.sampleSentences = sampleSentences;
    }
}
