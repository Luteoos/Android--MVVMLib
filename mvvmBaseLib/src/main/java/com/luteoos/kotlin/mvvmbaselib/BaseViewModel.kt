package com.luteoos.kotlin.mvvmbaselib

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.eightbitlab.rxbus.Bus
import io.reactivex.disposables.CompositeDisposable
import io.realm.Realm

/**
 * Created by Luteoos on 13.09.2018
 */

abstract class BaseViewModel : ViewModel(){
    val disposable : CompositeDisposable = CompositeDisposable()
    val realm = Realm.getDefaultInstance()
    private val message : MutableLiveData<String> = MutableLiveData()

    init {
        message.value = 1.toString()
    }

    /**
     * use it to assign message:LiveData
     * after sets LD to "" to avoid multiple calls
     */
    protected fun send(msg: String){
        message.value = msg
        message.value = ""
    }

    fun VMMessage(): LiveData<String> = message

    fun detachBus(){
        Bus.unregister(this)
    }
    fun detachDisposable(){
        disposable.clear()
    }

    override fun onCleared() {
        detachDisposable()
        detachBus()
        realm?.close()
        super.onCleared()
    }
}
