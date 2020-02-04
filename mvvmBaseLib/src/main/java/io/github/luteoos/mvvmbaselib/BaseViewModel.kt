package io.github.luteoos.mvvmbaselib

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Created by Luteoos on 13.09.2018
 * uses android.arch.lifecycle.MutableLiveData and ViewModel
 */

abstract class BaseViewModel : ViewModel(){

    /**
     * Serves as quick bus between VM and owner-View
     */
    private val message : MutableLiveData<Event<Int>> = MutableLiveData()

    /**
     * use it to assign message:LiveData
     * posts value only if hs activeObservers
     */
    protected fun send(msg: Int){
        message.let {
            if(it.hasActiveObservers())
                it.postValue(Event(msg))
        }
    }

    fun message(): LiveData<Event<Int>> = message
}
