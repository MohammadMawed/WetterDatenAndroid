package com.mohammadmawed.wetterdaten.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.anychart.APIlib
import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.DataEntry
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.core.cartesian.series.Line
import com.anychart.data.Mapping
import com.anychart.data.Set
import com.anychart.enums.Anchor
import com.anychart.enums.MarkerType
import com.anychart.enums.TooltipPositionMode
import com.anychart.graphics.vector.Stroke
import com.mohammadmawed.wetterdaten.MainActivity2
import com.mohammadmawed.wetterdaten.R
import com.mohammadmawed.wetterdaten.data.AverageDataModelClass
import java.lang.reflect.Array

class WeeklyDataFragment : Fragment() {

    private lateinit var viewModel: WeatherDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_weekly_data, container, false)

        requireActivity().onBackPressedDispatcher.addCallback(this) {
            findNavController().navigate(R.id.action_weeklyDataFragment_to_wholeDataFragment)
            // Handle the back button event
        }
        viewModel = ViewModelProvider(requireActivity())[WeatherDataViewModel::class.java]
        viewModel.loadHourData()

        val anyChartView: AnyChartView = view.findViewById(R.id.line_chart_view)
        APIlib.getInstance().setActiveAnyChartView(anyChartView)

        anyChartView.setProgressBar(view.findViewById(R.id.progress_bar))

        val cartesian: Cartesian = AnyChart.line()

        viewModel.hourDataLiveData.observe(viewLifecycleOwner) { array ->

            /*

            array.forEach {
                dataList.add(0, array[1])
                data = it

            }*/


            var data1: AverageDataModelClass = AverageDataModelClass()
            var data2: AverageDataModelClass = AverageDataModelClass()
            var data3: AverageDataModelClass = AverageDataModelClass()
            var data4: AverageDataModelClass = AverageDataModelClass()
            var data5: AverageDataModelClass = AverageDataModelClass()
            var data6: AverageDataModelClass = AverageDataModelClass()
            var data7: AverageDataModelClass = AverageDataModelClass()
            var data8: AverageDataModelClass = AverageDataModelClass()
            var data9: AverageDataModelClass = AverageDataModelClass()
            var data10: AverageDataModelClass = AverageDataModelClass()
            var data11: AverageDataModelClass = AverageDataModelClass()
            var data12: AverageDataModelClass = AverageDataModelClass()
            var data13: AverageDataModelClass = AverageDataModelClass()
            var data14: AverageDataModelClass = AverageDataModelClass()
            var data15: AverageDataModelClass = AverageDataModelClass()
            var data16: AverageDataModelClass = AverageDataModelClass()
            var data17: AverageDataModelClass = AverageDataModelClass()
            var data18: AverageDataModelClass = AverageDataModelClass()
            var data19: AverageDataModelClass = AverageDataModelClass()
            var data20: AverageDataModelClass = AverageDataModelClass()
            var data21: AverageDataModelClass = AverageDataModelClass()
            var data22: AverageDataModelClass = AverageDataModelClass()
            var data23: AverageDataModelClass = AverageDataModelClass()
            var data24: AverageDataModelClass = AverageDataModelClass()


            data1 = array[1]!!
            data2 = array[2]!!
            data3 = array[3]!!
            data4 = array[4]!!
            data5 = array[5]!!
            data6 = array[6]!!
            data7 = array[7]!!
            data8 = array[8]!!
            data9 = array[9]!!
            data10 = array[10]!!
            data11 = array[11]!!
            data12 = array[12]!!
            data13 = array[13]!!
            data14 = array[14]!!
            data15 = array[15]!!
            data16 = array[16]!!
            data17 = array[17]!!
            data18 = array[18]!!
            data19 = array[19]!!
            data20 = array[20]!!
            data21 = array[21]!!
            data22 = array[22]!!
            data23 = array[23]!!
            data24 = array[24]!!


            Log.d("Ss", array.values.toString())

            cartesian.animation(true)


            cartesian.crosshair().enabled(true)
            cartesian.crosshair()
                .yLabel(true) // TODO ystroke
                .yStroke(null as Stroke?, null, null, null as String?, null as String?)

            cartesian.tooltip().positionMode(TooltipPositionMode.POINT)

            cartesian.title("Weather Data in Homburg")

            cartesian.yAxis(0).title("Numbers in C")
            cartesian.xAxis(0).labels().padding(5.0, 5.0, 5.0, 5.0)

            val seriesData: MutableList<DataEntry> = ArrayList()

            var data111: AverageDataModelClass = AverageDataModelClass()
            var i: Int = 1
            for (data11 in array){
                data111 = array[i]!!
                i++
                Log.d("fff", data111.toString())
                seriesData.add(CustomDataEntry(data111.time, data111.temperature?.toFloat(), data111.humidity?.toFloat()))

            }

            /*seriesData.add(CustomDataEntry("00.00", data1.temperature?.toFloat(), data1.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("01.00", data2.temperature?.toFloat(), data2.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("03.00",  data3.temperature?.toFloat(), data3.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("04.00",  data4.temperature?.toFloat(), data4.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("04.00",  data5.temperature?.toFloat(), data5.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("05.00", data6.temperature?.toFloat(), data6.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("6.00", data7.temperature?.toFloat(), data7.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("7.00", data8.temperature?.toFloat(), data8.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("8.00", data9.temperature?.toFloat(), data9.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("9.00", data10.temperature?.toFloat(), data10.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("10.00", data11.temperature?.toFloat(), data11.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("11.00", data12.temperature?.toFloat(), data12.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("12.00", data13.temperature?.toFloat(), data13.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("13.00", data14.temperature?.toFloat(), data14.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("14.00", data15.temperature?.toFloat(), data15.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("15.00", data16.temperature?.toFloat(), data16.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("16.00", data17.temperature?.toFloat(), data17.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("17.00", data18.temperature?.toFloat(), data18.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("18.00", data19.temperature?.toFloat(), data19.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("19.00", data20.temperature?.toFloat(), data20.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("20.00", data21.temperature?.toFloat(), data21.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("21.00", data22.temperature?.toFloat(), data22.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("22.00", data23.temperature?.toFloat(), data23.humidity?.toFloat()))
            seriesData.add(CustomDataEntry("23.00", data24.temperature?.toFloat(), data24.humidity?.toFloat()))*/


            val set: Set = Set.instantiate()
            set.data(seriesData)
            val series1Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value' }")
            val series2Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value2' }")

            val series1: Line = cartesian.line(series1Mapping)
            series1.name("Temperature")
            series1.hovered().markers().enabled(true)
            series1.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4.0)
            series1.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5.0)
                .offsetY(5.0)

            val series2: Line = cartesian.line(series2Mapping)
            series2.name("Humidity")
            series2.hovered().markers().enabled(true)
            series2.hovered().markers()
                .type(MarkerType.CIRCLE)
                .size(4.0)
            series2.tooltip()
                .position("right")
                .anchor(Anchor.LEFT_CENTER)
                .offsetX(5.0)
                .offsetY(5.0)


            cartesian.legend().enabled(true)
            cartesian.legend().fontSize(13.0)

            /*cartesian1.title("Second chart");
            APIlib.getInstance().setActiveAnyChartView(anyChartView);
            linearGauge.title("First chart");
            APIlib.getInstance().setActiveAnyChartView(thermoanyChartView);
            cartesian.title("Second chart");*/
            //APIlib.getInstance().setActiveAnyChartView(anyChartView);


            anyChartView.setChart(cartesian)

        }




        return view
    }

    private class CustomDataEntry internal constructor(
        x: String?,
        value: Number?,
        value2: Number?
    ) :
        ValueDataEntry(x, value) {
        init {
            setValue("value2", value2)
        }
    }

}