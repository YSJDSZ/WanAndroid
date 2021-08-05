package com.yssz.xiangxuedemo.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.yssz.xiangxuedemo.R
import com.yssz.xiangxuedemo.adapter.MsgAdapter
import com.yssz.xiangxuedemo.bin.Msg
import com.yssz.xiangxuedemo.workmanager.DingDingActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var msgAdapter :MsgAdapter //延迟初始化 lateinit
    private val msgList = ArrayList<Msg>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initMsg()
        val linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager=linearLayoutManager
        if(!::msgAdapter.isInitialized){//判断adapter变量是否已经初始化
            msgAdapter = MsgAdapter(msgList)
        }
        recyclerView.adapter=MsgAdapter(msgList)
        send.setOnClickListener(this)
        val intent = Intent(this, DingDingActivity::class.java)
        startActivity(intent)
    }
    private fun initMsg(){
        msgList.add(Msg("aa！",Msg.TYPE_RECEIVED));
        msgList.add(Msg("bb！",Msg.TYPE_SENT));
        msgList.add(Msg("收到",Msg.TYPE_RECEIVED));
    }

    override fun onClick(v: View?) {
        when(v){
            send->{
//                val text = inputText.text.toString().trim()
//                msgList.add(Msg(text,Msg.TYPE_SENT));
//                msgAdapter.notifyItemInserted(msgList.size - 1)
//                recyclerView.scrollToPosition(msgList.size - 1) // 将RecyclerView
//                inputText.setText("");
                NewsListActivity.actionStart(this)

            }
        }
    }
}