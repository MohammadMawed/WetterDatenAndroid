package com.mohammadmawed.wetterdaten

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anychart.APIlib
import com.anychart.AnyChartView

import com.anychart.data.Mapping

import com.anychart.chart.common.dataentry.DataEntry

import com.anychart.AnyChart
import com.anychart.chart.common.dataentry.ValueDataEntry
import com.anychart.charts.Cartesian
import com.anychart.core.cartesian.series.Line
import com.anychart.data.Set
import com.anychart.graphics.vector.Stroke
import com.anychart.enums.*

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val anyChartView: AnyChartView = findViewById(R.id.line_chart_view)
        APIlib.getInstance().setActiveAnyChartView(anyChartView)

        anyChartView.setProgressBar(findViewById(R.id.progressBar));

        val cartesian: Cartesian = AnyChart.line()


       cartesian.animation(true)

       cartesian.padding(10.0, 20.0, 5.0, 20.0)

       cartesian.crosshair().enabled(true)
       cartesian.crosshair()
           .yLabel(true) // TODO ystroke
           .yStroke(null as Stroke?, null, null, null as String?, null as String?)

       cartesian.tooltip().positionMode(TooltipPositionMode.POINT)

       cartesian.title("Weather Data in Homburg")

       cartesian.yAxis(0).title("Numbers in C")
       cartesian.xAxis(0).labels().padding(5.0, 5.0, 5.0, 5.0)

       val seriesData: MutableList<DataEntry> = ArrayList()
       seriesData.add(CustomDataEntry("00.00", 17.0, 18.9, 10.1))
       seriesData.add(CustomDataEntry("01.00", 16.6, 20.3, 11.5))
       seriesData.add(CustomDataEntry("02.00", 14.1, 20.7, 12.2))
       seriesData.add(CustomDataEntry("03.00", 15.7, 21.6, 10))
       seriesData.add(CustomDataEntry("04.00", 12.0, 22.5, 8.9))
       seriesData.add(CustomDataEntry("05.00", 18.1, 17.9, 8.9))
       seriesData.add(CustomDataEntry("6.00", 3.6, 2.3, 2.8))
       seriesData.add(CustomDataEntry("7.00", 7.1, 4.0, 4.1))
       seriesData.add(CustomDataEntry("8.00", 8.5, 6.2, 5.1))
       seriesData.add(CustomDataEntry("9.00", 9.2, 11.8, 6.5))
       seriesData.add(CustomDataEntry("10.00", 10.1, 13.0, 12.5))
       seriesData.add(CustomDataEntry("11.00", 11.6, 13.9, 18.0))
       seriesData.add(CustomDataEntry("12.00", 16.4, 18.0, 21.0))
       seriesData.add(CustomDataEntry("13.00", 18.0, 23.3, 20.3))
       seriesData.add(CustomDataEntry("14.00", 13.2, 24.7, 19.2))
       seriesData.add(CustomDataEntry("15.00", 12.0, 18.0, 14.4))
       seriesData.add(CustomDataEntry("16.00", 3.2, 15.1, 9.2))
       seriesData.add(CustomDataEntry("17.00", 4.1, 11.3, 5.9))
       seriesData.add(CustomDataEntry("18.00", 6.3, 14.2, 5.2))
       seriesData.add(CustomDataEntry("19.00", 9.4, 13.7, 4.7))
       seriesData.add(CustomDataEntry("20.00", 11.5, 9.9, 4.2))
       seriesData.add(CustomDataEntry("21.00", 13.5, 12.1, 1.2))
       seriesData.add(CustomDataEntry("22.00", 14.8, 13.5, 5.4))
       seriesData.add(CustomDataEntry("23.00", 16.6, 15.1, 6.3))


       val set: Set = Set.instantiate()
       set.data(seriesData)
       val series1Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value' }")
       val series2Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value2' }")
       val series3Mapping: Mapping = set.mapAs("{ x: 'x', value: 'value3' }")

       val series1: Line = cartesian.line(series1Mapping)
       series1.name("Today")
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
       series2.name("Yesterday")
       series2.hovered().markers().enabled(true)
       series2.hovered().markers()
           .type(MarkerType.CIRCLE)
           .size(4.0)
       series2.tooltip()
           .position("right")
           .anchor(Anchor.LEFT_CENTER)
           .offsetX(5.0)
           .offsetY(5.0)

       val series3: Line = cartesian.line(series3Mapping)
       series3.name("Tomorrow")
       series3.hovered().markers().enabled(true)
       series3.hovered().markers()
           .type(MarkerType.CIRCLE)
           .size(4.0)
       series3.tooltip()
           .position("right")
           .anchor(Anchor.LEFT_CENTER)
           .offsetX(5.0)
           .offsetY(5.0)

       cartesian.legend().enabled(true)
       cartesian.legend().fontSize(13.0)
       cartesian.legend().padding(0.0, 0.0, 10.0, 0.0)

        /*cartesian1.title("Second chart");
        APIlib.getInstance().setActiveAnyChartView(anyChartView);
        linearGauge.title("First chart");
        APIlib.getInstance().setActiveAnyChartView(thermoanyChartView);
        cartesian.title("Second chart");*/
        //APIlib.getInstance().setActiveAnyChartView(anyChartView);


        anyChartView.setChart(cartesian)

    }
    private class CustomDataEntry internal constructor(
        x: String?,
        value: Number?,
        value2: Number?,
        value3: Number?
    ) :
        ValueDataEntry(x, value) {
        init {
            setValue("value2", value2)
            setValue("value3", value3)
        }
    }
}