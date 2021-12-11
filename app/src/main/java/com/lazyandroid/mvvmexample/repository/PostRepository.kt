package com.lazyandroid.mvvmexample.repository

import android.content.Context
import android.util.Log
import androidx.appcompat.widget.AppCompatButton
import com.android.volley.Request
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray


/**
 *  get data
 *
 * */
class PostRepository {



    fun getPosts(context: Context, onRetrieved: (JSONArray) -> Unit, onError: (Exception) -> Unit) {
        Log.d("test", "Api is called")
        val url = "https://jsonplaceholder.typicode.com/posts"
        val request = JsonArrayRequest(
            Request.Method.GET,
            url, null,
            {
                onRetrieved.invoke(it)

            },
            {
                onError.invoke(it)
            })

        Volley.newRequestQueue(context)
            .also {
                it.add(request)
            }

    }


}