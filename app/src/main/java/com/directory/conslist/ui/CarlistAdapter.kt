package com.directory.conslist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.directory.conslist.BR
import com.directory.conslist.ClickCarListener
import com.directory.conslist.R
import com.directory.conslist.databinding.CarItemBinding
import com.directory.conslist.model.Car

/**
 * Adapter to handle and populate the list of car
 *
 * @author katherine.sobejano on 11/18/2021.
 */
class CarlistAdapter(private val carList: List<Car>) : RecyclerView.Adapter<CarlistAdapter.ViewHolder>(), ClickCarListener {

    lateinit var carItemBinding: CarItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        carItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.car_item, parent, false)
        return ViewHolder(carItemBinding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val carResult = carList[position]
        displayRating(carList[position].rating)

        if (position == 0) {
            carItemBinding!!.details2nd.visibility = View.VISIBLE
        }

        for (i in carResult.prosList!!.indices) {
            if (carResult.prosList!![i] != "") {
                carItemBinding!!.prosList.append("""
                ${carResult.prosList!![i]}
                
                """.trimIndent())
            }
        }
        for (i in carResult.consList!!.indices) {
            if (carResult.consList!![i] != "") {
                carItemBinding!!.consList.append("""
                ${carResult.consList!![i]}
                
                """.trimIndent())
            }
        }

        holder.bind(carResult)
        carItemBinding.onClickExpandListener = this
    }

    override fun getItemCount(): Int {
        return carList.size
    }

    inner class ViewHolder(carItemBinding: CarItemBinding) : RecyclerView.ViewHolder(carItemBinding.root) {
        var carItemBinding: CarItemBinding = carItemBinding
        var imageView4: ImageView = carItemBinding.rate4
        var imageView5: ImageView = carItemBinding.rate5
        var prosList: TextView = carItemBinding.prosList
        var consList: TextView = carItemBinding.consList

        fun bind(obj: Any?) {
            carItemBinding.setVariable(BR.carInfo, obj)
            carItemBinding.executePendingBindings()
        }
    }

    /**
     * Method to display the rate
     */
    private fun displayRating(rating: Int) {
        when (rating) {
            4 -> carItemBinding!!.rate5.visibility = View.GONE
            3 -> {
                carItemBinding!!.rate5.visibility = View.GONE
                carItemBinding!!.rate4.visibility = View.GONE
            }
        }
    }

    /**
     * Method to show and hide the second section of every Car's details
     */
    override fun expandCarDetails(carResult: Car?) {
        if (carItemBinding!!.details2nd.visibility == View.VISIBLE) carItemBinding!!.details2nd.visibility = View.GONE else carItemBinding!!.details2nd.visibility = View.VISIBLE
    }
}
