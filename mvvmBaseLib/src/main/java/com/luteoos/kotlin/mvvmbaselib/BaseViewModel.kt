package com.luteoos.kotlin.mvvmbaselib

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * Created by Luteoos on 13.09.2018
 * uses android.arch.lifecycle.MutableLiveData and ViewModel
 */

abstract class BaseViewModel : ViewModel(){

    /**
     * Serves as quick bus between VM and owner-View
     */
    private val message : MutableLiveData<Int> = MutableLiveData()

    init {
        message.value = 0
    }

    /**
     * use it to assign message:LiveData
     * do not use 0 value, its considered default empty
     */
    protected fun send(msg: Int){
        message.value = msg
        message.value = 0
    }

    fun VMMessage(): LiveData<Int> = message

}
