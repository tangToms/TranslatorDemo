package com.example.translatordemo.entity;

public class SampleSentence {
    private String id;
    private String orginSentence;
    private String transSentence;

    public SampleSentence() {
    }

    public SampleSentence(String id, String orginSentence, String transSentence) {
        this.id = id;
        this.orginSentence = orginSentence;
        this.transSentence = transSentence;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrginSentence() {
        return orginSentence;
    }

    public void setOrginSentence(String orginSentence) {
        this.orginSentence = orginSentence;
    }

    public String getTransSentence() {
        return transSentence;
    }

    public void setTransSentence(String transSentence) {
        this.transSentence = transSentence;
    }
}
