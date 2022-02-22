package com.mohammadmawed.wetterdaten.data

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashMap

class WeatherDataFirebaseRepo {

    private val databaseReference: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().reference.child("data/data")
    }
    private val databaseReference1: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().reference.child("data/hourlyData")
    }

    fun loadData(
        liveData: MutableLiveData<ArrayList<WeatherDataModelClass>>,
        callback: OnDataReceiveCallback,
        errorFetchingDataLiveData: MutableLiveData<String>
    ) {

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children) {

                    val model = data.getValue(WeatherDataModelClass::class.java)

                    val humidity: String = model?.humidity.toString()
                    val temperature: String = model?.temperature.toString()
                    val time: String = model?.time.toString()


                    //Adding callback method to enable the realtime UI update
                    callback.onDataReceived(time, temperature, humidity)
                    val arrayListMainUI: ArrayList<WeatherDataModelClass> = ArrayList()

                    //Adding the data to arraylist as whole to observe it from the fragment
                    arrayListMainUI.add(model as WeatherDataModelClass)
                    liveData.postValue(arrayListMainUI)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                errorFetchingDataLiveData.postValue("Error fetching data, Details: ${error.message}")
            }
        })
    }


    fun loadHourData(
        hourLiveData: MutableLiveData<ArrayList<AverageDataModelClass>>,
        errorFetchingDataLiveData: MutableLiveData<String>
    ) {

        databaseReference1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (data in snapshot.children) {

                    val model = data.getValue(AverageDataModelClass::class.java)

                    val arrayListMainUI: ArrayList<AverageDataModelClass> = ArrayList()

                    //Adding the data to arraylist as whole to observe it from the fragment
                    arrayListMainUI.add(model as AverageDataModelClass)
                    hourLiveData.postValue(arrayListMainUI)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                errorFetchingDataLiveData.postValue("Error fetching data, Details: ${error.message}")
            }
        })
    }


    fun uploadFile(humidity: Int, temperature: Int) {
        val calendar: Calendar = Calendar.getInstance()
        @SuppressLint("SimpleDateFormat") val df = SimpleDateFormat("HH:mm")
        val time: String = df.format(calendar.time)
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