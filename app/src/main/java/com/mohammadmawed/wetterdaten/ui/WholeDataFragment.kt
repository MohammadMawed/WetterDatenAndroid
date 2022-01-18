package com.mohammadmawed.wetterdaten.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.mohammadmawed.wetterdaten.R
import com.mohammadmawed.wetterdaten.data.OnDataReceiveCallback


class WholeDataFragment : Fragment() {

    private lateinit var temperatureTextView: TextView
    private lateinit var currentHumidityTextView: TextView
    private lateinit var humidityTextView2: TextView
    private lateinit var currentTimeTextView: TextView
    private lateinit var circularProgressBar: CircularProgressBar
    private lateinit var circularProgressBar2: CircularProgressBar
    private lateinit var uploadButton: Button
    private lateinit var viewModel: WeatherDataViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_whole_data, container, false)

        var humidity = 14
        var temperature = 25
        val time = "16:30"

        temperatureTextView = view.findViewById(R.id.temperatureTextView)
        currentHumidityTextView = view.findViewById(R.id.currentHumidityTextView)
        humidityTextView2 = view.findViewById(R.id.humidityTextView2)
        currentTimeTextView = view.findViewById(R.id.currentTimeTextView)
        circularProgressBar2 = view.findViewById(R.id.circularProgressBar2)
        circularProgressBar = view.findViewById(R.id.circularProgressBar)
        uploadButton = view.findViewById(R.id.button)

        viewModel = ViewModelProvider(requireActivity()).get(WeatherDataViewModel::class.java)

        viewModel.loadWeatherData(object : OnDataReceiveCallback {
            //Receiving realtime data and updating the UI elements
            override fun onDataReceived(time: String?, temperature: String?, humidity: String?) {

                viewModel.weatherDataLiveData.observe(viewLifecycleOwner, { arrayList ->
                    Log.d("weather", arrayList.toString())
                    temperatureTextView.text = "$temperature°C"

                    currentHumidityTextView.text = "$humidity%"
                    humidityTextView2.text = "$humidity%"
                    circularProgressBar2.progress = humidity!!.toFloat()

                    currentTimeTextView.text = time

                })
            }
        } )

        uploadButton.setOnClickListener {
            humidity += 10
            temperature += 10
          viewModel.uploadFile(humidity, temperature, time)
        }
        return view
    }

}