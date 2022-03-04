package com.mohammadmawed.wetterdaten.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.anychart.charts.Cartesian
import com.mohammadmawed.wetterdaten.data.AverageDataModelClass
import com.mohammadmawed.wetterdaten.data.OnDataReceiveCallback
import com.mohammadmawed.wetterdaten.data.WeatherDataFirebaseRepo
import com.mohammadmawed.wetterdaten.data.WeatherDataModelClass
import kotlin.collections.ArrayList

class WeatherDataViewModel(application: Application) : AndroidViewModel(application) {

    private val repo = WeatherDataFirebaseRepo()

    private var _weatherDataMutableLiveData: MutableLiveData<ArrayList<WeatherDataModelClass>> =
        MutableLiveData()
    private var _hourDataMutableLiveData:  MutableLiveData<MutableMap<Int, AverageDataModelClass>> =
        MutableLiveData()
    private var _errorFetchingDataMutableLiveData: MutableLiveData<String> = MutableLiveData()
    private var _errorFetchingHourDataMutableLiveData: MutableLiveData<String> = MutableLiveData()

    val weatherDataLiveData: LiveData<ArrayList<WeatherDataModelClass>> =
        _weatherDataMutableLiveData
    val hourDataLiveData:  LiveData<MutableMap<Int, AverageDataModelClass>> =
        _hourDataMutableLiveData

    val errorFetchingDataLiveData: MutableLiveData<String> = _errorFetchingDataMutableLiveData
    val errorFetchingHourDataLiveData: MutableLiveData<String> = _errorFetchingHourDataMutableLiveData
    

    fun loadWeatherData(callback: OnDataReceiveCallback) {
        repo.loadData(_weatherDataMutableLiveData, callback, _errorFetchingDataMutableLiveData)
    }
    fun loadHourData(){
        repo.loadHourData(_hourDataMutableLiveData, _errorFetchingHourDataMutableLiveData)
    }

    fun uploadFile(humidity: Int, temperature: Int) {
        repo.uploadFile(humidity, temperature)
    }

}

