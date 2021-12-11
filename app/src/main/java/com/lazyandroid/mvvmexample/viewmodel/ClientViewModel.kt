package com.lazyandroid.mvvmexample.viewmodel

import android.content.Context
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lazyandroid.mvvmexample.model.ClientInfo
import com.lazyandroid.mvvmexample.repository.PostRepository
import org.json.JSONArray

class ClientViewModel : ViewModel() {

//    var clientInfo : ClientInfo?=null

    private var thread: HandlerThread = HandlerThread("com.lazyandroid.markthread")
    private var handler: Handler
    private var postRepository = PostRepository()






    // constructor
    init {
        if (!thread.isAlive) {
            thread.start()
        }
        handler = Handler(thread.looper)
    }


    // VieModel use
    private val clientInfoLiveData = MutableLiveData<MutableList<ClientInfo>>()

    // for View use
    val clientsData: LiveData<MutableList<ClientInfo>> = clientInfoLiveData


    fun addClientInfo(clientInfo: ClientInfo) {
        handler.post {
            // main thread handler
            Handler(Looper.getMainLooper()).post {
                if (clientInfoLiveData.value == null) {
                    clientInfoLiveData.value = mutableListOf(clientInfo)
                } else {
                    clientInfoLiveData.value!!.add(clientInfo)
                }
            }
        }

    }



    private var postsLiveData = MutableLiveData<JSONArray>()
    // casting , source of data for view
    val postsData :LiveData<JSONArray> = postsLiveData

    fun getPosts(context: Context,onError: (Exception) -> Unit){
        // call api
        postRepository.getPosts(context,{
              // api results
              postsLiveData.value = it
        }, {
            onError.invoke(it)
        })
    }


}