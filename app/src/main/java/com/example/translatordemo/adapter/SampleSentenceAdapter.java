package com.example.translatordemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.translatordemo.R;
import com.example.translatordemo.entity.SampleSentence;
import java.util.List;

public class SampleSentenceAdapter extends BaseAdapter {
    private List<SampleSentence> sampleSentences;
    private Context mContext;

    public SampleSentenceAdapter(List<SampleSentence> sampleSentences, Context mContext) {
        this.sampleSentences = sampleSentences;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return sampleSentences.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            convertView= LayoutInflater.from(mContext).inflate(R.layout.sample_sentence,parent,false);
            viewHolder=new ViewHolder();
            viewHolder.tv_id=convertView.findViewById(R.id.tv_id);
            viewHolder.tv_origin=convertView.findViewById(R.id.tv_origin);
            viewHolder.tv_trans=convertView.findViewById(R.id.tv_trans);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_id.setText(sampleSentences.get(position).getId());
        viewHolder.tv_origin.setText(sampleSentences.get(position).getOrginSentence());
        viewHolder.tv_trans.setText(sampleSentences.get(position).getTransSentence());
        return convertView;
    }

    static  class  ViewHolder{
        TextView tv_id;
        TextView tv_origin;
        TextView tv_trans;
    }
}
