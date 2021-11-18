package com.directory.conslist.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.directory.conslist.Utils
import com.directory.conslist.Utils.formatPrice
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Class to data bind the model
 *
 * @author katherine.sobejano on 11/18/2021.
 */
class Car(

    @field:Expose @field:SerializedName("make") var make: String,
    @field:Expose @field:SerializedName("marketPrice") var marketPrice: Double,
    @field:Expose @field:SerializedName("rating") var rating: Int, var image: Int, prosList: List<String>?, consList: List<String>?) {

    @SerializedName("consList") @Expose var consList: List<String>? = null

    @SerializedName("customerPrice") @Expose var customerPrice: Double? = null

    @SerializedName("model") @Expose var model: String? = null

    @SerializedName("prosList") @Expose var prosList: List<String>? = null

    val price: String
        get() = Utils.formatPrice(marketPrice)

    companion object {
        @JvmStatic
        @BindingAdapter("imageUrl")
        fun loadImage(view: ImageView, image: Int) {
            view.setImageResource(image)
        }
    }

    init {
        this.prosList = prosList
        this.consList = consList
    }
}