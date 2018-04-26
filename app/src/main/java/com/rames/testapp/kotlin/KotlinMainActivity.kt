package com.rames.testapp.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.app.Activity
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

import com.rames.testapp.R
import com.rames.testapp.util.Tips
import kotlinx.android.synthetic.main.activity_kotlin_main.*

class KotlinMainActivity : AppCompatActivity(), View.OnClickListener {
    var mytv:String = ""
//    var recycleView:RecyclerView? = null
    var adapter:TestAdapter? = null

    companion object {
        var callback: TestCallback? = null
        fun show(activity: Activity, back: TestCallback?) {
            callback = back
            val intent = Intent(activity, KotlinMainActivity::class.java)
            activity.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_main)
        btn_kotlin_main.setOnClickListener(this)
        var recycleView = findViewById(R.id.recycle_view) as RecyclerView
        recycleView.layoutManager = LinearLayoutManager(this)
        adapter = TestAdapter(arrayListOf("a", "b", "c", "d"), object: ItemClickListener{
            override fun onItemClick(position: Int) {
                Tips.showTips(this@KotlinMainActivity, "第："+(position+1)+"个")
            }
        })

//        adapter = TestAdapter(arrayListOf("a", "b", "c", "d"), ItemClickListener:() -> checkEvent())
        recycleView.adapter = adapter

//        btn_kotlin_main.setOnClickListener{
//            clickEvent()
//        }
    }

    override fun onClick(v: View?) {
        if (v == btn_kotlin_main){
            clickEvent()

            callback?.callback()
        }
    }

    fun clickEvent(): Int{
        mytv = tv_kotlin_main.text.toString()
        Tips.showTips(this, mytv)
        return mytv.length
    }
}
