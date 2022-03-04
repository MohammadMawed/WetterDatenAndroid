package com.mohammadmawed.wetterdaten.data

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import java.text.SimpleDateFormat
import java.util.*
import java.util.Map
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
        hourLiveData: MutableLiveData<MutableMap<Int, AverageDataModelClass>>,
        errorFetchingDataLiveData: MutableLiveData<String>
    ) {

        databaseReference1.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val arx: ArrayList<Int> = ArrayList()
                var aaa: MutableMap<Int, AverageDataModelClass> = mutableMapOf()
                var i: Int = 0
                for (data in snapshot.children) {

                    i += 1
                    val model = data.getValue(AverageDataModelClass::class.java)
                    aaa.put(i, model as AverageDataModelClass)
                    Log.d("s", aaa.toString())
                    //val arrayListMainUI: ArrayList<AverageDataModelClass> = ArrayList()
                    Log.d("Data Childern", data.toString())
                    Log.d("Data Childern2", model.toString())

                    //Adding the data to arraylist as whole to observe it from the fragment
                    var sss: String = model?.humidity.toString()

                    //aaa[i] = model.toString()

                    Log.d("aaa", aaa.toString())
                    val arrrr: ArrayList<String> = ArrayList()
                    for(ele in sss){
                        arrrr.add(ele.toString())
                        Log.d("DataChildern4", arrrr.toString())
                    }
                    arrrr.add(model?.humidity.toString())
                    //Log.d("DataChildern3", arrrr.toString())
                    //Log.d("DataChildern3", arrrr.size.toString())


                    //arrayListMainUI.add(model as AverageDataModelClass)

                    hourLiveData.postValue(aaa)

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