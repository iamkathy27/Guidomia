package com.directory.conslist.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.directory.conslist.BR
import com.directory.conslist.R
import com.directory.conslist.databinding.CarItemBinding
import com.directory.conslist.model.Car

/**
 * Adapter to handle and populate the list of car
 *
 * @author katherine.sobejano on 11/18/2021.
 */
class CarlistAdapter(private val carList: List<Car>) : RecyclerView.Adapter<CarlistAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding: CarItemBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.car_item, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val carResult = carList[position]
        displayRating(holder, carList[position].rating)

        for (i in carResult.prosList!!.indices) {
            if (carResult.prosList!![i] != "") {
                holder!!.prosList.append("""
                ${carResult.prosList!![i]}
                
                """.trimIndent())
            }
        }
        for (i in carResult.consList!!.indices) {
            if (carResult.consList!![i] != "") {
                holder!!.consList.append("""
                ${carResult.consList!![i]}
                
                """.trimIndent())
            }
        }
        holder.bind(carResult)
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
            carItemBinding.setVariable(BR.type, obj)
            carItemBinding.executePendingBindings()
        }
    }

    private fun displayRating(holder: ViewHolder, rating: Int) {
        when (rating) {
            4 -> holder.imageView5.visibility = View.GONE
            3 -> {
                holder.imageView5.visibility = View.GONE
                holder.imageView4.visibility = View.GONE
            }
        }
    }
}
