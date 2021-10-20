package com.example.retrofit_api_caching

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit_api_caching.adapter.MyAdapter
import com.example.retrofit_api_caching.model.ModelClass
import com.example.retrofit_api_caching.model.Post
import com.example.retrofit_api_caching.retrofit.RetrofitInstance

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    var dataList = ArrayList<Post>()


    lateinit var myAdapter: MyAdapter
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerView = findViewById(R.id.rv_list)
        recyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this@MainActivity)
        recyclerView.layoutManager = linearLayoutManager


        myAdapter = MyAdapter(baseContext,dataList)
        recyclerView.adapter = myAdapter


        getMyData()
    }

    private fun getMyData() {

        val retrofitData = RetrofitInstance.retrofitBuilder.getData()
        //Ctrl+shift+space to get the callbacks
        retrofitData.enqueue(object : Callback<ModelClass?> {
            override fun onResponse(call: Call<ModelClass?>, response: Response<ModelClass?>) {
                val responsebody= response.body()!!
                val aa = responsebody?.posts
//                myAdapter = MyAdapter(baseContext,aa)
//                recyclerView.adapter = myAdapter
                dataList.addAll(aa)
                myAdapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ModelClass?>, t: Throwable) {
                Log.d("MainActivity","onFailure:" + t.message)
            }
        })

    }
}