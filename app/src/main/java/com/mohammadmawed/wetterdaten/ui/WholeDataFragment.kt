package com.mohammadmawed.wetterdaten.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.snackbar.Snackbar
import com.mikhaellopez.circularprogressbar.CircularProgressBar
import com.mohammadmawed.wetterdaten.R
import com.mohammadmawed.wetterdaten.data.ConnectionLiveData
import com.mohammadmawed.wetterdaten.data.OnDataReceiveCallback


class WholeDataFragment : Fragment() {

    private lateinit var temperatureTextView: TextView
    private lateinit var currentHumidityTextView: TextView
    private lateinit var humidityTextView2: TextView
    private lateinit var currentTimeTextView: TextView
    private lateinit var circularProgressBar: CircularProgressBar
    private lateinit var circularProgressBar2: CircularProgressBar
    private lateinit var loadingProgressBar: CircularProgressIndicator
    private lateinit var weeklyDataButton: Button
    private lateinit var connectionImageView: ImageView
    private lateinit var mainLayout: ConstraintLayout

    private lateinit var viewModel: WeatherDataViewModel
    private lateinit var connectionLiveData: ConnectionLiveData

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_whole_data, container, false)

        var humidity = 14
        var temperature = 15

        temperatureTextView = view.findViewById(R.id.temperatureTextView)
        currentHumidityTextView = view.findViewById(R.id.currentHumidityTextView)
        humidityTextView2 = view.findViewById(R.id.humidityTextView2)
        currentTimeTextView = view.findViewById(R.id.currentTimeTextView)
        circularProgressBar2 = view.findViewById(R.id.circularProgressBar2)
        circularProgressBar = view.findViewById(R.id.circularProgressBar)
        loadingProgressBar = view.findViewById(R.id.loadingProgressBar)
        weeklyDataButton = view.findViewById(R.id.button)
        connectionImageView = view.findViewById(R.id.connectionImageView)
        mainLayout = view.findViewById(R.id.mainLayout)

        viewModel = ViewModelProvider(requireActivity())[WeatherDataViewModel::class.java]
        connectionLiveData = ConnectionLiveData(requireContext())

        viewModel.loadWeatherData(object : OnDataReceiveCallback {

            //Receiving realtime data and updating the UI elements
            override fun onDataReceived(time: String?, temperature: String?, humidity: String?) {
                loadingProgressBar.visibility = View.VISIBLE
                viewModel.weatherDataLiveData.observe(viewLifecycleOwner) { arrayList ->

                    Log.d("weather", arrayList.toString())
                    temperatureTextView.text = "$temperature°C"
                    circularProgressBar.progress = temperature!!.toFloat()

                    currentHumidityTextView.text = "$humidity%"
                    humidityTextView2.text = "$humidity%"
                    circularProgressBar2.progress = humidity!!.toFloat()

                    currentTimeTextView.text = time
                    loadingProgressBar.visibility = View.GONE
                }

                //Catching errors
                viewModel.errorFetchingDataLiveData.observe(viewLifecycleOwner) { error ->
                    Snackbar.make(mainLayout, error, Snackbar.LENGTH_LONG).show()
                }
            }
        })

        connectionLiveData.observe(viewLifecycleOwner) { isNetworkAvailable ->
            if (isNetworkAvailable) {
                connectionImageView.setImageResource(R.drawable.ic_outline_network_wifi_2_bar_24)
            } else if (!isNetworkAvailable) {
                connectionImageView.setImageResource(R.drawable.ic_outline_signal_wifi_bad_24)
                Snackbar.make(mainLayout, "You are offline", Snackbar.LENGTH_LONG).show()
            }
        }

        weeklyDataButton.setOnClickListener {
            findNavController().navigate(R.id.action_wholeDataFragment_to_weeklyDataFragment)
            /*humidity += 1
            temperature += 2
            viewModel.uploadFile(humidity, temperature)*/
        }

        return view
    }
}