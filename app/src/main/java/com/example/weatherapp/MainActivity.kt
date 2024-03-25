package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

//ae1cde4ac69fd588ed126e0e78efe622
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchweatherdata()
    }

    private fun fetchweatherdata() {
         val retrofit=Retrofit.Builder()
             .addConverterFactory(GsonConverterFactory.create())
             .baseUrl("https://api.openweathermap.org/data/2.5/")
             .build().create(apiinterface::class.java)

        val response=retrofit.getWeatherData("jalandhar" , "ae1cde4ac69fd588ed126e0e78efe622" , "metric")
        response.enqueue(object : Callback<Weatherapp>{
            override fun onResponse(call: Call<Weatherapp>, response: Response<Weatherapp>) {
                val responeBody =response.body()
                if(response.isSuccessful && responeBody!=null){
                    val temprature=responeBody.main.temp.toString()
                    Log.d("TAG", "onResponse: $temprature")
                }
            }

            override fun onFailure(call: Call<Weatherapp>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
}