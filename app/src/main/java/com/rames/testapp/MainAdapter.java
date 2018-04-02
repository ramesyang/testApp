package com.rames.testapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by yangju1 on 17/4/22.
 * yangju1@staff.weibo.com
 */
public class MainAdapter extends RecyclerView.Adapter<MainAdapter.UserViewHolder> {
    @Override
    public MainAdapter.UserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_adapter_item, parent, false);
//        View v = View.inflate(parent.getContext(), R.layout.main_adapter_item, false);
        return new UserViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MainAdapter.UserViewHolder holder, int position) {
        holder.headerIV.setImageResource(R.mipmap.ic_launcher);
        holder.text.setText("item"+(position+1));

        if(position == getItemCount()-1){
            holder.headerIV.setVisibility(View.GONE);
        }else{
            holder.headerIV.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        ImageView headerIV;
        TextView text;
        public UserViewHolder(final View itemView) {
            super(itemView);
            headerIV = (ImageView) itemView.findViewById(R.id.image);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}
