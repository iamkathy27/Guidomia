package com.directory.conslist.ui

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.directory.conslist.R
import com.directory.conslist.model.Car
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.*

/**
 * ViewModel class that stores data
 *
 * @author katherine.sobejano on 11/18/2021.
 */
class CarlistViewModel(application: Application) : AndroidViewModel(application) {
    private val carList: MutableLiveData<List<Car>> = MutableLiveData()
    fun getCarResult(context: Context): LiveData<List<Car>> {
        getResult(context)
        return carList
    }

    private fun getResult(context: Context) {
        try {
            var json: String? = null
            val `is` = context.assets.open("car_list.json")
            val size = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            json = String(buffer, StandardCharsets.UTF_8)
            val gb = GsonBuilder()
            val arrayType = object : TypeToken<List<Car?>?>() {}.type
            val data = gb.create().fromJson<List<Car>>(json, arrayType)
            val dataList: MutableList<Car> = ArrayList()
            val carImages = intArrayOf(R.drawable.ranger, R.drawable.alphine, R.drawable.bwm, R.drawable.mercedez)

            for ((count, carList) in data.withIndex()) {
                Log.e("CarList: ", carList.toString() + "")
                dataList.add(Car(data[count].make, data[count].marketPrice,
                        data[count].rating, carImages[count]))
            }

            carList.setValue(dataList)
        } catch (ex: IOException) {
            ex.printStackTrace()
        }
    }

}