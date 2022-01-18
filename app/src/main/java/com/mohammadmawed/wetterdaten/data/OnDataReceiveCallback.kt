package com.mohammadmawed.wetterdaten.data

interface OnDataReceiveCallback {
    fun onDataReceived(time: String?, temperature: String?, humidity: String?)
}