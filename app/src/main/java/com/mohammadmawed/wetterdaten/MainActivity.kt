package com.mohammadmawed.wetterdaten

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.anychart.APIlib

import com.anychart.AnyChart
import com.anychart.AnyChartView
import com.anychart.chart.common.dataentry.SingleValueDataSet
import com.anychart.charts.LinearGauge
import com.anychart.enums.*
import com.anychart.scales.Base
import com.anychart.scales.Linear
import com.google.android.material.floatingactionbutton.FloatingActionButton


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val thermoanyChartView: AnyChartView = findViewById(R.id.theromo_chart_view)
        val button: FloatingActionButton = findViewById(R.id.floatingActionButton)
        APIlib.getInstance().setActiveAnyChartView(thermoanyChartView)

        /*val anyChartView: AnyChartView = findViewById(R.id.line_chart_view)
        APIlib.getInstance().setActiveAnyChartView(anyChartView)*/

        button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        val linearGauge: LinearGauge = AnyChart.linear()

        // TODO data

        // TODO data

        ///Change the data from here
        linearGauge.data(SingleValueDataSet(arrayOf(8)))

        linearGauge.tooltip()
            .useHtml(true)
            .format(
                "function () {\n" +
                        "          return this.value + '&deg;' + 'C' +\n" +
                        "            ' (' + (this.value * 1.8 + 32).toFixed(1) +\n" +
                        "            '&deg;' + 'F' + ')'\n" +
                        "    }"
            )

        linearGauge.label(0).useHtml(true)
        linearGauge.label(0)
            .text("C&deg;")
            .position(Position.LEFT_BOTTOM)
            .anchor(Anchor.LEFT_BOTTOM)
            .offsetY("8px")
            .offsetX("30%")
            .fontColor("black")
            .fontSize(17)

        linearGauge.label(1)
            .useHtml(true)
            .text("F&deg;")
            .position(Position.RIGHT_BOTTOM)
            .anchor(Anchor.RIGHT_BOTTOM)
            .offsetY("8px")
            .offsetX("37.5%")
            .fontColor("black")
            .fontSize(17)

        val scale: Base = linearGauge.scale()
            .minimum(-30)
            .maximum(40)
        //.setTicks

        //.setTicks
        linearGauge.axis(0).scale(scale)
        linearGauge.axis(0)
            .offset("-1%")
            .width("0.5%")

        linearGauge.axis(0).labels()
            .format("{%Value}&deg;")
            .useHtml(true)

        linearGauge.thermometer(0)
            .name("Thermometer")
            .id(1)

        linearGauge.axis(0).minorTicks(true)
        linearGauge.axis(0).labels()
            .format(
                ("function () {\n" +
                        "    return '<span style=\"color:black;\">' + this.value + '&deg;</span>'\n" +
                        "  }")
            )
            .useHtml(true)

        linearGauge.axis(1).minorTicks(true)
        linearGauge.axis(1).labels()
            .format(
                ("function () {\n" +
                        "    return '<span style=\"color:black;\">' + this.value + '&deg;</span>'\n" +
                        "  }")
            )
            .useHtml(true)
        linearGauge.axis(1)
            .offset("3.5%")
            .orientation(Orientation.RIGHT)

        val linear: Linear = Linear.instantiate()
        linear.minimum(-20)
            .maximum(100)
        //.setTicks
        //.setTicks
        linearGauge.axis(1).scale(linear)

        thermoanyChartView.setChart(linearGauge)


        /*val cartesian: Cartesian = AnyChart.line()

        cartesian.animation(false)

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
        cartesian.legend().padding(0.0, 0.0, 10.0, 0.0)*/

        /*cartesian1.title("Second chart");
        APIlib.getInstance().setActiveAnyChartView(anyChartView);
        linearGauge.title("First chart");
        APIlib.getInstance().setActiveAnyChartView(thermoanyChartView);
        cartesian.title("Second chart");*/
        //APIlib.getInstance().setActiveAnyChartView(anyChartView);


        //anyChartView.setChart(cartesian)


    }


}