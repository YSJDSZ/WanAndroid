package com.yssz.xiangxuedemo.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.yssz.xiangxuedemo.R
import com.yssz.xiangxuedemo.adapter.NewsAdapter
import com.yssz.xiangxuedemo.bin.NewsItem
import com.yssz.xiangxuedemo.bin.NewsItemDate
import com.yssz.xiangxuedemo.service.NewsService
import com.yssz.xiangxuedemo.service.ServiceCreator
import kotlinx.android.synthetic.main.activity_drawer_layout_activiy.*
import kotlinx.android.synthetic.main.activity_drawer_layout_activiy.recyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsListActivity : AppCompatActivity() {
    private lateinit var newsAdapter: NewsAdapter //延迟初始化 lateinit
    private val newsList = ArrayList<NewsItemDate>()
    companion object {
        fun actionStart(context: Context) {
            val intent = Intent(context, NewsListActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_zi_dong,menu)
        return true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer_layout_activiy)
        setSupportActionBar(toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
        }
        navView.setCheckedItem(R.id.navMail)
        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            true
        }


        fab.setOnClickListener {
            Snackbar.make(it,"点点吗？",Snackbar.LENGTH_LONG)
                .setAction("undo"){
                    Toast.makeText(this,"nono",Toast.LENGTH_SHORT).show()
                } .show()
        }

        recyclerView.layoutManager=LinearLayoutManager(this)
        if(!::newsAdapter.isInitialized){//判断Nadapter变量是否已经初始化
            newsAdapter = NewsAdapter(newsList)
        }
        recyclerView.adapter= newsAdapter
        swipeRefresh.setOnRefreshListener {
            newsList.clear()
            getData(1)
        }
        getData(2);

    }

    /**
     * 获取列表数据
     */
    fun getData(page:Int){
        val newsService = ServiceCreator.create<NewsService>()
        newsService.getNewsItemData(page).enqueue(object : Callback<NewsItem> {
            override fun onResponse(call: Call<NewsItem>, response: Response<NewsItem>) {
                val body = response.body()
                val datas = body?.data?.datas
                if (datas!=null){
                    newsList.addAll(datas)
                    newsAdapter.notifyDataSetChanged()
                }
            }
            override fun onFailure(call: Call<NewsItem>, t: Throwable) {
                Toast.makeText(applicationContext,"noData",Toast.LENGTH_SHORT).show()
            }

        })
        swipeRefresh.isRefreshing = false
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.backup -> drawerLayout.openDrawer(GravityCompat.START)
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }
        return true
    }
}

