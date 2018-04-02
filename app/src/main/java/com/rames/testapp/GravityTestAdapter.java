package com.rames.testapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yangju1 on 17/6/21.
 * yangju1@staff.weibo.com
 */
public class GravityTestAdapter extends RecyclerView.Adapter<GravityTestAdapter.ViewHolder> {
    private ArrayList<String> list;

    public void setData(ArrayList<String> list){
        this.list = list;
        this.notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.text);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = View.inflate(parent.getContext(), R.layout.gravity_test_item, null);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return null==list ? 0 : list.size();
    }
}
