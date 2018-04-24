package com.rames.testapp.kotlin

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.rames.testapp.R
import kotlinx.android.synthetic.main.item_kotlin_main.view.*

/**
 *
 * @Author yangju1/yangju1@staff.weibo.com
 * @Date 2018/4/24 下午5:12
 */
class TestAdapter(val items:ArrayList<String>): RecyclerView.Adapter<TestAdapter.ViewHolder>(){
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_kotlin_main, parent, false)
        return ViewHolder(view)
    }

    class ViewHolder(val view: View): RecyclerView.ViewHolder(view){
        fun bind(content: String){
            view.content_tv.text = content
        }
    }
}