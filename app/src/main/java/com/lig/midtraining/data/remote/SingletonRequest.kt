package com.lig.midtraining.data.remote

import android.content.Context
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import dagger.Module
import dagger.Provides
import org.json.JSONObject
import javax.inject.Inject

//@Module
class SingletonRequest(
                  val result: (JSONObject) -> Unit,
                  val error: (String) -> Unit) {

//    fun POST(vararg params: Pair<String, Any>) {
//        // HashMap to pass arguments to Volley
//        val hashMap = HashMap<String, String>()
//        params.forEach {
//            // Convert all Any type to String and add to HashMap
//            hashMap[it.first] = it.second.toString()
//        }
//        // Make the Http Request
//        makeRequest(Request.Method.POST, hashMap)
//    }

//    @Provides
    fun makeRequest(url: String) {
        // Creating a StringRequest
        val req = object : StringRequest(Method.GET,
            "https://api.themoviedb.org/3/discover/$url", { res ->

            // Creating JSON object from the response string
            // and passing it to result: (JSONObject) -> Unit function
            result(JSONObject(res.toString().trim()))
        }, { volleyError ->

            // Getting error message and passing it
            // to val error: (String) -> Unit function
            error(volleyError.message!!)
        }) {
        }

        // Adding request to the queue
        volley.add(req)
    }

    // For using Volley RequestQueue as a singleton
    // call WolfRequest.init(applicationContext) in
    // app's Application class
    companion object {
        var context: Context? = null
        val volley: RequestQueue by lazy {
            Volley.newRequestQueue(
                context
                ?: throw NullPointerException(" Initialize Request in application class"))
        }

        fun init(context: Context) {
            Companion.context = context
        }
    }
}