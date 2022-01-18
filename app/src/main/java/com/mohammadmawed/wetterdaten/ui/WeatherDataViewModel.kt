package com.mohammadmawed.wetterdaten.ui

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.mohammadmawed.wetterdaten.data.OnDataReceiveCallback
import com.mohammadmawed.wetterdaten.data.WeatherDataModelClass
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class WeatherDataViewModel(application: Application) : AndroidViewModel(application) {

    private var _weatherDataMutableLiveData: MutableLiveData<ArrayList<WeatherDataModelClass>> =
        MutableLiveData()

    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().reference.child("data")

    var arrayListMainUI: ArrayList<WeatherDataModelClass> = ArrayList()



    val weatherDataLiveData: LiveData<ArrayList<WeatherDataModelClass>>
        get() = _weatherDataMutableLiveData

    fun loadWeatherData(callback: OnDataReceiveCallback) {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children) {

                    val model = data.getValue(WeatherDataModelClass::class.java)

                    val humidity: String = model?.humidity.toString()
                    val temperature: String = model?.temperature.toString()
                    val time: String = model?.time.toString()


                    arrayListMainUI.add(model as WeatherDataModelClass)

                    //Adding callback method to enable the realtime UI update
                    callback.onDataReceived(time, temperature, humidity)

                    _weatherDataMutableLiveData.postValue(arrayListMainUI)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }


    fun uploadFile(humidity: Int, temperature: Int, time: String)  {
        val calendar: Calendar = Calendar.getInstance()
        val timeInMillis: Long = calendar.timeInMillis
        val hashMap = HashMap<String, String>()

        hashMap["time"] = time
        hashMap["temperature"] = temperature.toString()
        hashMap["humidity"] = humidity.toString()
        databaseReference.child("data/").setValue(hashMap)
            .addOnSuccessListener {

            }

    }

}

