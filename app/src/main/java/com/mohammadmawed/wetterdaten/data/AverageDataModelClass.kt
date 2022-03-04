package com.mohammadmawed.wetterdaten.data

import android.os.Parcel


data class AverageDataModelClass(
    var time: String? = null,
    var date: String? = null,
    var humidity: String? = null,
    var temperature: String? = null
){

}
