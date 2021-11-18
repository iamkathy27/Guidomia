package com.directory.conslist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.directory.conslist.R
import com.directory.conslist.databinding.ActivityMainBinding
import com.directory.conslist.model.Car

/**
 * Main activity that shows the list of car
 *
 * @author katherine.sobejano on 11/18/2021.
 */
class CarlistActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var viewModel: CarlistViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CarlistViewModel::class.java)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(mainBinding.getRoot())

        viewModel.getCarResult(this).observe(this, { carList: List<Car> ->
            val carlistAdapter = CarlistAdapter(carList)
            mainBinding.listRecyclerView.layoutManager = LinearLayoutManager(application)
            mainBinding.setMyAdapter(carlistAdapter)
        })
    }
}
