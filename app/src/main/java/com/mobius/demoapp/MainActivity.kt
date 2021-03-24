package com.mobius.demoapp

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobius.demoapp.adapter.RecyclerAdapter
import com.mobius.demoapp.network.ConnectionType
import com.mobius.demoapp.network.NetworkMonitorUtil
import com.mobius.demoapp.response.APIResponse
import com.mobius.demoapp.retrofit.ApiService
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val TAG: String = "MainActivity"
    private val networkMonitor = NetworkMonitorUtil(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       /* if (checkNetwork()) {
            getCurrentData()
        }
        else if (!checkNetwork()) {
            Toast.makeText(this, "Network connection is not available", Toast.LENGTH_SHORT).show()
        }*/
        networkMonitor.result = { isAvailable, type ->
            runOnUiThread {
                when (isAvailable) {
                    true -> {
                        when (type) {
                            ConnectionType.Wifi -> {
                                Log.i("NETWORK_MONITOR_STATUS", "Wifi Connection")
                                getCurrentData()
                            }
                            ConnectionType.Cellular -> {
                                Log.i("NETWORK_MONITOR_STATUS", "Cellular Connection")
                                getCurrentData()
                            }
                            else -> {
                                getCurrentData()
                            }
                        }
                    }
                    false -> {
                        Log.i("NETWORK_MONITOR_STATUS", "No Connection")
                        Toast.makeText(this, "Network connection is not available", Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }

    }

    internal fun getCurrentData() {
        val retrofit = Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val service = retrofit.create(ApiService::class.java)
        val call = service.getCurrentWeatherData()
        call.enqueue(object : Callback<List<APIResponse>> {
            override fun onResponse(call: Call<List<APIResponse>>, response: Response<List<APIResponse>>) {
                if (response.code() == 200) {


                    // val WeatherResponseLatest = response.body()!!

                    Log.d("responseis",response.toString())

                    val result = response.body()
                    Log.d("responseisss",result.toString())

                    showResult(result!!)


                }
            }

            override fun onFailure(call: Call<List<APIResponse>>, t: Throwable) {
            }
        })
    }
    companion object {

        var BaseUrl = "https://run.mocky.io/"

    }

    private fun showResult(apiResponse: List<APIResponse>){
        for (response in apiResponse){
            printLog("title : ${response.id}")

            recycler_view.apply {
                layoutManager = LinearLayoutManager(applicationContext)
                adapter = RecyclerAdapter(apiResponse)
            }
        }
    }
    private fun printLog(message: String){
        Log.d(TAG, message)
    }

    private fun checkNetwork(): Boolean {
        val connManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        return true
    }

    override fun onStop() {
        super.onStop()
        networkMonitor.unregister()
    }

    override fun onResume() {
        super.onResume()
        networkMonitor.register()
    }

}