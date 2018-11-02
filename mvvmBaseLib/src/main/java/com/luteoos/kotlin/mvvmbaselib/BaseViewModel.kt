package com.luteoos.kotlin.mvvmbaselib

import android.arch.lifecycle.ViewModel
import com.eightbitlab.rxbus.Bus
import io.reactivex.disposables.CompositeDisposable
import io.realm.Realm

/**
 * Created by Luteoos on 13.09.2018
 */

abstract class BaseViewModel() : ViewModel(){
    val disposable : CompositeDisposable = CompositeDisposable()
    val realm: Realm = Realm.getDefaultInstance()

    fun detachBus(){
        Bus.unregister(this)
    }
    fun detachDisposable(){
        disposable.clear()
    }

    override fun onCleared() {
        detachDisposable()
        detachBus()
        realm.close()
        super.onCleared()
    }
}