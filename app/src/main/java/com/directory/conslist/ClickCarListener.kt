package com.directory.conslist

import com.directory.conslist.model.Car

/**
 * Listener to expand the list
 *
 * @author katherine.sobejano on 11/19/2021.
 */
interface ClickCarListener {
    fun expandCarDetails(carResult: Car?)
}