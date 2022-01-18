package com.mohammadmawed.wetterdaten.data


import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*

class WeatherDataFirebaseRepo {

    lateinit var dataMutableLiveData: MutableLiveData<ArrayList<WeatherDataModelClass>>

    private val databaseReference: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().reference.child("data")
    }

    fun loadData(liveData: MutableLiveData<ArrayList<WeatherDataModelClass>>, callback: OnDataReceiveCallback) {
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
                TODO("Not yet implemented")
            }
        })
    }
}